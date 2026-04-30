package com.api.tests;

import com.api.base.GetBookingService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.tests.CreateBookingTest.*;

public class GetBookingIdTest extends TestBase {


    @Test(description = "Get Booking Id from Create Booking")
    public void getBookingID(){

        GetBookingService getBookingService = new GetBookingService();
        Response getResponse = getBookingService.getBookingId(bookingId);
        Assert.assertEquals(getResponse.getStatusCode(),200);
    }

    @Test(description= "Filter Response Data based on First and Last names passed as Query Parameters")
    public void getAllBookingIdsTestWithQueryParamsFirstNameLastName(){

        GetBookingService getBookingService = new GetBookingService();
        Response response  = getBookingService.getBookingIdWithFirstAndLastNameQueryParams(fname,lname);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
        String firstName = response.jsonPath().getString("firstname");
        String lastName = response.jsonPath().getString("lastname");
        Assert.assertEquals(firstName,fname);
        Assert.assertEquals(lastName,lname);

    }

    @Test(description= "Filter Response Data based on Checkin and Checkout Dates passed as Query Parameters")
    public void getAllBookingIdsTestWithQueryParamsCheckinAndCheckoutDates(){

        GetBookingService getBookingService = new GetBookingService();
        Response response  = getBookingService.getBookingIdWithCheckinAndCheckoutQueryParams("2026-04-30","2026-05-11");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
        String checkin = response.jsonPath().getString("bookingdates.checkin");
        String checkout = response.jsonPath().getString("bookingdates.checkout");
        Assert.assertEquals(checkin,"2026-04-30");
        Assert.assertEquals(checkout,"2026-05-11");

    }
}
