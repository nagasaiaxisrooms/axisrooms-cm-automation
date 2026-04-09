package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

WebDriver driver;
WebDriverWait wait;

public LoginPage(WebDriver driver){
this.driver = driver;
wait = new WebDriverWait(driver, Duration.ofSeconds(20));
}

/* Locators */

By supportTab = By.xpath("//*[contains(text(),'Support')]");

By passwordLoginToggle = By.id("toggleOtpLink");

By otpLoginToggle = By.id("toggleOtpLink");

By emailField = By.id("emailId");

By passwordField = By.id("password");

By loginWithOtpBtn = By.id("primaryBtnText");

By errorMessage = By.id("mainMsg");

By otp1 = By.id("otp1");

By otp2 = By.id("otp2");

By otp3 = By.id("otp3");

By otp4 = By.id("otp4");

By otp5 = By.id("otp5");

By otp6 = By.id("otp6");

By forgotPasswordLink = By.xpath("//a[contains(text(),'Forgot password')]");


/* Actions */


public void clickSupportTab(){
wait.until(ExpectedConditions.elementToBeClickable(supportTab)).click();
}

public void clickPasswordLogin(){
wait.until(ExpectedConditions.elementToBeClickable(passwordLoginToggle)).click();
}

public void clickOTPLogin(){
wait.until(ExpectedConditions.elementToBeClickable(otpLoginToggle)).click();
}

//public void enterEmail(String email){
//wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys("sai@axisrooms.com");
//}
//
//public void enterPassword(String password){
//wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys("Sai@2965");
//}

public void enterEmail(String email){
System.out.println("Email value = " + email);
wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
    driver.findElement(emailField).sendKeys(email);
}

public void enterPassword(String password){
System.out.println("Password value = " + password);	
wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
    driver.findElement(passwordField).sendKeys(password);
}

public void clickLoginButton(){
wait.until(ExpectedConditions.elementToBeClickable(loginWithOtpBtn)).click();
}


public void enterOTP(String otp) {

   for(int i=1; i<=6; i++) {

	   driver.findElement(By.id("otp"+i))
       	.sendKeys(String.valueOf(otp.charAt(i-1)));
    }
}

public boolean isDashboardPage() {
	return driver.getCurrentUrl().contains("support/home");
}

public void verifyOTP() {
    driver.findElement(By.xpath("//button[contains(text(),'Verify')]")).click();
}


public String getErrorMessage(){

return wait.until(
ExpectedConditions.visibilityOfElementLocated(errorMessage)
).getText();

}

}


//public void clickSupportTab(){
//
//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//wait.until(ExpectedConditions.elementToBeClickable(
//By.xpath("//*[contains(text(),'Support')]")
//)).click();
//
//}
//
//public void clickPasswordLogin() {
//
//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//wait.until(ExpectedConditions.elementToBeClickable(
//By.id("chipPwd")
//)).click();
//
//}
//
//public void clickOTPLogin(){
//wait.until(ExpectedConditions.elementToBeClickable(otpLoginToggle)).click();
//}
//
//public void enterEmail(String email){
//wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
//}
//
//public void enterPassword(String password){
//wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
//}
//

//public void clickLoginWithOTP(){
//wait.until(ExpectedConditions.elementToBeClickable(loginWithOtpBtn)).click();
//}
//
//}
