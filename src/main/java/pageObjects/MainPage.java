package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private By acceptCookies = By.id("rcc-confirm-button");
    private By orderButton(String buttonType) {
        return By.xpath(".//div[contains(@class, '" + buttonType + "')]//button[text() = 'Заказать']");
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void acceptCookies() {
        driver.findElement(acceptCookies).click();
    }

    public void clickOrderButton(String buttonType) {
        driver.findElement(orderButton(buttonType)).click();
    }
}
