package com.plantexchange.plantexchange.repository;

import com.plantexchange.plantexchange.model.DealTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<DealTag, String> {
}
