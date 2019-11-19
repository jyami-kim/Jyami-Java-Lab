package com.jyami.jpalab.domain;

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


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParentTest {
    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        Parent parent = Parent.builder()
                .name("parent")
                .build();

        Child child1 = Child.builder()
                .parent(parent)
                .name("child1")
                .build();

        Child child2 = Child.builder()
                .parent(parent)
                .name("child2")
                .build();

        parentRepository.save(parent);

        entityManager.clear(); //영속성 컨텍스트 제거
    }

    @Test
    public void Parent만_저장해도_Child_저장되는지_확인(){
        Parent parent = parentRepository.findById(1L).get();
        for(Child child: parent.getChildList()){
            assertThat(child.getName()).startsWith("child");
        }
    }

    @Test
    public void Parent컬렉션의_child를지웠을_때(){
        Parent parent1 = Parent.builder()
                .name("parent")
                .build();

        Child child1 = Child.builder()
                .parent(parent1)
                .name("child1")
                .build();

        Child child2 = Child.builder()
                .parent(parent1)
                .name("child2")
                .build();

        parentRepository.save(parent1);
        childRepository.save(child1);
        childRepository.save(child2);

        entityManager.clear();

        Parent parent = parentRepository.findById(2L).get();
        Child remove = parent.getChildList().remove(0);
        parentRepository.save(parent);

        entityManager.clear();
        Parent findParent = parentRepository.findById(2L).get();
        assertThat(findParent.getChildList().size()).isEqualTo(1);
    }
}