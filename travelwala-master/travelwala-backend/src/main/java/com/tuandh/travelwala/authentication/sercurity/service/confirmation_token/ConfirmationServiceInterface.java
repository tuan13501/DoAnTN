package com.tuandh.travelwala.authentication.sercurity.service.confirmation_token;


import com.tuandh.travelwala.authentication.sercurity.entity.AppUser;
import com.tuandh.travelwala.authentication.sercurity.entity.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationServiceInterface {
    ConfirmationToken createConformationToken(AppUser userEntity);

    void updateConfirmedAt(ConfirmationToken token);

    Optional<ConfirmationToken> findConfirmationToken(String token);
}
