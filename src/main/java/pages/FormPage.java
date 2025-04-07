package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FormPage {
    private WebDriver driver;
    private String url = "https://d3pv22lioo8876.cloudfront.net/tiptop/";

    // WebElements using different locator strategies
    @FindBy(name = "my-disabled")
    private WebElement disabledInput;

    @FindBy(name = "my-readonly")
    private WebElement readonlyInput;

    @FindBy(xpath = "//input[@value='Readonly input']")
    private WebElement readonlyInputByValue;

    @FindBy(name = "my-select")
    private WebElement colorDropdown;

    @FindBy(xpath = "//select[@name='my-select']/option")
    private List<WebElement> colorOptions;

    @FindBy(id = "submit-form")
    private WebElement submitButton;

    @FindBy(name = "my-name")
    private WebElement nameInput;

    @FindBy(name = "my-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//body[contains(text(),'Received')]")
    private WebElement receivedConfirmation;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToForm() {
        driver.get(url);
    }

    public boolean isDisabledInputDisabled() {
        return disabledInput.getAttribute("disabled") != null;
    }

    public boolean isReadonlyInputReadOnly() {
        return readonlyInput.getAttribute("readonly") != null;
    }

    public boolean isReadonlyInputByValueReadOnly() {
        return readonlyInputByValue.getAttribute("readonly") != null;
    }

    public int getColorDropdownOptionsCount() {
        Select select = new Select(colorDropdown);
        return select.getOptions().size();
    }

    public int getColorDropdownOptionsCountByXpath() {
        return colorOptions.size();
    }

    public boolean isSubmitButtonDisabled() {
        return submitButton.getAttribute("disabled") != null;
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void selectColor(String color) {
        Select select = new Select(colorDropdown);
        select.selectByValue(color);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public boolean isReceivedTextDisplayed() {
        try {
            // Wait for the page to load after submit
            Thread.sleep(1000);
            return driver.getPageSource().contains("Received");
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean verifySubmittedData(String name, String password, String color) {
        try {
            // Wait for page to load after submission
            Thread.sleep(1500);

            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);

            // Check if all form parameters are present in the URL
            boolean containsName = currentUrl.contains("my-name=" + name);
            boolean containsPassword = currentUrl.contains("my-password=" + password);
            boolean containsColor = currentUrl.contains("my-select=" + color);

            // Debug information
            System.out.println("Contains name: " + containsName);
            System.out.println("Contains password: " + containsPassword);
            System.out.println("Contains color: " + containsColor);

            return containsName && containsPassword && containsColor;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}