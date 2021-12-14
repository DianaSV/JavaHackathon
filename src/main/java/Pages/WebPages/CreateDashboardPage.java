package Pages.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */
public class CreateDashboardPage {
    @FindBy(xpath = "//*[@id='PanelFrameTitle']")
    private static WebElement title;

    @FindBy(css = "div:nth-child(2) > textarea")
    private static WebElement description;

    @FindBy(xpath = "//label[text()='Bars']")
    private static WebElement barsStyle;

    @FindBy(xpath = "//*[@class='css-zer0lh']//*[@placeholder='Choose']")
    private static WebElement unitPicker;

    @FindBy(xpath = "//*[@class='rc-cascader-menu']//*[@title='Date & time']")
    private static WebElement dateAndTimePicker;

    @FindBy(xpath = "//*[@class='rc-cascader-menu']//*[@title='Datetime ISO']")
    private static WebElement dateTimeISO;

    @FindBy(xpath = "//button[contains(@title,'back to dashboard')]")
    private static WebElement applybtn;



    public  WebElement getTitle(){
        return title;
    }

    public  WebElement getDescription(){
        return description;
    }

    public  WebElement getBarsStyle(){
        return barsStyle;
    }

    public  WebElement getUnitPicker(){
        return unitPicker;
    }

    public  WebElement getDateAndTimePicker(){
        return dateAndTimePicker;
    }

    public  WebElement getDateTimeISO(){
        return dateTimeISO;
    }
    public  WebElement getApplyBtn(){
        return applybtn;
    }
}
