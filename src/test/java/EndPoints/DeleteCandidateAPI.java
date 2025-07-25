package EndPoints;

import Base.APITestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;

public class DeleteCandidateAPI extends APITestBase {
    @Test(dependsOnMethods = {"EndPoints.AddCandidateAPI.addCandidate"})
    public void deleteCandidate() {
        if (getCandidateID() == null) {
            throw new IllegalStateException("Candidate ID is not set for deletion");
        }

        Response response = getAuthenticatedRequest()
                .when()
                .delete("/recruitment/candidates/" + getCandidateID())
                .then()
                .statusCode(200)
                .extract().response();

        // Validate response body (assuming API returns a confirmation)
        assertTrue(response.jsonPath().getBoolean("success") || response.getBody().asString().isEmpty(),
                "Expected successful deletion but received: " + response.getBody().asString());

        // Handle unexpected status codes
        if (response.getStatusCode() == 401) {
            throw new IllegalStateException("Authentication failed: Invalid or expired access token");
        } else if (response.getStatusCode() == 404) {
            throw new IllegalStateException("Candidate with ID " + getCandidateID() + " not found");
        }
    }
}