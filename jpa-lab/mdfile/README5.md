# 다양한 연관관계 매핑

## 연간관계 매핑시 고려사항 3가지

### 1. 다중성

- 다대일 [N:1] : ```@ManyToOne```

- 일대다 [1:N] :  ```@OneToMany```
- 일대일 [1:1] :  ```@OneToOne```
- 다대다 [N:M] : ```@ManyToMany```

### 2. 단방향, 양방향

### 3. 연관관계의 주인

- 테이블은 외래 키 하나로 두 테이블이 연관관계를 맺음
- 객체 양방향 관계는 A->B, B-> A처럼 참조가 2군데
- 객체 양방향 관계는 참조가 2군데 있다. 둘중 테이블의 **외래 키를 관리하는 곳을 지정**해야함
  A를 바꿀때 B도 같이 바꿀지 / B를 바꿀때 A도 같이 바꿀지 

- 연관관계의 주인 : 외래 키를 관리하는 참조
- 주인의 반대편 : 외래 키에 영향을 주지 않음

## 다대일 [N:1] 

연관관계의 주인 : N이다

### 다대일 단방향

Member : N - Team : 1

Member에서 Team을 참조한다.

```java
@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // 외래키
    private Team team;

}
```

```java
@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
}

```

외래키가 있는 곳에 참조를 걸고 연관관계 매핑을 한다.

DB입장에서 보면 당연히 N에서 FK가 있어야한다.

반대로 Team이라면, list가 들어가니까 설계가 안맞다.

- 가장 많이 사용한다
- 다대일의 반대는 일대다 이다.



### 다대일 양방향

Member : N - Team : 1

Member에서 Team을 참조한다. Team에서도 Member를!

연관관계 주인이 FK 관리한다.
반대쪽은 어차피 읽기만 가능하기 때문에 Team에서 List<Member>를 추가하기만 하면 된다.

이때 **mappedBy**로 연관관계의 주인을 읽을 것이라는 것 명시가 중요

```java
// Team 클래스
	@OneToMany(mappedBy = "team") //참조를 당하는 쪽에서 읽기만 가능! 
    private List<Member> members = new ArrayList<>();
```

- 외래키가 있는 쪽이 연관관계의 주인
- 양쪽을 서로 참조하도록 개발



## 일대다 [1:N]

### 일대다 단방향

권장하지 않는다.

Team을 중심으로! : Team에서 외래키를 관리

Team은 Member를 알고싶은데 Member는 Team을 알고싶지 않음.

DB입장 : Member에 FK 걸어야한다.

Team의 List<Member> 바꾸었을 때 DB의 Mebmer중에 어떤 것의 TEAM_ID를 바꿔야한다.



```java
@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

}
```

```java
@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();
}
```

DB에는 잘 들어가는데 UpdateQurey가 나가는 등 query가 많이 나간다.

team에서 Member list를 저장할 때, Member테이블에도 team_id를 update해줘야한다.

Team을 건드렸는데 Member 테이블에 영향이간다 > 이해, 추적에서 조금 어렵다.

- 일대다 단방향은 일대다(1:N)에서 **일(1)이 연관관계의 주인**

- 테이블 일대다 관계는 항상 **다(N) 쪽에 외래 키가 있음**

- 객체와 테이블의 차이 때문에 반대편 테이블의 외래 키를 관리하는 특이한 구조

- @JoinColumn을 꼭 사용해야 함. 그렇지 않으면 조인 테이블 방식을 사용 (중간에 테이블 하나 추가)

  > team_member라는 중간테이블이 생겨버린다 : team_id와 member_id를 갖고있다.
  >
  > 단점 : 테이블이 1개 더들어가서 운영이 어렵다.

- 일대다 단반향 매핑의 단점

  - 엔티티가 관리하는 외래키가 다른 테이블에 있음
  - 연관관계 관리를 위해 추가로 UPDATE SQL 실행

- 일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하자 : 객체관계를 조금 포기!

### 일대다 양방향

약간 야매로 된다ㅋㅋㅋ

```java
// Member 클래스
    @ManyToOne
    @JoinColumn(name="TEAM_ID", insertable = false, updatable = false) //중요!!
    private Team team;
```

근데 이러면 Team Member모두 @JoinColumn이 붙어서 둘다 연관관계의 주인이된다.

그래서 JoinColumn의 옵션을 사용해서 mapping은 되어있고 값은 다 쓰는데 insertable, updatable을 막아 read 전용으로 만든다.

관리는 Team으로하고 Member는 읽기만한다.

- 이런 매핑은 공식적으로 존재 X
- @JoinColumn(insertable=false, updatable=false)
- **읽기 전용 필드**를 사용해서 양방향 처럼 사용하는 방법
- **다대일 양방향을 사용하자**



## 일대다 일대일 [1:1]

## 다대다 [N:M]