package com.eep.taxe.models;


import com.eep.taxe.models.Age.Ages;

/**
 * This class represents one of the Goals a Player needs to accomplish
 * to be rewarded points. A Goal has a starting and an ending station.
 * 
 * Some methods are defined, such as:
 *  - Calculate what is the optimal number of turns the goal
 *  	 	could be accomplished in (for scoring purposes);
 *  - Check if the a Goal can be accomplished by a Player
 *  		(to avoid generating impossible goals);
 *  - Check if a Journey could accomplish or has already
 *  		accomplished a given goal.
 *  
 */
public class Goal implements GoalInterface {

	private static final long serialVersionUID = 8933787860452341781L;
	
	private Ages age;
	private String shortDescription;
	private String longDescription;
	private Station startingStation;
	private Station endingStation;
	
	/**
	 * Build a Goal
	 * @param age				The Age when the Goal is generated.
	 * @param shortDescription	A short description of the Goal (e.g. "Travel from X to Y");
	 * @param longDescription	A longer description of the Goal (currently not used)
	 * @param startingStation	The starting station to accomplish the goal
	 * @param endingStation		The ending station to accomplish the goal
	 */
	public Goal(Ages age, String shortDescription, String longDescription, 
			Station startingStation, Station endingStation){
		this.age = age;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.startingStation = startingStation;
		this.endingStation = endingStation;
	}
	
	
	/** Get the associated Age for the Goal */
	@Override
	public Age getAge() {
		return new Age(this.age);
	}

	/** Set the associated Age for the Goal */
	@Override
	public void setAge(Ages age) {
		this.age = age;
	}

	/** Get the short description of the goal */
	@Override
	public String getShortDescription() {
		return this.shortDescription;
	}

	/** Set the short description of the goal */
	@Override
	public void setShortDescription(String s) {
		this.shortDescription = s;
	}

	/** Get the long description of the goal */
	@Override
	public String getLongDescription() {
		return this.longDescription;
	}

	/** Set the long description of the goal */
	@Override
	public void setLongDescription(String s) {
		this.longDescription = s;
	}

	/** Get the starting Station for the Goal */
	@Override
	public Station getStartingStation() {
		return this.startingStation;
	}

	/** Get the ending Station for the Goal */
	@Override
	public Station getEndingStation() {
		return this.endingStation;
	}

	/** Set the starting Station for the Goal */
	@Override
	public void setStartingStation(Station s) {
		this.startingStation = s;
	}

	/** Set the ending Station for the Goal */
	@Override
	public void setEndingStation(Station s) {
		this.endingStation = s;
		
	}

	
	public Boolean hasJourneyAccomplishedGoal(Journey journey){
		if (willJourneyAccomplishGoal(journey)){
			if (journey.isJourneyComplete()){
				return true;
			}
		}
		return false;
	}

	/** Check if a given journey's start and end station are the same as the goal's start and end station
	 * @param journey A non-empty journey
	 * @return True if the given journey will accomplish the goal upon completion 
	 */
	@Override
	public Boolean willJourneyAccomplishGoal(Journey journey){
		if (journey.isEmpty()){
			return false;
		}
		
		if (journey.getStartingStation() == this.startingStation 
				&& journey.getEndingStation() == this.endingStation) {
			
			return true;
		}
		
		return false;
	}
	
	
	/** 
	 * Check if Player can accomplish the Goal
	 * @param p 		The Player
	 */
	@Override
	public Boolean canBeAccomplishedBy(Player p) {
		// TODO ATM all goals can be accomplished and this method is not used.
		return null;
	}

	/** Calculate shortest number of turns it will take a train to complete, travelling at its base speed
	 * @param shortestPathLength Should first be calculated using Dijkstra's algorithm
	 * @param baseSpeed	The unmodified speed of a train */
	@Override
	public int optimalNumberOfTurns(float shortestPathLength, float baseSpeed) {
		float shortestTurns = shortestPathLength / baseSpeed; 
		return (int) Math.ceil(shortestTurns); //If not int round up to next int
	}

}
