package com.api.models.request;

public class CreateBookingRequest {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesRequest bookingdates;
    private String additionalneeds;

    public CreateBookingRequest(){}
    public CreateBookingRequest(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDatesRequest bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesRequest getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDatesRequest bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "CreateBookingRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

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
