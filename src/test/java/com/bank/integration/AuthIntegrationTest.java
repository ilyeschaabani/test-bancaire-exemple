package com.bank.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthIntegrationTest {

    @BeforeAll
    static void configureRestAssured() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void logsInWithValidCredentials() {
        System.out.println("Sending POST /api/login request to reqres.in");

        Response response = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "email": "eve.holt@reqres.in",
                          "password": "cityslicka"
                        }
                        """)
                .when()
                .post("/api/login")
                .then()
                .extract()
                .response();

        System.out.println("Response status: " + response.statusCode());

        assertEquals(200, response.statusCode());
        assertNotNull(response.jsonPath().getString("token"));
    }
}