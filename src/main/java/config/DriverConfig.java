package config;

import com.codeborne.selenide.Configuration;

public class DriverConfig {

    public static void getBrowser() {
        String browserName = System.getProperty("browser", "yandex");
        switch (browserName) {
            case "chrome":
                Configuration.browser = "chrome";
                break;
            case "yandex":
                Configuration.browser = MyYandexFactory.class.getName();
                break;
            case "firefox":
                Configuration.browser = "firefox";
                break;
            default:
                throw new RuntimeException("Browser " + browserName + " not exist");
        }
    }

}
