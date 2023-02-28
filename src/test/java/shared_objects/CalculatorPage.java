package shared_objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CalculatorPage {
    private WebDriver driver;

    // Page Locators
    By iframe_calculator = By.id("fullframe");
    By cnv_calculator = By.id("canvas");



    public CalculatorPage() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Waits for, and finds the desired element via By locator
     *
     * @param locator By locator of the element we are trying to find
     * @return The located WebElement
     */
    public WebElement find(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Sends keystrokes to the calculator app
     * If the number is negative, include keystroke for pressing the -/+ button
     *
     * @param strInput input keystroke
     */
    public void inputCalculatorKeys(String strInput) {
        if (strInput.contains("-") && strInput.length() > 1) {
            new Actions(driver).sendKeys("#").perform();
        }
        new Actions(driver).sendKeys(strInput).perform();
    }

    /**
     * Takes a screenshot of the canvas calculator
     */
    public void takeScreenshotActual() {
        try {
            driver.switchTo().frame(find(iframe_calculator));
            File screenshot = find(cnv_calculator).getScreenshotAs(OutputType.FILE);
            File screenshotLocation = new File("target/screenshots/actual.png");
            FileUtils.copyFile(screenshot, screenshotLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Compares the actual result (image) to the expected result (image)
     *
     * @param strExpected name of the expected image
     */
    public void compareExpectedAndActualResults(String strExpected) throws IOException {
        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") +"/src/test/java/resources/"+strExpected+".png"));
        BufferedImage actualImage = ImageIO.read(new File(System.getProperty("user.dir") +"/target/screenshots/actual.png"));

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        Assert.assertFalse("The results do not match!",diff.hasDiff());
    }

    /**
     * Takes a screenshot of the canvas calculator
     * Note: Used to save the expected output
     *
     * @param strImageName name to save the file as
     */
    public void takeScreenshot(String strImageName) {
        try {
            driver.switchTo().frame(find(iframe_calculator));
            File screenshot = find(cnv_calculator).getScreenshotAs(OutputType.FILE);
            File screenshotLocation = new File("/src/test/java/resources/"+strImageName+".png");
            FileUtils.copyFile(screenshot, screenshotLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
