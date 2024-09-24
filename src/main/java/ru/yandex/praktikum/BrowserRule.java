package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserRule extends ExternalResource {

    private WebDriver driver;

    protected void before() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://qa-scooter.praktikum-services.ru/");
}
    protected void after() {
        driver.quit();
    }
    public WebDriver getWebDriver() {
        return driver;
    }
}

