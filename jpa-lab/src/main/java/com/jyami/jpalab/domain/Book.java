package com.jyami.jpalab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("Book")
public class Book extends Item {
    private String author;
    private String isbn;
}
