package com.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CreateBookingRequest {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesRequest bookingdates;
    private String additionalneeds;

    public static class CreateBookingRequestBuilder{

        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        public BookingDatesRequest bookingdates;
        private String additionalneeds;

        public CreateBookingRequestBuilder setFirstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public CreateBookingRequestBuilder setLastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public CreateBookingRequestBuilder setTotalPrice(int totalprice){
            this.totalprice = totalprice;
            return this;
        }

        public CreateBookingRequestBuilder setDepositPaid(boolean depositpaid){
            this.depositpaid = depositpaid;
            return this;
        }

        public CreateBookingRequestBuilder setBookingDates(String checkin, String checkout) {
            this.bookingdates = new BookingDatesRequest(checkin, checkout);
            return this;
        }

        public CreateBookingRequestBuilder setAdditionalNeeds(String additionalneeds){
            this.additionalneeds = additionalneeds;
            return this;
        }

        public CreateBookingRequest build(){
            return new CreateBookingRequest(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        }
    }

}
