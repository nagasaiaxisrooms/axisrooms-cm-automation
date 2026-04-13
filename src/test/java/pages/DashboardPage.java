package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
	private WebElement dashboardTitle;
	
	@FindBy(xpath = "//span[contains(text(),'Inventory')]")
	private WebElement inventoryTab;
	
	@FindBy(xpath = "//span[contains(text(),'Pricing')]")
	private WebElement pricingTab;
	
	@FindBy(xpath = "//span[contains(text(),'Bookings')]")
	private WebElement bookingsTab;
	
	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	private WebElement reportsTab;
	
	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	private WebElement settingsTab;
	
	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	private WebElement logoutButton;

	@FindBy(xpath = "//a[contains(text(), 'Channel Manager')]")
	private WebElement channelManagerTab;
	
	@FindBy(xpath = "//a[contains(text(), 'Channel Status') and contains(@href, 'viewArcChannelsStatus')]")
	private WebElement channelStatusOption;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
	}
	
	public boolean isDashboardPage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickInventoryTab() {
		wait.until(ExpectedConditions.elementToBeClickable(inventoryTab));
		inventoryTab.click();
	}
	
	public void clickPricingTab() {
		wait.until(ExpectedConditions.elementToBeClickable(pricingTab));
		pricingTab.click();
	}
	
	public void clickBookingsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(bookingsTab));
		bookingsTab.click();
	}
	
	public void clickReportsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(reportsTab));
		reportsTab.click();
	}
	
	public void clickSettingsTab() {
		wait.until(ExpectedConditions.elementToBeClickable(settingsTab));
		settingsTab.click();
	}
	
	public void clickLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
	}
	
	public void clickChannelManagerTab() {
		wait.until(ExpectedConditions.elementToBeClickable(channelManagerTab));
		channelManagerTab.click();
	}
	
	public void clickChannelStatusOption() {
		wait.until(ExpectedConditions.elementToBeClickable(channelStatusOption));
		channelStatusOption.click();
	}
	
	public String getDashboardTitle() {
		return dashboardTitle.getText();
	}
	
	public void waitForDashboardToLoad() {
		wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
	}
	
	public void navigateToInventory() {
		clickInventoryTab();
		// Add wait for inventory page to load if needed
	}
	
	public void navigateToPricing() {
		clickPricingTab();
		// Add wait for pricing page to load if needed
	}
	
	public void navigateToBookings() {
		clickBookingsTab();
		// Add wait for bookings page to load if needed
	}
	
	public void navigateToReports() {
		clickReportsTab();
		// Add wait for reports page to load if needed
	}
	
	public void navigateToSettings() {
		clickSettingsTab();
		// Add wait for settings page to load if needed
	}
}