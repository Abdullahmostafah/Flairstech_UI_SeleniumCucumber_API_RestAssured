package RestAssuredTask;

public class DeleteCandidateAPI {

    public void deleteCandidate(int candidateId) {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
                .delete("/recruitment/candidates/" + candidateId);

        System.out.println("Candidate deleted: " + response.getStatusCode());
    }

}
