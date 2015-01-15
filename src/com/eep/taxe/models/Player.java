package com.eep.taxe.models;

import java.util.Vector;

import com.eep.taxe.res.Map;

public class Player implements PlayerInterface {

	private static final long serialVersionUID = 3534438349877377215L;
	
	private int 				score;
	private String				nickname;
	private int					goalsCompleted;
	
	private Vector<Usable>		inventory;
	private Vector<Train>		trains;
	
	private Gold				gold;
	private Metal				metal;
	
	private Vector<Goal>		currentGoals;
	
	public Player() {
		this.gold  = new Gold();
		this.metal = new Metal();
		
		Map m = new Map();
		Vector<Vertex> vertices = m.generateMap();
	}
	
	/**
	 * Compare two players by their score
	 * @param o	The other player
	 * @return	1, 0, -1
	 */
	@Override
	public int compareTo(Player o) {
		return Integer.valueOf(getScore()).compareTo(
			Integer.valueOf(o.getScore())
		);
	}

	@Override
	public String getNickname() {
		return this.nickname;
	}

	@Override
	public void setNickname(String newNickname) {
		this.nickname = newNickname;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public void setScore(int newScore) {
		this.score = newScore;
	}

	@Override
	public int incrementScore(int delta) {
		this.setScore(this.getScore() + delta);
		return this.getScore();
	}

	@Override
	public int getGoalsCompleted() {
		return this.goalsCompleted;
	}

	@Override
	public void setGoalsCompleted(int newGoalsCompleted) {
		this.goalsCompleted = newGoalsCompleted;
	}

	@Override
	public int incrementGoalsCompleted(int delta) {
		this.setGoalsCompleted(this.getGoalsCompleted() + delta);
		return this.getGoalsCompleted();
	}

	@Override
	public Age getCurrentAge() {
		return new Age(this.getGoalsCompleted());
	}

	@Override
	public Vector<Usable> getInventory() {
		return this.inventory;
	}

	@Override
	public boolean hasResource(Usable resource) {
		return this.inventory.contains(resource);
	}

	@Override
	public boolean canBuy(Usable resource) {
		return (
			this.getGoldQuantity() 	>= 	resource.getCostInGold() 	&&
			this.getMetalQuantity()	>=	resource.getCostInMetal()
		);
	}

	@Override
	public void buy(Usable resource) {
		if ( !this.canBuy(resource) )
			return;
		this.reduceGold   (resource.getCostInGold());
		this.reduceMetal  (resource.getCostInMetal());
		this.inventory.add(resource);
	}

	@Override
	public Gold getGold() {
		return this.gold;
	}

	@Override
	public Metal getMetal() {
		return this.metal;
	}

	@Override
	public int getGoldQuantity() {
		return this.getGold().getQuantity();
	}

	@Override
	public int getMetalQuantity() {
		return this.getMetal().getQuantity();
	}

	@Override
	public int addGold(int delta) {
		this.getGold().setQuantity(this.getGoldQuantity() + delta);
		return this.getGoldQuantity();
	}

	@Override
	public int addMetal(int delta) {
		this.getMetal().setQuantity(this.getMetalQuantity() + delta);
		return this.getMetalQuantity();
	}

	@Override
	public int reduceGold(int delta) {
		return this.addGold(-delta);
	}

	@Override
	public int reduceMetal(int delta) {
		return this.addMetal(-delta);
	}

	@Override
	public Vector<Train> getTrains() {
		return this.trains;
	}

	@Override
	public boolean canBuyTrain(Train train) {
		return (
			this.getGoldQuantity() 	>= 	train.getCostInGold() 	&&
			this.getMetalQuantity()	>=	train.getCostInMetal()
		);
	}

	@Override
	public void buyTrain(Train train) {
		if ( !this.canBuyTrain(train) )
			return;
		this.reduceGold   (train.getCostInGold());
		this.reduceMetal  (train.getCostInMetal());
		this.trains.add(train);
	}

	@Override
	public void sellTrain(Train train) {
		if ( !this.trains.contains(train) )
			return;
		this.trains.remove(train);
		this.addGold(train.getCostInGold());
		this.addMetal(train.getCostInMetal());
	}

	@Override
	public int currentGoalsNo() {
		return this.getCurrentGoals().size();
	}

	@Override
	public Vector<Goal> getCurrentGoals() {
		return this.currentGoals;
	}

	
	@Override
	public void generateGoal() {
		
		//If max number of goals is reached
		if (this.currentGoalsNo() >= 3) {
			return;
		}
		
		for (Train train : trains){
			if (! train.hasActiveGoal() ){
				
				//Starting station is where train is currently located
				Station startingStation = train.getStationToStartNextGoalAt();
				
				//Station that is randomly chosen must not be the same as the starting station
				Station endingStation = startingStation;
				while (endingStation == startingStation){
					endingStation = Map.getRandomStationFromMap();
				}
				
				//Create goal based upon starting and ending station
				Goal goal = new Goal(this.getCurrentAge().age, 
						"Travel from " +startingStation.getName() +"to " +endingStation.getName(),
						"Long description - maybe random scenario?",
						startingStation,
						endingStation);
				
				this.currentGoals.add(goal);
				
				break; //Exit for loop early
				
			}
		}
		
	}

	@Override
	public boolean canAccomplish(Goal goal) {
		return goal.canBeAccomplishedBy(this);
	}


}
