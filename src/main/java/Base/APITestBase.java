package Base;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import Utils.ConfigReaderWriter;

public class APITestBase {
    protected static String accessToken;
    protected static Integer candidateID;
    protected RequestSpecification requestSpec;

    public APITestBase() {
        configureRestAssured();
    }

    private void configureRestAssured() {
        baseURI = ConfigReaderWriter.getPropKey("baseURI");
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(RestAssuredConfig.config()
                        .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .build();
    }

    public RequestSpecification getAuthenticatedRequest() {
        // Use static accessToken if available, otherwise try Properties
        if (accessToken == null) {
            try {
                accessToken = ConfigReaderWriter.getPropKey("accessToken");
            } catch (RuntimeException e) {
                throw new IllegalStateException("Access token is not set in either APITestBase or config.properties");
            }
        }
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken);
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String token) {
        APITestBase.accessToken = token;
        ConfigReaderWriter.setPropKey("accessToken", token); // Sync with Properties
    }

    public static Integer getCandidateID() {
        return candidateID;
    }

    public static void setCandidateID(Integer id) {
        APITestBase.candidateID = id;
    }
}