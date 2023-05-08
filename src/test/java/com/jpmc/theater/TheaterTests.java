package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.LocalDateProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TheaterTests {
	@Test
	void totalFeeForCustomer() {
		Theater theater = new Theater(LocalDateProvider.singleton(), buildShowing());
		Customer john = new Customer("John Doe", "id-12345");
		Reservation reservation = theater.reserve(john, 2, 4);
		assertEquals(reservation.totalFee(), 50);
	}

	@Test
	void printMovieSchedule() {
		Theater theater = new Theater(LocalDateProvider.singleton(), buildShowing());
		theater.printSchedule();
	}

	private List<Showing> buildShowing() {
		LocalDateProvider provider = LocalDateProvider.singleton();
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
		Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
		Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
		List<Showing> schedule = List.of(
				new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
				new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
				new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
				new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
				new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
				new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
				new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
				new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
				new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0))));

		return schedule;
	}
}
