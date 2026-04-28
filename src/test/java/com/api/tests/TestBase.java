package com.api.tests;

import com.api.base.AuthService;
import com.api.base.BookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    protected static String token = null;
    protected static int bookingId = 0;
    Faker faker = new Faker();
    protected  String firstName = faker.name().firstName();
    protected String lastName = faker.name().lastName();
    protected int price = faker.number().numberBetween(1,1000);

    @BeforeSuite
    public void setup(){

            AuthService authService = new AuthService();
            Response authResponse = authService.createToken(new AuthRequest("admin","password123"));
            token = authResponse.jsonPath().getString("token");
            //System.out.println(token);
            CreateBookingRequest createBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                    .setFirstname(firstName)
                    .setLastname(lastName)
                    .setTotalPrice(price)
                    .setDepositPaid(true)
                    .setBookingDates("2026-04-28","2026-04-29")
                    .setAdditionalNeeds("breakfast").build();

            BookingService bookingService = new BookingService();
            Response response = bookingService.createBooking(createBookingRequest);
            Assert.assertEquals(response.getStatusCode(),200);
            System.out.println(response.asPrettyString());
            bookingId = response.jsonPath().getInt("bookingid");
            String actualFirstName = response.jsonPath().get("booking.firstname");
            String actualLastName = response.jsonPath().get("booking.lastname");
            Assert.assertEquals(actualFirstName,firstName);
            Assert.assertEquals(actualLastName,lastName);
    }
}
