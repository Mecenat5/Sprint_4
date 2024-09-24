package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private final By textOfQuestion = By.className("accordion__heading"); // текст вопроса в блоке "Вопросы о важном"
    private final By textOfAnswer = By.xpath(".//div[@class='accordion__panel']/p"); // текст ответа в блоке "Вопросы о важном"
    private final By questionsAboutImportant = By.className("Home_FourPart__1uthg"); // блок "Вопросы о важном"
    private final By orderTopButton = By.xpath(".//button[@class='Button_Button__ra12g']"); //Кнопка оформления заказа вверху страницы сайта
    private final By orderDownButton = By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']"); // Кнопка оформления заказа внизу страницы сайта

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    public MainPage scrollToQuestionsAboutImportant() { // перейти к блоку "Вопросы о важном"
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsAboutImportant));
        return this;
    }

    public MainPage clickOfQuestion(int index) { // нажать на вопрос
        driver.findElements(textOfQuestion).get(index).click();
        return this;
    }

    public String getTextOfQuestion(int index) { // получить текст вопроса
        return driver.findElements(textOfQuestion).get(index).getText();
    }
    public String getTextOfAnswer(int index) { // получить текст ответа
        return driver.findElements(textOfAnswer).get(index).getText();
    }

    public MainPage scrollToOrderDownButton() { // перейти к кнопке "Заказать" внизу страницы сайта
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderDownButton));
        return this;
    }

    public void clickOrderTopButton() { // нажать на кнопку "Заказать" вверху страницы сайта
        driver.findElement(orderTopButton).click();
    }
    public void clickOrderDownButton() { // нажать на кнопку "Заказать" внизу страницы сайта
        driver.findElement(orderDownButton).click();
    }
}
