package com.api.tests;

import com.api.base.DeleteBookingService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DeleteBookingTest extends TestBase{

    @Test(description= "Delete Booking By Id")
    public void deleteBooking(){

        DeleteBookingService deleteBookingService = new DeleteBookingService();
        Response deleteResponse = deleteBookingService.deleteBooking(bookingId,token);
        Assert.assertEquals(deleteResponse.getStatusCode(),201);

    }

}
