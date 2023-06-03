package config;

import com.codeborne.selenide.webdriver.ChromeDriverFactory;

public class MyYandexFactory extends ChromeDriverFactory {
    @Override
    public void setupWebdriverBinary() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
    }
}

