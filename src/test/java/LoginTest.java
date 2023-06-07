import config.App;
import helpers.ApiHelpers;
import helpers.UserInfoConstructor;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ConstructorPage;
import pages.RegisterPage;
import pages.RestorePage;

import java.util.HashMap;
import java.util.stream.Stream;

import static com.codeborne.selenide.LocalStorageConditions.item;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тесты на вход в аккаунт из разных точек")
public class LoginTest extends BaseTest {

    @ParameterizedTest(name = "{index} - Вход в аккаунт через {0}")
    @MethodSource("loginMethods")
    @DisplayName("Авторизация ранее зарегистрированного пользователя")
    @Description("Проверяется возможность успешного входа в аккаунт из разных мест в приложении")
    public void loginTest(String loginMethod) {
        HashMap<String, String> userInfo = new UserInfoConstructor().constructUser();
        ApiHelpers.registerUser(userInfo);

        switch (loginMethod) {
            case "userCabinetButton":
                open(App.CONSTRUCTOR_URL, ConstructorPage.class)
                        .goToUserCabinetUnauthorized()
                        .authorize(userInfo.get("email"), userInfo.get("password"));
                break;
            case "loginButton":
                open(App.CONSTRUCTOR_URL, ConstructorPage.class)
                        .pressLoginButton()
                        .authorize(userInfo.get("email"), userInfo.get("password"));
                break;
            case "registerPage":
                open(App.REGISTRATION_URL, RegisterPage.class)
                        .toLogin()
                        .authorize(userInfo.get("email"), userInfo.get("password"));
                break;
            case "restorationPage":
                open(App.RESTORATION_URL, RestorePage.class)
                        .toLogin()
                        .authorize(userInfo.get("email"), userInfo.get("password"));
                break;
            default:
                throw new IllegalArgumentException("Invalid login method: " + loginMethod);
        }

        localStorage().shouldHave(item("accessToken"));
        localStorage().shouldHave(item("refreshToken"));
    }

    static Stream<String> loginMethods() {
        return Stream.of("userCabinetButton", "loginButton", "registerPage", "restorationPage");
    }
}
