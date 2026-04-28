package com.api.base;

import io.restassured.response.Response;

public class GetBookingService extends BaseService{

    private static final String BASE_PATH = "/booking/";

    public Response getBookingId(int bookingId){
        return getRequest(BASE_PATH+bookingId);
    }
}
