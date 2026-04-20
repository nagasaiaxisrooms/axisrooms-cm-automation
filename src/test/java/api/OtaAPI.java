package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OtaAPI {
    private String otaBaseUrl;
    private String partnerToken;
    
    public OtaAPI(String otaBaseUrl, String partnerToken) {
        this.otaBaseUrl = otaBaseUrl;
        this.partnerToken = partnerToken;
    }
    
    public Response getOTAInventory(String otaHotelId, String date) {
        RestAssured.baseURI = otaBaseUrl;
        
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + partnerToken)
                .contentType(ContentType.JSON)
                .queryParam("hotelId", otaHotelId)
                .queryParam("date", date);
                
        // Placeholder for the actual OTA endpoint (e.g., Booking.com Opportunity API) //
        return request.get("/v1/opportunity/inventory");
    }
    
    public Response getOTAPricing(String otaHotelId, String date) {
        RestAssured.baseURI = otaBaseUrl;
        
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + partnerToken)
                .contentType(ContentType.JSON)
                .queryParam("hotelId", otaHotelId)
                .queryParam("date", date);
                
        // Placeholder for actual OTA rate fetching
        return request.get("/v1/opportunity/pricing");
    }
}
