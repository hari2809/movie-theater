package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.ShowingService;
import com.jpmc.theater.service.ShowingServiceImpl;

public class MovieTests {
	static ShowingService showService  ;
	
	@BeforeAll
	public static void init() {
		showService=new ShowingServiceImpl();
	}
	
		
    @Test
    void specialMovieWith20PercentDiscount() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)));
        assertEquals(20, showService.calculateFee(showing,2));

        System.out.println(Duration.ofMinutes(90));
    }
    
    @Test
    void movieWith25PercentDiscountAtDiscountHours() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30)));
        assertEquals(18.75, showService.calculateFee(showing,2));

        System.out.println(Duration.ofMinutes(90));
    }
    
    @Test
    void movieOn7thWithOneDollerDiscount() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.of(2023, 05, 07), LocalTime.of(18, 30)));
        assertEquals(23, showService.calculateFee(showing,2));

        System.out.println(Duration.ofMinutes(90));
    }
    
    @Test
    void movieWithSequenceOneAndThreeDollerDiscount() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30)));
        assertEquals(9.5, showService.calculateFee(showing,1));

        System.out.println(Duration.ofMinutes(90));
    }
    
    @Test
    void movieWithSequenceTwoAndTwoDollerDiscount() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30)));
        assertEquals(10.5, showService.calculateFee(showing,1));

        System.out.println(Duration.ofMinutes(90));
    }
    
    @Test
    void movieWithNoDiscount() {
    	
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 30)));
        assertEquals(12.5, showService.calculateFee(showing,1));

        System.out.println(Duration.ofMinutes(90));
    }
}
