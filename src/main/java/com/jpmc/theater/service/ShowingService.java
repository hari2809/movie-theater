package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;
/**
 * 
 * @author admin
 *
 */
public interface ShowingService {
	
	/**
	 * 
	 * @param showing
	 * @param audienceCount
	 * @return
	 */
	public double calculateFee(Showing showing, int audienceCount);

}
