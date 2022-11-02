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

@Tags({@Tag("API"), @Tag("USER_LIST"), @Tag("REGRESS")})
@Owner("proto")
@Feature("Work with reqres user lists")
@DisplayName("Test user lists")
public class UserListTest extends TestBase {

    @Test
    @DisplayName("List of Users is not Empty")
    void listOfUsersNotEmptyTest() {
        AtomicReference<List<User>> userList = new AtomicReference<>();

        step("Given a list of users from site", () ->
                userList.set(
                        given()
                                .when()
                                .spec(Specifications.reqSpec)
                                .get(Reqres.users, 2)
                                .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                                .extract().body().jsonPath().getList("data", User.class)));

        step("Check that user list is not empty", () ->
                assertFalse(userList.get().isEmpty()));
    }

    @Test
    @DisplayName("Check size of User List")
    void checkSizeOfUserListTest() {
        AtomicReference<List<User>> userList = new AtomicReference<>();

        step("Given a list of users from site", () -> userList.set(
                given()
                        .when()
                        .spec(Specifications.reqSpec)
                        .get(Reqres.users, 2)
                        .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                        .extract().body().jsonPath().getList("data", User.class)));

        step("Check what user list is not empty", () ->
                assertEquals(6, userList.get().size()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Edwards", "Tobias"})
    @DisplayName("Check last name of George")
    void checkLastNameTest(String expectedLastName) {
        AtomicReference<String> actualLastName = new AtomicReference<>();

        step("Get lastname of Tobias", () ->
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
                assertEquals(expectedLastName, actualLastName.get()));
    }
}
