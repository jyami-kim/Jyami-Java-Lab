package com.jyami.springsecuritypolling.domain.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by jyami on 2020/07/31
 */
@Entity
@Getter
@NoArgsConstructor
public class Choice {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    private String text;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "poll_id", nullable = false)
    @Setter
    private Poll poll;
}
