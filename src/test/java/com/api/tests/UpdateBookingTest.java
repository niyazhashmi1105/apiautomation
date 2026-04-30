package com.api.tests;

import com.api.base.UpdateBookingService;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.tests.CreateBookingTest.*;


public class UpdateBookingTest extends TestBase{

    @Test(description = "Update Booking by passing Booking Id to the endpoint")
    public void updateBooking(){

        UpdateBookingService updateBookingService = new UpdateBookingService();
        CreateBookingRequest updateBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                .setFirstname(fname)
                .setLastname(lname)
                .setTotalPrice(price)
                .setDepositPaid(true)
                .setBookingDates("2026-04-28","2026-04-29")
                .setAdditionalNeeds("breakfast").build();
        Response putResponse = updateBookingService.updateBooking(updateBookingRequest,bookingId,token);
        String firstName = putResponse.jsonPath().get("firstname");
        String lastName = putResponse.jsonPath().get("lastname");
        Assert.assertEquals(firstName,fname);
        Assert.assertEquals(lastName,lname);
        Assert.assertEquals(putResponse.getStatusCode(),200);

    }
}
