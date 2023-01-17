package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", 1);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.0, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0)),100
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 27.0);

    }

    @Test
    void initialize(){
        var customer = new Customer("John Doe",1);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.0, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0)),100
        );
        var reservation = new Reservation(customer, showing,10);
        assertNotNull(reservation);
    }
}
