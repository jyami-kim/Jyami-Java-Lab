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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProxyTest {
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
    public void 멤버와조회할때_팀도함께_조회() {
        Member findMember = entityManager.find(Member.class, 1L);
        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.username = " + findMember.getUsername());
    }

    @Test
    public void 멤버만_조회() {
        Member findMember = entityManager.getReference(Member.class, 1L);
        System.out.println("findMember = " + findMember.getClass());
        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.username = " + findMember.getUsername());
    }

    @Test
    public void 프록시_테스트() {
        Member findMember = entityManager.getReference(Member.class, 1L);
        System.out.println("before findMember = " + findMember.getClass());
        System.out.println("findMember.username = " + findMember.getUsername());
        System.out.println("after findMember = " + findMember.getClass());
    }


    @Test
    public void 프록시_엔티티상속_테스트() {
        Member member1 = Member.builder()
                .username("member1")
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .username("member2")
                .build();
        memberRepository.save(member2);

        entityManager.clear();

        Member m1 = entityManager.find(Member.class, member1.getId());
        Member m2 = entityManager.getReference(Member.class, member2.getId());

        System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));   // false
        System.out.println("m1 instanceof : " + (m1 instanceof Member));        // true
        System.out.println("m2 instanceof : " + (m2 instanceof Member));        // true

//        System.out.println("m1 == m2" + (m1 == m2));
    }

    @Test
    public void 프록시_영속성_테스트() {
        Member member1 = Member.builder()
                .username("member1")
                .build();
        memberRepository.save(member1);

        entityManager.clear();

        Member m1 = entityManager.find(Member.class, member1.getId()); //영속성 상태
        System.out.println("m1 = " + m1.getClass());

        Member references = entityManager.getReference(Member.class, member1.getId());
        System.out.println("reference = " + references.getClass());

        System.out.println("a == a" + (m1 == references));
    }

    @Test
    public void 프록시_영속성_테스트2() {
        Member member1 = Member.builder()
                .username("member1")
                .build();
        memberRepository.save(member1);

        entityManager.clear();

        Member refMember = entityManager.getReference(Member.class, member1.getId()); //영속성 상태
        System.out.println("refMember = " + refMember.getClass());

        Member findMember = entityManager.find(Member.class, member1.getId());
        System.out.println("findMember = " + findMember.getClass());

        System.out.println("a == a" + (refMember == findMember));
    }

    @Test
    public void 프록시_준영속성_테스트() {
        Member member1 = Member.builder()
                .username("member1")
                .build();
        memberRepository.save(member1);

        entityManager.clear();

        Member refMember = entityManager.getReference(Member.class, member1.getId()); //영속성 상태
        System.out.println("refMember = " + refMember.getClass());

        entityManager.detach(refMember); //영속성 컨텍스트 관리 안한다.
//        entityManager.close();
//        entityManager.clear();

        assertThatThrownBy(() -> {
            refMember.getUsername();
        }).isInstanceOf(org.hibernate.LazyInitializationException.class);
    }


    @Test
    public void 프록시_초기화여부_확인() {
        Member member1 = Member.builder()
                .username("member1")
                .build();
        memberRepository.save(member1);

        entityManager.clear();

        Member refMember = entityManager.getReference(Member.class, member1.getId()); //영속성 상태
        System.out.println("refMember = " + refMember.getClass());
        Hibernate.initialize(refMember);
        System.out.println(refMember.getUsername());
        System.out.println("isLoaded = " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(refMember));
    }

}
