package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class WHAdeptaSororitas {
    private final SelenideElement shieldOfFaithLink = $x("//a[@href = \"#Shield-of-Faith\"]");
    private final SelenideElement shieldOfFaithText = $x("//a[@name=\"Shield-of-Faith\"]/following::p");
    private final SelenideElement btnArmyList = $x("//div[@id=\"btnArmyList\"]");
    private final SelenideElement armyList = $x("//div[@class = \"tooltipster-box\"]");

    public WHAdeptaSororitas goToShieldOfFaithRule() {
        shieldOfFaithLink.click();
        return this;
    }

    public String getShieldOfFaithText() {
        return shieldOfFaithText.text();
    }

    @SneakyThrows
    public WHUnitPage openUnitPageByName(String name) {
        btnArmyList.hover();
        armyList.shouldBe(visible);
        new ArrayList<>($$x("//div[@class = \"NavColumns3\"]//a")
                .filter(Condition.text(name)))
                .get(0).click();
        return new WHUnitPage();
    }
}
