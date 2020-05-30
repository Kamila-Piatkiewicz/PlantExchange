package com.plantexchange.plantexchange.controller;

import com.plantexchange.plantexchange.authentication.UserRegistrationForm;
import com.plantexchange.plantexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ApplicationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> registerUserAccount
            (@RequestBody @Valid UserRegistrationForm userRegistrationForm, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            userService.registerNewUser(
                    userRegistrationForm.getEmail(),
                    userRegistrationForm.getPassword());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("User with this email already exists.", HttpStatus.BAD_REQUEST);
        }
    }

}

