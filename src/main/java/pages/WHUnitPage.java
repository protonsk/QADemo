package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class WHUnitPage {
    public final SelenideElement powerPrice = $x("//div[@class = \"img-opa PowerPrice\"]");
    public final SelenideElement unitName = $x("//h3/div");
}
