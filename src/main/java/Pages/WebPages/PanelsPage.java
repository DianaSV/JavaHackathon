package Pages.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */
public class PanelsPage {
    @FindBy(css = "div > h2")
    private static List<WebElement> allPanelsTitleList;

    @FindBy(css = "span[aria-label='Panel header item Remove']")
    private static WebElement removePanelOption;

    @FindBy(css = "button[aria-label='Confirm Modal Danger Button']")
    private static WebElement acceptRemoveAlert;

    // Getters
    public List<WebElement> getAllPanelsTitleList(){
        return allPanelsTitleList;
    }

    public WebElement getRemovePanelOption(){
        return removePanelOption;
    }

    public WebElement getAcceptRemoveAlert(){
        return acceptRemoveAlert;
    }
}
