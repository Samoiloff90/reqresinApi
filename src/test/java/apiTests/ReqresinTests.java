package apiTests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {

    @Test
    void getSingleUserTest() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .body("data.first_name", is("Janet"))
                .statusCode(200);
    }

    @Test
    void getSingleUserNotFoundTest() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void createUserTest() {

        String inputDataCreate = "{\"name\": \"Eugene\", " +
                "\"job\": \"Developer\"}";

        given()
                .body(inputDataCreate)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .body("name", is("Eugene"))
                .statusCode(201);
    }

    @Test
    void updateUserTest() {

        String inputDataUpdate = "{\"name\": \"Eugene\", " +
                "\"job\": \"Developer\"}";

        given()
                .body(inputDataUpdate)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users")
                .then()
                .body("job", is("Developer"))
                .statusCode(200);
    }

    @Test
    void deleteUserTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void registerSuccessfulTest(){

        String inputDataRegister = "{\"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"pistol\"}";

        given()
                .body(inputDataRegister)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(200);
    }

    @Test
    void registerUnSuccessfulTest(){

        String inputDataError = "{\"email\": \"sydney@fife\"}";

        given()
                .body(inputDataError)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400);
    }

    @Test
    void sucessfulLoginTest() {

        String authorizedData = "{\"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"cityslicka\"}";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void missingPasswoedLoginTest() {

        String authorizedData = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
