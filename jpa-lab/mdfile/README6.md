# 고급 매핑

## 1. 상속관계 매핑

- 관계형 데이터베이스는 상속 관계X
- 슈퍼타입 서브타입 관계라는 모델링 기법이 객체 상속과 유사
- 상속관계 매핑: 객체의 상속과 구조와 DB의 슈퍼타입 서브타입 관계를 매핑

- 슈퍼타입 서브타입 논리 모델을 실제 물리 모델로 구현하는 방법
  1. 각각 테이블로 변환 -> 조인 전략
  2. 통합 테이블로 변환 -> 단일 테이블 전략
  3. 서브타입 테이블로 변환 -> 구현 클래스마다 테이블 전략

>  테이블은 여러개의 모델링이 나오지만, 객체는 상속관계라는 1개의 개념이다.
>
> 객체관계는 같지만 DB설계를 다르게 할 수 있음

- 관계형 데이터베이스는 상속 관계 X
- 슈퍼타입 서브타입 관계라는 모델링 기법이 객체 상속과 유사
- 상속관계 매핑 : 객체의 상속, 구조와 DB의 슈퍼타입 서브타입 관계를 매핑



```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn
@Getter
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Book")
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    public Book(String name, int price, String author, String isbn) {
        super(name, price);
        this.author = author;
        this.isbn = isbn;
    }
}

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Album")
public class Album extends Item{
    private String artist;

    @Builder
    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Movie")
public class Movie extends Item {
    private String actor;
    private String director;

    @Builder
    public Movie(String name, int price, String actor, String director) {
        super(name, price);
        this.actor = actor;
        this.director = director;
    }
}
```

[Repository 코드]

```java
public interface ItemRepository<T extends Item> extends JpaRepository<T, Long> {}
public interface BookRepository extends JpaRepository<Book, Long> {}
public interface AlbumRepository extends JpaRepository<Album, Long> {}
public interface MovieRepository extends JpaRepository<Movie, Long> {}
```

이때 ItemRepository extends 를 꼭 기억하자!! **[abstract class jpaRepository 상속법]**

 ItemRepository만 사용해도 Book, Album, Movie를 모두 가져올 수 있다. (type casting 사용해서)

[테스트 코드]

```java
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
```



### 1-0. 주요 어노테이션

- @Inheritance(strategy = InheritanceType.XXX) = default: SINGLE_TABLE

  - JOINED : 조인 전략
  - SINGLE_TABLE : 단일 테이블 전략
  - TABLE_PER_CLASS : 구현 클래스마다 테이블 전략

- @DiscriminatorColumn(name = "DTYPE") = default: DTYPE

  DTYPE이라는 Column이 super class의 table에 생기고, 
  DTYPE의 값은  sub class의 이름으로 지정된다.

  SingleTable 전략에서 없어도 DTYPE 이생성되기도 하는데, 그래도 운영상 써주자

- @DiscriminatorValue("XXX") = default: classname

  

[예시]

```java
@Inheritance(strategy = InheritanceType.JOIN)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item{}

@DiscriminatorValue("ALBUM_TYPE")
public class Album extends Item{}

@DiscriminatorValue("BOOK_TYPE")
public class Book extends Item{}

@DiscriminatorValue("MOVIE_TYPE")
public class Movie extends Item{}
```



DB 설계를 바꿨는데도 코드를 많이 수정하지 않아도 된다!! : JPA의 큰 장점!!

Join이 성능이 안나오네 -> singletable로 고치자!! 
: query를 사용하면 코드를 많이 바꿔야함 근데 JPA사용하면 바꾸는게 엄청 쉽다.



### 1-1. 조인전략

데이터를 가져올 때 JOIN을 이용해서 가져온다.

insert는 두번 ITEAM ALBUM

select는 PK, FK를 이용해서 JOIN해서 가져온다.

abstract class에는 type을 컬럼을 두어서 구분한다.

```java
@Inheritance(strategy = InheritanceType.JOIN)
@DiscriminatorColumn
public abstract class Item{}
```

#### 1-1-1. 장점

- 테이블 정규화
- 외래 키 참조 무결성 제약조건 활용 가능
- 저장공간 효율화

#### 1-1-2. 단점

- 조회시 조인을 많이 사용, 성능 저하
- 조회 쿼리가 복잡함
- 데이터 저장시 INSERT SQL 2번 호출

> 조인 성능이 생각보다 치명적이진 않고, 오히려 저장공간이 더 효율적일 수도 있음
>
> 그래도 단일 테이블 전략과 비교했을 때 단점이다!
>
> 조인이 정규화도 되고 객체랑도 잘 맞고 설계 입장에서 잘 맞아 떨어진다.



### 1-2. 단일 테이블 전략 - 기본 전략

subclass 의 모든 멤버변수를 테이블의 컬럼으로 가져온다.

insert도 한번에 되고, select도 한번에 되니까 아무래도 성능이 나오지!

```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item{}
```

#### 1-2-1. 장점

- 조인이 필요 없으므로 일반적으로 조회 성능이 빠름
- 조회 쿼리가 단순함

#### 1-2-2. 단점

- 자식 엔티티가 매핑한 컬럼은 모두 null 허용

- 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있고 상황에 따라서 조회 성능이 오히려 느려질 수 있다. 

  > NULL 조건이 데이터 무결성 입장에서 애매하다.
  >
  > ALBUM 저장하면 > Book, Movie 관련 column이 모두 null이 되어야한다.
  >
  > 조회 성능을 문제시 하려면 임계점을 넘어야하는데 보통은 없음



### 1-3. 구현 클래스마다 테이블 전략

subclass 자체를 테이블로 만든다 + superclass의 멤버변수도 포함해서!

superclass를 아예 없애버리고, table을 subclass 기준으로 만든 후, 
superclass의 멤버변수도 같이 포함하게 한다.

Item table 자체가 존재하지 않고, Movie, Book, Album table만 존재한다.

@DiscriminatorColumn의 의미가 없다! (없어도 된다.)

단순하게 값을 넣고 뺄 때는 좋은데, 이외의 경우에는 세 개 테이블을 모두 찾아봐서 쿼리가 복잡하게 나간다.

> ex ) Item id가 5번이라고 할 때!

```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item{}
```

#### 이 전략은 데이터베이스 설계자와 ORM 전문가 둘 다 싫어하는 전략임!

#### 1-3-1. 장점

- 서브 타입을 명확하게 구분해서 처리할 때 효과적
- Not Null 제약조건 사용가능

#### 1-3-2. 단점

- 여러 자식 테이블을 함께 조회할 때 성능이 느림 (UNION SQL)
- 자식 테이블을 통합해서 쿼리하기 어려움



## 2. @MappedSuperclass - 매핑 정보 상속

- 공통 매핑 정보가 필요할 때 사용한다. (ex : baseTimeEntity 같은 것)

> 위에서 말한 상속 관계 매핑에서 테이블까지 고민하기 싫음.
> DB는 따로 쓰되, 객체입장에서 속성만 상속 받아서 쓰고 싶을때!

```java
@MappedSuperclass
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifedBy;
    private LocalDateTime lastModifiedDate;
}

@Entity
public class Member extends BaseEntity{ ... }

@Entity
public class Team extends BaseEntity{ ... }
```

매핑 정보만 받는 슈퍼 클래스로 하고싶다면

1. extends 로 클래스 설정하기
2. @MappedSuperclass 어노테이션 추가하기.

그냥 속성을 같이 쓰고 싶을 때 사용한다!!

```java
@Column(name = "CREATED_BY") // 이런식으로 column 설정도 충분히 가능하다.
private String createdBy;
```

JPA의 이벤트 기능으로 아예 어노테이션으로 시간, auth 정보를 편리하게 만들어 버릴 수 있다.

- 상속관계 매핑 X

- 엔티티X, 테이블과 매핑X (@Entity 안붙였다.)

- 부모 클래스를 상속 받는 **자식 클래스에 매핑 정보만 제공**

- 조회, 검색 불가(**em.find(BaseEntity)불가**)

  > em.find(BaseEntity.class, 1L);  불가능

- 직접 생성해서 사용할 일이 없으므로 **추상 클래스 권장**

- 테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑 정보를 모으는 역할

- 주로 등록일, 수정일, 등록자, 수정자 같은 전체 엔티티에서 공통으로 적용하는 정보를 모을 때 사용

- 참고 : @Entity 클래스는 엔티티나 @MappedSuperclass로 지정한 클래스만 상속가능하다.

```java
@MappedSuperclass //매핑 정보 상속
public abstract class BaseEntity{...}

@Entity //상속 관계 매핑
public abstract class Item extends BaseEntity{...}

@Entity
public class Album extends Item{...}
```

