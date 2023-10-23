package utils;

import adapters.ProjectAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.QaseProject.APIResponse;
import dto.QaseProject.Project;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestRunsChecker {
    public void isTestRunCreated() {
        boolean isTestRun = Boolean.parseBoolean(System.getProperty("testRun"));
        boolean isAPI = Boolean.parseBoolean(PropertyReader.getProperty("api"));
        if (!isAPI && isTestRun) {
            Gson gson = new Gson();
            ProjectAPI projectAPI = new ProjectAPI();
            Response response = projectAPI.getAllRuns(PropertyReader.getProperty("codeProject"));
            APIResponse<Project> result = gson.fromJson(response.asString(),
                    new TypeToken<APIResponse<Project>>() {
                    }.getType());
            int count = result.getResult().getCount();
            if (count != 0) {
                int testRunID = result.getResult().entities.get(0).getId();
                projectAPI.deleteTestRun(PropertyReader.getProperty("codeProject"), testRunID);
                log.info("<----> Test run has been deleted! <---->");
                System.out.println("<----> Test run has been deleted! <---->");
            } else {
                System.out.println("There is no created test runs");
                log.info("<----> Test run hasn't been created yet! <---->");
            }
        }
    }
}