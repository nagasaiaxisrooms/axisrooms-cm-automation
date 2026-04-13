package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class InventoryAPI {

    private String baseUrl;
    private String token;

    public InventoryAPI(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public Response getInventory(String hotelId, String roomId, String startDate, String endDate) {
        RestAssured.baseURI = baseUrl;

        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .queryParam("hotelId", hotelId)
                .queryParam("roomId", roomId)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate);

        return request.get("/api/v1/inventory");
    }

    public Response updateInventory(String hotelId, String roomId, String date, int availableRooms) {
        RestAssured.baseURI = baseUrl;

        JSONObject requestBody = new JSONObject();
        requestBody.put("hotelId", hotelId);
        requestBody.put("roomId", roomId);
        requestBody.put("date", date);
        requestBody.put("availableRooms", availableRooms);

        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody.toString());

        return request.post("/api/v1/inventory/update");
    }

    public Response getRoomTypes(String hotelId) {
        RestAssured.baseURI = baseUrl;

        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .queryParam("hotelId", hotelId);

        return request.get("/api/v1/roomtypes");
    }
}
