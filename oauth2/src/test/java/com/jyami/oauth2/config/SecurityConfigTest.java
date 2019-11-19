package com.jyami.oauth2.config;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(
        properties = "spring.config.location=classpath:/application-google.yml")
class SecurityConfigTest {
    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void google로그인_시도하면_OAuth인증창_등장한다() throws Exception {
        given()
                .when()
                .redirects().follow(false) // 리다이렉트 방지
                .get("/login")
                .then()
                .statusCode(302)
                .header("Location", containsString("https://accounts.google.com/o/oauth2/auth"));
    }

}