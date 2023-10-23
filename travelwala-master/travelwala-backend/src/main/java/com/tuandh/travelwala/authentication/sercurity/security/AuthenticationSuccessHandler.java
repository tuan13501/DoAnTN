package com.tuandh.travelwala.authentication.sercurity.security;

import com.tuandh.travelwala.authentication.sercurity.dto.oauth2.OAuth2UserInfoFactory;
import com.tuandh.travelwala.authentication.sercurity.exception.OAuth2AuthenticationProcessingException;
import com.tuandh.travelwala.authentication.sercurity.service.AppUserService;
import com.tuandh.travelwala.authentication.sercurity.service.loginToken.LoginToken;
import com.tuandh.travelwala.authentication.sercurity.service.loginToken.LoginTokenService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    private OAuth2AuthorizedClientService authorizedClientService;
    AppUserService userService;
    LoginTokenService loginTokenService;
    private final Environment env;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response2, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authenticationToken.getAuthorizedClientRegistrationId(),
                        authenticationToken.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                    .getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate
                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map userAttributes = response.getBody();
            String email = (String) userAttributes.get("email");
            if (userService.existsByEmail(email)){
                processRedirect(response2,email,userService.findByEmail(email).getFirstName());
            } else {
                // create new user
                try {
                    userService.createOauth2User(OAuth2UserInfoFactory.getOAuth2UserInfo(client.getClientRegistration().getClientName(),userAttributes));
                } catch (OAuth2AuthenticationProcessingException e) {
                    throw new RuntimeException(e);
                }
                processRedirect(response2,email, userService.findByEmail(email).getFirstName());

            }
        }

        response2.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    private void processRedirect(HttpServletResponse response2, String email, String username) throws IOException {
        response2.setStatus(HttpServletResponse.SC_FOUND);
        UriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory();
        LoginToken loginToken = loginTokenService.createToken(userService.findByEmail(email));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refreshToken",loginToken.getRefresh_token());
        params.add("accessToken",loginToken.getAccess_token());
        params.add("username",username);

        params.add("type",loginToken.getType());
        UriBuilder uriBuilder = uriBuilderFactory.builder()
                .scheme("https")
                .host(env.getProperty("redirect-host")).path("/google-oauth2").path("/success")
                .queryParams(params);
        response2.sendRedirect(uriBuilder.build().toString());
    }
}
