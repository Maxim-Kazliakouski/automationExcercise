package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectAPI {
    Gson gson;
    public static final String URL_API = PropertyReader.getProperty("qase.url.api");

    public ProjectAPI() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200)
        );
    }

    public Response createTestRun(String codeProject, String testRunName) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", testRunName);
        data.put("include_all_cases", true);
        return given()
                .body(data)
                .when()
                .post("/v1/run/" + codeProject)
                .then()
                .extract().response();
    }

    public ValidatableResponse deleteTestRun(String codeProject, int idTestRun) {
        return
                given()
                        .when()
                        .delete("/v1/run/{code}/{id}", codeProject, idTestRun)
                        .then();
    }

    public Response getAllRuns(String projectCode) {
        return RestAssured
                .when()
                .get("/v1/run/" + projectCode)
                .then()
                .extract().response();
    }

    public void setStatus(String status,
                          String codeProject,
                          Integer idTestRun,
                          String idTestCase,
                          Long time) {
        Map<String, Object> data = new HashMap<>();
        data.put("case_id", idTestCase);
        data.put("status", status);
        data.put("time", time);
        given()
                .body(data)
                .when()
                .post("/v1/result/{code}/{id}", codeProject, idTestRun)
                .then();
    }
}