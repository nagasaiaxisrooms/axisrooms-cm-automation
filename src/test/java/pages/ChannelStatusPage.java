package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	// Element logic moved into the method directly to ensure we find the visible one
	
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
	
	public void clickHotelLink(String hotelId) {
		WebElement hotelLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'viewArcProductDetails.html?id=" + hotelId + "')]")));
		hotelLink.click();
	}

	public void hoverOverManageDropdown() {
		// Using a more robust XPath that includes the child <i> tag and using visibilityOfElementLocated 
		// so it keeps searching until a visible element matching the path is found.
		By manageLocator = By.xpath("//li[@menu-index='0' and contains(@class, 'active')]/a[contains(., 'Manage')]");
		WebElement visibleManageDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(manageLocator));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(visibleManageDropdown).perform();
	}
}
