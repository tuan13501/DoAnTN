package com.tuandh.travelwala.authentication.sercurity.service.loginToken;

import com.tuandh.travelwala.authentication.sercurity.entity.AppUser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface LoginTokenService {
    LoginToken refreshToken(HttpServletRequest request) throws IOException;

    LoginToken createToken(AppUser user);
}
