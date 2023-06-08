package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    SelenideElement nameField = $x("//label[text()='Имя']/following-sibling::input");
    SelenideElement emailField = $x("//label[text()='Email']/following-sibling::input");
    SelenideElement passwordField = $x("//label[text()='Пароль']/following-sibling::input");
    SelenideElement invalidPasswordMessage = $x("//*[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
    SelenideElement toLoginLink = $x("//*[@class='Auth_link__1fOlj' and text()='Войти']");
    SelenideElement registerButton = $x("//button[text()='Зарегистрироваться']");

    @Step("Переход по ссылке на страницу входа")
    public LoginPage toLogin() {
        toLoginLink.click();
        return page(LoginPage.class);
    }

    @Step("Регистрация нового пользователя")
    public LoginPage doRegister(String name, String email, String password) {
        this.setNameField(name);
        this.setEmailField(email);
        this.setPasswordField(password);
        registerButton.click();
        return page(LoginPage.class);
    }

    @Step("Установка значения в поле 'Имя'")
    public RegisterPage setNameField(String name) {
        nameField.setValue(name);
        return this;
    }

    @Step("Установка значения в поле 'Email'")
    public RegisterPage setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }

    @Step("Установка значения в поле 'Пароль'")
    public RegisterPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step("Нажатие на кнопку регистрации")
    public RegisterPage pressRegisterButton() {
        registerButton.click();
        return this;
    }

    @Step("Проверка сообщения о невалидном пароле")
    public void checkMessageAboutInvalidPassword() {
        invalidPasswordMessage.shouldBe(visible);
        invalidPasswordMessage.shouldHave(cssValue("color", "rgba(229, 43, 26, 1)"));
    }
}
