package com.api.base;

import com.api.filters.LoggingFilter;
import com.api.filters.MaskingLoggingFilter;
import com.api.listeners.ExtentReportListener;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class BaseService {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final Logger logger = LogManager.getLogger(BaseService.class);

    private final RequestSpecification requestSpecification;


    static{
        RestAssured.filters(new MaskingLoggingFilter());
    }

    public BaseService() {
        requestSpecification = given().baseUri(BASE_URL);
    }

    protected Response postRequest(Object payload, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
    }

    protected Response getRequest(String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).get(endpoint);
    }

    protected Response getRequestWithQueryParams(String endpoint, Map<String, ?> queryParams) {

        return requestSpecification.contentType(ContentType.JSON).queryParams(queryParams).get(endpoint);
    }

    protected Response putRequest(Object payload,String endpoint,String token) {
        return requestSpecification.contentType(ContentType.JSON).header("Cookie", "token="+token).body(payload).put(endpoint);
    }

    protected Response patchRequest(Object payload,String endpoint,String token) {
        return requestSpecification.contentType(ContentType.JSON).header("Cookie", "token="+token).body(payload).patch(endpoint);
    }

    protected Response deleteRequest(String endpoint,String token) {
        return requestSpecification.contentType(ContentType.JSON).header("Cookie", "token="+token).delete(endpoint);
    }

    public static void extentLog(Status status, String message) {
        logger.info(message);

        ExtentTest test = ExtentReportListener.getTest();
        if (test != null) {
            test.log(status, message);
        }
    }

}
