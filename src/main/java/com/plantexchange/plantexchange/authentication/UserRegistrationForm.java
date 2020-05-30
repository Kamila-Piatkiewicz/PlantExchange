package com.plantexchange.plantexchange.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationForm {

    @NotNull(message = "Email must be provided")
    @Email(message = "Email must be valid")
    @Size(min = 4, max = 64, message = "Email must contain from 4 to 64 characters")
    private String email;

    @NotNull(message = "Password must be provided")
    @Size(min = 6, max = 32, message = "Password must contain from 6 to 32 characters")
    private String password;

}