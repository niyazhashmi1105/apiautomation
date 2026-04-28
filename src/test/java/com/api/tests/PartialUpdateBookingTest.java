package com.api.tests;
import com.api.base.PartialUpdateService;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PartialUpdateBookingTest extends TestBase {

    @Test(description = "Update Booking by passing Booking Id to the endpoint")
    public void partialUpdateBooking(){

        String fakerFirstName = faker.name().firstName();
        String fakerLastName = faker.name().lastName();
        PartialUpdateService partialUpdateService = new PartialUpdateService();
        CreateBookingRequest partialBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                .setFirstname(fakerFirstName)
                .setLastname(fakerLastName)
                .setBookingDates("2026-04-28","2026-04-29")
                .build();
        Response patchResponse = partialUpdateService.partialUpdateBooking(partialBookingRequest,bookingId,token);
        String firstName = patchResponse.jsonPath().get("firstname");
        String lastName = patchResponse.jsonPath().get("lastname");
        Assert.assertEquals(firstName,fakerFirstName);
        Assert.assertEquals(lastName,fakerLastName);
        Assert.assertEquals(patchResponse.getStatusCode(),200);

    }
}
