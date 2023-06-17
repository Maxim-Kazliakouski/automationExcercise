package tests.base;

//import adapters.ProjectAPI;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.PropertyReader;
//import utils.TestRunRunner;
//import utils.TestRunsChecker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
//import static steps.Hooks.caseID;
//import static steps.Hooks.TEST CASEName;

@Log4j2

public class TestListener implements ITestListener {
//    static Integer testRunID;
//    String codeProject = PropertyReader.getProperty("codeProject");
//    boolean testRun = Boolean.parseBoolean(PropertyReader.getProperty("testRun"));

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.printf("======================================== STARTING TEST CASE --> '%s' ========================================%n", iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.printf("======================================== FINISHED TEST CASE --> '%s'; Duration: %ss ========================================%n", iTestResult.getMethod().getMethodName(), getExecutionTime(iTestResult));
//        if (testRun && iTestResult.getStatus() == 1) {
//            ProjectAPI api = new ProjectAPI();
//            api.setStatus("passed", codeProject, testRunID, caseID, getExecutionTime(iTestResult));
//        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.printf("======================================== FAILED TEST CASE --> '%s'; Duration: %ss ========================================%n", iTestResult.getMethod().getMethodName(), getExecutionTime(iTestResult));
        log.info(format("========================= FAILED TEST CASE --> '%s'; Duration: %ss =========================", iTestResult.getMethod().getMethodName(), getExecutionTime(iTestResult)));
        log.error(iTestResult.getThrowable().getMessage());
//        if (testRun && iTestResult.getStatus() == 2) {
//            ProjectAPI api = new ProjectAPI();
//            api.setStatus("failed", codeProject, testRunID, caseID, getExecutionTime(iTestResult));
//        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.printf("======================================== SKIPPING TEST CASE --> '%s' ========================================%n", iTestResult.getMethod().getMethodName());
        log.info(format("========================= SKIPPING TEST CASE --> '%s' =========================", iTestResult.getMethod().getMethodName()));
        log.error(iTestResult.getThrowable().getMessage());
//        if (testRun && iTestResult.getStatus() == 4) {
//            ProjectAPI api = new ProjectAPI();
//            api.setStatus("skipped", codeProject, testRunID, caseID, getExecutionTime(iTestResult));
//        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

//    @Override
//    public void onStart(ITestContext iTestContext) {
//        TestRunsChecker testRunsChecker = new TestRunsChecker();
//        TestRunRunner testRunRunner = new TestRunRunner();
//        testRunsChecker.isTestRunCreated();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        testRunID = testRunRunner.launchTestRun(format("TestRun -- %s", dtf.format(now)));
//    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}