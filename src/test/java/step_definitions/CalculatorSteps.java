package step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import shared_objects.CalculatorPage;


public class CalculatorSteps extends CalculatorPage {

        private final WebDriver driver;

        public CalculatorSteps() {
                this.driver = super.getDriver();
        }

        @Given("^Open chrome browser and start application$")
        public void openChromeBrowserAndStartApplication() {
                driver.get("https://www.online-calculator.com/full-screen-calculator/");
        }

        @When("^I enter following values and press the Equals button$")
        public void calculateValuesWithGivenOperation(DataTable dataTable) {
                inputCalculatorKeys(dataTable.cell(0,1));
                inputCalculatorKeys(dataTable.cell(2,1));
                inputCalculatorKeys(dataTable.cell(1,1));
                inputCalculatorKeys("=");
        }

        @Then("^I should be able to see$")
        public void iShouldBeAbleToSee(DataTable dataTable) {
                try {
                        takeScreenshotActual();
                        compareExpectedAndActualResults(dataTable.cell(0,1));
                } catch (Exception e) {
                        System.out.println("Could not take screenshot");
                        e.printStackTrace();
                }
        }

        @After
        public void tearDown() {
                driver.close();
                driver.quit();
        }
}