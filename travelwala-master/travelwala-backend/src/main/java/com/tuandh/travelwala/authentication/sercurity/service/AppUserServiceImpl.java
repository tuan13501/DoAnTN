package com.tuandh.travelwala.authentication.sercurity.service;

import com.tuandh.travelwala.authentication.sercurity.dto.AppUserRegisterRequest;
import com.tuandh.travelwala.authentication.sercurity.dto.AppUserRegisterResponse;
import com.tuandh.travelwala.authentication.sercurity.dto.oauth2.OAuth2UserInfo;
import com.tuandh.travelwala.authentication.sercurity.entity.AppUser;
import com.tuandh.travelwala.authentication.sercurity.entity.AppUserRole;
import com.tuandh.travelwala.authentication.sercurity.entity.ConfirmationToken;
import com.tuandh.travelwala.authentication.sercurity.entity.Provider;
import com.tuandh.travelwala.authentication.sercurity.exception.UserAlreadyExistException;
import com.tuandh.travelwala.authentication.sercurity.repo.AppUserRepo;
import com.tuandh.travelwala.authentication.sercurity.service.confirmation_token.ConfirmationServiceInterface;
import com.tuandh.travelwala.authentication.sercurity.service.email.EmailServiceInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final static String USER_NOT_FOUND_MSG = "User %s not found";
    private final static String EMAIL_EXIST_MSG = "User already exists for email %s";
    private final static String USERNAME_EXIST_MSG = "User already exists for username %s";
    private final static String TELEPHONE_EXIST_MSG = "User already exists for telephone %s";
    private PasswordEncoder passwordEncoder;
    private final ConfirmationServiceInterface confirmationService;

    private final EmailServiceInterface emailService;

    Environment env;

    @Override
    public AppUserRegisterResponse register(AppUserRegisterRequest user) throws UserAlreadyExistException {
        //Let's check if user already registered with us
        Optional<AppUser> checkUser = appUserRepo.findByEmailOrUsernameOrTelephone(user.getEmail(),user.getUsername(),user.getTelephone());

        if (checkUser.isPresent()&& !checkUser.get().isEnabled()) {
            // create token
            ConfirmationToken confirmationToken = confirmationService.createConformationToken(checkUser.get());
            this.sendConfirmToken(confirmationToken.getToken(), confirmationToken.getAppUser().getEmail());
            return new AppUserRegisterResponse();
            // resend email
        }

        if(checkIfUserExist(user.getUsername())){
            throw new UserAlreadyExistException(String.format(USERNAME_EXIST_MSG,user.getUsername()));
        }
        if(checkIfEmailExist(user.getEmail())){
            throw new UserAlreadyExistException(String.format(EMAIL_EXIST_MSG,user.getEmail()));
        }
        if(checkIfTelephoneExist(user.getTelephone())){
            throw new UserAlreadyExistException(String.format(TELEPHONE_EXIST_MSG,user.getTelephone()));
        }
        AppUser userEntity = new AppUser();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user.getPassword());
        userEntity.setAppUserRole(AppUserRole.USER);
        userEntity.setProvider(Provider.local);
        appUserRepo.save(userEntity);
        AppUserRegisterResponse appUserRegisterResponse = new AppUserRegisterResponse();
        BeanUtils.copyProperties(userEntity, appUserRegisterResponse);
        // generate token and save it
        // ConfirmationToken confirmationToken = confirmationService.createConformationToken(userEntity);
        // this.sendConfirmToken(confirmationToken.getToken(),confirmationToken.getAppUser().getEmail());

        return appUserRegisterResponse;
    }

    @Override
    public void confirmToken(String token) throws IllegalStateException{
        ConfirmationToken confirmationToken = confirmationService
                .findConfirmationToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt()!=null) {
            throw new IllegalStateException("Already confirmed");
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token was expired");
        }
        log.info(confirmationToken.getAppUser().getUsername());
        confirmationService.updateConfirmedAt(confirmationToken);

        this.enableAppUser(confirmationToken.getAppUser().getEmail());
    }

    @Override
    public void sendConfirmToken(String token, String to) {
        String link = "http://"+env.getProperty("host")+"/api/register/confirm?token="+token;

        try {
            emailService.sendHtmlEmail(link,to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void enableAppUser(String accountName) {
        AppUser appUser = appUserRepo.findByEmailOrUsernameOrTelephone(accountName,accountName,accountName).get();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return appUserRepo.existsByEmail(email);
    }

    @Override
    public AppUser findByEmail(String email) {
        return appUserRepo.findByEmail(email);
    }

    public boolean checkIfUserExist(String username) {
        return appUserRepo.findByUsername(username) != null;
    }

    public boolean checkIfEmailExist(String email) {
        return appUserRepo.findByEmail(email) != null;
    }

    public boolean checkIfTelephoneExist(String telephone) {
        return appUserRepo.findByTelephone(telephone) != null;
    }

    private void encodePassword( AppUser userEntity, String plainPassword){
        userEntity.setPassword(passwordEncoder.encode(plainPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // login by username/ email/ phone
        return appUserRepo.findByEmailOrUsernameOrTelephone(username,username,username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG,username)));
    }

    @Override
    public void createOauth2User(OAuth2UserInfo user) {
        AppUser userEntity = new AppUser();
        userEntity.setProvider(user.getProvider());
        userEntity.setAppUserRole(AppUserRole.USER);
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(null);
        userEntity.setUsername(null);
        userEntity.setEnabled(true);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setAvatar(user.getImageUrl());
        appUserRepo.save(userEntity);
    }
}
