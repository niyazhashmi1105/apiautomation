package com.api.tests;

import com.api.base.AuthService;
import com.api.base.BookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    public static String token = null;
    public static int bookingId = 0;

    @BeforeSuite
    public void setup(){
            AuthService authService = new AuthService();
            Response authResponse = authService.createToken(new AuthRequest("admin","password123"));
            token = authResponse.jsonPath().getString("token");
            //System.out.println(token);

            CreateBookingRequest createBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                    .setFirstname("john")
                    .setLastname("doe")
                    .setTotalPrice(1000)
                    .setDepositPaid(true)
                    .setBookingDates("2026-04-28","2026-04-29")
                    .setAdditionalNeeds("breakfast").build();

            BookingService bookingService = new BookingService();
            Response response = bookingService.createBooking(createBookingRequest);
            Assert.assertEquals(response.getStatusCode(),200);
            System.out.println(response.asPrettyString());
            bookingId = response.jsonPath().getInt("bookingid");
    }
}
