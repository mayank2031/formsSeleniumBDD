package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormSteps {
    private WebDriver driver;
    private FormPage formPage;
    private String name;
    private String password;
    private String color;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        formPage = new FormPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to the form page")
    public void i_navigate_to_the_form_page() {
        formPage.navigateToForm();
    }

    @Then("the disabled input field should be disabled")
    public void the_disabled_input_field_should_be_disabled() {
        assertTrue("The disabled input field should be disabled", formPage.isDisabledInputDisabled());
    }

    @Then("the readonly input field should be readonly")
    public void the_readonly_input_field_should_be_readonly() {
        assertTrue("The readonly input field should be readonly using first xpath", formPage.isReadonlyInputReadOnly());
        assertTrue("The readonly input field should be readonly using second xpath", formPage.isReadonlyInputByValueReadOnly());
    }

    @Then("the color dropdown should have {int} options")
    public void the_color_dropdown_should_have_options(Integer optionCount) {
        assertEquals("The color dropdown should have correct number of options using first method",
                optionCount.intValue(), formPage.getColorDropdownOptionsCount());
        assertEquals("The color dropdown should have correct number of options using second method",
                optionCount.intValue(), formPage.getColorDropdownOptionsCountByXpath());
    }

    @Then("the submit button should be disabled")
    public void the_submit_button_should_be_disabled() {
        assertTrue("The submit button should be disabled when no data entered", formPage.isSubmitButtonDisabled());
    }

    @When("I enter {string} in the name field")
    public void i_enter_in_the_name_field(String nameInput) {
        name = nameInput;
        formPage.enterName(nameInput);
    }

    @When("I enter {string} in the password field")
    public void i_enter_in_the_password_field(String passwordInput) {
        password = passwordInput;
        formPage.enterPassword(passwordInput);
    }

    @Then("the submit button should be enabled")
    public void the_submit_button_should_be_enabled() {
        assertTrue("The submit button should be enabled", !formPage.isSubmitButtonDisabled());
    }

    @When("I select {string} color from the dropdown")
    public void i_select_color_from_the_dropdown(String colorValue) {
        color = colorValue;
        formPage.selectColor(colorValue);
    }

    @When("I click the submit button")
    public void i_click_the_submit_button() {
        formPage.clickSubmit();
    }

    @Then("the page should show {string} text")
    public void the_page_should_show_text(String expectedText) {
        assertTrue("The page should display '" + expectedText + "' text", formPage.isReceivedTextDisplayed());
    }

    @Then("all the data should be passed to the URL")
    public void all_the_data_should_be_passed_to_the_url() {
        assertTrue("Form data should be passed to URL", formPage.verifySubmittedData(name, password, color));
    }
}