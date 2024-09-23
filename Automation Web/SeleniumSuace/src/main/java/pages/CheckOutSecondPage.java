package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CheckOutSecondPage {
    WebDriver driver;

    // Locators
    public By finishButton = By.id("finish");
    public By secondHeader = By.xpath("//div[@class='header_secondary_container']//span[@class='title']");

    public By cartItemNames = By.className("inventory_item_name");
    public By cartItemPrices = By.className("inventory_item_price");
    public By subTotal = By.className("summary_subtotal_label");
    // Constructor
    public CheckOutSecondPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickFinishButton() {
        driver.findElement(finishButton).click();
    }
    public String getSecondTittle(){
        return driver.findElement(secondHeader).getText();
    }
}
