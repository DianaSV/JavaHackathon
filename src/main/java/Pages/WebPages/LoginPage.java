package Pages.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */
public class LoginPage {
    @FindBy(css = "input[name='user']")
    private static WebElement usernameInput;

    @FindBy(css = "input[name='password']")
    private static WebElement passwordInput;

    @FindBy(css = "button[aria-label='Login button']")
    private static WebElement loginButton;

    // Getters
    public  WebElement getUsernameInput(){
        return usernameInput;
    }

    public  WebElement getPasswordInput(){
        return passwordInput;
    }

    public  WebElement getLoginButton(){
        return loginButton;
    }
}
