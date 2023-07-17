package selenium.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.Page.LoginPage;
import selenium.base.Setup;

public class RunLogin {

    private WebDriver webDriver;
    private Setup setup;

    private LoginPage loginPage;
    private By login = By.linkText("Login / Register");

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        setup = new Setup(webDriver);
        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void testRunlogin() throws InterruptedException {
        webDriver.get("http://13.250.120.40:8090/");
        setup.clickElement(login);
        setup.waitForPageloaded();
        webDriver.findElement(By.name("username")).sendKeys("doctor");
        webDriver.findElement(By.name("password")).sendKeys("123");
        webDriver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        Thread.sleep(5000);
        webDriver.quit();
    }
}
