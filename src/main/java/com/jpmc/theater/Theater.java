package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.LocalDateProvider;

public class Theater {
	static Logger log = Logger.getLogger(Theater.class.getName());

	LocalDateProvider provider;
	private List<Showing> schedule;
	
	/**
	 * 
	 * @param provider
	 * @param schedule
	 */
	public Theater(LocalDateProvider provider, List<Showing> schedule) {
		this.provider = provider;
		this.schedule = schedule;

	}

	/**
	 * 
	 * @param customer
	 * @param sequence
	 * @param howManyTickets
	 * @return
	 */
	public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
		Showing showing;
		try {
			showing = schedule.get(sequence - 1);
		} catch (RuntimeException ex) {
			log.error("Theater.reserve() Unable to find show for given sequence");
			throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
		}
		return new Reservation(customer, showing, howManyTickets);
	}

	/**
	 * 
	 */
	public void printSchedule() {
		log.info(provider.currentDate());
		log.info("===================================================");
		schedule.forEach(s -> System.out
				.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " "
						+ humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee()));
		log.info("===================================================");
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.registerModule(new JavaTimeModule());
		try {
			log.info(objMapper.writeValueAsString(schedule));
		} catch (JsonProcessingException e) {		
			log.error("Exception while creating json");
		}

	}
	
	/**
	 * 
	 * @param duration
	 * @return
	 */
	public String humanReadableFormat(Duration duration) {
		long hour = duration.toHours();
		long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

		return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin,
				handlePlural(remainingMin));
	}

	/**
	 * (s) postfix should be added to handle plural correctly
	 * @param value
	 * @return
	 */	
	private String handlePlural(long value) {
		if (value == 1) {
			return "";
		} else {
			return "s";
		}
	}

	public static void main(String[] args) {

		Theater theater = buildTheater();
		theater.printSchedule();
	}

	/**
	 * Builds Theater information
	 * @return
	 */
	private static Theater buildTheater() {
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
		Theater theater = new Theater(provider, schedule);
		return theater;
	}
}
