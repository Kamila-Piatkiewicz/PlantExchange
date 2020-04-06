package com.plantexchange.plantexchange.controller;

import com.plantexchange.plantexchange.authentication.UserRegistrationForm;
import com.plantexchange.plantexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ApplicationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        UserRegistrationForm form = new UserRegistrationForm();
        model.addAttribute("userRegistrationForm", form);
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUserAccount
            (@ModelAttribute("userRegistrationForm") @Valid UserRegistrationForm userRegistrationForm,
             HttpServletRequest request, Errors errors) {

        try {
            userService.registerNewUser(
                    userRegistrationForm.getEmail(),
                    userRegistrationForm.getPassword());
        } catch (Exception e) {

        }
        return "register";
    }

}

