package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface AgeInterface extends Comparable<Age>, Serializable {

	//public Age 				Age(int goalsCompleted);		    // Create Age object from number of goals completed
	//public Age 				Age(Age.Ages age);					// Create Age object from Age enum

	public String 			getName();							// Get the name of the Age
	public String 			getDescription();					// Get a description of the Age
	public String			getImageFile();						// Get Image file for the Image
		
	public Vector<Train> 	getTrains();						// Get a list of Trains for the Age
	
	/*
	 * Future
	 * public Vector<RandomObstacleEvent> 	allRandomObstacleEvents();
	 * public RandomObstacleEvent			randomObstacleEvent();
	 */

}
