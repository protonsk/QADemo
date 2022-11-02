package tests.api;

import base.TestBase;
import classes.Reqres;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.CreateUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import specifications.Specifications;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tags({@Tag("CREATING_USER"), @Tag("REGRESS"), @Tag("API")})
@Owner("proto")
@Feature("Creating and registering user")
@DisplayName("Creating and registering user")
public class CreateUserTest extends TestBase {

    @Test
    @DisplayName("Creating user")
    void creatingUserTest() {
        AtomicReference<CreateUser> user = new AtomicReference<>(new CreateUser("morpheus", "leader"));

        step("Creating user request", () -> user.set(given()
                .spec(Specifications.reqSpec)
                .when()
                .body(user)
                .post(Reqres.createUser)
                .then().spec(Specifications.resSpec.statusCode(201)).log().all()
                .extract().as(CreateUser.class)));

        step("Check the Id of new user", () ->
                assertNotNull(user.get().getId()));
    }

    @Test
    @DisplayName("Unsuccessful registration of the user")
    void unRegistrationUserTest() {
        AtomicReference<CreateUser> user = new AtomicReference<>(new CreateUser());
        user.get().setEmail("sydney@fife");

        step("Registration of the user request", () -> user.set(given()
                .when()
                .spec(Specifications.reqSpec)
                .body(user)
                .post(Reqres.registerUser)
                .then().spec(Specifications.resSpec.statusCode(400)).log().all()
                .extract().as(CreateUser.class)));

        step("Check returned error", () ->
                assertEquals("Missing password", user.get().getError()));
    }
}
