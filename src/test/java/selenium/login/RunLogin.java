package selenium.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RunLogin {

    public WebDriver webDriver;

    @Test
    public void testRunlogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        webDriver.get();
    }
}
