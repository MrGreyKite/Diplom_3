package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class UserProfilePage {
    SelenideElement logoutLink = $(byTagAndText("button", "Выход"));
    SelenideElement profilePageInfo = $(byTagAndText("p",
            "В этом разделе вы можете изменить свои персональные данные"));

    SelenideElement logo = $(".AppHeader_header__logo__2D0X2");
    SelenideElement toConstructor = $x("//*[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']")
            .parent();

    @Step("Проверка, что в профиле отображается информация для авторизованного юзера")
    public void checkProfilePageInfo(){
        profilePageInfo.shouldBe(visible);
    }

    @Step("Выход из аккаунта")
    public LoginPage logout() {
        logoutLink.click();
        return page(LoginPage.class);
    }

    @Step("Нажатие на лого")
    public ConstructorPage clickOnLogo() {
        logo.click();
        return page(ConstructorPage.class);
    }

    @Step("Нажатие на ссылку 'Конструктор'")
    public ConstructorPage clickOnConstructorLink() {
        toConstructor.click();
        return page(ConstructorPage.class);
    }
}
