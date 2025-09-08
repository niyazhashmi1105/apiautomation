package com.api.base.service;

import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;

public class BookingService extends BaseService {

    private static final String BASE_PATH = "/booking";

    public Response getAllBookingIds(){
        return getRequest(BASE_PATH);
    }

    public Response createBooking(CreateBookingRequest payload){
        return postRequest(payload,BASE_PATH);
    }
}
