package com.chocochip.amaji.review.domain;

import com.chocochip.amaji.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_url")
    private String url;

    //=========  다대일   =========//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    public Review(Long id, String url, Restaurant restaurant) {
        this.id = id;
        this.url = url;
        this.restaurant = restaurant;
    }
}
