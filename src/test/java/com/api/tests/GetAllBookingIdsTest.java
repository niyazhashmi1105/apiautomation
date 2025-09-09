package com.api.tests;

import com.api.base.BookingService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllBookingIdsTest {

    @Test
    public void getAllBookingIdsTest(){

        BookingService bookingService = new BookingService();
        Response response  = bookingService.getAllBookingIds();
        //System.out.println(response.prettyPrint());
        //System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty());
        Assert.assertTrue(bookingIds.size()>0);
    }
}
