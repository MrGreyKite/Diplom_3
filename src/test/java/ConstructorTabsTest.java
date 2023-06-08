import config.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ConstructorPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тесты на страницу Конструктора")
public class ConstructorTabsTest extends BaseTest {

    @ParameterizedTest(name = "{index} - На таб {0}")
    @MethodSource("tabsToSelect")
    @DisplayName("Проверка переключения табов после открытия конструктора")
    public void selectNonDefaultTabsInConstructorTest(String tab) {
       open(App.CONSTRUCTOR_URL, ConstructorPage.class).clickOnTab(tab).checkIfTabIsSelected(tab);
    }

    @Test
    @DisplayName("Проверка возвращения на таб, который был выбран по умолчанию")
    public void returnToDefaultTab() {
        ConstructorPage cp = open(App.CONSTRUCTOR_URL, ConstructorPage.class).
                clickOnTab("Соусы").
                clickOnTab("Булки");
        cp.checkIfTabIsUnselected("Соусы");
        cp.checkIfTabIsSelected("Булки");
    }

    static Stream<String> tabsToSelect() {
        return Stream.of("Соусы", "Начинки");
    }
}
