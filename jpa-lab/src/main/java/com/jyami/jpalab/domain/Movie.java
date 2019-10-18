package com.jyami.jpalab.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Movie")
public class Movie extends Item {
    private String actor;
    private String director;

    @Builder
    public Movie(String name, int price, String actor, String director) {
        super(name, price);
        this.actor = actor;
        this.director = director;
    }
}
