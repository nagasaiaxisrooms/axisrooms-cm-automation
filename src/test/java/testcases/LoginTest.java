package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import pages.ChannelStatusPage;
import utilities.EmailOTPReaderTest;

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
	Thread.sleep(5000);
	
	System.out.println("Navigating to Dashboard and Channel Manager...");
	DashboardPage dashboard = new DashboardPage(driver);
	
	dashboard.clickChannelManagerTab();
	Thread.sleep(2000);
	
	dashboard.clickChannelStatusOption();
	Thread.sleep(5000); // wait a bit more for page load
	
	System.out.println("Entering Hotel ID 1388 into search box...");
	ChannelStatusPage channelStatus = new ChannelStatusPage(driver);
	channelStatus.enterSearchKey("1388");
	Thread.sleep(2000);
	
	System.out.println("Clicking Search Hotel Button...");
	channelStatus.clickSearchHotel();
	Thread.sleep(3000);
	
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
