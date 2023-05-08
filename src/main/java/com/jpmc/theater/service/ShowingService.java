package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;

public interface ShowingService {
	
	
	public double calculateFee(Showing showing, int audienceCount);

}
