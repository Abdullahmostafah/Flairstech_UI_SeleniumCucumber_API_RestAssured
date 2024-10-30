package EndPoints;

import TestBases.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GetTokenAPI extends TestBase {

    @Test
    public void getToken() {
        baseURI();
        String requestBody = "{\"username\": \"Admin\", \"password\": \"admin123\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/validate")
                .then()
                .statusCode(200)  // Expecting either 200
                .extract().response();
        response.prettyPrint();

        if (response.getStatusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
            setAccessToken(accessToken);
            System.out.println("Access Token: " + accessToken);
        } else
            System.out.println("Error: " + response.jsonPath().getString("message"));
    }

}
