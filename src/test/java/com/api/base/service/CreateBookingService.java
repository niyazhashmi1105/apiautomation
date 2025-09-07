package com.api.base.service;

import io.restassured.response.Response;

public class CreateBookingService extends BaseService{


    private static final String BASE_PATH = "/booking";

    public Response createBooking(Object payload){
        return postRequest(payload,BASE_PATH);
    }

}
