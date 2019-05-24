# Spring Boot 소개

## Spring Boot Start

### 특징

토이를 만드는게 아니라 제품수준의 어플리 케이션을 만들때 도와주는 툴이다.

opinated view : 스프링 부트가 갖고있는 컨벤션을 의미한다 (널리 사용되는 설정)

Spring platform에 대한 기본 설정 뿐만아니라 다른 library에 대한 설정(tomcat)도 기본적으로 해준다

### 목표

- 모든 스프링 개발을 할 때 더 빠르고 더 폭넓은 사용성을 제공한다.
- 일일히 설정하지 않아도 convention으로 정해져있는 설정을 제공한다. 하지만 우리의 요구사항에 맞게 이런 설정을 쉽고 빠르게 바꿀 수 있다.(스프링 부트를 사용하는 이유)
- non-fucntional 설정도 제공해 준다. 비즈니스로직 구현에 필요한 기능 외에도 non-functional feature도!
- XML 사용하지 않고, code generation도 하지 않는다.

> Spring 루 : 독특하게 code generation을 해주는데 지금은 잘 사용되지 않는다. generation을 안해서 더 쉽고 명확하고 커스터마이징하기 쉽다.  > spring boot의 bb

### System Requirements

Spring boot 는 java 8 이상을 필요로 한다.

지원하는 servletContainer로는 tomcat, jetty Undertow가 있다.



# Spring Boot 시작하기

Intellij ultimate를 사용하면 Spring boot initializer가 있으나, community 버전은 없다. 따라서 자신이 원하는 build tool을 이용해서 만들어 주면된다 **Spring boot initializer를 이용하지 않고, 프로젝트 생성하는 법** 을 공부 할 것이다.



## gradle project에서 시작

auto import OK (build.gradle 파일 변경할 때 마다 바로바로 변경 : dependency 추가 등)

spring.io > project > spring boot > Learn > Reference Doc > Gradle Installation

<https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/htmlsingle/#getting-started-gradle-installation> 



### build.gradle

build.gradle 파일을 입력해준다.

```groovy
plugins {
	id 'org.springframework.boot' version '2.0.3.RELEASE'
	id 'java'
}
```

> 의존성 관리와 매우 관련이 있는 설정이다.

```groovy
dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
	testCompile("org.springframework.boot:spring-boot-starter-test")
}
```

> 일반적으로 프로젝트는 하나이상의 "starter"에 대한 의존성을 선언한다.
> spring boot는 의존선 선언을 간소화했으며, jar를 생성하는데 유용한 Gradle 플러그인을 제공한다.



### **initial build.gradle 파일**

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.4.RELEASE")
    }
}

apply plugin: 'java'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

bootJar {
    baseName = 'spring-boot-getting-started'
    version =  '0.1.0'
}

repositories {
    mavenCentral()
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
```



### SpringBootApplication.java

```java
package com.jyami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }

}
```

### SpringBootApplication 어노테이션을 사용해서, SpringApplication을 run하는 메소드 호출!

> Intellij 설정에서 
> Build, Execution, Deployment > Compiler > Annotation Processors에 들어가 
> Enable annotation processing에 체크해줘야 gradle로 build한 annotation들을 사용할 수 있다.



Spring MVC가 돌아가려면 여러 dependency가 필요한데, 어떻게 하여 수많은 의존성들이 들어왔는가?

mvc앱을 설정해야하는데 (bean, tomcat .. ) : 이게 @SpringBootApplication에 설정되어있다. > InableAutoCompletecation



### Run (실행하기)

Run을 하고 log를 보면 벌서  Tomcat이 8080 port에서 실행되고 있음을 알 수 있고
http://localhost:8080 을 띄어보면, tomcat web application이 동작함을 알 수 있다. (error이긴 하지만)



### build (빌드하기)

```shell
gradle build
```

이 package를 build한다. java프로젝트이므로 jar파일이 생성되고, 이 jar파일을 생성한다

```
java -jar build/libs/spring-boot-getting-started-0.1.0.jar
```

jar 파일을 실행하면, 아까와 같은 spring web application이 동작하게 된다.





## 웹으로 Spring Boot project 시작

http://start.spring.io

원하는 build 형태의 spring boot project를 생성해준다. (dir 형태로!)



# 스프링 프로젝트의 구조

gradle java 기본 프로젝트 구조와 동일하다

| 저장 파일     | 파일 경로         | 설명                                                         |
| ------------- | ----------------- | ------------------------------------------------------------ |
| 소스 코드     | src/main/java     | -                                                            |
| 소스 리소스   | src/main/resource | java application에서 resources 기준으로<br />아래 것들을 참조 가능 (classpath) |
| 테스트 코드   | src/test/java     | -                                                            |
| 테스트 리소스 | src/test/resource | test 관련 리소스를 만들 수 있다.                             |

**메인 애플리케이션 위치 (@SpringBootApplication)**  : 기본 패키지```package com.jyami```
 프로젝트가 쓰고있는 가장 최상위 패키지! > why? 컴포넌트 스캔을 하기 때문

com.jyami에서부터 시작을 해서, 그 아래에 있는 파일들을 스캔해서 bean으로 등록한다.

> src/main/java 위치에 넣으면 모든 패키지를 스캔하므로

만약 java>com.hello 패키지가 있고, 그안에 메인 애플리케이션이 아닌 java파일이 있으면, 그 java파일은 component 스캔이 이루어지지 않는다.