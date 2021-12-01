package Utilities;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.logging.Level;

import org.w3c.dom.Document;


/**
 * @author Rolan Abdualiev , Dec 2021
 * @project QA-AutomationDemonstration
 */

public class CommonOps extends Variables {

    @BeforeClass
    @Parameters({"PlatformName", "BrowserName", "URL",
            "Timeout", "DDTFile", "ActiveDB", "DBURL",
            "DBUsername", "DBPassword", "ApiUserName", "ApiPassword"})
    public void startSession(String PlatformName, String BrowserName, String URL,
                             String Timeout, String DDTFile, String ActiveDB, String DBURL,
                             String DBUsername, String DBPassword, String ApiUserName,
                             String ApiPassword) throws IOException, SQLException, ClassNotFoundException {

        platform = PlatformName;
        browserName = BrowserName;
        url = URL;
        timeout = Timeout;
        ddtFile = DDTFile;
        activeDB = ActiveDB;
        dbURL = DBURL;
        dbUserName = DBUsername;
        dbPassword = DBPassword;
        apiUserName = ApiUserName;
        apiPassword = ApiPassword;
        softAssert = new SoftAssert();
        screen = new Screen();

        switch (platform) {
            case "web":
                initWeb();
                break;
            case "mobile":
                initAppium();
                break;
            case "api":
                initAPI();
                break;
            case "electron":
                initElectron();
                break;
            case "desktop":
                initDesktop();
                break;
            default:
                throw new RuntimeException("Invalid platform name");
        }
    }

//    @BeforeMethod
//    public void beforeMethod(Method method) {
//        if (!platform.equalsIgnoreCase("api")) {
//            try {
//                MonteScreenRecorder.startRecord(method.getName());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (platform.equalsIgnoreCase("desktop")) {
//            DesktopWorkFlows.cleanCalc();
//        }
//    }


    public static void initWeb() {
        switch (browserName) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                // driver = initFirefoxDriver();
                break;
            case "ie":
                // driver = initIEDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Type");
        }

        driver.manage().window().maximize();
        setDriverTimeoutAndWait();
        driver.get(url);
        actions = new Actions(driver);

        ManagePages.initializeWebPages();
    }

    public static void initElectron() {
        electronAppPath = "C:/Users/thero/AppData/Local/Programs/todolist/Todolist.exe";

        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        now = LocalDateTime.now();

        System.setProperty("webdriver.chrome.driver", "./Drivers/electrondriver.exe");
        chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(electronAppPath);
        dc = new DesiredCapabilities();
        dc.setCapability("chromeOptions", chromeOptions);
        dc.setBrowserName(browserName);
        driver = new ChromeDriver(dc);
        setDriverTimeoutAndWait();

        ManagePages.initializeElectronPages();
    }

    @Step("Initialize api http request")
    public static void initAPI() {
        usersApiUrl = "http://localhost:3000/api/users/";
        adminApiURL = "http://localhost:3000/api/admin/users";

        RestAssured.baseURI = "http://localhost:3000/api/org";
        httpRequest = RestAssured.given().auth().preemptive().basic(apiUserName, apiPassword);
        httpRequest.header("Content-Type", "application/json");

    }

    @Step("Initialize Desktop")
    public static void initDesktop() throws IOException {
        dc.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App"); // TODO From external file.
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), dc);
        setDriverTimeoutAndWait();
    }

    @Step("Initialize Appium")
    public static void initAppium() throws MalformedURLException, SQLException, ClassNotFoundException {
        reportDirectory = "reports";
        reportFormat = "xml";
        testName = "Untitled";

        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "ac58c4ec");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.shivgadhia.android.ukMortgageCalc");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        appiumDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
        appiumDriver.setLogLevel(Level.INFO);
        ManageDB.initSQLConnection();
        ManagePages.initializeAppiumPages();
    }


    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static void setDriverTimeoutAndWait() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(timeout), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(timeout));
    }

    public static String getData(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        switch (platform) {
            case "desktop":
                fXmlFile = new File("./Files/Calculator.xml");
                break;
            case "api":
                fXmlFile = new File("./Files/Api.xml");
                break;
            case "mobile":
                fXmlFile = new File("./Files/Mortgage.xml");
                break;
        }
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    @AfterClass
    public static void endSession() throws SQLException {
        if(activeDB.equals("yes"))
            ManageDB.closeDBCon();
        else if(!platform.equals("api"))
            driver.quit();
    }
}
