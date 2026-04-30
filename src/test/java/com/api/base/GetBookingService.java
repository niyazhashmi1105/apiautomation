package com.api.base;

import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.api.tests.CreateBookingTest.bookingId;

public class GetBookingService extends BaseService{

    private static final String BASE_PATH = "/booking/";

    public Response getBookingId(int bookingId){
        return getRequest(BASE_PATH+bookingId);
    }

    public Response getBookingIdWithFirstAndLastNameQueryParams(String firstName, String lastName){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("firstname", firstName);
        queryParams.put("lastname", lastName);
        return getRequestWithQueryParams(BASE_PATH+bookingId,queryParams);
    }

    public Response getBookingIdWithCheckinAndCheckoutQueryParams(String checkin, String checkout){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkin", LocalDate.parse(checkin));
        queryParams.put("checkout", LocalDate.parse(checkout));
        return getRequestWithQueryParams(BASE_PATH+bookingId,queryParams);
    }
}
