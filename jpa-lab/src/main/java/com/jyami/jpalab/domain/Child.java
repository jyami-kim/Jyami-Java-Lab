package com.jyami.jpalab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @Setter
    private Parent parent;

    @Builder
    public Child(String name, Parent parent) {
        this.name = name;
        this.parent = parent;
        parent.getChildList().add(this);
    }
}
