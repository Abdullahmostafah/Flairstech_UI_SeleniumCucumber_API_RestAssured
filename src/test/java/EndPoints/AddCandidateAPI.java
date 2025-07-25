package EndPoints;

import Base.APITestBase;
import Utils.ConfigReaderWriter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class AddCandidateAPI extends APITestBase {
    @Test(dependsOnMethods = {"EndPoints.GetTokenAPI.getToken"})
    public void addCandidate() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("firstName", "Abdullah");
        requestBody.put("lastName", "Mostafa");
        requestBody.put("email", "Abdullah@mostafa.com");

        // Validate request body
        if (!requestBody.containsKey("firstName") || !requestBody.containsKey("lastName") || !requestBody.containsKey("email")) {
            throw new IllegalArgumentException("Request body missing required fields: firstName, lastName, email");
        }
        if (requestBody.get("firstName").isEmpty() || requestBody.get("lastName").isEmpty() || requestBody.get("email").isEmpty()) {
            throw new IllegalArgumentException("Request body fields cannot be empty");
        }

        Response response = getAuthenticatedRequest()
                .body(requestBody)
                .when()
                .post("/recruitment/candidates")
                .then()
                .statusCode(201)
                .extract().response();

        // Validate response body
        assertNotNull(response.jsonPath().get("data.id"), "Candidate ID not returned in response");
        assertEquals(response.jsonPath().getString("data.firstName"), "Abdullah", "First name mismatch");
        assertEquals(response.jsonPath().getString("data.lastName"), "Mostafa", "Last name mismatch");
        assertEquals(response.jsonPath().getString("data.email"), "Abdullah@mostafa.com", "Email mismatch");

        // Handle unexpected status codes
        if (response.getStatusCode() == 401) {
            throw new IllegalStateException("Authentication failed: Invalid or expired access token");
        } else if (response.getStatusCode() == 400) {
            throw new IllegalStateException("Bad request: " + response.getBody().asString());
        }

        Integer id = response.jsonPath().getInt("data.id");
        setCandidateID(id);
        ConfigReaderWriter.setDynamicValue("candidateID", id.toString());
    }
}