package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int ticketsReserved;

    public Reservation(Customer customer, Showing showing, int ticketsReserved) {
        this.customer = customer;
        this.showing = showing;
        this.ticketsReserved = ticketsReserved;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double totalFee() {
        return showing.calculateShowingPrice()*ticketsReserved;
    }



}