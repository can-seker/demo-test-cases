package Pages;

import Base.BaseLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
public class AutomationStepDefinitions extends BaseLib{
    private static WebDriver driver;
    WebElement btnAltyapiSorgula = driver.findElement(By.id(""));

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
    @Given("Navigate to {string}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
}
