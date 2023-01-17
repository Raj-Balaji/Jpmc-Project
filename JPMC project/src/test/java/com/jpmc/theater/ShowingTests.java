package com.jpmc.theater;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShowingTests {



    List<Showing> showingList;
    Movie ironman;
    Movie spiderman;
    Movie aquaman;
    Movie avengers;
    int sequence;

    @BeforeAll
    public void setUp()
    {
        int sequence=0;
        ironman = new Movie("IronMan ", Duration.ofMinutes(90), 10, 0);
        spiderman = new Movie("SpiderMan ", Duration.ofMinutes(90), 10, 0);
        aquaman = new Movie("AquaMan ", Duration.ofMinutes(90), 10, 1);
        avengers = new Movie("Avengers ", Duration.ofMinutes(90), 10, 0);
        showingList = new ArrayList<>();
        showingList.add(new Showing(ironman, ++sequence,LocalDateTime.of(LocalDate.now(),LocalTime.of(6,0)),100));
        showingList.add(new Showing(spiderman, ++sequence, LocalDateTime.of(LocalDate.now(),LocalTime.of(9,0)),100));
        showingList.add(new Showing(aquaman, ++sequence, LocalDateTime.of(LocalDate.now(),LocalTime.of(14,0)),100));
        showingList.add(new Showing(avengers, ++sequence, LocalDateTime.of(LocalDate.now(),LocalTime.of(18,0)),100));
    }

    @Test
    public void InitializeTest(){
        assertNotNull(showingList.get(0));
        assertNotNull(showingList.get(0).getMovie());
        assertNotNull(showingList.get(0).getSequenceOfTheDay());
    }

   @Test
    public void discountRulesTest(){
        assertEquals(showingList.get(0).calculateShowingPrice(),7);     // Discount rule = 1st movie 3$ off
        assertEquals(showingList.get(1).calculateShowingPrice(),8);    // Discount rule = 2nd movie 2$ off
        assertEquals(showingList.get(2).calculateShowingPrice(),7.5);    // Discount rule = special movie 20% off


    }
    @Test
    public void seatsAvailabilityTest(){
        assertTrue(showingList.get(0).seatsAvailableToReserve(100));
        assertTrue(!showingList.get(0).seatsAvailableToReserve(200));

    }

}
