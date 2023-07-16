package selenium.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
    private WebDriver webDriver;
    private By username = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@id='password-field']");
    private By ButtonLogin = By.xpath("//button[normalize-space()='Login']");
    private Setup setup;
    public LoginPage(WebDriver driver){
        this.webDriver = driver;
    }

    public  void  signIn(String user, String pass ){
//        setup.waitForPageloaded();
        setup.setText(username, user);
        setup.setText(password, pass);
        setup.clickElement(ButtonLogin);
    }
}
