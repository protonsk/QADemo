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

@Tags({@Tag("UPDATING_USER"), @Tag("REGRESS"), @Tag("API")})
@Owner("proto")
@Feature("Updating user")
@DisplayName("Updating user test")
public class UpdateUserTest extends TestBase {

    @Test
    @DisplayName("Updating user with PUT request")
    void updatingUserWithPutTest() {
        AtomicReference<CreateUser> user = new AtomicReference<>(new CreateUser("morpheus", "zion resident"));

        step("Updating user with PUT", () -> user.set(given()
                .when()
                .spec(Specifications.reqSpec)
                .body(user)
                .put(Reqres.updateUser, 2)
                .then().spec(Specifications.resSpec.statusCode(200)).log().all()
                .extract().as(CreateUser.class)));

        step("Checking the updatedAt requisite", () ->{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            assertTrue(user.get().getUpdatedAt().contains(format.format(new Date())),
                    String.format("%s do not contains %s", user.get().getUpdatedAt(), format.format(new Date())));
        });
    }

    @Test
    @DisplayName("Deleting the user with DELETE")
    void deletingUserTest() {

        step("Deleting the user", () -> given()
                .when()
                .spec(Specifications.reqSpec)
                .delete(Reqres.updateUser, 2)
                .then().spec(Specifications.resSpec.statusCode(204)).log().all());
    }
}
