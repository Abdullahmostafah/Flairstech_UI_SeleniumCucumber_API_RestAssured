package EndPoints;

import TestBases.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddCandidateAPI extends TestBase {

    @Test
    public void addCandidate() {
        baseURI();
        String requestBody = "{\"firstName\": \"Abdullah\", \"lastName\": \"Mostafa\", \"email\": \"Abdullah@mostafa.com\"}";
        Response response = (Response) given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody).when()
                .post("/api/v2/recruitment/candidates")
                .then().statusCode(201).extract().response();
        response.prettyPrint();

        System.out.println("Candidate added: " + response.getStatusCode());

        candidateID = response.jsonPath().get("data.id");

        if (candidateID != null) {
            System.out.println("Extracted ID: " + candidateID);
            setCandidateID(candidateID);
        } else {
            System.out.println("ID not found in the response.");
        }
    }
}

