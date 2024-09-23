package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class SideBar {
    WebDriver driver;
    public By sideBarButton = By.id("react-burger-menu-btn");
    public By logOutButton = By.id("logout_sidebar_link");
    public SideBar(WebDriver driver) {
        this.driver = driver;
    }
    public void clickSideBard() {
        driver.findElement(sideBarButton).click();
    }
    public void clicklogOutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton)).click();
    }
}
