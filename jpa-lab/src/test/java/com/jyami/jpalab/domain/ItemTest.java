package com.jyami.jpalab.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        Movie movie = Movie.builder()
                .actor("맷데이먼")
                .director("리들리스콧")
                .name("마션")
                .price(10000)
                .build();

        Book book = Book.builder()
                .author("조영호")
                .isbn("isbn")
                .name("객체지향의 사실과 오해")
                .price(10000)
                .build();

        Album album = Album.builder()
                .artist("엔플라잉")
                .name("야호")
                .price(30000)
                .build();

        itemRepository.save(movie);
        itemRepository.save(book);
        itemRepository.save(album);

        entityManager.clear();
    }

    @Test
    public void Item의_서브클래스_객체들_casting으로_가져오기() {
        Movie movie = (Movie) itemRepository.findAll().get(0);
        Book book = (Book) itemRepository.findAll().get(1);
        Album album = (Album) itemRepository.findAll().get(2);

        assertThat(movie.getName()).isEqualTo("마션");
        assertThat(book.getName()).isEqualTo("객체지향의 사실과 오해");
        assertThat(album.getArtist()).isEqualTo("엔플라잉");

    }
}