package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SyncLogsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators for Sync Logs specifically
    @FindBy(xpath = "//h1[contains(text(),'Logs') or contains(text(),'Sync History')]")
    private WebElement logsTitle;
    
    // Assuming a table structure for logs
    @FindBy(xpath = "//table[@id='syncLogsTable']//tbody//tr")
    private List<WebElement> logRows;
    
    public SyncLogsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
    }
    
    public boolean isLogsPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logsTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getLatestSyncStatus() {
        wait.until(ExpectedConditions.visibilityOfAllElements(logRows));
        if (logRows.isEmpty()) {
            return "No Logs Found";
        }
        // Assuming status is in the 3rd column (adjust as needed based on actual HTML)
        WebElement statusCell = logRows.get(0).findElement(By.xpath("./td[3]"));
        return statusCell.getText().trim();
    }
    
    public String getLatestSyncPayload() {
        wait.until(ExpectedConditions.visibilityOfAllElements(logRows));
        if (logRows.isEmpty()) {
            return "No Logs Found";
        }
        // Assuming payload is in the 4th column (adjust as needed based on actual HTML)
        WebElement payloadCell = logRows.get(0).findElement(By.xpath("./td[4]"));
        return payloadCell.getText().trim();
    }
}
