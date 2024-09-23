package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage {
    public By cartItemNames;
    WebDriver driver;

    // Locators
    public By backpack = By.id("add-to-cart-sauce-labs-backpack");
    public By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    public By cartButton = By.className("shopping_cart_link");
    public By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    public By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");
    public By inventoryItems = By.className("inventory_item");
    public By itemName = By.className("inventory_item_name");
    public By itemPrice = By.className("inventory_item_price");

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void addBackpackToCart() {
        driver.findElement(backpack).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(bikeLight).click();
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }
    public void addFirstAndLastItemsToCart() {
        List<WebElement> items = driver.findElements(inventoryItems);
        if (!items.isEmpty()) {
            items.get(0).findElement(By.tagName("button")).click(); // Add first item
            items.get(items.size() - 1).findElement(By.tagName("button")).click(); // Add last item
        }
    }
    public String getFirstItemName() {
        return driver.findElements(itemName).get(0).getText();
    }

    public String getLastItemName() {
        List<WebElement> names = driver.findElements(itemName);
        return names.get(names.size() - 1).getText();
    }

    public String getFirstItemPrice() {
        return driver.findElements(itemPrice).get(0).getText();
    }

    public String getLastItemPrice() {
        List<WebElement> prices = driver.findElements(itemPrice);
        return prices.get(prices.size() - 1).getText();
    }
}
