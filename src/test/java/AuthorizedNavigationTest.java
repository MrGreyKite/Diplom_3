import config.App;
import helpers.ApiHelpers;
import helpers.UserInfoConstructor;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ConstructorPage;
import pages.UserProfilePage;

import java.util.HashMap;

import static com.codeborne.selenide.LocalStorageConditions.item;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Тесты на навигацию авторизованным пользователем")
public class AuthorizedNavigationTest extends BaseTest {
    HashMap<String, String> userInfo;

    @BeforeEach
    @Step("Создание тестового пользователя и подстановка токена в браузер")
    void createTestUserAndAuthorize() {
        UserInfoConstructor uc = new UserInfoConstructor();
        userInfo = uc.constructUser();
        ApiHelpers.registerUser(userInfo);

        open(App.getConstructorUrl());
        localStorage().setItem("accessToken", ApiHelpers.getAuthToken());
        localStorage().setItem("refreshToken", ApiHelpers.getRefreshToken());
        refresh();
    }

    @Test
    @DisplayName("Переход на страницу конструктора из профиля - через логотип")
    public void goToConstructorPageFromProfileThroughLogoTest() {
        open(App.getProfileUrl(), UserProfilePage.class).
                clickOnLogo().
                checkIfCreateOrderButtonIsPresent();

        webdriver().shouldHave(url(App.getConstructorUrl()));

    }

    @Test
    @DisplayName("Переход на страницу конструктора из профиля - через кнопку-ссылку 'Конструктор'")
    public void goToConstructorPageFromProfileThroughConstructorLinkTest() {
        open(App.getProfileUrl(), UserProfilePage.class).
                clickOnConstructorLink().
                checkIfCreateOrderButtonIsPresent();

        webdriver().shouldHave(url(App.getConstructorUrl()));
    }

    @Test
    @DisplayName("Переход на страницу профиля из Конструктора")
    public void goToProfilePageFromConstructorTest() {
        open(App.getConstructorUrl(), ConstructorPage.class).
                goToUserCabinetAuthorized().
                checkProfilePageInfo();

        webdriver().shouldHave(url(App.getProfileUrl() + "/profile"));
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверяется, что после выхода из аккаунта пользователя перебрасывает на страницу входа " +
            "и пропадает токен авторизации")
    public void logoutTest() {
        open(App.getProfileUrl(), UserProfilePage.class).
                logout();

        webdriver().shouldHave(url(App.getLoginUrl()));
        localStorage().shouldNotHave(item("accessToken"));
        localStorage().shouldNotHave(item("refreshToken"));
    }


}
