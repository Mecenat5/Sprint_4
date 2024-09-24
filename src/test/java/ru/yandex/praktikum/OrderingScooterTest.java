package ru.yandex.praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderingScooterTest {

    private String personName;
    private String personSurname;
    private String personAdress;
    private String personMetroStation;
    private String personNumberPhone;
    private String personDateDelivery;
    private String personRentalPeriod;
    private String personColorScooter;
    private String personCommentCourier;
    private final String expectedOrderSuccessText = "Заказ оформлен"; //уточнить

    public OrderingScooterTest(String personName, String personSurname, String personAdress, String personMetroStation,String personNumberPhone, String personDateDelivery, String personRentalPeriod, String personColorScooter,String personCommentCourier){
        this.personName = personName;
        this.personSurname = personSurname;
        this.personAdress = personAdress;
        this.personMetroStation = personMetroStation;
        this.personNumberPhone = personNumberPhone;
        this.personDateDelivery = personDateDelivery;
        this.personRentalPeriod = personRentalPeriod;
        this.personColorScooter = personColorScooter;
        this.personCommentCourier = personCommentCourier;

    }
    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Андрей","Михайлов","Рождественская, д. 10", "Комсомольская", "847412487587","24.09.2024","двое суток","чёрный жемчуг","Побыстрее"},
                {"Варвара","Иванова","Преображенская, д. 8","Черкизовская","88005644487","25.09.2024","пятеро суток","серая безысходность","Привезите быстрее"},
        };
    }
    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void orderButtonAtTheTopOfThePage() { // тест для заказа самоката через кнопку "Заказать" вверху страницы
        MainPage mainPage = new MainPage(browserRule.getWebDriver());
        OrderingScooter orderingScooter = new OrderingScooter(browserRule.getWebDriver());

        mainPage.clickOrderTopButton();
        orderingScooter.windowForWhomIsTheScooter(personName, personSurname, personAdress,personMetroStation, personNumberPhone);
        orderingScooter.windowAboutRent(personDateDelivery, personRentalPeriod, personColorScooter, personCommentCourier);
        orderingScooter.clickButtonYesPlaceAnOrder();


        MatcherAssert.assertThat(
                "Заказ не оформлен",
                orderingScooter.getOrderHasBeenPlaced(),
                containsString(expectedOrderSuccessText)
        );
    }

    @Test
    public void orderButtonAtTheDownOfThePage() { // тест для заказа самоката через кнопку "Заказать" внизу страницы
        MainPage mainPage = new MainPage(browserRule.getWebDriver());
        OrderingScooter orderingScooter = new OrderingScooter(browserRule.getWebDriver());

        mainPage.scrollToOrderDownButton();
        mainPage.clickOrderDownButton();
        orderingScooter.windowForWhomIsTheScooter(personName, personSurname, personAdress,personMetroStation, personNumberPhone);
        orderingScooter.windowAboutRent(personDateDelivery, personRentalPeriod, personColorScooter, personCommentCourier);
        orderingScooter.clickButtonYesPlaceAnOrder();

        MatcherAssert.assertThat(
                "Заказ не оформлен",
                orderingScooter.getOrderHasBeenPlaced(),
                containsString(expectedOrderSuccessText)
        );
    }
}