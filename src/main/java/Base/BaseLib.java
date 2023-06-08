package Base;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
public class BaseLib {

    Random random = new Random();

    public int randomNumber(int length){
        return random.nextInt(length);
    }

    public WebElement waitElementId(WebDriver driver, int waitTimeInSeconds, String elementId){
        Duration timeout = Duration.ofSeconds(waitTimeInSeconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
    }
}