package com.api.filters;

import com.api.base.BaseService;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MaskingLoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(MaskingLoggingFilter.class);
    private static final Set<String> SENSITIVE_FIELDS = Set.of("password", "token", "authorization");
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {

        logRequest(filterableRequestSpecification);
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        logResponse(response);
        return response;
    }

    private void logRequest(FilterableRequestSpecification filterableRequestSpecification) {

        logger.info("BASE URI: {}", filterableRequestSpecification.getBaseUri());
        BaseService.extentLog(Status.INFO,"BASE URI: "+filterableRequestSpecification.getBaseUri());
        BaseService.extentLog(Status.INFO,"Request Method: "+ filterableRequestSpecification.getMethod());

        // Mask Headers
        Map<String, String> headers = new HashMap<>();
        filterableRequestSpecification.getHeaders().forEach(h -> {
            if (isSensitive(h.getName())) {
                headers.put(h.getName(), "****");
            }
            else if (h.getName().equalsIgnoreCase("Cookie")) {
                String value = maskCookie(h.getValue());
                headers.put(h.getName(), value);
            }
            else {
                headers.put(h.getName(), h.getValue());
            }
        });
        logger.info("Headers: " + headers);
        BaseService.extentLog(Status.INFO,"Request Headers: "+headers);

        // Mask Body
        if (filterableRequestSpecification.getBody() != null) {
            String rawBody = filterableRequestSpecification.getBody();
            String maskedBody = maskBody(rawBody);
            String prettyBody = prettyPrint(maskedBody);
            logger.info("Request Body:\n" + prettyBody);
            BaseService.extentLog(Status.INFO, "<pre>" + prettyBody + "</pre>");
        }
    }

    private void logResponse(Response response) {
        logger.info("===== RESPONSE =====");
        logger.info("Status: " + response.getStatusCode());
        BaseService.extentLog(Status.INFO,"Status: " +response.getStatusCode());

        String rawBody = response.getBody().asString();
        String maskedBody = maskBody(rawBody);
        String prettyBody = prettyPrint(maskedBody);
        logger.info("Response Body:\n" + prettyBody);
        BaseService.extentLog(Status.INFO, "<pre>" + prettyBody + "</pre>");
    }

    private boolean isSensitive(String key) {
        return SENSITIVE_FIELDS.contains(key.toLowerCase());
    }

    private String maskBody(String body) {
        for (String field : SENSITIVE_FIELDS) {
            body = body.replaceAll(
                    "(\"" + field + "\"\\s*:\\s*\")(.*?)(\")",
                    "$1****$3"
            );
        }
        return body;
    }

    private String maskCookie(String cookieValue) {
        if (cookieValue == null) return null;
        return cookieValue.replaceAll("(token=)([^;]+)", "$1****");
    }

    private String prettyPrint(String json) {
        try {
            Object jsonObj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
        } catch (Exception e) {
            return json;
        }
    }
}
