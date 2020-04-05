package com.plantexchange.plantexchange.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "email", length = 64, nullable = false)
    String email;

    @Column(name = "password", length = 64, nullable = false)
    String password;
}
