package com.jyami.jpalab.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder
    private Member(String username, Team team) {
        this.username = username;
        this.team = team;
    }
}
