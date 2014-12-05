package com.eep.taxe.models;

import java.io.Serializable;

public interface JourneyInterface extends Serializable {

	public Boolean 			isMoving();								// Is the train moving or stationary
	public float			getTrainSpeed();						// Get current speed of the train, return 0 if stationary
	public void				setTrainSpeed(float trainSpeed);		// Set current speed of the train
	
	public Edge				getCurrentEdge();						// Get Edge the train is currently travelling on
	public void				setCurrentEdge(Edge currentEdge);		// Set Edge the train is currently travelling on
	public float			getProgressOnEdge();					// Get how far along the edge is the train as a percentage
	public void				incrementProgressOnEdge();				// Increment progress based upon train speed
	
}
