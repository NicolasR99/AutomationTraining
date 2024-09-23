package Steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.*;
import resources.TestData;
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
public class E2ESteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutFirstPage checkoutFirstPage;
    private CheckOutSecondPage checkoutSecondPage;
    private CheckoutCompletePage checkoutCompletePage;
    private WaitUtils waitUtils;
    private SideBar sideBar;
    private String firstItemName;
    private String lastItemName;
    private String firstItemPrice;
    private String lastItemPrice;

    public E2ESteps() {
        // Initialize the driver and page objects
        driver = BrowserSetup.initializeBrowser("firefox"); // You can change to any browser as needed
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutFirstPage = new CheckoutFirstPage(driver);
        checkoutSecondPage = new CheckOutSecondPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        waitUtils = new WaitUtils(driver, 2);
        sideBar = new SideBar(driver);
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Assert.assertTrue(driver.findElement(loginPage.usernameField).isDisplayed(), "Username field present");
        Assert.assertTrue(driver.findElement(loginPage.passwordField).isDisplayed(), "Password field present");
    }

    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        loginPage.login(TestData.username, TestData.password);
    }

    @Then("I should see the inventory page")
    public void i_should_see_the_inventory_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User is not on the inventory page");
    }

    @When("I add the first and last items to the cart")
    public void i_add_the_first_and_last_items_to_the_cart() {
        inventoryPage.addFirstAndLastItemsToCart();
        firstItemName = inventoryPage.getFirstItemName();
        lastItemName = inventoryPage.getLastItemName();
        firstItemPrice = inventoryPage.getFirstItemPrice();
        lastItemPrice = inventoryPage.getLastItemPrice();

    }
    @When("I Click on Cart Button")
    public void i_go_to_the_cart(){
        inventoryPage.goToCart();
    }
    @Then("I should see {int} items in the cart")
    public void i_should_see_items_in_the_cart(int expectedItems) {
        waitUtils.waitForUrlContains("/cart.html");
        List<WebElement> cartItems = driver.findElements(cartPage.cartItemNames);
        Assert.assertEquals(cartItems.size(), expectedItems, "Number of products in cart is not correct");
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.clickCheckoutButton();
    }

    @When("I enter checkout information")
    public void i_enter_checkout_information() {
        checkoutFirstPage.fillCheckoutInformation(TestData.firstName, TestData.lastName, TestData.postalCode);
        checkoutFirstPage.clickContinueButton();
    }

    @Then("I should see the order summary")
    public void i_should_see_the_order_summary() {
        Assert.assertEquals(checkoutSecondPage.getSecondTittle(), "Checkout: Overview", "Page title is not correct");
        Assert.assertEquals(checkoutSecondPage.cartItemNames.size(), 2, "Cart items are not correct");
    }

    @When("I confirm my order")
    public void i_confirm_my_order() {
        checkoutSecondPage.clickFinishButton();
    }

    @Then("I should see the order confirmation message")
    public void i_should_see_the_order_confirmation_message() {
        Assert.assertEquals(checkoutCompletePage.getConfirmationMessage(), "Thank you for your order!", "Confirmation message is not correct");
    }

    @When("I log out")
    public void i_log_out() {
        sideBar.clickSideBard();
        sideBar.clicklogOutButton();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
