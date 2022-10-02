package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Questions {
    private WebDriver driver;
    private By questionButton = By.className("accordion__button");
    private By answer = By.xpath(".//div[@class = 'accordion__panel' and not(@hidden)]//p");

    public Questions(WebDriver driver){
        this.driver = driver;
    }

    public void scrollToQuestions() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(questionButton)).perform();
    }

    public void clickQuestion(String questionText) {
        List<WebElement> questions = driver.findElements(questionButton);
        int index = -1;
        for(int i = 0; i < questions.size(); i++) {
            if(questions.get(i).getText().contains(questionText)) {
                index = i;
                break;
            }
        }
        questions.get(index).click();
    }

    public String getAnswerText() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(answer));
        return driver.findElement(answer).getText();
    }
}
