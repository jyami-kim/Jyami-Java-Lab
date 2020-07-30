package com.jyami.springsecuritypolling.user;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created by jyami on 2020/07/30
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
}
