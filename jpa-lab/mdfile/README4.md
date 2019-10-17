# 연관관계 매핑 기초

## 1. 단방향 연관관계

-

## 2. 양방향 연관관계와 연관관계의 주인 : 기본

양방향 연관관계 -> 양쪽으로 참조한다.

객체 : 참조를 활용
테이블 : FK를 이용한 join

객체-테이블 사이 패러다임 차이를 봐야한다.



### 2-1. 테이블 연관관계

단방향과 양방향과 차이가 없다. 

TEAM->MEMBER 알고싶든, MEMBER -> TEAM알고싶든 Foreign Key로 join해서 알 수 있다.
양방향 단방향 상관없이 FK로 모든 연관관계 알 수 있다.



### 2-2. 객체 연관관계

Member에서 Team변수를 갖고있으면 Team으로 갈 수 있다. 
Team에서는 List<Member>를 갖고있어야 Member로 갈 수 있다.

멤버변수로 다른 객체를 갖고있어야 서로에게 접근이 가능하다.



[참고] :  List<T> 멤버변수를 사용할 땐 꼭 ```new ArrayList<>()``` 이용해서 초기화를 해주자!
add() 할 때 NullPointError가 안뜨게!

```java
@Entity
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @Builder
    private Team(String name) { //여기 그냥 members도 param으로 넣었다가 에러 팡!
        this.name = name;
    }
}

```

```java
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
    @JoinColumn(name= "TEAM_ID")
    private Team team;

    @Builder
    private Member(String username, Team team) {
        this.username = username;
        this.team = team;
    }
}

```

궁금한 것

```java
EntitiyTransaction tx = em.getTrasaction();
em.persist(team);
em.flush();
em.clear();
```



반대 방향으로도 그래프 탐색이 가능해 진다.

```java
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
```



강좌랑 cascade 부분만 달라서 왜 그런가 하고 생각해 봤는데 강좌에서는 save를 두번 했었다.
강좌코드 대로 코딩하고 테스트한 결과는 아래

```java
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

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder
    private Member(String username, Team team) {
        this.username = username;
        this.team = team;
    }
}

```

```java
@Entity
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @Builder
    private Team(String name) { //여기 그냥 members도 param으로 넣었다가 에러 팡!
        this.name = name;
    }
}
```

테스트코드

```java
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

        teamRepository.save(team);

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
```



>  Member 저장할 때 Team을 저장하도록 cascade 설정을 하지 않고, 
>
> TestCode 작성시에 Member save , Team save를 각각 해줬다
>
> Team이 이미 save가 된 상태에서 Member를 save할 경우인데, 
>
> 어차피 DB에서는 Member에 FK가 있기 때문에 매핑이 가능해진다!
>
> 객체에서의 매핑은 이미 Team, Member 모두 각자의 참조객체를 갖고있기 때문에 가능하고!

 

> 내가 처음에 작성한 코드의 경우에는 member만 저장해서 team도 같이 저장하는 것이었기 때문에 Member를 저장할 때 cascade 옵션을 줘야했다.
>
> 따라서 Member repository에 저장하더라도, Team의 Insert를 먼저 실행 후에, Member insert를 진행하여 Member Table의 FK에 Team을 저장해준다.

```sql
Hibernate: insert into team (member_id, name) values (null, ?)
Hibernate: insert into member (member_id, team_id, username) values (null, ?, ?)

Hibernate: select member0_.member_id as member_i1_0_, member0_.team_id as team_id3_0_, member0_.username as username2_0_ from member member0_
```



Q. 양방향 매핑이 좋은가? 

A. 객체는 사실 단방향이 좋다! -> 신경쓸게 많음



### 2-3. 객체와 테이블이 관계를 맺는 차이

#### 2-3-1. 객체의 연관관계 - 2개

​	Member -> Team 연관관계 1개 (단방향) - Team 레퍼런스 객체

​	Team -> Member 연관관계 1개 (단방향) - Member 레퍼런스 객체

- 객체의 양방향 관계는 사실 양방향 관계가 아니라 서로 다른 단방향 관계 2개다
- 객체를 양방향으로 참조하려면 단방향 연관 관계를 2개 만들어야 한다.

```java
class Member{
    Team team;    // TEAM -> Member (team.getMember())
}
class Team{
    Member member;	// MEMBER -> TEAM (member.getTeam())
}
```

#### 2-3-2. 테이블의 연관관계 - 1개

​	Team <-> Member 연관관계 1개 (양방향) - FK하나로 양쪽의 연관관계 알 수 있음 (join)

- 테이블은 외래 키 하나로 두 테이블의 연관관계를 관리
- MEMBER.TEAM_ID 외래 키 하나로 양방향 연관관계 가짐 (양쪽으로 조인할 수 있다.)

```sql
SELECT * 
FROM MEMBER M
JOIN TEAM T ON M.TEAM_ID = T.TEAM_ID

SELECT * 
FROM TEAM T
JOIN MEMBER M ON T.TEAM_ID = M.TEAM_ID
```

## 2-4. 연관관계의 주인

딜레마가 생긴다 > **solution** : 둘중 하나로 외래키를 관리한다!

- Team에 있는 List<Member> 로 FK를 관리할지
- Member에 있는 Team으로 FK를 관리할지

#### 2-4-1. 양방향 매핑 규칙

- 객체의 두 관계중 하나를 연관관계의 주인으로 지정
- 연관관계의 주인만이 외래 키를 관리 (등록, 수정)
- 주인이 아닌쪽은 읽기만 가능
- 주인은 mappedBy 속성 사용X
- 주인이 아니면 mappedBy 속성으로 주인 지정

> mappedBy : 나는 누군가에 의해서 매핑이 되었어! 나는 주인이 아니야!



```java
public class Team {
    @OneToMany(mappedBy = "team") 
    private List<Member> members = new ArrayList<>();
}

public class Member { 
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
```

mappedBy : 나는 team에 의해서 관리가 된다 : Member 객체의 team 변수에 의해서 관리된다.

@JoinColumn의 Team: 나는 앞으로 Team을 관리할꺼야

#### 2-4-2. 누구를 주인으로?

- **외래키가 있는 곳**을 주인으로 정해라
- 여기서는 Member.team이 연관관계의 주인!

> 성능 이슈!
>
> Member의 경우에는 insert 쿼리 하나인데
>
> Team의 경우에는 insert 쿼리 + update 쿼리

DB 입장에서 외래키가 있는 곳이 무조건 N 

= N 이 있는 곳이 무조건 주인 

= @ManyToOne 이 무조건 주인



## 3. 양방향 연관관계와 연관관계의 주인 : 주의점, 정리

### 3-1. 양방향 매핑시 가장 많이 하는 실수

- 연관관계의 주인에 값을 입력하지 않음

  ```java
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
      public void 일차캐싱에_따른_저장_테스트() {
  
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
  }
  ```

- entityManager.clear(); 을 안했을 경우 
  : 1차 캐시를 해서 영속성 컨텍스트가 되어있는 상태
  값 세팅 연관관계가 되어있는걸 그냥 가져온다.
  이렇게 실행하면 DB에서 select 쿼리가 안 나간다. 

- Team이 그냥 영속성 컨텍스트에 들어가있어서, team에는 현재 member가 없는상태.
  그러다보니 1차 캐싱으로 인해 아무것도 안들어가 있음!

- 객체지향적으로 양쪽다 값을 입력해야 한다!

### 3-2. 양방향 연관관계 주의

- 순수 객체 상태를 고려해서 항상 양쪽에 값을 성정하자
- 연관관계 편의 메소드를 생성하자
- 양방향 매핑시에 무한루프를 조심하자
  예 ) toString(), lombok, JSON 생성 라이브러리



```java
Team team = Team.builder()
    .name("TeamA")
    .build();

teamRepository.save(team);

Member member = Member.builder()
    .username("member1")
    .team(team)
    .build();

team.getMembers().add(member);
```

이런식으로 Member에 한줄을 넣어주기 보다! **연관관계 편의 메소드**를 생성하자

Member에서 team을 set 해줄때 설정해버린다. - 하나면 세팅해도 두개가 같이 세팅이 되게!

```java
@Builder
private Member(String username, Team team) {
    this.username = username;
    this.team = team;
    team.getMembers().add(this);
}
```

편의 메소드는 일에 넣어도 되고, 다에 넣어도 된다 : 상황을 보고 만들기를 추천한다.



@ToString / toString() 메소드

```java
//Team 클래스
@Override
public String toString() {
    return "Team{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", members=" + members +
        '}';
}

//Member 클래스
@Override
public String toString() {
    return "Member{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", team=" + team +
        '}';
}
```

JSON 생성 라이브러리 : entity를 바로 Controller에서 바로 response 해버릴때 문제가 생긴다.

Member > Team > Member > Team > Member > Team > ...



1. lombok에서 toString을 쓰지마라

2. Controller에는 절대 Entity를 반환하지 마라.



## 4. 양방향 매핑 정리

- 단방향 매핑만으로도 이미 연관관계 매핑은 완료
- 양방향 매핑은 반대방향으로 조회(객체 그래프 탐색) 기능이 추가된 것 뿐
- JPQL에서 역방향으로 탐색할 일이 많음
- 단방향 매핑을 잘 하고 양방향은 필요할 때 추가해도됨 (테이블에 영향을 주지 않음)

JPA에서의 설계는 단방향만으로도 객체와 테이블의 매핑이 완료되어야한다. 

테이블은 한번 만들면 굳어지는 것!

