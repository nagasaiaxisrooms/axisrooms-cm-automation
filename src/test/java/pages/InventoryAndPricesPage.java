package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

public class InventoryAndPricesPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//div[@class='availability_count']//input")
	private WebElement availabilityCountInput;

	@FindBy(xpath = "//button[contains(@class, 'singlePriceUpdateSave')]")
	private WebElement saveButton;
	
	// @FindBy(xpath = "//button[@data-dismiss='modal' and contains(@class, 'close-modal')]")
	@FindBy(xpath = "//button[contains(@class, 'close-modal')]")
	private WebElement closeModalButton;

	@FindBy(xpath = "//a[contains(@class, 'rates-list-toggle-btn')]")
	private WebElement toggleRateplans;

	@FindBy(xpath = "//button[@onclick='openSyncInventoryAndRatesPopup();']") 
	private WebElement syncInvRatesBtn;

	@FindBy(xpath = "//input[contains(@class, 'start-date')]")
	private WebElement startDateInput;

	@FindBy(xpath = "//input[contains(@class, 'end-date')]")
	private WebElement endDateInput;

	@FindBy(xpath = "//button[contains(@class, 'start-sync-fn-btn')]")
	private WebElement startSyncBtn;


	public InventoryAndPricesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
	}
	
	public void clickAvailabilityCountInput() {
		wait.until(ExpectedConditions.elementToBeClickable(availabilityCountInput));
		availabilityCountInput.click();
	}

	public void updateAvailabilityCount(String count) {
		wait.until(ExpectedConditions.visibilityOf(availabilityCountInput));
		availabilityCountInput.clear();
		availabilityCountInput.sendKeys(count);
	}

	public void updateInventory(String date, String roomName, String count) {
		String dynamicXPath = String.format("//div[contains(@class, 'availability_count')]//input[contains(@data-original-title, '%s') and contains(@data-original-title, '%s')]", date, roomName);
		WebElement dynamicInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
		dynamicInput.clear();
		dynamicInput.sendKeys(count);
	}

	public void clickSaveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		saveButton.click();
	}

	public void closeModal() {
		List<WebElement> closeButtons = driver.findElements(By.xpath("//button[contains(@class, 'close-modal') or contains(@data-dismiss, 'modal')]"));
		for (WebElement btn : closeButtons) {
			if (btn.isDisplayed()) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(btn));
					btn.click();
					return;
				} catch (Exception e) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", btn);
					return;
				}
			}
		}
		// Fallback to JS click on the first found button if none seem displayed
		if (!closeButtons.isEmpty()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", closeButtons.get(0));
		}
	}

	public void clickToggleRateplans() {
		wait.until(ExpectedConditions.elementToBeClickable(toggleRateplans));
		toggleRateplans.click();
	}

	public void updatePrice(String date, String roomName, String price) {
		String dynamicXPath = String.format("//div[@id='occ_2']//div[contains(@title, '%s') and contains(@title, '%s')]//input[contains(@class, 'occ-level-price')]", date, roomName);
		WebElement dynamicInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
		dynamicInput.clear();
		dynamicInput.sendKeys(price);
	}

	public boolean isSaveButtonDisplayed() {
		return saveButton.isDisplayed();
	}

	public void clickSyncInvRatesBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(syncInvRatesBtn));
		syncInvRatesBtn.click();
	}

	public void setSyncDateRange(String startDate, String endDate) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		wait.until(ExpectedConditions.visibilityOf(startDateInput));
		// Use JS to set the value directly since it might be a readonly datepicker
		js.executeScript("arguments[0].value='" + startDate + "';", startDateInput);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", startDateInput);
		
		wait.until(ExpectedConditions.visibilityOf(endDateInput));
		js.executeScript("arguments[0].value='" + endDate + "';", endDateInput);
		js.executeScript("arguments[0].dispatchEvent(new Event('change'));", endDateInput);
	}

	public void clickStartSyncBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(startSyncBtn));
		startSyncBtn.click();
	}
	
}
