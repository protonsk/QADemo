package tests.api;

import base.TestBase;
import classes.Reqres;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.CreateUser;
import org.junit.jupiter.api.*;
import specifications.Specifications;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Tags({@Tag("API"), @Tag("UPDATE_USER"), @Tag("REGRESS")})
@Owner("proto")
@Feature("Work with reqres update user methods")
@DisplayName("Update user test")
public class UpdateUserTest extends TestBase {

    @Test
    @DisplayName("Check success update user by PUT")
    void successCreateUserByPutTest() {
        AtomicReference<CreateUser> user = new AtomicReference<>(new CreateUser("morpheus", "zion resident"));

        step("Send request for update user", () -> user.set(given()
                .when()
                .spec(Specifications.reqSpec)
                .body(user)
                .put(Reqres.updateUser, 2)
                .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                .extract().as(CreateUser.class)));

        step("Check last update time", () ->{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            assertTrue(user.get().getUpdatedAt().contains(format.format(new Date())),
                    String.format("%s do not contains %s", user.get().getUpdatedAt(), format.format(new Date())));
        });
    }

    @Test
    @DisplayName("Check success DELETE")
    void successDeleteUserTest() {

        step("Send request for delete user", () -> given()
                .when()
                .spec(Specifications.reqSpec)
                .delete(Reqres.updateUser, 2)
                .then().spec(Specifications.resSpec.statusCode(204)).log().all());
    }
}
