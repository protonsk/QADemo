package pages;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

//Page with Location information
public class AWCityPage {
    //Check the City name in H1 header
    private final SelenideElement cityName = $x("//h1[@class = 'header-loc']");

    public String GetCityName() {

        WaitForElement(cityName);
        return cityName.text();
    }
    //Wait for rendering
    public void WaitForElement(SelenideElement el) {
        el.should(visible, Duration.ofSeconds(3));
    }

}
