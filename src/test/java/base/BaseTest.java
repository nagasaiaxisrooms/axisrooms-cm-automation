package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

public WebDriver driver;

@BeforeMethod
public void setup(){
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver();

driver.manage().window().maximize();

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

driver.get("https://sandbox.axisrooms.com/");

}

@AfterMethod
public void tearDown(){

 if(driver != null){
        driver.quit();
    }

}

}
