package com.jyami.springsecuritypolling.domain.user;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created by jyami on 2020/07/30
 */
@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
}
