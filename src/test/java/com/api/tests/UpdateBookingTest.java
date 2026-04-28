package com.api.tests;

import com.api.base.UpdateBookingService;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UpdateBookingTest extends TestBase{

    @Test(description = "Update Booking by passing Booking Id to the endpoint")
    public void updateBooking(){

        String fakerFirstName = faker.name().firstName();
        String fakerLastName = faker.name().lastName();
        UpdateBookingService updateBookingService = new UpdateBookingService();
        CreateBookingRequest updateBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                .setFirstname(fakerFirstName)
                .setLastname(fakerLastName)
                .setTotalPrice(price)
                .setDepositPaid(true)
                .setBookingDates("2026-04-28","2026-04-29")
                .setAdditionalNeeds("breakfast").build();
        Response putResponse = updateBookingService.updateBooking(updateBookingRequest,bookingId,token);
        String firstName = putResponse.jsonPath().get("firstname");
        String lastName = putResponse.jsonPath().get("lastname");
        Assert.assertEquals(firstName,fakerFirstName);
        Assert.assertEquals(lastName,fakerLastName);
        Assert.assertEquals(putResponse.getStatusCode(),200);

    }
}
