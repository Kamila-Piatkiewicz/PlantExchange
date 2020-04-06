package com.plantexchange.plantexchange.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    public User() { }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
