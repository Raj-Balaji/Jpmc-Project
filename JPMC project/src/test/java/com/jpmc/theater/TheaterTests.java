package com.jpmc.theater;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TheaterTests {

    Theater theater;
    Customer john;
    @BeforeAll
    void setUp(){
        theater = new Theater(LocalDateProvider.singleton());
        john = new Customer("John Doe", 12345);
        theater.reserve(john,1,10);
    }



    @Test
    void printSchedules() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        theater.printJsonSchedule();
    }

    @Test
    void scheduleFormat(){

        assertTrue(theater.JsonSchedule().getClass().equals(JSONObject.class)); // Check one of the requirements, whether the Json format is beng used in the schedule
    }


    @Test
    void testReservation(){ // Test the reservation function to see if the customer names are being saved
        theater.reserve(john,2,40);
        assertNotNull( theater.getTotalReservations().stream().filter(reservation -> john.getName().equals(reservation.getCustomer().getName())).findAny().orElse(null));

    }


}
