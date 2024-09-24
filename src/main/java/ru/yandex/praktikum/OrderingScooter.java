package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderingScooter {
    private final WebDriver driver;
    private final By whoForScooter = By.xpath(".//*[@class = 'Order_Header__BZXOb' and text() = 'Для кого самокат']"); // заголовок страницы "Для кого самокат"
    private final By name = By.xpath(".//input[@placeholder = '* Имя']"); // поле для ввода имени
    private final By surname = By.xpath(".//input[@placeholder = '* Фамилия']"); // поле для ввода фамилии
    private final By address = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']"); // поле для ввода адреса
    private final By metroStation = By.xpath(".//input[@placeholder = '* Станция метро']"); // поле для ввода станции метро
    private final By listMetroStation = By.className("select-search__select"); // выпадающее поле со списком станций метро
    private final By chooseMetroStations = By.xpath(".//div[@class='select-search__select']//div[starts-with(@class,'Order_Text')]"); // выбрать станцию метро
    private final By numberPhone = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']"); // поле для ввода номера телефона
    private final By buttonNext = By.className("Button_Middle__1CSJM"); // кнопка "Далее"
    private final By aboutRent = By.xpath(".//*[@class = 'Order_Header__BZXOb' and text() = 'Про аренду']"); // заголовок страницы "Про аренду"
    private final By dateDelivery = By.xpath(".//input[@placeholder = '* Когда привезти самокат']"); // поле для ввода даты доставки
    private final By dateSelected = By.className("react-datepicker__day--selected"); // дата в календаре
    private final By rentalPeriod = By.className("Dropdown-placeholder"); // поле для выбора срока аренды
    private final By colorScooter = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label"); // поле выбора цвета самоката
    private final By commentCourier = By.xpath("//input[@placeholder = 'Комментарий для курьера']"); // поле для ввода комментария курьеру
    private final By buttonOrder = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']"); // кнопка "Заказать" на странице "Про аренду"
    private final By placeAnOrder = By.className("Order_ModalHeader__3FDaJ"); // окно "Хотите оформить заказ?"
    private final By buttonYes = By.xpath(".//button[text() = 'Да']"); // кнопка "Да"
    private final By orderHasBeenPlaced = By.xpath(".//*[text() = 'Заказ оформлен']"); // окно "Заказ оформлен"

    public OrderingScooter (WebDriver driver) {
        this.driver = driver;
    }

    public OrderingScooter sendName(String personName){ // ввести имя
        driver.findElement(name).sendKeys(personName);
        return this;
    }

    public OrderingScooter sendSurname(String personSurname){ // ввести фамилию
        driver.findElement(surname).sendKeys(personSurname);
        return this;
    }
    public OrderingScooter sendAddress(String personAdress){ // ввести адрес
        driver.findElement(address).sendKeys(personAdress);
        return this;
    }
    public OrderingScooter sendMetro(String personMetroStation){ // выбрать станцию метро
        driver.findElement(metroStation).sendKeys(personMetroStation);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(listMetroStation));
        driver.findElement(chooseMetroStations).click();
        return this;
    }

    public OrderingScooter sendNumberPhone(String personNumberPhone){ // ввести телефон
        driver.findElement(numberPhone).sendKeys(personNumberPhone);
        return this;
    }
    public OrderingScooter clickButtonNext() { // нажать кнопку "Далее" для перехода в окно "Про аренду"
        driver.findElement(buttonNext).click();
        return this;
    }
    public OrderingScooter windowForWhomIsTheScooter(String personName,String personSurname,String personAdress,String personMetroStation,
                                               String personNumberPhone){ // заполнение формы "Для кого самокат"

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(whoForScooter));

        sendName(personName);
        sendSurname(personSurname);
        sendAddress(personAdress);
        sendMetro(personMetroStation);
        sendNumberPhone(personNumberPhone);
        clickButtonNext();
        return this;
    }

    public OrderingScooter chooseDateDelivery(String personDateDelivery){  // выбрать дату доставки

        driver.findElement(dateDelivery).sendKeys(personDateDelivery);
        driver.findElement(dateSelected).click();
        return this;
    }
    public OrderingScooter chooseRentalPeriod(String personRentalPeriod) { // выбрать период пользования самокатом
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();

        return this;
    }
    public OrderingScooter chooseColorScooter(String personColorScooter){ // выбрать цвет самоката
       driver.findElement(colorScooter).click();

       return this;
    }

    public OrderingScooter writeComment(String personCommentCourier){ // написать комментарий курьеру
        driver.findElement(commentCourier).sendKeys(personCommentCourier);
        return this;
    }
    public OrderingScooter clickButtonOrder(){ // нажать на кнопку Заказать
        driver.findElement(buttonOrder).click();
        return this;
    }

    public OrderingScooter windowAboutRent(String personDateDelivery, String personRentalPeriod,String personColorScooter,String personCommentCourier){ // окно заполнения формы Про аренду

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(aboutRent));

        chooseDateDelivery(personDateDelivery);
        chooseRentalPeriod(personRentalPeriod);
        chooseColorScooter(personColorScooter);
        writeComment(personCommentCourier);
        clickButtonOrder();

        return this;
    }
    public  OrderingScooter clickButtonYesPlaceAnOrder(){ // нажать на кнопку Да в окне "Хотите оформить заказ?"
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.presenceOfElementLocated(placeAnOrder));

        driver.findElement(buttonYes).click();
        return this;
    }

    public String getOrderHasBeenPlaced() { //  появление сообщения об успешном создании заказа
        return driver.findElement(orderHasBeenPlaced).getText();
    }
}
