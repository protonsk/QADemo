package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.Map;

abstract public class TestBase {
    protected static boolean isRemoteDriver = false;
    public static final org.apache.commons.configuration.Configuration CONFIG;

    static {
        try {
            CONFIG = new PropertiesConfiguration("application.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        if (System.getProperty("host", "local").equals("remote")) {
            isRemoteDriver = true;
            Configuration.driverManagerEnabled = false;
            Configuration.browserCapabilities = getRemoteWDCapabilities();
            Configuration.remote = CONFIG.getString("serverUrl");
        } else {
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    private static DesiredCapabilities getRemoteWDCapabilities() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "105.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        return capabilities;
    }
}
