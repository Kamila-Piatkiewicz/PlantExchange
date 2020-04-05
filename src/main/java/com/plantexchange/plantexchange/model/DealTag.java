package com.plantexchange.plantexchange.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@Getter
@Entity
@Table(name = "tag")
public class DealTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name", length = 64, nullable = false)
    String name;

}
