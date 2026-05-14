package com.api.listeners;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ISuite suite) {
        logger.info("Suite Started: {}", suite.getName());
        ExtentReportListener.setupSparkReporter("reports/report.html");
    }
    public void onStart(ITestContext context) {
        logger.info("Test Case Started: {} ", context.getName());
    }

    public void onTestStart(ITestResult result) {
        logger.info("Test Started: {}", result.getName());
        ExtentReportListener.createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: {}", result.getName());
        logger.info("Test Case Description: {}", result.getMethod().getDescription());
        ExtentReportListener.getTest().log(Status.PASS, result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: {}", result.getName());
        logger.error("Exception: ", result.getThrowable());
        ExtentReportListener.getTest().log(Status.FAIL, result.getMethod().getMethodName());
        ExtentReportListener.getTest().log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed: {} ",context.getName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("{} skipped", result.getName());
        ExtentReportListener.getTest().log(Status.SKIP, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Suite Finished: {}", suite.getName());
        ExtentReportListener.flushReport();
        ExtentReportListener.removeTest();
    }
}
