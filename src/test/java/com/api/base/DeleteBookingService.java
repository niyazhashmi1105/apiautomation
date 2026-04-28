package com.api.base;

import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;

public class DeleteBookingService extends BaseService {

    private static final String BASE_PATH = "/booking";


    public Response deleteBooking(int bookingId, String token){
        return deleteRequest(BASE_PATH+"/"+bookingId,token);
    }
}
