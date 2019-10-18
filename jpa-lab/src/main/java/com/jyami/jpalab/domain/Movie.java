package com.jyami.jpalab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("Movie")
public class Movie extends Item {
    private String actor;
    private String director;
}
