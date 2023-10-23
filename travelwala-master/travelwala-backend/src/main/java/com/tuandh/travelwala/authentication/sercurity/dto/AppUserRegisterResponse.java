package com.tuandh.travelwala.authentication.sercurity.dto;

import com.tuandh.travelwala.authentication.sercurity.entity.AppUserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AppUserRegisterResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String username;
    private AppUserRole appUserRole;
}
