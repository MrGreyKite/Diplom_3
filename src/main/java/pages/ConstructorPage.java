package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorPage {

    private final String tabLocatorTemplate = "//*[@class='text text_type_main-default' and text()='%s']";
    SelenideElement loginToAccountButton = $(byTagAndText("button", "Войти в аккаунт"));
    SelenideElement createOrderButton = $(byTagAndText("button", "Оформить заказ"));
    SelenideElement userCabinetLink = $x("//*[@class='AppHeader_header__link__3D_hX']/p[text()='Личный Кабинет']");

    @Step("Нажатие на таб с именем {0}")
    public ConstructorPage clickOnTab(String tabName) {
        SelenideElement tab = $x(String.format(tabLocatorTemplate, tabName)).parent();
        tab.click();
        return this;
    }

    @Step("Проверка, что выбран таб с именем {0}")
    public void checkIfTabIsSelected(String tabName) {
        SelenideElement tab = $x(String.format(tabLocatorTemplate, tabName)).parent();
        assertTrue(tab.has(cssClass("tab_tab_type_current__2BEPc")), "The tab is not selected");
    }

    @Step("Проверка, что таб с именем {0} не выбран")
    public void checkIfTabIsUnselected(String tabName) {
        SelenideElement tab = $x(String.format(tabLocatorTemplate, tabName)).parent();
        tab.shouldNotHave(cssClass("tab_tab_type_current__2BEPc"));

    }

    @Step("Переход по кнопке 'Личный кабинет' - авторизованным пользователем")
    public UserProfilePage goToUserCabinetAuthorized() {
        userCabinetLink.click();
        return page(UserProfilePage.class);
    }

    @Step("Переход по кнопке 'Личный кабинет' - неавторизованным пользователем")
    public LoginPage goToUserCabinetUnauthorized() {
        userCabinetLink.click();
        return page(LoginPage.class);
    }

    @Step("Нажатие на кнопку входа в аккаунт")
    public LoginPage pressLoginButton() {
        loginToAccountButton.click();
        return page(LoginPage.class);
    }

    @Step("Проверка, что на странице доступна кнопка 'Оформить заказ'")
    public void checkIfCreateOrderButtonIsPresent() {
        createOrderButton.shouldBe(visible).shouldBe(enabled);
    }



}
