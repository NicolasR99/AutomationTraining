package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CheckoutCompletePage {

    WebDriver driver;

    // Locators
    public By confirmationMessage = By.className("complete-header");
    public By backHomeButton = By.id("back-to-products");
    // Constructor
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
    public void clickBackToProducts(){
        driver.findElement(backHomeButton).click();

    }
}

