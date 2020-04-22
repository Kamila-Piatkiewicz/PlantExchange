package com.plantexchange.plantexchange.controller;

import com.plantexchange.plantexchange.model.PlantDeal;
import com.plantexchange.plantexchange.repository.PlantDealRepository;
import com.plantexchange.plantexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/plant-deals")
public class PlantDealRestController {

    @Autowired
    PlantDealRepository dealRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public PlantDeal getPlantDeal(@PathVariable int id) {
        return dealRepository.findById(id).get();
    }

    @GetMapping(value = "", produces = "application/json")
    public List<PlantDeal> getAllPlantDeals() {
        return dealRepository.findAll();
    }

    @PostMapping(value = "",
            produces = "application/json",
            consumes = "application/json")
    public PlantDeal addPlantDeal(@RequestBody PlantDeal deal, Principal principal) {
        deal.setAuthor(userRepository.findByEmail(principal.getName()));
        deal.setPublishedDate(LocalDateTime.now());
        return dealRepository.save(deal);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public PlantDeal updatePlantDeal(@PathVariable PlantDeal deal) {
        return dealRepository.save(deal);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deletePlantDeal(@PathVariable int id) {
        dealRepository.deleteById(id);
    }
}
