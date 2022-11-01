package tests.ui;

import base.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.AWMainPage;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    @DisplayName("Choose Location")
    public void checkLocationText() {
        final String expectedText = "Лимассол, Лимасол";
        AtomicReference<String> actualText = new AtomicReference<>();

        step("Search the Location", () ->
                actualText.set(new AWMainPage()
                        .SearchLocation(expectedText)
                        .GetCityName()));

        step("Checking the Location name", () ->
                assertEquals(expectedText, actualText.get()));
    }

}
