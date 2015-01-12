package com.eep.taxe.models;

import java.io.Serializable;

public interface JourneyInterface extends Serializable {

	public Boolean 			isMoving();													// Is the train moving or stationary
	public Boolean			isJourneyComplete();										// Is the journey along the path complete
	
	public Edge				getCurrentEdge();											// Get Edge the train is currently travelling on
	public void				setCurrentEdge(Edge currentEdge, float initialProgress);	// Set Edge the train is currently travelling on
	public float			getProgressOnEdge();										// Get how far along the edge is the train as a percentage
	
	public Edge				getNextEdge();												// Get the edge that comes after the current edge
	
	public float			getDistanceTravelledOnEdge();								// Get the distance travelled on the current edge
	public float			getDistanceTravelledOnJourney();							// Get the distance travelled on the journey
	
	public void				startJourney();												// Start the first turn of the train's journey
	public void				incrementProgressOnEdge();									// Increment progress based upon train speed
	
}
