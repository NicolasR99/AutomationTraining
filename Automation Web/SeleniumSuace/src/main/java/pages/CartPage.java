package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CartPage {
    WebDriver driver;

    // Locators
    public By checkoutButton = By.id("checkout");
    public By cartItemNames = By.className("inventory_item_name");
    public By cartItemPrices = By.className("inventory_item_price");
    // Constructor
    public By secondHeader = By.xpath("//div[@class='header_secondary_container']//span[@class='title']");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
    public String getSecondTitle() {
        return driver.findElement(secondHeader).getText();
    }

}
