package tests.ui;

import base.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.WHGameChoosePage;
import pages.WHUnitPage;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags({@Tag("UI"), @Tag("REGRESS")})
@Owner("proto")
@Feature("Test some Web resource")
@DisplayName("Wahapedia UI tests")
public class WahapediaTest extends TestBase {

    @Test
    @DisplayName("Test game choose")
    public void checkMainPageText() {
        String mainPageHeaderText = "Playing This Game";
        AtomicReference<String> headerText = new AtomicReference<>();

        step("Open web resource, choose game and get header text", () ->
                headerText.set(new WHGameChoosePage()
                        .chooseWHGame()
                        .getHeaderText()));

        step("Assert what we open right game", () ->
                assertEquals(mainPageHeaderText, headerText.get()));
    }

    @Test
    @DisplayName("Check text of rule")
    public void checkShieldOfFaithText() {
        String expectedText = "Conviction is the greatest armour.";
        AtomicReference<String> actualText = new AtomicReference<>();

        step("Go to expected faction rules", () ->
                actualText.set(new WHGameChoosePage()
                        .chooseWHGame()
                        .openAdeptaSororitasRules()
                        .goToShieldOfFaithRule()
                        .getShieldOfFaithText()));

        step("Assert what we open right game", () ->
                assertEquals(expectedText, actualText.get()));
    }

    @ParameterizedTest
    @MethodSource("unitGenerator")
    @DisplayName("Check unit power cost")
    public void checkUnitCost(Unit unit) {
        AtomicReference<WHUnitPage> whUnitPage = new AtomicReference<>();

        step("Open unit page", () -> whUnitPage.set(new WHGameChoosePage()
                .chooseWHGame()
                .openAdeptaSororitasRules()
                .openUnitPageByName(unit.getName())));

        step("Assert what we open right unit", () ->
                assertEquals(unit.getName(), whUnitPage.get().unitName.text()));

        step("Check unit power price", () ->
                assertEquals(unit.getCost(), Integer.parseInt(whUnitPage.get().powerPrice.text())));
    }

    private static Stream<Arguments> unitGenerator() {
        return Stream.of(
                Arguments.of(new Unit("MORVENN VAHL", 14)),
                Arguments.of(new Unit("Immolator".toUpperCase(), 7))
                );
    }
}
