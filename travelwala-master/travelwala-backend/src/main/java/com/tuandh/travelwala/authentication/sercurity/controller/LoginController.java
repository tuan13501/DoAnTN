package com.tuandh.travelwala.authentication.sercurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuandh.travelwala.authentication.sercurity.service.AppUserService;
import com.tuandh.travelwala.authentication.sercurity.service.loginToken.LoginToken;
import com.tuandh.travelwala.authentication.sercurity.service.loginToken.LoginTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    LoginTokenService loginTokenService;

    AuthenticationManager authenticationManager;

    AppUserService userService;

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            LoginToken loginToken = loginTokenService.refreshToken(request);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            new ObjectMapper().writeValue(response.getOutputStream(), loginToken);
        } catch (Exception e) {
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
        }
    }
}

