package com.exercise.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MeasurementResourceIntegrationTest {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void should_return_preconfigured_measurements() {
		when().get("/measurements?id=3").then().statusCode(OK.value()).body("timestamp", hasItems(1115961331123L));
	}

	@Test
	public void should_add_new_measurement() {
		given().contentType(JSON)
				.body("    {\r\n\"value\": 12.2235,\r\n\"timestamp\": 1115961331123,\r\n\"sensorId\": 1\r\n}")
				.when().post("/measurements").

		then().statusCode(OK.value());

		when().get("/measurements?id=3").then().statusCode(OK.value()).body("timestamp", hasItems(1115961331123L));
	}

}
