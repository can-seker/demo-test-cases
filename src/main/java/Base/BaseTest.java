package Base;
import Pages.AutomationStepDefinitions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BaseTest extends BaseLib{
    public WebDriver driver;

    @Before
    public void setUp() {

        String tarayici = System.getProperty("browser");
        String osName = System.getProperty("os.name");

        if (tarayici== null) {tarayici="Chrome";}

        System.out.println(tarayici);
        if (tarayici.equals("Grid")){
            ChromeOptions options = new ChromeOptions();
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (tarayici.equals("Chrome")) {
            //System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--remote-allow-origins=*");
            // co.addArguments("--headless");
            // co.addArguments("--incognito");
            //WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(co);
        } else {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions fo = new FirefoxOptions();
            fo.addArguments("--headless");
            fo.addArguments("--incognito");
            driver = new FirefoxDriver(fo);
        }
        AutomationStepDefinitions.setDriver(driver);
    }

    @After
    public void afterDown() {
        //driver.quit();
    }
}
