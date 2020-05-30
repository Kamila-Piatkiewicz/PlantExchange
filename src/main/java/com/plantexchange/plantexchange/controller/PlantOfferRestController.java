package com.plantexchange.plantexchange.controller;

import com.plantexchange.plantexchange.model.OfferCategory;
import com.plantexchange.plantexchange.model.PlantOffer;
import com.plantexchange.plantexchange.model.Tag;
import com.plantexchange.plantexchange.repository.PlantOfferRepository;
import com.plantexchange.plantexchange.repository.TagRepository;
import com.plantexchange.plantexchange.repository.UserRepository;
import com.plantexchange.plantexchange.service.PlantOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/plant-offers")
public class PlantOfferRestController {

    @Autowired
    PlantOfferRepository plantOfferRepository;
    @Autowired
    PlantOfferService plantOfferService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public PlantOffer getPlantOffer(@PathVariable int id) {
        return plantOfferRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<PlantOffer> getPlantOffers(
            @RequestParam(required = false) OfferCategory category,
            @RequestParam(required = false) List<Tag> tags,
            @RequestParam(required = false) String city) {
        return plantOfferService.findByQuery(category, tags, city);
    }

    @PostMapping(value = "",
            produces = "application/json",
            consumes = "application/json")
    public PlantOffer addPlantOffer(@RequestBody PlantOffer offer, Principal principal, HttpServletResponse response) {
        offer.setAuthor(userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new IllegalStateException("Could not find logged user in the database")));
        offer.setPublishedDate(LocalDateTime.now());
        if (tagRepository.findAllById(offer.getTags().stream().map(Tag::getName).collect(toList())).size() < offer.getTags().size()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return plantOfferRepository.save(offer);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public PlantOffer updatePlantOffer(@PathVariable int id, @RequestBody PlantOffer offer,
                                       Principal principal, HttpServletResponse response) {
        PlantOffer persistedOffer = plantOfferRepository.findById(id).orElse(null);
        if (persistedOffer == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        if (!persistedOffer.getAuthor().getEmail().equals(principal.getName())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        if (tagRepository.findAllById(offer.getTags().stream().map(Tag::getName).collect(toList())).size() < offer.getTags().size()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        persistedOffer.setCategory(offer.getCategory());
        persistedOffer.setTitle(offer.getTitle());
        persistedOffer.setDescription(offer.getDescription());
        persistedOffer.setCity(offer.getCity());
        persistedOffer.setTags(offer.getTags());
        persistedOffer.setPhotos(offer.getPhotos());
        persistedOffer.setPrice(offer.getPrice());
        return plantOfferRepository.save(persistedOffer);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deletePlantOffer(@PathVariable int id, Principal principal, HttpServletResponse response) {
        PlantOffer plantOffer = plantOfferRepository.findById(id).orElse(null);
        if (plantOffer == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (!plantOffer.getAuthor().getEmail().equals(principal.getName())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        plantOfferRepository.deleteById(id);

    }
}
