package com.ict.group06.travelwala.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {
    @NotBlank(message = "Can't leave first name blank")
    private String firstName;

    @NotBlank(message = "Can't leave last name blank")
    private String lastName;

    @NotBlank(message = "Can't leave phone number blank")
    @Size(min = 7, message = "Invalid phone number")
    private String phoneNumber;

    @Email(message = "Invalid email")
    private String email;
}
