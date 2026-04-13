package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChannelStatusPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id = "key-text")
	private WebElement searchBox;
	
	@FindBy(xpath = "//button[contains(text(),'Search Hotel')]")
	private WebElement searchHotelButton;
	
	public ChannelStatusPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
	}
	
	public void enterSearchKey(String key) {
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(key);
	}
	
	public void clickSearchHotel() {
		wait.until(ExpectedConditions.elementToBeClickable(searchHotelButton));
		searchHotelButton.click();
	}
}
