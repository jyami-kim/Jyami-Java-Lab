package com.jyami.springsecuritypolling.domain.poll;

import com.jyami.springsecuritypolling.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@Entity
public class Poll extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poll_id")
    private Long id;

    @NotBlank
    @Size(max=140)
    private String question;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Size(min = 2, max = 6)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Choice> choices = new ArrayList<>();

    @NotNull
    private Instant expirationDateTime;

    public void addChoice(Choice choice){
        choices.add(choice);
        choice.setPoll(this);
    }

    public void removeChoice(Choice choice){
        choices.remove(choice);
        choice.setPoll(null);
    }

}
