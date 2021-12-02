package Pages.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */

public class DashboardPage {

    @FindBy(css = "span.react-resizable-handle.react-resizable-handle-se")
    private static WebElement resizeableElement;

    //*[@id="reactRoot"]/div/main/div[3]/div/div/div[1]/div/div/div[1]/div/div/div/section/div[2]/div
    @FindBy(css = "div.scrollbar-view > div > div > div > div > div > div:nth-child(1) > section > div.panel-content > div")
    private static WebElement dashboard;

    //*[@id="reactRoot"]/div/main/div[3]/div/div[2]/div[2]/div[1]/div/div/div/div[2]/div/a/div[2]/div
    @FindBy(css = "a.css-1ol0wmf")
    private static WebElement currentDashboard;

    @FindBy(css = "h2.css-1m35bcr")
    private static WebElement dashboardHeader;

    public WebElement getResizeElement() {
        return resizeableElement;
    }

    public WebElement getDashboard() {
        return dashboard;
    }

    public WebElement getCurrentDashboard() {
        return currentDashboard;
    }

    private String getDashboardWidth() {
        return dashboard.getCssValue("width");
    }

    private String getDashboardHeight() {
        return dashboard.getCssValue("height");
    }

    public String getDashboardHeaderText() {
        return dashboardHeader.getText();
    }

    public Map<String, String> getDimensions() {

        return new HashMap<String, String>() {{
            put("width", getDashboardWidth());
            put("height", getDashboardHeight());
        }};
    }
}
