package com.tuandh.travelwala.authentication.sercurity.controller;

import com.tuandh.travelwala.authentication.sercurity.dto.AppUserRegisterRequest;
import com.tuandh.travelwala.authentication.sercurity.dto.AppUserRegisterResponse;
import com.tuandh.travelwala.authentication.sercurity.exception.UserAlreadyExistException;
import com.tuandh.travelwala.authentication.sercurity.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(value = "/api/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<?> userRegistration(final @Valid @RequestBody AppUserRegisterRequest userData){
        try {
            AppUserRegisterResponse appUserRegisterResponse = appUserService.register(userData);
            return ResponseEntity.status(HttpStatus.CREATED).body(appUserRegisterResponse);
        }catch (UserAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token){
        try {
            appUserService.confirmToken(token);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Confirmed");
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }


}
