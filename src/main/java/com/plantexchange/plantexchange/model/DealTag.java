package com.plantexchange.plantexchange.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class DealTag {

    @Id
    @Column(name = "name", length = 64, nullable = false)
    String name;

    public DealTag(String name) {
        this.name = name;
    }
}
