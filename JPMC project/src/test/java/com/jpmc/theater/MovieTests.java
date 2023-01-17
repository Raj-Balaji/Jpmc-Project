package com.jpmc.theater;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieTests {

    Movie spiderMan;
    Movie ironMan;

    @BeforeAll
    public void setUp() {

        ironMan = new Movie("IronMan ", Duration.ofMinutes(90), 5, 0);
        spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        System.out.println("Hi");
    }

    @Test
    public void unitTests(){
        assertNotNull(ironMan);
        Movie ironManCopy = new Movie("IronMan ", Duration.ofMinutes(90), 5, 0);
        assertTrue(ironMan.equals(ironManCopy));

    }



}
