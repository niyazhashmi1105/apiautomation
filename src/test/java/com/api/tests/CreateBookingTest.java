package com.api.tests;

import com.api.base.CreateBookingService;
import com.api.models.request.BookingDatesRequest;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;


public class CreateBookingTest extends TestBase{

    public static int bookingId;
    static String fname = getFirstName();
    static String lname = getLastName();
    static int price = getPrice();

    @Test(description="Create Booking API", enabled = false)
    public void createBooking(){

        BookingDatesRequest bookingDatesRequest = new BookingDatesRequest("2026-04-30","2026-05-11");
        CreateBookingRequest createBookingRequest = new CreateBookingRequest(fname,lname, price,true,bookingDatesRequest,"breakfast");
        CreateBookingService bookingService = new CreateBookingService();
        Response response = bookingService.createBooking(createBookingRequest);
        Assert.assertEquals(Optional.ofNullable(response.jsonPath().get("booking.firstname")).get(),fname);
    }


    @Test(description="Create Booking API using Builder Design Pattern")
    public void createBookingUsingBuilder(){

        CreateBookingRequest createBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                .setFirstname(fname)
                .setLastname(lname)
                .setTotalPrice(price)
                .setDepositPaid(true)
                .setBookingDates("2026-04-30","2026-05-11")
                .setAdditionalNeeds("breakfast").build();

        CreateBookingService bookingService = new CreateBookingService();
        Response response = bookingService.createBooking(createBookingRequest);
        Assert.assertEquals(response.getStatusCode(),200);

        bookingId = response.jsonPath().getInt("bookingid");
        if(bookingId == 0 || bookingId < 0){
            System.err.println("Error in generating Booking Id");
        }
        System.out.println("Booking Id: "+bookingId);
        String firstName = response.jsonPath().get("booking.firstname");
        String lastName = response.jsonPath().get("booking.lastname");
        Assert.assertTrue(bookingId>0);
        Assert.assertEquals(firstName,fname);
        Assert.assertEquals(lastName,lname);
    }
}
