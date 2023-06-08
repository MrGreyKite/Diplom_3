import com.codeborne.selenide.logevents.SelenideLogger;
import config.DriverConfig;
import helpers.ApiHelpers;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.localStorage;

public class BaseTest {

    @BeforeAll
    @Step("Инициализация браузера")
    static void setUp() {
        DriverConfig.getBrowser();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true).savePageSource(true));

    }


    @AfterEach
    @Step("Удаление тестового юзера - если создан")
    void deleteTestUser() {
        String token = localStorage().getItem("accessToken");
        if(token != null) {
            ApiHelpers.setAuthToken(token);
        }

        if(ApiHelpers.getAuthToken() != null && !ApiHelpers.getAuthToken().isEmpty()) {
            ApiHelpers.deleteUser(ApiHelpers.getAuthToken());
        }
        localStorage().clear();
    }
}
