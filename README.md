<a href="https://github.com/"><img alt="github.com" height="50" src="readme_files/technologies/github.svg"/></a>

# QA demo project

## Technologies

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="readme_files/technologies/intelij_idea.svg" width="50"/></a>
<a href="https://www.java.com/"><img alt="Java" height="50" src="readme_files/technologies/java.svg" width="50"/></a>
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="readme_files/technologies/junit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="readme_files/technologies/selenide.svg" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="Rest Assured" height="50" src="readme_files/technologies/rest_assured.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="readme_files/technologies/selenoid.svg" width="50"/></a>
<a href="https://maven.apache.org/"><img alt="Maven" height="50" src="readme_files/technologies/maven.png" width="50"/></a>
</div>

## Tests

This project contains API and UI tests:

* API tests of Reqres.in (free real API simulator)

:white_check_mark: REST-assured

:white_check_mark: GET, POST, PUT, DELETE

:white_check_mark: Status codes


* UI tests of AccuWewather.com:

:white_check_mark: PageObject

:white_check_mark: Check page Title

:white_check_mark: Input the text for search

:white_check_mark: Check new page 


## Code examples


> *API test*

```java

@Tags({@Tag("CREATING_USER"), @Tag("REGRESS"), @Tag("API")})
@Owner("proto")
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
}
```

> *Simple UI autotest realisation*

```java

@Tags({@Tag("UI"), @Tag("REGRESS")})
@Owner("proto")
@Feature("Test some Web resource")
@DisplayName("AccuWeather UI tests")
public class AccuWeatherTest extends TestBase {

    @Test
    @DisplayName("Main page text check")
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

