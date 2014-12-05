package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface PlayerInterface extends Comparable<Player>, Serializable {

	public String 			getNickname();								// Set the player's nickname
	public void 			setNickname(String newNickname);			// Get the player's nickname
	
	public int 				getScore();									// Get the current score for the player
	public void 			setScore(int newScore);						// Set the current score for the player
	public void 			incrementScore(int delta);					// Increment the current score for the player
	
	public int 				getGoalsCompleted();						// Get the number of completed goals in the game
	public int 				setGoalsCompleted(int newGoalsCompleted);	// Set the number of completed goals in the game
	public int 				incrementGoalsCompleted(int delta);			// Increment the number of completed goals
	public Age 				getCurrentAge();							// Get the current age for the player
	
	public Vector<Resource> getInventory();								// Get the list of the player resources
	public boolean 			hasResource(Resource resource);				// Check if a player has a given resource
	public int 				countResource(Resource resource);			// Counts occurrences of a given resource type
	
	public boolean 			canBuy(Usable resource);					// Check if the player has enough resources to buy
	public void 			buy(Usable resource);						// Add element to the inventory and decrement resources
	
	public int 				getGold();									// Get the amount of gold
	public int 				getMetal();									// Get the amount of metal
	public int 				addGold(int delta);							// Add the amount of gold
	public int 				addMetal(int delta);						// Add the amount of metal
	public int 				reduceGold(int delta);						// Reduce gold of the player
	public int				reduceMetal(int delta);						// Reduce metal of the player 
	
	public Vector<Train> 	getTrains();								// Get the list of trains of the player
	public boolean			canBuyTrain(Train train);					// Check if the player has enough resources to buy
	public void 			buyTrain(Train train);						// Add a train and decrement metal and gold
	public void 			sellTrain(Train train);						// Sell the train and increment metal and gold
		
	public int 				currentGoalsNo();							// Get the number of current goals for the player
	public Vector<Goal> 	getCurrentGoals();							// Get the list of the current goals of the player
	public void 			generateGoal();								// Generate a Random Goal and add it to the Current Goals
	public boolean			canAccomplish(Goal goal);					// Check if a player can accomplish a given goal
	
}
