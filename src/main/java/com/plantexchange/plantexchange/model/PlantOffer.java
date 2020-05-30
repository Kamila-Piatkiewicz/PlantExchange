package com.plantexchange.plantexchange.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class PlantOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(name = "published_date", length = 64, nullable = false)
    private LocalDateTime publishedDate;

    @NotNull(message = "Category of the deal must be provided")
    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 64, nullable = false)
    private OfferCategory category;

    @NotEmpty(message = "Title must be provided")
    @Column(name = "title", length = 120, nullable = false)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", length = 8)
    private Double price;

    @OneToMany(targetEntity = Photo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id")
    private List<Photo> photos;

    @ManyToMany(targetEntity = Tag.class)
    @JoinColumn(name = "tags")
    private List<Tag> tags;

    @Column(name = "city", length = 64)
    String city;

}
