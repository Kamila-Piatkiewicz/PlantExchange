package com.plantexchange.plantexchange;

import com.plantexchange.plantexchange.model.DealCategory;
import com.plantexchange.plantexchange.model.DealTag;
import com.plantexchange.plantexchange.model.PlantDeal;
import com.plantexchange.plantexchange.model.User;
import com.plantexchange.plantexchange.repository.PlantDealRepository;
import com.plantexchange.plantexchange.repository.TagRepository;
import com.plantexchange.plantexchange.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Collections;

import static java.util.Arrays.asList;

@SpringBootApplication
public class PlantExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantExchangeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            TagRepository tagRepo = ctx.getBean(TagRepository.class);
            DealTag tag = tagRepo.save(DealTag.builder()
                    .name("mały rozmiar")
                    .build());

            UserRepository userRepo = ctx.getBean(UserRepository.class);
            User user = userRepo.save(User.builder()
                    .email("test@test.pl")
                    .password("password")
                    .build());

            PlantDealRepository plantRepo = ctx.getBean(PlantDealRepository.class);
            plantRepo.save(PlantDeal.builder()
                    .author(user)
                    .category(DealCategory.SELL)
                    .publishedDate(LocalDateTime.now())
                    .title("Sprzedam xxxxx")
                    .tags(Collections.singletonList(tag))
                    .build());

        };
    }

}
