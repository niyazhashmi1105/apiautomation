package com.api.tests;
import com.api.base.PartialUpdateService;
import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.api.tests.CreateBookingTest.*;

public class PartialUpdateBookingTest extends TestBase {

    @Test(description = "Update Booking by passing Booking Id to the endpoint")
    public void partialUpdateBooking(){

        PartialUpdateService partialUpdateService = new PartialUpdateService();
        CreateBookingRequest partialBookingRequest = new CreateBookingRequest.CreateBookingRequestBuilder()
                .setFirstname(fname)
                .setLastname(lname)
                .setBookingDates("2026-04-28","2026-04-29")
                .build();
        Response patchResponse = partialUpdateService.partialUpdateBooking(partialBookingRequest,bookingId,token);
        String firstName = patchResponse.jsonPath().get("firstname");
        String lastName = patchResponse.jsonPath().get("lastname");
        Assert.assertEquals(firstName,fname);
        Assert.assertEquals(lastName,lname);
        Assert.assertEquals(patchResponse.getStatusCode(),200);

    }
}
