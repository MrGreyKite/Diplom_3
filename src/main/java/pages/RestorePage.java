package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class RestorePage {
    SelenideElement toLoginLink = $x("//*[@class='Auth_link__1fOlj' and text()='Войти']");

    @Step("Переход по ссылке на страницу входа")
    public LoginPage toLogin() {
        toLoginLink.click();
        return page(LoginPage.class);
    }
}
