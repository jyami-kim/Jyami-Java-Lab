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
@DiscriminatorValue("Album")
public class Album extends Item{
    private String artist;

    @Builder
    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
