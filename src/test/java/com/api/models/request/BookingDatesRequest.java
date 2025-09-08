package com.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class BookingDatesRequest {

    private String checkin;
    private String checkout;

}
