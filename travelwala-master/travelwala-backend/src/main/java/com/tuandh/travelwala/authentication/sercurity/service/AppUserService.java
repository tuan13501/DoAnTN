package com.tuandh.travelwala.authentication.sercurity.service;

import com.tuandh.travelwala.authentication.sercurity.dto.AppUserRegisterRequest;
import com.tuandh.travelwala.authentication.sercurity.dto.AppUserRegisterResponse;
import com.tuandh.travelwala.authentication.sercurity.dto.oauth2.OAuth2UserInfo;
import com.tuandh.travelwala.authentication.sercurity.entity.AppUser;
import com.tuandh.travelwala.authentication.sercurity.exception.UserAlreadyExistException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserService {
    AppUserRegisterResponse register(AppUserRegisterRequest user) throws UserAlreadyExistException;
    void confirmToken(String token) throws IllegalStateException;

    void sendConfirmToken(String token, String to);

    void enableAppUser(String accountName);

    Boolean existsByEmail(String email);

    AppUser findByEmail(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void createOauth2User(OAuth2UserInfo user);
}
