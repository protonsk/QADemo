package tests.api;

import base.TestBase;
import classes.Reqres;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import models.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import specifications.Specifications;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Tags({@Tag("USERS_LIST"), @Tag("REGRESS"), @Tag("API")})
@Owner("proto")
@Feature("Users list")
@DisplayName("Users list")
public class UsersListTest extends TestBase {

    @Test
    @DisplayName("Users list isn't Empty")
    void usersListNotEmptyTest() {
        AtomicReference<List<User>> userList = new AtomicReference<>();

        step("Getting a users list with GET", () ->
                userList.set(
                        given()
                                .when()
                                .spec(Specifications.reqSpec)
                                .get(Reqres.users, 2)
                                .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                                .extract().body().jsonPath().getList("data", User.class)));

        step("Check that users list isn't empty", () ->
                assertFalse(userList.get().isEmpty()));
    }

    @Test
    @DisplayName("Checking the size of Users List")
    void checkingSizeOfUsersListTest() {
        AtomicReference<List<User>> userList = new AtomicReference<>();
        final int expectedRes = 6;

        step("Getting a users list with GET", () -> userList.set(
                given()
                        .when()
                        .spec(Specifications.reqSpec)
                        .get(Reqres.users, 2)
                        .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                        .extract().body().jsonPath().getList("data", User.class)));

        step("Checking the size of users list", () ->
                assertEquals(expectedRes, userList.get().size()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Edwards", "Tobias"})
    @DisplayName("Checking the last name of <George>")
    void checkingLastNameTest(String expectedRes) {
        AtomicReference<String> actualLastName = new AtomicReference<>();

        step("Getting the last name of George", () ->
                actualLastName.set(
                        given()
                                .when()
                                .spec(Specifications.reqSpec)
                                .get(Reqres.users, 2)
                                .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                                .extract().body().jsonPath().getList("data", User.class)
                                .stream().filter(user -> user.getFirstName().equals("George"))
                                .findFirst().get().getLastName()));

        step("Check actual last name", () ->
                assertEquals(expectedRes, actualLastName.get()));
    }
}
