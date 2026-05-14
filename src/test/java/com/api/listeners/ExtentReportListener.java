package com.api.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener {

    private ExtentReportListener() {}
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setupSparkReporter(String reportName){
        ExtentSparkReporter extentSparkReporter = null;
        if(extentReports == null){
            extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "//"+reportName);
        }

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName){
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest(){
        return extentTest.get();
    }

    public static void flushReport(){
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public static void removeTest() {
        extentTest.remove();
    }
}
