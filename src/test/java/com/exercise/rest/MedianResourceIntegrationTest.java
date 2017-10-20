package com.exercise.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MedianResourceIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_return_preconfigured_medians() {
        when().
                get("/medians?id=3&start=1115961331120&end=1115961331125").
        then().
                statusCode(OK.value()).
                body("timestamp", hasItems(1115961331123L));
    }

}
