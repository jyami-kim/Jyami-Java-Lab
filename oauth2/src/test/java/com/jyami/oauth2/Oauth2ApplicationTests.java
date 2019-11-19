package com.jyami.oauth2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

class Oauth2ApplicationTests {

    @Before
    public void setup() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void 기본path로_접근하면_index_html_호출된다() throws Exception {
        given()
                .when()
                    .get("/")
                .then()
                    .statusCode(200)
//                    .contentType("text/plain;charset=UTF-8")
                    .body(containsString("testing success"));
    }

    @Test
    public void 다른_path로_접근하면_403_redirection() throws Exception {
        given()
                .when()
                .get("/test")
                .then()
                .statusCode(403)
//                    .contentType("text/plain;charset=UTF-8")
                .body(containsString("testing success"));
    }

}
