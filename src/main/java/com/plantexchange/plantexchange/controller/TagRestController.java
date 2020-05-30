package com.plantexchange.plantexchange.controller;

import com.plantexchange.plantexchange.model.Tag;
import com.plantexchange.plantexchange.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagRestController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping(value = "/{name}", produces = "application/json")
    public Tag getTag(@PathVariable String name) {
        return tagRepository.findById(name).orElse(null);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @PostMapping(value = "",
            produces = "application/json",
            consumes = "application/json")
    public Tag addTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

}
