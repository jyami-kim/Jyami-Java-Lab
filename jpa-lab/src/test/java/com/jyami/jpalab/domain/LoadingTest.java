package com.jyami.jpalab.domain;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoadingTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        Team team = Team.builder()
                .name("TeamDefault")
                .build();

        teamRepository.save(team);

        Member member = Member.builder()
                .username("MemberDefault")
                .team(team)
                .build();

        memberRepository.save(member);

        entityManager.clear(); //영속성 컨텍스트 제거
    }

    @Test
    public void 지연로딩_즉시로딩_테스트() {
        Member member = memberRepository.findById(1L).get();
        assertThat(member.getUsername()).isEqualTo("MemberDefault");
        System.out.println("m = " + member.getTeam().getClass());
        System.out.println("team.name = " + member.getTeam().getName());
    }

    @Test
    public void JPQL의_N_플러스_1_문제() {
        List<Member> members = entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
