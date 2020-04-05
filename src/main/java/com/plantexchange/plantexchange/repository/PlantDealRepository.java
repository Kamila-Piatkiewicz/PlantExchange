package com.plantexchange.plantexchange.repository;

import com.plantexchange.plantexchange.model.PlantDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantDealRepository extends JpaRepository<PlantDeal, Integer> {

}
