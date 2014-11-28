package com.eep.taxe.models;

import java.io.Serializable;

public interface GoalInterface extends AgeRelatedInterface, Serializable {

	public String	 		getShortDescription();				// Get the short description of the goal
	public void 			setShortDescription(String s);		// Set the short description of the goal
	
	public String 			getLongDescription();				// Get the long description of the goal
	public void 			setLongDescription(String s);		// Set the long description of the goal
	
	public Vertex 			getStartingPoint();					// Get the starting Vertex for the Goal
	public Vertex 			getEndingPoint();					// Get the ending Vertex for the Goal
	public void				setStartingPoint(Vertex v);			// Set the starting Vertex for the Goal
	public void 			setEndingPoint(Vertex v);			// Set the ending Vertex for the Goal
	
	public int 				calculateReward();					// Calculate the Score Reward for the Goal
	public Boolean			canBeAccomplishedBy(Player p);		// Check if Player can accomplish the Goal
	public int 				estimateDuration();					// Calculate duration of the Goal
	
}
