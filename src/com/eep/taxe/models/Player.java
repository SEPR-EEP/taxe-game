package com.eep.taxe.models;

import java.util.Random;
import java.util.Vector;

import com.eep.taxe.res.Generator;

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
	
	private final int 			MAX_USABLES = 6;
	
	public Player() {
		this.gold  = new Gold();
		this.metal = new Metal();
		
		this.trains = new Vector<Train>();
		this.inventory = new Vector<Usable>();
		this.currentGoals = new Vector<Goal>();
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

	public void cashInCompletedGoals(Vector<Vertex> vertices){
		
		for (Train train : this.getTrains()){
			
			//For each of player's goals check if train's journey has completed it
			for (Goal goal : this.getCurrentGoals()){
				int reward = goal.calculateReward(train.getJourney(), vertices); //0 if journey has not completed goal
				
				//If score is rewarded for goal it is completed, so remove it
				if (reward > 0){
					train.setStationToStartNextGoalAt(goal.getEndingStation());
					this.getCurrentGoals().remove(goal);
				}
				
				this.incrementScore(reward);
			}
		}
		
		
	}
	
	@Override
	public Vector<Goal> getCurrentGoals() {
		return this.currentGoals;
	}

	public void addGoal(Goal goal){
		if (this.currentGoalsNo() >= 3){
			return;
		}
		
		this.getCurrentGoals().addElement(goal);
		
	}
	
	@Override
	public void generateGoal(Game game) {
		
		for (Train train : trains){
			
			/*
			//If number of goals is not yet maximum assign a goal to the train
			if (this.currentGoalsNo() < 3){
				this.currentGoals.add(Generator.generateGoal(train, game.getVertices(), this, game));
			}*/
			
			//If trains journey is completed add a new goal if it completed a goal
			if (train.getJourney().isJourneyComplete() && this.currentGoalsNo() < 3){
				this.currentGoals.add(Generator.generateGoal(train, game.getVertices(), this, game));
	
			}
		}
	}

	@Override
	public boolean canAccomplish(Goal goal) {
		return goal.canBeAccomplishedBy(this);
	}

	/**
	 * Adds at most n random usable to the player's inventory.
	 * When the inventory is full, it stops.
	 * @param 	n	How many items to add.
	 */
	public void addRandomUsables(int n) {
		
		for ( int i = 0; i < n; i++ ) {
			
			// If the inventory is full, just don't bother
			if ( this.getInventory().size() >= MAX_USABLES ) {
				break;
			}
			
			// Take all possible upgrades
			Vector<Usable> pool = Generator.generateTrainSpeedModifier(this.getCurrentAge().age);
			int r = (new Random()).nextInt(pool.size()); // Pick a random number
			
			this.getInventory().add(
				pool.get(r)
			);
			
		}
		
	}


}
