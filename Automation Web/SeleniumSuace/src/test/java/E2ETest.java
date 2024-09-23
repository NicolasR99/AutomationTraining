import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import pages.CheckoutFirstPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.CartPage;
import pages.CheckOutSecondPage;
import resources.TestData;
import resources.WaitUtils;
import resources.BrowserSetup;
import pages.SideBar;
import java.util.List;

public class E2ETest {
    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutFirstPage checkoutFirstPage;
    CheckOutSecondPage checkoutSecondPage;
    CheckoutCompletePage checkoutCompletePage;
    WaitUtils waitUtils;
    SideBar sideBar;
    String firstItemName;
    String lastItemName;
    String firstItemPrice;
    String lastItemPrice;

    @BeforeClass
    public void setup() {
        // Initialize browser using BrowserSetup class
        driver = BrowserSetup.initializeBrowser("firefox"); // You can change "chrome" to "firefox" or "edge" as needed
        driver.get("https://www.saucedemo.com/");

        // Initialize page objects
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutFirstPage = new CheckoutFirstPage(driver);
        checkoutSecondPage = new CheckOutSecondPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        waitUtils = new WaitUtils(driver, 2);
        sideBar = new SideBar(driver);
    }
    @Test(priority = 3, description = "Review cart")
    @Description("Test Description: Review the cart items and proceed to checkout")
    @Severity(SeverityLevel.NORMAL)
    @Story("Review Cart")
    public void testReviewCart() {
        Assert.assertEquals(cartPage.getSecondTitle(), "Your Cart", "Page title is not correct");

        // This code is for the first and the last item
        // Match the name items
        List<WebElement> cartItems = driver.findElements(cartPage.cartItemNames);
        Assert.assertEquals(cartItems.get(0).getText(), firstItemName, "First item name is not correct");
        Assert.assertEquals(cartItems.get(1).getText(), lastItemName, "Last item name is not correct");

        // Match the price items
        List<WebElement> cartItemPrices = driver.findElements(cartPage.cartItemPrices);
        Assert.assertEquals(cartItemPrices.get(0).getText(), firstItemPrice, "First item price is not correct");
        Assert.assertEquals(cartItemPrices.get(1).getText(), lastItemPrice, "Last item price is not correct");

        cartPage.clickCheckoutButton();
    }
    @Test(priority = 4, description = "Enter checkout information")
    @Description("Test Description: Enter the user information for checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Checkout")
    public void testCheckoutFirstPart() {
        Assert.assertTrue(driver.findElement(checkoutFirstPage.firstNameField).isDisplayed(), "First Name field present");
        Assert.assertTrue(driver.findElement(checkoutFirstPage.lastNameField).isDisplayed(), "Last Name field present");
        Assert.assertTrue(driver.findElement(checkoutFirstPage.postalCodeField).isDisplayed(), "Postal Code field present");
        Assert.assertTrue(driver.findElement(checkoutFirstPage.continueButton).isEnabled(), "Continue button enabled");
        Assert.assertEquals(checkoutFirstPage.getSecondTittle(),"Checkout: Your Information", "Page title is not correct");
        checkoutFirstPage.fillCheckoutInformation(TestData.firstName, TestData.lastName, TestData.postalCode);

        Assert.assertTrue(driver.findElement(checkoutFirstPage.continueButton).isEnabled(), "Finish button not enabled");
        checkoutFirstPage.clickContinueButton();
    }
    @Test(priority = 5, description = "Review order summary")
    @Description("Test Description: Review the order summary and confirm the prices")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Order Summary")
    public void testCheckoutSecondPart() {

        Assert.assertEquals(checkoutSecondPage.getSecondTittle(),"Checkout: Overview", "Page title is not correct");
        // Match the name items
        List<WebElement> cartItems = driver.findElements(checkoutSecondPage.cartItemNames);
        Assert.assertEquals(cartItems.get(0).getText(), firstItemName, "First item name is not correct");
        Assert.assertEquals(cartItems.get(1).getText(), lastItemName, "Last item name is not correct");

        // Match the price items
        List<WebElement> cartItemPrices = driver.findElements(checkoutSecondPage.cartItemPrices);
        Assert.assertEquals(cartItemPrices.get(0).getText(), firstItemPrice, "First item price is not correct");
        Assert.assertEquals(cartItemPrices.get(1).getText(), lastItemPrice, "Last item price is not correct");

        // Obtain the total price displayed on the page
        String totalPriceActualText = driver.findElement(checkoutSecondPage.subTotal).getText();

        // Extract only the numeric value from the total price
        String totalPriceActualValue = totalPriceActualText.replace("Item total: ", "");
        double totalPriceActualDouble = Double.parseDouble(totalPriceActualValue.replace("$", ""));

        // Calculate the expected total price
        double price1 = Double.parseDouble(firstItemPrice.replace("$", ""));
        double price2 = Double.parseDouble(lastItemPrice.replace("$", ""));
        double totalPriceExpected = price1 + price2;

        // Verify that the calculated total price matches the displayed total price
        Assert.assertEquals(totalPriceActualDouble, totalPriceExpected, "Total price does not match the sum of item prices");

        checkoutSecondPage.clickFinishButton();
    }
}