import config.App;
import helpers.UserInfoConstructor;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.LoginPage;
import pages.RegisterPage;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Тесты на регистрацию нового пользователя")
public class RegistrationTest extends BaseTest {

    HashMap<String, String> userInfo;

    @BeforeEach
    @Step("Создание пользовательских данных для регистрации")
    void getUserData() {
        UserInfoConstructor uc = new UserInfoConstructor();
        userInfo = uc.constructUser();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    @Description("Проверяется успешное совершение регистрации, перенаправление после регистрации " +
            "и возможность авторизоваться с этими данными: после авторизации меняется кнопка в Конструкторе")
    public void registrationWithValidPasswordTest() {
        LoginPage loginPage = open(App.getRegistrationUrl(), RegisterPage.class).
                doRegister(userInfo.get("name"), userInfo.get("email"), userInfo.get("password"));

        webdriver().shouldHave(url(App.getLoginUrl()));
        loginPage.authorize(userInfo.get("email"), userInfo.get("password")).checkIfCreateOrderButtonIsPresent();

    }

    @ParameterizedTest(name = "Невалидный пароль - {0}")
    @ValueSource(strings = {"5букв", " "})
    @DisplayName("Регистрация с неподходящим паролем")
    @Description("Проверяется регистрация с паролем меньше 6 символов - должна выводиться ошибка")
    public void registrationWithInvalidPasswordTest(String incorrectPass){
        open(App.getRegistrationUrl(), RegisterPage.class).
                setEmailField(userInfo.get("email")).setNameField(userInfo.get("name")).setPasswordField(incorrectPass).
                pressRegisterButton().
                checkMessageAboutInvalidPassword();
    }
}
