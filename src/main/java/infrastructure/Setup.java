package infrastructure;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Setup {

    public static WebDriver driver;
    public static Properties element = new Properties();

    public static void loadPropertyFile() {

        try {
            element.load(new FileInputStream("src/main/java/com/config/element.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        loadPropertyFile();
        return element.getProperty(key);

    }

    public static void initialization() {

        String browser = Setup.getProperty("browser");
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/selenium/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            ChromeOptions options = getChromeOptions();
            options.addArguments("--remote-allow-origins=*");
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/selenium/drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.get(Setup.getProperty("URL"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--enable-screenshot-testing-with-mode");
        options.addArguments("--disable-password-generation");
        return options;
    }


    public static void screenShot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}