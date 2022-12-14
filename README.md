# QA demo project

## Technologies

<UL>
<LI>IntelliJ IDEA
<LI>JAVA
<LI>JUnit5
<LI>Selenide
<LI>REST-assured
<LI>Selenoid
<LI>Maven
</UL>

## Tests

This project contains API and UI tests:
<UL>
<LI>API tests of Reqres.in (free real API simulator)
<UL>
<LI>REST-assured

<LI>GET, POST, PUT, DELETE

<LI>Status codes
</UL>

<LI>UI tests of AccuWewather.com
<UL>
<LI>PageObject

<LI>Check page Title

<LI>Input the text for search

<LI>Check new page 
</UL>
</UL>
## Code examples


> *API test*

```java

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
}
```

> *UI test*

```java

@Tags({@Tag("REGRESS"), @Tag("UI")})
@Owner("proto")
@Feature("Test AccuWeather site")
@DisplayName("AccuWeather UI tests")
public class AccuWeatherTest extends TestBase {

    @Test
    @DisplayName("Main page Title check")
    public void checkMainPageText() {
        final String expectedText = "Ежедневный прогноз погоды для областей, стран и глобальный прогноз | AccuWeather";
        AtomicReference<String> headerText = new AtomicReference<>();

        step("Open site and get header text", () ->
                headerText.set(new AWMainPage()
                        .getHeaderText()));

        step("Assert that the header is OK", () ->
                assertEquals(expectedText, headerText.get()));
    }
}
```

