package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    SelenideElement toRegisterLink;

    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    SelenideElement toRestorePasswordLink;

    @FindBy(how = How.NAME, using = "name")
    SelenideElement emailField;

    @FindBy(how = How.NAME, using = "Пароль")
    SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    SelenideElement loginButton;

    @Step("Авторизация по логину и паролю")
    public ConstructorPage authorize(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        loginButton.click();
        return page(ConstructorPage.class);
    }

    @Step("Перейти на страницу восстановления доступа")
    public RestorePage toRestorePassword() {
        toRestorePasswordLink.click();
        return page(RestorePage.class);
    }

    @Step("Перейти на страницу регистрации")
    public RegisterPage toRegister() {
        toRegisterLink.click();
        return page(RegisterPage.class);
    }
}
