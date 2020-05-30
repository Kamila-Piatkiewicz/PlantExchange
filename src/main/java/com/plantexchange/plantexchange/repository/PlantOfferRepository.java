package com.plantexchange.plantexchange.repository;

import com.plantexchange.plantexchange.model.PlantOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantOfferRepository extends JpaRepository<PlantOffer, Integer> {

    @Query("FROM PlantOffer d where d.author.email = :authorEmail")
    List<PlantOffer> findAllByAuthor(String authorEmail);

}
