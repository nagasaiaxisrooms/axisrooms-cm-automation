package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ChannelStatusPage;
import pages.DashboardPage;
import pages.InventoryAndPricesPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

					
@Test(priority = 1)
public void validLoginTest() throws InterruptedException {
 LoginPage login = new LoginPage(driver);
	
 login.clickSupportTab();
	
// login.clickPasswordLogin();
	
 login.enterEmail("nagasai@axisrooms.com");
 Thread.sleep(1000);
	
 login.enterPassword("Sai@2965");
 Thread.sleep(1000);
	
 login.clickLoginButton();
 Thread.sleep(1000);
 
// System.out.println("Waiting for OTP email...");
//
// Thread.sleep(15000); // wait for OTP mail to arrive
//
// String otp = EmailOTPReaderTest.getOTP();
//
// System.out.println("OTP from Email = " + otp);

 login.enterOTP("123456");

 login.verifyOTP();

 Thread.sleep(5000);

// Assert.assertTrue(login.isDashboardPage());
	
 System.out.println("Valid Login Test Executed");
}

@Test(priority = 2)
public void loginWithUnregisteredEmail() throws InterruptedException {

    LoginPage login = new LoginPage(driver);
    
    login.clickSupportTab();

//    login.clickPasswordLogin();

    login.enterEmail("unknownuser@test.com");
    Thread.sleep(2000);

    login.enterPassword("Password123");
    Thread.sleep(2000);

    login.clickLoginButton();
    Thread.sleep(2000);

    String errorMessage = login.getErrorMessage();

    System.out.println("Error Message: " + errorMessage);

    Assert.assertTrue(errorMessage.contains("Incorrect UserType or Email Address"));

}

@Test(priority = 3)
public void loginWithWrongPassword() throws InterruptedException {

    LoginPage login = new LoginPage(driver);
    
    login.clickSupportTab();

//    login.clickPasswordLogin();

    login.enterEmail("nagasai@axisrooms.com");
    Thread.sleep(2000);

    login.enterPassword("WrongPassword");
    Thread.sleep(2000);

    login.clickLoginButton();
    Thread.sleep(2000);

    String errorMessage = login.getErrorMessage();

    System.out.println("Error Message: " + errorMessage);

    Assert.assertTrue(errorMessage.contains("Incorrect Password"));

}

@Test(priority = 4)
public void validLoginwithOTPLogin() throws InterruptedException {
	LoginPage login = new LoginPage(driver);
	
	 login.clickSupportTab();
	 
	 login.clickPasswordLogin();
		
	 login.enterEmail("nagasai@axisrooms.com");
	 Thread.sleep(2000);
		
	 login.clickLoginButton();
	 Thread.sleep(2000);
	 
//	 System.out.println("Waiting for OTP email...");
//
//	 Thread.sleep(15000); // wait for OTP mail to arrive
//
//	 String otp = EmailOTPReaderTest.getOTP();
//
//	 System.out.println("OTP from Email = " + otp);

	 login.enterOTP("123456");

	 login.verifyOTP();

	 Thread.sleep(5000);

//	 Assert.assertTrue(login.isDashboardPage());
		
	 System.out.println("Valid Login Test Executed");
}

// -------------------------------------------------Inventory & pricing sync-------------------------------------------------------------------------------------

@Test(priority = 5)
public void inventoryAndPricingSyncTest() throws InterruptedException {
	LoginPage login = new LoginPage(driver);
	
	login.clickSupportTab();
	login.enterEmail("nagasai@axisrooms.com");
	Thread.sleep(1000);
	
	login.enterPassword("Sai@2965");
	Thread.sleep(1000);
	
	login.clickLoginButton();
	Thread.sleep(1000);
	
	login.enterOTP("123456");
	login.verifyOTP();
	Thread.sleep(3000);
	
	System.out.println("Navigating to Dashboard and Channel Manager...");
	DashboardPage dashboard = new DashboardPage(driver);
	
	// try {
	// 	dashboard.waitForDashboardToLoad();
	// } catch (Exception e) {
	// 	System.out.println("Dashboard title not visible yet, proceeding...");
	// }
	
	dashboard.clickChannelManagerTab();
	Thread.sleep(2000);
	
	dashboard.clickChannelStatusOption();
	Thread.sleep(3000);
	
	System.out.println("Entering Hotel ID 1388 into search box...");
	ChannelStatusPage channelStatus = new ChannelStatusPage(driver);
	channelStatus.enterSearchKey("1388");
	Thread.sleep(2000);
	
	System.out.println("Clicking Search Hotel Button...");
	channelStatus.clickSearchHotel();
	Thread.sleep(3000);

	System.out.println("Clicking on the searched Hotel Link...");
	channelStatus.clickHotelLink("1388");
	Thread.sleep(3000);

	System.out.println("Hovering over Manage Dropdown...");
	channelStatus.hoverOverManageDropdown();
	Thread.sleep(2000);

	System.out.println("Clicking Inventory and Prices Option...");
	dashboard.clickInventoryAndPricesOption();
	Thread.sleep(3000);
	
	InventoryAndPricesPage inventoryAndPrices = new InventoryAndPricesPage(driver);
	
	// update new value of availability count for specific dates dynamically
	System.out.println("Updating Availability Count for 29 Apr...");
	inventoryAndPrices.updateInventory("29 Apr", "Certification Room1", "44");
	Thread.sleep(1000);

	System.out.println("Updating Availability Count for 30 Apr...");
	inventoryAndPrices.updateInventory("30 Apr", "Certification Room1", "36");
	Thread.sleep(1000);

	System.out.println("Updating Availability Count for 1 May...");
	inventoryAndPrices.updateInventory("1 May", "Certification Room1", "40");
	Thread.sleep(2000);
	
	System.out.println("Clicking Save Button...");
	inventoryAndPrices.clickSaveButton();
	Thread.sleep(5000);

	System.out.println("Closing the Modal...");
	inventoryAndPrices.closeModal();
	Thread.sleep(1000);

	System.out.println("Clicking Toggle Rateplans...");
	inventoryAndPrices.clickToggleRateplans();	
	Thread.sleep(3000);

	// update new value of price for specific dates dynamically
	System.out.println("Updating Price for 29 Apr...");
	inventoryAndPrices.updatePrice("29 Apr", "Certification Room1", "2410");
	Thread.sleep(1000);

	System.out.println("Updating Price for 30 Apr...");
	inventoryAndPrices.updatePrice("30 Apr", "Certification Room1", "2430");
	Thread.sleep(1000);

	System.out.println("Updating Price for 1 May...");
	inventoryAndPrices.updatePrice("1 May", "Certification Room1", "2440");
	Thread.sleep(3000);
	
	// check if save button is displayed then click it else print message
	if(inventoryAndPrices.isSaveButtonDisplayed()) {
		System.out.println("Save Button is displayed");
		System.out.println("Clicking Save Button...");
		inventoryAndPrices.clickSaveButton();
		Thread.sleep(5000);
	} else {
		System.out.println("Save Button is not displayed");
	}

	// System.out.println("Clicking Sync Inv Rates Button...");
	// inventoryAndPrices.clickSyncInvRatesBtn();
	// Thread.sleep(5000);
	
	// System.out.println("Setting Sync Date Range...");
	// inventoryAndPrices.setSyncDateRange("2026/04/29", "2026/05/01");
	// Thread.sleep(2000);
	
	// System.out.println("Clicking Start Sync Button...");
	// inventoryAndPrices.clickStartSyncBtn();
	// Thread.sleep(5000);

	System.out.println("Inventory and Pricing Sync Test Executed");
}




//@Test
//public void verifyLogin(){
//
//LoginPage login = new LoginPage(driver);
//
//login.clickSupportTab();
//
//login.clickPasswordLogin();
//
//login.enterEmail("nagasai@axisrooms.com");
//
//login.enterPassword("Sai@2965");
//
//login.clickLoginButton();
//
//}

}
