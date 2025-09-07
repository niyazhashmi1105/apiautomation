package com.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateBookingResponse {

    private int bookingid;
    private BookingResponse booking;

    public CreateBookingResponse(){}
}
