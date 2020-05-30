package com.plantexchange.plantexchange.repository;

import com.plantexchange.plantexchange.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
