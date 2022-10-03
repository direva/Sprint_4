package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderForm {
    private WebDriver driver;
    private By input(String label) { return By.xpath(".//input[contains(@placeholder, '" + label + "')]"); }
    private By selectValue(String value) {
        return By.xpath(".//div[contains(@class, 'Order_Text') and text() = '" + value + "']");
    };
    private By datePickerValue(String value) {
        return By.xpath(".//div[@class = 'react-datepicker-popper']//div[text() = '" + value + "']");
    }
    private By periodField = By.className("Dropdown-control");
    private By periodValue(String value) {
        return By.xpath(".//div[@class = 'Dropdown-option' and text() = '" + value + "']");
    }
    private By colorCheckbox(String color) {
        return By.xpath(".//label[text() = '" + color + "']");
    }

    private By button(String label) {
        return By.xpath(".//div[contains(@class, 'Order_Content')]//button[text() = '" + label + "']");
    }

    private By orderApproveHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderForm(WebDriver driver){
        this.driver = driver;
    }

    public void fillInput(String label, String value) {
        driver.findElement(input(label)).sendKeys(value);
    }

    public void selectStation(String station) {
        driver.findElement(input("Станция метро")).sendKeys(station);
        driver.findElement(selectValue(station)).click();
    }

    public void setDate(String date) {
        driver.findElement(input("Когда")).click();
        driver.findElement(datePickerValue(date)).click();
    }

    public void selectPeriod(String period) {
        driver.findElement(periodField).click();
        driver.findElement(periodValue(period)).click();
    }

    public void checkColor(String color) {
        driver.findElement(colorCheckbox(color)).click();
    }

    public void pressButton(String label) {
        driver.findElement(button(label)).click();
    }

    public String getOrderApprovedHeader() {
        return driver.findElement(orderApproveHeader).getText();
    }
}
