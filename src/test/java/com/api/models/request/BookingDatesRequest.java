package com.api.models.request;


public class BookingDatesRequest {

    private String checkin;
    private String checkout;

    public BookingDatesRequest(){}
    public BookingDatesRequest(String checkin,String checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    @Override
    public String toString() {
        return "BookingDatesRequest{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
