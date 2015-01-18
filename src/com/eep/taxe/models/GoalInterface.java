package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface GoalInterface extends AgeRelatedInterface, Serializable {

	public String	 		getShortDescription();				// Get the short description of the goal
	public void 			setShortDescription(String s);		// Set the short description of the goal
	
	public String 			getLongDescription();				// Get the long description of the goal
	public void 			setLongDescription(String s);		// Set the long description of the goal
	
	public Station 			getStartingStation();				// Get the starting Station for the Goal
	public Station 			getEndingStation();					// Get the ending Station for the Goal
	public void				setStartingStation(Station s);		// Set the starting Vertex for the Goal
	public void 			setEndingStation(Station s);		// Set the ending Vertex for the Goal
	

	public int 				calculateReward(Journey journey, Vector<Vertex> vertices);					//Calculate reward for a given completed journey
	public Boolean 			willJourneyAcomplishGoal(Journey journey);						//Check if a given journey's start and end station are those of the Goal
	public Boolean			canBeAccomplishedBy(Player p);									// Check if Player can accomplish the Goal
	public int 				optimalNumberOfTurns(float shortestPathLength, float baseSpeed);					// Calculate duration of the Goal
	
}
