package com.bank.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionApiIntegrationTest {

    @BeforeAll
    static void configureRestAssured() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void createsUserWithPostRequest() {
        System.out.println("Sending POST /api/users request to reqres.in");

        Response response = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "Copilot Demo",
                          "job": "qa"
                        }
                        """)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response();

        System.out.println("Response status: " + response.statusCode());

        assertTrue(response.statusCode() == 200 || response.statusCode() == 201);
        assertEquals("Copilot Demo", response.jsonPath().getString("name"));
        assertEquals("qa", response.jsonPath().getString("job"));
        assertNotNull(response.jsonPath().getString("id"));
    }
}