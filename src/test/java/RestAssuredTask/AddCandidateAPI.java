package RestAssuredTask;

public class AddCandidateAPI {


    public void addCandidate() {
        String candidateData = "{ \"firstName\":\"John\", \"lastName\":\"Doe\", \"email\":\"john.doe@example.com\" }";
        Response response = RestAssured.given()
                .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
                .contentType("application/json")
                .body(candidateData)
                .post("/recruitment/candidates");

        System.out.println("Candidate added: " + response.getStatusCode());
    }
}
