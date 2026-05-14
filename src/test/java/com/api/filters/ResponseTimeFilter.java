package com.api.filters;

import com.api.base.BaseService;
import com.aventstack.extentreports.Status;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResponseTimeFilter implements Filter {

    private static final Logger logger =
            LogManager.getLogger(ResponseTimeFilter.class);

    private static final long THRESHOLD_MS = 2000;

    @Override
    public Response filter(
            FilterableRequestSpecification  requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext context) {

        long startTime = System.currentTimeMillis();
        Response response = context.next(requestSpec, responseSpec);
        long responseTime = System.currentTimeMillis() - startTime;

        if (responseTime > THRESHOLD_MS) {
            logger.warn("⚠️ SLOW API: {} {} took {} ms (threshold: {} ms)",
                    requestSpec.getMethod(),
                    requestSpec.getURI(),
                    responseTime,
                    THRESHOLD_MS);
            BaseService.extentLog(Status.WARNING,
                    "⚠️ Response Time : " + responseTime
                            + " ms  (SLOW — threshold: " + THRESHOLD_MS + " ms)");

        } else {
            logger.info("✅ {} {} responded in {} ms",
                    requestSpec.getMethod(),
                    requestSpec.getURI(),
                    responseTime);
            BaseService.extentLog(Status.INFO,
                    "✅ Response Time : " + responseTime + " ms");
        }

        return response;
    }
}
