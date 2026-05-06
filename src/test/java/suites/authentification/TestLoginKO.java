package suites.authentification;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestLoginKO {
    @Test
    public void testLoginMotDePasseIncorrect() {
        String baseUrl = System.getProperty("BASE_URL", "https://recette-api.attijaribank.com");
        RestAssured.baseURI = baseUrl;

        given()
            .contentType("application/json")
            .body("{ \"username\": \"user1\", \"password\": \"wrong\" }")
        .when()
            .post("/api/auth/login")
        .then()
            .statusCode(401)
            .body("error", equalTo("Invalid credentials"));
    }
}