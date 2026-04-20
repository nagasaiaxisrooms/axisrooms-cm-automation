package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryAndPricesPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//div[@class='availability_count']//input")
	private WebElement availabilityCountInput;

	@FindBy(xpath = "//button[contains(@class, 'singlePriceUpdateSave')]")
	private WebElement saveButton;
	
	
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
	
}
