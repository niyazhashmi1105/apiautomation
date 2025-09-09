package com.api.tests;

import com.api.base.BookingService;
import com.api.models.request.BookingDatesRequest;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;


public class CreateBookingTest {


    @Test(description="Create Booking API")
    public void createBooking(){

        BookingDatesRequest bookingDatesRequest = new BookingDatesRequest("2025-09-07","2025-09-16");
        CreateBookingRequest createBookingRequest = new CreateBookingRequest("niyaz","hashmi", 1000,true,bookingDatesRequest,"breakfast");
        BookingService bookingService = new BookingService();
        Response response = bookingService.createBooking(createBookingRequest);
        //System.out.println(Optional.ofNullable(response.jsonPath().get("booking.firstname")).get());
        Assert.assertEquals(Optional.ofNullable(response.jsonPath().get("booking.firstname")).get(),"niyaz");
        //CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
        //System.out.println(Optional.ofNullable(response.jsonPath().get("bookingid")).get());
    }


    @Test(description="Create Booking API using Builder Design Pattern")
    public void createBookingUsingBuilder(){

        CreateBookingRequest createBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                .setFirstname("john")
                .setLastname("doe")
                .setTotalPrice(1000)
                .setDepositPaid(true)
                .setBookingDates("2025-09-01","2025-09-15")
                .setAdditionalNeeds("breakfast").build();

        BookingService bookingService = new BookingService();
        Response response = bookingService.createBooking(createBookingRequest);
        Assert.assertEquals(response.getStatusCode(),200);
        //System.out.println(response.asPrettyString());
        int bookingId = response.jsonPath().getInt("bookingid");
        String firstName = response.jsonPath().get("booking.firstname");
        String lastName = response.jsonPath().get("booking.lastname");
        Assert.assertTrue(bookingId>0);
        Assert.assertEquals(firstName,"john");
        Assert.assertEquals(lastName,"doe");
    }
}
