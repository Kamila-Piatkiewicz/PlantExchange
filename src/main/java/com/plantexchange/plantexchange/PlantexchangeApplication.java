package com.plantexchange.plantexchange;

import com.plantexchange.plantexchange.model.OfferCategory;
import com.plantexchange.plantexchange.model.PlantOffer;
import com.plantexchange.plantexchange.model.Tag;
import com.plantexchange.plantexchange.model.User;
import com.plantexchange.plantexchange.repository.PlantOfferRepository;
import com.plantexchange.plantexchange.repository.TagRepository;
import com.plantexchange.plantexchange.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

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
                    new Tag("Rozmiar S"),
                    new Tag("Rozmiar M"),
                    new Tag("Rozmiar L"),
                    new Tag("Szczepka"),
                    new Tag("Monstera"),
                    new Tag("Skrzydłokwiat"),
                    new Tag("Bluszcz"),
                    new Tag("Kolekcjonerski"),
                    new Tag("Kwitnący")));

            UserService userService = ctx.getBean(UserService.class);
            User user1 = userService.registerNewUser("test@test.pl", "123456");
            User user2 = userService.registerNewUser("test2@test.pl", "123456");

            PlantOfferRepository plantRepo = ctx.getBean(PlantOfferRepository.class);
            plantRepo.save(PlantOffer.builder()
                    .author(user1)
                    .category(OfferCategory.SELL)
                    .publishedDate(LocalDateTime.now())
                    .title("Sprzedam bluszcz")
                    .tags(asList(new Tag("Rozmiar M"), new Tag("Bluszcz")))
                    .description("Mam do sprzedania bluszcz odmiana Gold Heart, najdłuższe pędy mają 40cm.")
                    .city("Lublin")
                    .price(25.)
                    .build());

            plantRepo.save(PlantOffer.builder()
                    .author(user1)
                    .category(OfferCategory.GIVE_AWAY)
                    .publishedDate(LocalDateTime.now())
                    .title("Oddam monsterę")
                    .tags((asList(new Tag("Rozmiar L"), new Tag("Monstera"))))
                    .description("Oddam monsterę do odratowania, ma uszkodzonych kilka liści, była przelana.")
                    .city("Warszawa")
                    .build());

            plantRepo.save(PlantOffer.builder()
                    .author(user2)
                    .category(OfferCategory.EXCHANGE)
                    .publishedDate(LocalDateTime.now())
                    .title("Wymienię skrzydłokwiat na inną cieniolubną")
                    .tags(asList(new Tag("Rozmiar S"), new Tag("Skrzydłokwiat"), new Tag("Kwitnący")))
                    .description("Wymienię skrzydłokwiat na inną roślinę która da radę w cieniu.")
                    .city("Lublin")
                    .build());

        };
    }

}
