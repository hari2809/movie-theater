package com.jpmc.theater.service;

import org.apache.log4j.Logger;

import com.jpmc.theater.model.Showing;

/**
 * 
 * @author admin
 *
 */
public class ShowingServiceImpl implements ShowingService {

	static Logger log = Logger.getLogger(ShowingServiceImpl.class.getName());
	private static int MOVIE_CODE_SPECIAL = 1;
	private static int DISCOUNT_MOVIE_START_HOURS = 11;
	private static int DISCOUNT_MOVIE_END_HOURS = 16;
	private static int DEFAULT_DISCOUNT_DAY = 7;
	private static int SEQUENCE_ONE = 1;
	private static int SEQUENCE_TWO = 2;

	
	/**
	 * 
	 */
	public double calculateFee(Showing showing, int audienceCount) {
		return calculateTicketPrice(showing) * audienceCount;
	}

	/**
	 * 
	 * @param showing
	 * @return
	 */
	private double calculateTicketPrice(Showing showing) {
		return showing.getMovieFee() - getDiscount(showing);
	}

	/**
	 * Calculates discount based on movie and showing information
	 * @param showing
	 * @return
	 */
	private double getDiscount(Showing showing) {

		log.info("Inside ShowingServiceImpl::getDiscount()");

		double specialDiscount = 0;

		if (showing.getStartTime().getHour() >= DISCOUNT_MOVIE_START_HOURS
				&& showing.getStartTime().getHour() <= DISCOUNT_MOVIE_END_HOURS) {
			log.info("Inside ShowingServiceImpl::getDiscount() Discount hours condition");
			specialDiscount = showing.getMovie().getTicketPrice() * 0.25; // 25% discount for special movie
		} else if (MOVIE_CODE_SPECIAL == showing.getMovie().getSpecialCode()) {
			log.info("Inside ShowingServiceImpl::getDiscount() Special Movie condition");
			specialDiscount = showing.getMovie().getTicketPrice() * 0.2; // 20% discount for special movie
		} else if (showing.getStartTime().getDayOfMonth() == DEFAULT_DISCOUNT_DAY) {
			log.info("Inside ShowingServiceImpl::getDiscount() 7th Day condition");
			specialDiscount = 1;
		}

		double sequenceDiscount = 0;
		if (showing.getSequenceOfTheDay() == SEQUENCE_ONE) {
			log.info("Inside ShowingServiceImpl::getDiscount() Sequence 1");
			sequenceDiscount = 3; // $3 discount for 1st show
		} else if (showing.getSequenceOfTheDay() == SEQUENCE_TWO) {
			log.info("Inside ShowingServiceImpl::getDiscount() Sequence 1");
			sequenceDiscount = 2; // $2 discount for 2nd show
		}

		return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
	}

}
