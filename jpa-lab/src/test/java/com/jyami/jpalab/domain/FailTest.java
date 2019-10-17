package com.jyami.jpalab.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FailTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    public void 일차캐싱에_따른_저장실패_테스트() {

        Team team = Team.builder()
                .name("TeamA")
                .build();

        teamRepository.save(team);

        Member member = Member.builder()
                .username("member1")
                .team(team)
                .build();

//        team.getMembers().add(member);

        memberRepository.save(member);

        // 주인(Member)이 연관관계를 설정하지 않음!!
        // 역방향(주인이 아닌 방향)만 연관관계 설정
//        entityManager.clear();

        Team findTeam = teamRepository.findAll().get(0);
        List<Member> members = findTeam.getMembers();

        assertThat(members).isEmpty();
    }

    @Test
    public void 양방향_객체저장() {


        Team team = Team.builder()
                .name("TeamA")
                .build();

        teamRepository.save(team);

        Member member = Member.builder()
                .username("member1")
                .team(team)
                .build();

        team.getMembers().add(member);

        memberRepository.save(member);

        entityManager.clear();

        Member member1 = memberRepository.findAll().get(0);
        String username = member1.getUsername();
        assertThat(username).isEqualTo("member1");

        Team team1 = member1.getTeam();
        assertThat(team1.getName()).isEqualTo("TeamA");

        List<Member> members = team1.getMembers();
        for (Member m : members) {
            assertThat(m.getUsername()).startsWith("member");
        }
    }
}