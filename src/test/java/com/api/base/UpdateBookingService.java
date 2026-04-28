package com.api.base;

import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;

public class UpdateBookingService extends BaseService{

    private static final String BASE_PATH = "/booking";


    public Response updateBooking(CreateBookingRequest payload,int bookingId,String token){
        return putRequest(payload,BASE_PATH+"/"+bookingId,token);
    }
}
