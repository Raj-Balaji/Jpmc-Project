package com.jpmc.theater;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    private int availableSeats;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime, int availableSeats) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.availableSeats=availableSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public int movieIsSpecial()
    {
        return movie.getSpecialCode();
    }

    public boolean seatsAvailableToReserve(int seatsReserved){

        if( (availableSeats-seatsReserved )<0)
            return false;
        else {
            availableSeats=availableSeats-seatsReserved;
            return true;
        }
    }
    public void deductSeats(int numTickets)
    {
        availableSeats= availableSeats-numTickets;
    }
    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }
    public double getBaseMovieFee() {
        return movie.getBaseTicketPrice();
    }
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }
    public double calculateShowingPrice(){

        List<Double> discount = new ArrayList<>();
        LocalDateTime discountStart= LocalDateTime.of(LocalDate.now(),LocalTime.of(11,0));
        LocalDateTime discountEnd= LocalDateTime.of(LocalDate.now(),LocalTime.of(15,0));
        if(getSequenceOfTheDay()==1)     // First Movie of the Day
        {
            discount.add(3.0);
        }
        else if(getSequenceOfTheDay()==2)    // 2nd movie of the day
        {
          discount.add(2.0);
        }
        if(movieIsSpecial() == 1)     // Special Movie
        {
            discount.add(getBaseMovieFee()*.20);
        }
        if(getSequenceOfTheDay()==7)     // Movies showing on the 7th day of the Month
        {

            discount.add(1.0);
        }
        if(showStartTime.isAfter(discountStart) && showStartTime.isBefore(discountEnd))   //
        {
            discount.add(getBaseMovieFee()*.25);
        }
        discount.add(0.0);
        return getBaseMovieFee()- Collections.max(discount);

    }




}
