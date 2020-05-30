package com.plantexchange.plantexchange.controller;

import com.plantexchange.plantexchange.model.PlantOffer;
import com.plantexchange.plantexchange.model.User;
import com.plantexchange.plantexchange.repository.PlantOfferRepository;
import com.plantexchange.plantexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    PlantOfferRepository plantOfferRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "", produces = "application/json")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{email}", produces = "application/json")
    public User getUser(@PathVariable String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @GetMapping(value = "/{email}/plant-offers", produces = "application/json")
    public List<PlantOffer> getUserOffers(@PathVariable String email) {
        return plantOfferRepository.findAllByAuthor(email);
    }

}
