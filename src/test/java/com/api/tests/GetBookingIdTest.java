package com.api.tests;

import com.api.base.GetBookingService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.api.tests.CreateBookingTest.bookingId;

public class GetBookingIdTest extends TestBase {


    @Test(description = "Get Booking Id from Create Booking")
    public void getBookingID(){

        GetBookingService getBookingService = new GetBookingService();
        Response getResponse = getBookingService.getBookingId(bookingId);
        Assert.assertEquals(getResponse.getStatusCode(),200);
    }
}
