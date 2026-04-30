package com.api.tests;

import com.api.base.CreateBookingService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllBookingIdsTest {

    @Test
    public void getAllBookingIdsTest(){

        CreateBookingService bookingService = new CreateBookingService();
        Response response  = bookingService.getAllBookingIds();
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids: "+bookingIds);
        System.out.println("Booking Ids Size: "+bookingIds.size());
        Assert.assertFalse(bookingIds.isEmpty());
        Assert.assertTrue(bookingIds.size()>0);
    }
}
