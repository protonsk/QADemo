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

@Tags({@Tag("API"), @Tag("CREATE_USER"), @Tag("REGRESS")})
@Owner("proto")
@Feature("Work with reqres create user method")
@DisplayName("Creating user")
public class CreateUserTest extends TestBase {

    @Test
    @DisplayName("Check success create user")
    void successCreateUserTest() {
        AtomicReference<CreateUser> user = new AtomicReference<>(new CreateUser("morpheus", "leader"));

        step("Send request for create user", () -> user.set(given()
                .spec(Specifications.reqSpec)
                .when()
                .body(user)
                .post(Reqres.createUser)
                .then().spec(Specifications.resSpec.statusCode(201)).log().all()
                .extract().as(CreateUser.class)));

        step("Check new user have id", () ->
                assertNotNull(user.get().getId()));
    }

    @Test
    @DisplayName("Check unsuccessful registration user")
    void unsuccessfulRegistrationUserTest() {
        AtomicReference<CreateUser> user = new AtomicReference<>(new CreateUser());
        user.get().setEmail("sydney@fife");

        step("Send request for registration user", () -> user.set(given()
                .when()
                .spec(Specifications.reqSpec)
                .body(user)
                .post(Reqres.regUser)
                .then().spec(Specifications.resSpec.statusCode(400)).log().all()
                .extract().as(CreateUser.class)));

        step("Check error description", () ->
                assertEquals("Missing password", user.get().getError()));
    }
}
