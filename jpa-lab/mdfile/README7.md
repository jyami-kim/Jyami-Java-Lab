# 프록시와 연관관계 관리

## 1. 프록시

Member를 조회할 때 Team도 함께 조회해야 할까?

- Member 가져올 때 Team도 함께 출력
  - jpa에서 member가져올 때 team도 가져오면 좋다.
- Member 가져올때 오로지 member만!
  - jpa에서 member가져올 때 team도 가져오면 안좋다!



### 1-1. 프록시 기초

- em.find() : DB를 통해서 실제 엔티티 객체조회
- em.getReference() : 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회

DB의 쿼리가 안나가는데 조회가 되는 것

```java
@Test
public void 멤버와조회할때_팀도함께_조회() {
    Member findMember = entityManager.find(Member.class, 1L);
    System.out.println("findMember.id = " + findMember.getId());
    System.out.println("findMember.username = " + findMember.getUsername());
    }
```

```sql
select 
	member0_.member_id as member_i1_0_0_, 
	member0_.team_id as team_id3_0_0_, 
	member0_.username as username2_0_0_, 
	team1_.member_id as member_i1_1_1_, 
	team1_.name as name2_1_1_ 
from 
	member member0_ 
left outer join 
	team team1_ 
		on member0_.team_id=team1_.member_id 
where member0_.member_id=?
```

자동적으로 Member를 조회하는데 Team도 join이 되서 같이 조회가된다.

```java
@Test
public void 멤버만_조회() {
    Member findMember = entityManager.getReference(Member.class, 1L);
}
```

이 경우 select 쿼리가 안나간다!!

```java
@Test
public void 멤버만_조회() {
    Member findMember = entityManager.getReference(Member.class, 1L);
    System.out.println("findMember.id = " + findMember.getId());
    System.out.println("findMember.username = " + findMember.getUsername());
}
```

이 경우에는 select 쿼리가 나간다!

getReference() 를 호출하는 시점에는 DB에 Query를 호출하지 않는다.
이 값이 실제 사용되는 시점 (username)에 DB에 Query를 호출한다.

```java
System.out.println("findMember = " + findMember.getClass());
```

```
findMember = class com.jyami.jpalab.domain.Member$HibernateProxy$injSwDL2
```

이름이 Member가 아니다! HibernateProxy : 강제로 만든 가짜클래스이다 : **프록시 클래스**



### 1-2. 프록시 특징

- 실제 클래스를 상속 받아서 만들어짐
- 실제 클래스와 겉 모양이 같다.
- 사용하는 입장에서는 진짜 객체인지 프록시 객체인지는 구분하지 않고 사용하면 됨 (이론상)
- 프록시 객체는 실제 객체의 참조(target)를 보관
- 프록시 객체를 호출하면 프록시 객체는 실제 객체의 메소드 호출

```j
em.getReference(Member.class, 1L); //프록시객체 가져온다.
```

> getName() > Member target에 값이 없다 > 영속성 컨텍스트에 실제 값 가져오라 요청 > db가 그 값을 가져오고, Proxy객체에 진짜 객체를 연결시켜준다. 그래서 target.getName()으로 name을 가져온다.

영속성 컨텍스트에 초기화 요청 : 프록시에 값이 없을 때 DB에서 진짜 값을 달라.



### 1-3. 프록시 객체 매커니즘

- 프록시 객체는 처음 사용할 때 한 번만 초기화

```java
@Test
public void 프록시_테스트() {
    Member findMember = entityManager.getReference(Member.class, 1L);
    System.out.println("1st = " + findMember.getUsername());
    	//1st에서는 query가 나간다.
    System.out.println("2nd = " + findMember.getUsername());
    	//2nd에서는 query가 나가지 않는다.
}
```

- 프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아님, 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능

```java
@Test
public void 프록시_테스트() {
    Member findMember = entityManager.getReference(Member.class, 1L);
    System.out.println("before findMember = " + findMember.getClass());
    System.out.println("findMember.username = " + findMember.getUsername());
    System.out.println("after findMember = " + findMember.getClass());
}
```

```
before findMember = class com.jyami.jpalab.domain.Member$HibernateProxy$EYDMo7wU
Hibernate: [select query]
findMember.username = member1
after findMember = class com.jyami.jpalab.domain.Member$HibernateProxy$EYDMo7wU
```

- 프록시 객체는 원본 엔티티를 상속 받음, 따라서 타입 체크시 주의해야함 (== 비교 실패, instance of 사용)  => 프록시로 넘어올지, 원래 객체 타입으로 넘어올지 모른다

```java
@Test
public void 프록시_엔티티상속_테스트() {
    Member member1 = Member.builder()
        .username("member1")
        .build();
    memberRepository.save(member1);

    Member member2 = Member.builder()
        .username("member2")
        .build();
    memberRepository.save(member1);

    entityManager.clear();

    Member m1 = entityManager.find(Member.class, member1.getId());
    Member m2 = entityManager.getReference(Member.class, member2.getId());

    System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));   // false
    System.out.println("m1 instanceof : " + (m1 instanceof Member));        // true
    System.out.println("m2 instanceof : " + (m2 instanceof Member));        // true
}
```

- 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환

```java
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
}
```

```
m1 = class com.jyami.jpalab.domain.Member
reference = class com.jyami.jpalab.domain.Member
```

> 멤버를 이미 1차 캐싱했는데 굳이 proxy로 가져오는게 의미가 없다.
>
> JPA는 한 트랜잭션에서 같은거를 보장해준다.
> 한 영속성 컨텍스트에서 가져온거면 true.
>
> System.out.println(m==reference) // true로 무조껀 만들어 줘야한다 : proxy가 아닌 실 값 가져옴

```java
Member reference1 = entityManager.getReference(Member.class, member1.getId());
System.out.println("reference1 = " + reference1.getClass());

Member reference2 = entityManager.getReference(Member.class, member1.getId());
System.out.println("reference2 = " + reference2.getClass());

System.out.println("a == a" + (reference1 == reference2)); //true
```

```
reference1 = class com.jyami.jpalab.domain.Member$HibernateProxy$Xr6pfd5T
reference2 = class com.jyami.jpalab.domain.Member$HibernateProxy$Xr6pfd5T
```

> 같은 프록시 객체를 가져온다. a == a 를 보장해주어야 하기 때문이다.

```java
Member refMember = entityManager.getReference(Member.class, member1.getId()); 
System.out.println("refMember = " + refMember.getClass());

Member findMember = entityManager.find(Member.class, member1.getId());
System.out.println("findMember = " + findMember.getClass());

System.out.println("a == a" + (refMember == findMember));
```

```
refMember = class com.jyami.jpalab.domain.Member$HibernateProxy$HbLZp8PQ
Hibernate: [select 쿼리] 
findMember = class com.jyami.jpalab.domain.Member$HibernateProxy$HbLZp8PQ
```

> find() 에서도 proxy가 반환된다!!
>
> proxy를 한번 조회되면 em.find()에서 proxy를 반환해버린다! == 비교를 완료하려고
>
> "**프록시든 아니든 개발에 문제가 없게 하는게 중요하다.**"

- 영속성 컨텍스트의 도움을 받을 수 있는 준영속 상태일 때, 프록시를 초기화

```java
Member refMember = entityManager.getReference(Member.class, member1.getId()); //영속성 상태
System.out.println("refMember = " + refMember.getClass());

entityManager.detach(refMember); //영속성 컨텍스트 관리 안한다.
entityManager.close();

assertThatThrownBy(() -> {
    refMember.getUsername();
}).isInstanceOf(org.hibernate.LazyInitializationException.class);
```

> 에러 : could not initialize proxy - no Session
>
> 영속성 컨텍스트의 도움을 받지 못해서 proxy에 연결되었던 객체에 대한 target이 없어지는 듯
>
> 그래서 transaction 설정과 proxy 설정을 같게 하려고 한다~

### 1-4. 프록시 확인

- 프록시 인스턴스의 초기화 여부 확인

  persistenceUnitUtil.isLoaded(Object entity)

  ```java
  System.out.println("isLoaded = " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(refMember));
  ```

- 프록시 클래스 확인 방법

  entity.getClass().getName() 출력 (..javasist.. or HibernateProxy..)

  ```java
  System.out.println("refMember = " + refMember.getClass()); //클래스 확인
  System.out.println(refMember.getUsername()); //강제 호출
  ```

- 프록시 강제 초기화

  ```java
  System.out.println("refMember = " + refMember.getClass());
  Hibernate.initialize(refMember); // 강제 초기화
  ```

- 참고: JPA 표준은 강제 초기화 없음
  강제 호출: member.getName()



## 2. 즉시로딩과 지연로딩

### 2-1. 지연로딩 LAZY를 사용해서 프록시로 조회

멤버 클래스만 DB에서 조회한다.

```java
@ManyToOne(fetch = FetchType.LAZY)  ///fecth 설정을 해준다.
@JoinColumn(name = "TEAM_ID")
private Team team;
```

```java
@Test
public void 지연로딩() {
    Member member = memberRepository.findById(1L).get();
    assertThat(member.getUsername()).isEqualTo("MemberDefault");
	System.out.println("m = " + member.getTeam().getClass()); 
    //getTeam()은 프록시 가져오는 것
}
```

```sql
Hibernate: select 
	member0_.member_id as member_i1_0_0_, 
	member0_.team_id as team_id3_0_0_, 
	member0_.username as username2_0_0_ 
from 
	member member0_ 
where 
	member0_.member_id=?
m = class com.jyami.jpalab.domain.Team$HibernateProxy$gs0vf0Qv
```

멤버만 나가는 걸 알 수 있다!
그리고 Team은 proxy 객체를 가져온다.

```java
System.out.println("team.name = " + member.getTeam().getName());
```

```sql
select 
	team0_.member_id as member_i1_1_0_, 
	team0_.name as name2_1_0_ 
from 
	team team0_ 
where 
	team0_.member_id=?
```

그래서 위와 같이 영속성 컨텍스트 초기화를 하게 될 때 그때 쿼리가 나간다.

- Member에서 Team을 가져올 때 Lazy로 설정해두었기 때문에,
  Team 객체 안에는 프록시 객체를 넣어둔다.
  실제 team을 사용하는 시점에 영속성 컨텍스트 초기화를 한다.
- BM 상에서 Member조회시 Team을 같이 조회하지 않을 때 LAZY를 사용하면!



### 2-2. 즉시로딩 EAGER를 사용해서 함께 조회

```java
@ManyToOne(fetch = FetchType.EAGER)  ///fecth 설정을 해준다.
@JoinColumn(name = "TEAM_ID")
private Team team;
```

```sql
Hibernate: insert into team (member_id, name) values (null, ?)
Hibernate: insert into member (member_id, team_id, username) values (null, ?, ?)
Hibernate: select 
	member0_.member_id as member_i1_0_0_, 
	member0_.team_id as team_id3_0_0_, 
	member0_.username as username2_0_0_, 
	team1_.member_id as member_i1_1_1_, 
	team1_.name as name2_1_1_ 
from 
	member member0_ 
left outer join 
	team team1_ on member0_.team_id=team1_.member_id 
where 
	member0_.member_id=?
m = class com.jyami.jpalab.domain.Team
```

즉시 로딩이기 때문에 Proxy를 가져올 필요가 없어서
getClass() 를 했을 때 실제 객체가 나온다!

proxy를 가져오지 않으니까 영속성 컨텍스트 초기화를 해줄 필요가 없다.

BM 상에서 Mebmer를 쓸때 항상 Team도 조회할 경우!

JPA 구현체는 가능하면 조인을 사용해서 **SQL 한번에 함께** 조회



### 2-3. 프록시와 즉시로딩 주의

1. 가급적 지연 로딩만 사용(특히 실무에서)
   만약 관련 링크객체가 N개면 N개만큼 Join이 발생해서 나간다.
2. 즉시 로딩을 적용하면 예상하지 못한 SQL이 발생
3. 즉시로딩을 JPQL에서 N+1 문제를 일으킨다.
4. @ManyToOne, @OneToOne은 기본이 즉시로딩 -> LAZY로 설정 (X To One 시리즈)
5. @OneToMany, @ManyToMany는 기본이 지연 로딩



#### 2-3-1. JPQL N+1 문제 preview

```java
  @Test
  public void JPQL의_N_플러스_1_문제() {
      List<Member> members = entityManager.createQuery("select m from Member m", Member.class)
          .getResultList();
  }
```

```sql
  Hibernate: select 
  	member0_.member_id as member_i1_0_, 
  	member0_.team_id as team_id3_0_, 
  	member0_.username as username2_0_ 
  from 
  	member member0_
  Hibernate: select 
  	team0_.member_id as member_i1_1_0_,
      team0_.name as name2_1_0_ 
     from 
     team team0_ 
     where 
     	team0_.member_id=?
```

- 쿼리가 두번나간다!!

- JPQL : 1번째 파라미터가 sql query로 그대로 읽힌다.

  따라서 쿼리대로 Member를 가져온다.

  근데 Team이 즉시로딩이 되어있음! 즉시로딩이라 무조껀 그안에 값이 들어가 있어야 하기 때문에 Team도 가져온다.

  따라서 Team 쿼리를 또 따로 보낸다.

- 쿼리가 N+1 나간다

  - **1 :** 처음에 내보낸 쿼리  (N개의 Member 리턴)
  - **N :** EAGER 설정이 되어있어 참조 객체를 가져오기 위한 추가 쿼리 (N개의 Member 각각의 Team 값을 채우기 위해 각 Team을 찾기위해 N개의 쿼리가 나간다.)

- 이걸 LAZY로 잡으면 그냥 Member만 가져오고, Team은 proxy 객체라서 쿼리가 1개만 나가게된다.

- 해결 기본은 fetchJoin : runtime에 동적으로 내가 원하는애들만 선택해서 가져온다.  

  > application안에서도 member만 가져올 때 / member + team 가져올때가 구분되기 때문에
  >
  > ```java
  > List<Member> members = entityManager.createQuery("select m from Member m join fecth m.team", Member.class).getResultList();
  > ```
  >
  > 이 한방 쿼리에 모든게 들어가 있다.

 

### 2-4. 지연 로딩 활용

지금은 굉장히 이론적이고, 실무에서는 그냥 다 LAZY로 해야한다.

- Member와 Team은 자주 함께 사용 : 즉시로딩

- Member와 Order는 가끔 사용 : 지연로딩

- Order와 Product는 자주 함께 사용 : 즉시로딩

  



## 3. 영속성 전이: CASCADE

- 특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속성 상태로 만들고 싶을 때
- 예 : 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장

- 영속성 전이는 연관관계를 매핑하는 것과는 아무 관련이 없음
- 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화하는 편리함을 제공할 뿐

```java
@Entity
@NoArgsConstructor
@Getter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //cascade 옵션 : Parent를 저장할 때 child도 같이 저장하고 싶다.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    @Builder
    public Parent(String name) {
        this.name = name;
    }
}
```

```java
@Entity
@Getter
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn
    private Parent parent;

    @Builder
    public Child(String name, Parent parent) {
        this.name = name;
        this.parent = parent;
        parent.getChildList().add(this); //양방향 위해 추가함!
    }
}

```

[테스트 코드]

```java
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
}
```

```sql
Hibernate: insert into parent (id, name) values (null, ?)
Hibernate: insert into child (id, name, parent_id) values (null, ?, ?)
Hibernate: insert into child (id, name, parent_id) values (null, ?, ?)
---
Hibernate: select parent0_.id as id1_2_0_, parent0_.name as name2_2_0_ from parent parent0_ where parent0_.id=?
Hibernate: select childlist0_.parent_id as parent_i3_0_0_, childlist0_.id as id1_0_0_, childlist0_.id as id1_0_1_, childlist0_.name as name2_0_1_, childlist0_.parent_id as parent_i3_0_1_ from child childlist0_ where childlist0_.parent_id=?

```

> 심플하게 Parent를 저장할 때, Parent안에 있는 객체인 Child도 같이 저장할 때



### 3-1. CASCADE의 종류

- ALL : 모두 적용
- PERSIST : 영속 - 저장할 때만 lifecycle을 맞출래
- REMOVE : 삭제
- MERGE : 병합
- REFERESH : refresh
- DETACH : detach

> 하나의 부모가 자식들을 관리할 때는 의미가 있다.
> ex ) 게시판에 댓글, 첨부파일의 경로 등이 들어갈 때 : 의미 있음
>
> 그러나 여러 엔티티에서 관리한다면 쓰면 안된다.
>
> 소유자가 하나일 때는 써도 된다.
>
> 단일 엔티티에 완전히 종속적일 때 사용하자
>
> Child와 Parent의 lifecycle이 완전히 비슷할 때 사용하자



## 4. 고아객체

- 고아 객체 제거 : 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제

- orphanRemoval = true

  ```java
  Parent parent1 = em.find(Parent.class, id);
  parent1.getChildren().remove(0);
  // 자식 엔티티를 컬렉션에서 제거
  ```

  ```sql
  DELETE FROM CHILD WHERE ID = ?
  ```

  연관관계가 끊어져버린 상태 > delete가 나간다.

  ```java
  public class Parent{
      @OneToMany(mappedBy = "parent", orphanRemoval = true) // orphanRemoval 옵션 추가
      private List<Child> childList = new ArrayList<>();    
  }
  ```

- 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제하는 기능

- 참조하는 곳이 하나일 때 사용해야함!!

- 특정 엔티티가 개인 소유할 때 사용

- @OneToOne, @OneToMany만 가능

- 참고 : 개념적으로 부모를 제거하면 자식은 고아가된다. 
  따라서 고아 객체 기능을 제거 기능을 활성화하면, 부모를 제거할 때 자식도 함께 제거된다.
  이것은 CascadeType.REMOVE 처럼 동작한다.



## 5. 영속성 전이 + 고아 객체, 생명주기

```java
public class Parent{
    @OneToMany(mappedBy = "parent", cascade = CascadeType=ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();    
}
```

- CasecadeType.ALL + orphanRemovel = true

- 스스로 생명주기를 관리하는 엔티티는 em.persist()로 영속화, em.remove()로 제거

- 두 옵션을 모두 활성화 하면 부모 엔티티를 통해서 자식의 생명주기를 관리할 수 있다.

  > 자식 repository가 필요 없어진다.

- 도메인 주도 설계(DDD)의 Aggregate Root 개념을 구현할 때 유용