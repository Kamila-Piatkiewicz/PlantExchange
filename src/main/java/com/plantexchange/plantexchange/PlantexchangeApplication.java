package com.plantexchange.plantexchange;

import com.plantexchange.plantexchange.model.DealCategory;
import com.plantexchange.plantexchange.model.DealTag;
import com.plantexchange.plantexchange.model.PlantDeal;
import com.plantexchange.plantexchange.model.User;
import com.plantexchange.plantexchange.repository.PlantDealRepository;
import com.plantexchange.plantexchange.repository.TagRepository;
import com.plantexchange.plantexchange.repository.UserRepository;
import com.plantexchange.plantexchange.service.UserService;
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
            tagRepo.saveAll(asList(
                    new DealTag("Rozmiar S"),
                    new DealTag("Rozmiar M"),
                    new DealTag("Rozmiar L"),
                    new DealTag("Szczepka"),
                    new DealTag("Monstera"),
                    new DealTag("Skrzydłokwiat"),
                    new DealTag("Bluszcz"),
                    new DealTag("Kolekcjonerski"),
                    new DealTag("Kwitnący")));

            UserService userService = ctx.getBean(UserService.class);
            User user1 = userService.registerNewUser("test@test.pl","123");
            User user2 = userService.registerNewUser("test2@test.pl","123");

            PlantDealRepository plantRepo = ctx.getBean(PlantDealRepository.class);
            plantRepo.save(PlantDeal.builder()
                    .author(user1)
                    .category(DealCategory.SELL)
                    .publishedDate(LocalDateTime.now())
                    .title("Sprzedam bluszcz")
                    .tags(tagRepo.findAllById(asList("Rozmiar M", "Bluszcz")))
                    .description("Mam do sprzedania bluszcz odmiana Gold Heart, najdłuższe pędy mają 40cm.")
                    .city("Lublin")
                    .price(25.)
                    .build());

            plantRepo.save(PlantDeal.builder()
                    .author(user1)
                    .category(DealCategory.GIVE_AWAY)
                    .publishedDate(LocalDateTime.now())
                    .title("Oddam monsterę")
                    .tags(tagRepo.findAllById(asList("Rozmiar L", "Monstera")))
                    .description("Oddam monsterę do odratowania, ma uszkodzonych kilka liści, była przelana.")
                    .city("Warszawa")
                    .build());

            plantRepo.save(PlantDeal.builder()
                    .author(user2)
                    .category(DealCategory.EXCHANGE)
                    .publishedDate(LocalDateTime.now())
                    .title("Wymienię skrzydłokwiat na inną cieniolubną")
                    .tags(tagRepo.findAllById(asList("Rozmiar S", "Skrzydłokwiat", "Kwitnący")))
                    .description("Świeżo ukorzeniony skrzydłokwiat, wymienię na inną roślinę która da radę w cieniu.")
                    .city("Lublin")
                    .build());

        };
    }

}
