package com.plantexchange.plantexchange.repository;

import com.plantexchange.plantexchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
