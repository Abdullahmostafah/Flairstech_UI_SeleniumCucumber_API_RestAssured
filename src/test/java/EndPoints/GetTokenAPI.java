package EndPoints;

import Base.APITestBase;
import Utils.ConfigReaderWriter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static Utils.ConfigReaderWriter.getPropKey;

public class GetTokenAPI extends APITestBase {
    @Test
    public void getToken() {
        String requestBody = String.format(
                "{\"username\": \"%s\", \"password\": \"%s\"}",
                getPropKey("api.username"),
                getPropKey("api.password")
        );

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().response();

        // Handle unexpected status codes
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to get token. Status: " + response.getStatusCode() + ", Response: " + response.getBody().asString());
        }

        String token = response.jsonPath().getString("data.token");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not found in response: " + response.getBody().asString());
        }

        setAccessToken(token);
        ConfigReaderWriter.setPropKey("accessToken", token);
        System.out.println(token);
    }
}