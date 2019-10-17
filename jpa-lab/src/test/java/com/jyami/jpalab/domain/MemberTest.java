package com.jyami.jpalab.domain;

import com.jyami.jpalab.domain.Member;
import com.jyami.jpalab.domain.MemberRepository;
import com.jyami.jpalab.domain.Team;
import com.jyami.jpalab.domain.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Before
    public void setUp() throws Exception {
        Team team = Team.builder()
                .name("TeamA")
                .build();

//        teamRepository.save(team);

        Member member = Member.builder()
                .username("member1")
                .team(team)
                .build();

        memberRepository.save(member);
    }

    @Test
    public void 잘_저장되었는지_불러오기() {
        Member member = memberRepository.findAll().get(0);
        String username = member.getUsername();
        assertThat(username).isEqualTo("member1");

        Team team = member.getTeam();
        assertThat(team.getName()).isEqualTo("TeamA");

        List<Member> members = team.getMembers();
        for (Member m : members) {
            assertThat(m.getUsername()).startsWith("member");
        }

    }
}