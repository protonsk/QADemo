package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AWMainPage {
    public final static String BASE_URL = "https://www.accuweather.com/";

    //Search data entry field
    private final SelenideElement searchLocation = $x("//input[@class = 'search-input' ]");

    //Check the Title of a page
    public String getHeaderText(){
        return Selenide.title();
    }

    //Enter the Search text and get new page
    public AWCityPage SearchLocation(String location) throws InterruptedException {
        searchLocation.sendKeys(location);
        searchLocation.pressEnter();
        Thread.sleep(2000);
        //searchBtn.click();
        return new AWCityPage();
    }


    public AWMainPage() throws InterruptedException {
        openPage(BASE_URL);
        //because of my network delay
        Thread.sleep(5000);
    }

    public void openPage(String url){
        Selenide.open(url);
    }

}
