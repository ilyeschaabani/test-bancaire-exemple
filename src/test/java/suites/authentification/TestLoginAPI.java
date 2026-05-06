package suites.authentification;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestLoginAPI {
    @Test
    public void testGetPost() {
        // Utilise un service public stable qui ne nécessite pas d'authentification
        String baseUrl = System.getProperty("BASE_URL", "https://jsonplaceholder.typicode.com");
        RestAssured.baseURI = baseUrl;

        given()
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", notNullValue());
    }
}