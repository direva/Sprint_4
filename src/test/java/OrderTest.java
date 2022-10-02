import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.MainPage;
import pageObjects.OrderForm;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private String buttonType;
    private String name;
    private String surname;
    private String address;
    private String station;
    private String phonenumber;
    private String date;
    private String period;
    private String color;
    private String comment;

    public OrderTest(
            String buttonType,
            String name,
            String surname,
            String address,
            String station,
            String phonenumber,
            String date,
            String period,
            String color,
            String comment) {
        this.buttonType = buttonType;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phonenumber = phonenumber;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                { "Header", "имя", "фамилия", "адрес", "Бульвар Рокоссовского", "+79999999999", "1", "сутки", "чёрный жемчуг", "коммент" },
                { "Finish", "тест", "тест", "тестадрес", "Черкизовская", "+79098888888", "2", "семеро суток", "серая безысходность", "" },
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void shouldCreateOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickOrderButton(buttonType);
        OrderForm orderForm = new OrderForm(driver);
        orderForm.fillInput("Имя", name);
        orderForm.fillInput("Фамилия", surname);
        orderForm.fillInput("Адрес", address);
        orderForm.selectStation(station);
        orderForm.fillInput("Телефон", phonenumber);
        orderForm.pressButton("Далее");
        orderForm.setDate(date);
        orderForm.selectPeriod(period);
        orderForm.checkColor(color);
        orderForm.fillInput("Комментарий", comment);
        orderForm.pressButton("Заказать");
        orderForm.pressButton("Да");
        String actualResult = orderForm.getOrderApprovedHeader();
        assertThat(actualResult, containsString("Заказ оформлен"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}