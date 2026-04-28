package com.api.base;

import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;

public class PartialUpdateService extends BaseService{

    private static final String BASE_PATH = "/booking";

    public Response partialUpdateBooking(CreateBookingRequest payload, int bookingId, String token){
        return patchRequest(payload,BASE_PATH+"/"+bookingId,token);
    }
}
