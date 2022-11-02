<a href="https://github.com/"><img alt="github.com" height="50" src="readme_files/technologies/github.svg"/></a>

# QA demo project

## Content

* <a href="#stack">Technology stack</a>
* <a href="#objects">Tests</a>
* <a href="#console">Run test from comandline</a>
* <a href="#code">Code</a>



<a id="stack"></a>

## Technology stack

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="readme_files/technologies/intelij_idea.svg" width="50"/></a>
<a href="https://www.java.com/"><img alt="Java" height="50" src="readme_files/technologies/java.svg" width="50"/></a>
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="readme_files/technologies/junit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="readme_files/technologies/selenide.svg" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="Rest Assured" height="50" src="readme_files/technologies/rest_assured.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="readme_files/technologies/selenoid.svg" width="50"/></a>
<a href="https://maven.apache.org/"><img alt="Maven" height="50" src="readme_files/technologies/maven.png" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="readme_files/technologies/jenkins.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure" height="50" src="readme_files/technologies/allure.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="readme_files/technologies/github.svg" width="50"/></a>
</div>

<a id="objects"></a>

## Tests

In this project I've created tests for 2 resources - Reqres.in and AccuWeather.com:

* API work with:

:white_check_mark: POJO

:white_check_mark: GET, POST, PUT, DELETE

:white_check_mark: Status codes


* UI:

:white_check_mark: Work with PageObject

:white_check_mark: Check page Title

:white_check_mark: Input the text for search

:white_check_mark: Check new page 

<a id="console"></a>

## Local run

```bash
mvn clean test 
-Dtest=${TEST_TYPE}

```

## Selenoid run

```bash
mvn clean test 
-Dtest=${TEST_TYPE}
-Dhost=remote

```

> `${TEST_TYPE}` - JUnit filtering by Tags  [ *REGRESS* <sub>(default)</sub> , *API*, *UI* ]
>


<a id="code"></a>

## Code

<a id="intelij"></a>

#### <img alt="InteliJ IDEA" height="50" src="readme_files/technologies/intelij_idea.svg" width="50"/>InteliJ IDEA</a><img alt="Java" height="50" src="readme_files/technologies/java.svg" width="50"/>Java</a><img alt="JUnit 5" height="50" src="readme_files/technologies/junit5.svg" width="50"/>JUnit 5</a><img alt="Selenide" height="50" src="readme_files/technologies/selenide.svg" width="50"/>Selenide</a><img alt="Rest Assured" height="50" src="readme_files/technologies/rest_assured.png" width="50"/>Rest Assured</a>

> *Simple API autotest realisation*

```java

@Tags({@Tag("API"), @Tag("CREATE_USER"), @Tag("REGRESS")})
@Owner("proto")
@Feature("Work with reqres.in create user method")
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

