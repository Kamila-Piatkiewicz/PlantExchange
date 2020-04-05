package com.plantexchange.plantexchange.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "deal")
public class PlantDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne(targetEntity = User.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    User author;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 64, nullable = false)
    DealCategory category;

    @Column(name = "published_date", length = 64, nullable = false)
    LocalDateTime publishedDate;

    @Column(name = "title", length = 120, nullable = false)
    String title;

    @Column(name = "description", length = 1000)
    String description;

    @Column(name = "price", length = 8)
    Double price;

    @ManyToOne(targetEntity = Photo.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_urls")
    List<Photo> photos;

    @ManyToMany(targetEntity = DealTag.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "tags")
    List<DealTag> tags;

    @Column(name = "city", length = 64)
    String city;

}
