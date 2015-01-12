package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

public class Goal implements GoalInterface {

	private static final long serialVersionUID = 8933787860452341781L;
	
	private Ages age;
	private String shortDescription;
	private String longDescription;
	private Station startingStation;
	private Station endingStation;
	
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

	/** Calculate the Score Reward for the Goal */
	@Override
	public int calculateReward() {
		return 0;
	}

	/** Check if Player can accomplish the Goal */
	@Override
	public Boolean canBeAccomplishedBy(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

	/** Calculate shortest number of turns it will take a train to complete, travelling at its base speed
	 * @param shortestPathLength Should first be calculated using Dijkstra's algorithm
	 * @param baseSpeed	The unmodified speed of a train */
	@Override
	public int optimalDuration(float shortestPathLength, float baseSpeed) {
		float shortestTurns = shortestPathLength / baseSpeed; 
		return (int) Math.ceil(shortestTurns); //If not int round up to next int
	}

}
