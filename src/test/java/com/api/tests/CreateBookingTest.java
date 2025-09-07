package com.api.tests;

import com.api.base.service.CreateBookingService;
import com.api.models.request.BookingDatesRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.response.CreateBookingResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class CreateBookingTest {

    @Test
    public void createBooking(){

        BookingDatesRequest bookingDatesRequest = new BookingDatesRequest("2025-09-07","2025-09-16");
        CreateBookingRequest createBookingRequest = new CreateBookingRequest("niyaz","hashmi", 1000,true,bookingDatesRequest,"breakfast");
        CreateBookingService createBookingService = new CreateBookingService();
        Response response = createBookingService.createBooking(createBookingRequest);
        System.out.println(Optional.ofNullable(response.jsonPath().get("booking.firstname")).get());
        Assert.assertEquals(Optional.ofNullable(response.jsonPath().get("booking.firstname")).get(),"niyaz");
        //CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
        System.out.println(Optional.ofNullable(response.jsonPath().get("bookingid")).get());
    }
}
