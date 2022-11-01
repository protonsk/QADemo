package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class WHMainPage {
    private final SelenideElement pageHeader = $x("//span[@class = 'page_header_span' ]");
    private final SelenideElement factions = $x("//div[@class = 'NavBtn NavBtn_Factions' ]");
    private final SelenideElement adeptaSororitasLink = $x("//a[@href=\"/wh40k9ed/factions/adepta-sororitas\"]");
    public String getHeaderText(){
        return pageHeader.text();
    }

    public WHAdeptaSororitas openAdeptaSororitasRules() {
        factions.hover();
        adeptaSororitasLink.click();
        return new WHAdeptaSororitas();
    }
}
