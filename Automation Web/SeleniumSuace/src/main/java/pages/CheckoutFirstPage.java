package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CheckoutFirstPage {
    WebDriver driver;

    // Locators
    public By firstNameField = By.id("first-name");
    public By lastNameField = By.id("last-name");
    public By postalCodeField = By.id("postal-code");
    public By continueButton = By.id("continue");
    public By secondHeader = By.xpath("//div[@class='header_secondary_container']//span[@class='title']");

    // Constructor
    public CheckoutFirstPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public String getSecondTittle(){
        return driver.findElement(secondHeader).getText();
    }
}
