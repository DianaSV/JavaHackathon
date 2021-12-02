package Pages.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */
public class GrafanaMenuPage {
    @FindBy(css = "a[aria-label='Create'] svg")
    private static WebElement plusElement;

    @FindBy(css = "ul a.css-1j1rze6[href='/dashboard/new']")
    private static WebElement addDashboardElement;

    @FindBy(css = "a[aria-label='Server Admin']")
    private static WebElement serverAdminElement;

    @FindBy(css = "ul a.css-10jwdjd[href='/admin/users']")
    private static WebElement serverAdminUsersElement;

    @FindBy(css = "h1.css-1aanzv4")
    private static WebElement grafanaHeader;

    @FindBy(css = "nav[aria-label='Search links'] a.css-mgcb1x")
    private static WebElement generalButton;

    @FindBy(css = "div.css-1cd0a4j.dropdown path")
    private static WebElement searchDashboards;

    @FindBy(css = "div.css-1cd0a4j.dropdown ul")
    private static WebElement searchDashboardsLabel;


    // Getters
    public WebElement getPlusElement() {
        return plusElement;
    }

    public WebElement getAddDashboardElement() {
        return addDashboardElement;
    }

    public WebElement getServerAdminElement() {
        return serverAdminElement;
    }

    public WebElement getServerAdminUsers() {
        return serverAdminUsersElement;
    }

    public WebElement getGeneralButton() {
        return generalButton;
    }

    public String getGrafanaHeaderText() {
        return grafanaHeader.getText();
    }

    public WebElement getSearchDashboards() {
        return searchDashboards;
    }

    public WebElement getSearchDashboardsLabel() {
        return searchDashboardsLabel;
    }

}
