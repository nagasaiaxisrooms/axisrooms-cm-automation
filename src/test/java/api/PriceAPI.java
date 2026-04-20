package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class PriceAPI {

    private String baseUrl;
    private String token;

    public PriceAPI(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public Response getPrice(String hotelId, String roomId, String startDate, String endDate) {
        RestAssured.baseURI = baseUrl;

        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .queryParam("hotelId", hotelId)
                .queryParam("roomId", roomId)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate);

        return request.get("/api/v1/price");
    }

    public Response updatePrice(String hotelId, String roomId, String date, double price) {
        RestAssured.baseURI = baseUrl;

        JSONObject requestBody = new JSONObject();
        requestBody.put("hotelId", hotelId);
        requestBody.put("roomId", roomId);
        requestBody.put("date", date);
        requestBody.put("price", price);

        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody.toString());

        return request.post("/api/v1/price/update");
    }
}
