package com.jpmc.theater.model;

/**
 * 
 * @author admin
 *
 */
public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    /**
     * 
     * @return
     */
    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
        
    }
}