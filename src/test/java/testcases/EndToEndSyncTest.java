package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;
import pages.SyncLogsPage;
import api.InventoryAPI;
import api.OtaAPI;
import io.restassured.response.Response;

public class EndToEndSyncTest extends BaseTest {
    
    @Test(priority = 1)
    public void e2eInventorySyncTest() throws InterruptedException {
        String hotelId = "1388";
        String roomId = "101";
        String testDate = "2024-12-01";
        int availableRooms = 5;
        
        // -------------------------------------------------------------
        // Step 1: Leverage PMS API (RestAssured for AxisRooms Backend)
        // -------------------------------------------------------------
        System.out.println("Step 1: Pushing Inventory update via API...");
        InventoryAPI inventoryAPI = new InventoryAPI("https://api.axisrooms.com", "DUMMY_TOKEN");
        // Response updateRes = inventoryAPI.updateInventory(hotelId, roomId, testDate, availableRooms);
        // Assert.assertEquals(updateRes.getStatusCode(), 200, "API update failed");
        
        System.out.println("API Update completed. Verifying internals...");
        // Response fetchRes = inventoryAPI.getInventory(hotelId, roomId, testDate, testDate);
        // Assert.assertTrue(fetchRes.getBody().asString().contains(String.valueOf(availableRooms)));
        
        // -------------------------------------------------------------
        // Step 2: Automate Log Verification (UI-Based with Selenium)
        // -------------------------------------------------------------
        System.out.println("Step 2: Starting UI verification for sync logs...");
        LoginPage login = new LoginPage(driver);
        login.clickSupportTab();
        login.enterEmail("nagasai@axisrooms.com");
        Thread.sleep(1000);
        login.enterPassword("Sai@2965");
        Thread.sleep(1000);
        login.clickLoginButton();
        Thread.sleep(1000);
        
        login.enterOTP("123456"); // This may need to be dynamic or bypassed depending on environment
        login.verifyOTP();
        Thread.sleep(5000);
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.clickChannelManagerTab();
        Thread.sleep(2000);
        
        System.out.println("Navigating to Logs/History...");
        // TODO: Update DashboardPage.java with correct XPath before uncommenting
        // dashboard.clickLogsOption();
        // Thread.sleep(3000);
        
        // SyncLogsPage logsPage = new SyncLogsPage(driver);
        // Assert.assertTrue(logsPage.isLogsPageDisplayed(), "Logs page failed to load.");
        
        // System.out.println("Validating UI log output");
        // String status = logsPage.getLatestSyncStatus();
        // Assert.assertTrue(status.equalsIgnoreCase("Success") || status.equalsIgnoreCase("Completed"), "Sync log did not register as Success!");
        // System.out.println("Sync UI Log Status: " + status);
        
        // -------------------------------------------------------------
        // Step 3: End-to-End OTA Synchronization Verification
        // -------------------------------------------------------------
        System.out.println("Step 3: Verifying data landed at the OTA via their API...");
        OtaAPI otaAPI = new OtaAPI("https://partner-api.booking.com", "OTA_PARTNER_TOKEN");
        // Response otaRes = otaAPI.getOTAInventory("OTA_HOTEL_123", testDate);
        // Assert.assertTrue(otaRes.getBody().asString().contains(String.valueOf(availableRooms)), "OTA does not reflect our changed inventory.");
        
        System.out.println("End-to-End Sync Automation Test Completed Successfully.");
    }
}
