package com.eep.taxe.models;

import java.util.Random;
import java.util.Vector;

import com.eep.taxe.res.Generator;

/**
 * This class represents a single player in the Game.
 * It is used as a container for data exchange with the server
 * for all of the information related to a single player.
 * 
 * This is the place where you should store everything that
 * has to do with a player.
 * 
 * It contains everything in possession of a player:
 * 	- The goals to complete
 *  - The usable resources
 *  - The spendable resources (gold and metal)
 *  - The current score
 *  
 *  
 *
 */
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
	
	private final int 			MAX_USABLES = 5;
	
	/**
	 * Instantiates a new empty player object.
	 */
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

	/**
	 * Checks if the player already has a given Usable in their inventory.
	 * @param resource		The resource to check
	 * @return				True if it is in the player inventory, false otherwise.
	 */
	@Override
	public boolean hasResource(Usable resource) {
		return this.inventory.contains(resource);
	}

	/**
	 * Checks if the player has enough spendable resources to buy a given usable resource. 
	 * NOTE: This does not check if there are too many elements in the inventory.
	 * @param 	resource		The Resource to check.
	 * @resource				True if the player has enough gold and metal to buy the resource,
	 * 							false otherwise.
	 */
	@Override
	public boolean canBuy(Usable resource) {
		return (
			this.getGoldQuantity() 	>= 	resource.getCostInGold() 	&&
			this.getMetalQuantity()	>=	resource.getCostInMetal()
		);
	}

	/**
	 * Checks if the user can buy a resource. If yes, decrements the player's
	 * gold and metal by the cost of the resource, and finally adds the resource
	 * to the player's inventory.
	 * @param	Usable 		The resource to buy.
	 */
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

	/**
	 * Cashes in the goals that have been accomplished.
	 * Checks if any goal has been accomplished. If yes,
	 * calculate the score (not yet implemented). Finally,
	 * returns the number of goals that have been accomplished.
	 * @param game		The game instance
	 * @return			The number of goals accomplished
	 */
	public int cashInCompletedGoals(Game game){
		int r = 0;
		
		for (Train train : this.getTrains()){
			
			//For each of player's goals check if train's journey has completed it
			Vector<Goal> goals = new Vector<Goal>(this.getCurrentGoals());
			for (Goal goal : goals){
				
				Journey journey = train.getJourney(); //Train's journey
				
				//Skip to next iteration if the train has no journey
				if (journey == null){
					continue;
				}
				
				//CALLING A METHOD TO CALCULATE SCORING COULD GO HERE
				
				
				//If goal is completed remove it
				if (goal.hasJourneyAccomplishedGoal(journey)){
					train.setStationToStartNextGoalAt(goal.getEndingStation());
					this.getCurrentGoals().remove(goal);
					
					//Generate a new goal to replace this one
					this.generateGoalToStartAtCurrentStation(train, game);
					
					r++;
				}
			}
			
		}
		return r;
	}
	
	@Override
	public Vector<Goal> getCurrentGoals() {
		return this.currentGoals;
	}
	
	/**
	 * If there is space for a new goal, generate a new one for a train
	 * in a given game.
	 * @param train		The train
	 * @param game		The game instance
	 */
	@Override
	public void generateGoal(Train train, Game game) {
		
		if (this.currentGoalsNo() < 3){
			Goal newGoal = Generator.generateGoal(train, game.getVertices(), this, game.getRandomStation(), game);
			this.currentGoals.add(newGoal);
		}
		
	}
	
	/**
	 * If there is space for a new goal, generate a new one for a train
	 * in a given game. The generated goal will start at the station where
	 * the train is currently placed.
	 * @param train		The train
	 * @param game		The game instance
	 */
	public void generateGoalToStartAtCurrentStation(Train train, Game game){
		//If trains journey is completed or max goal not yet reached add a new goal
		if (train.getJourney().isJourneyComplete() && this.currentGoalsNo() < 3){
			Goal newGoal = Generator.generateGoal(train, game.getVertices(), this, train.getStationToStartNextGoalAt(), game);
			this.currentGoals.add(newGoal);
			train.setStationToStartNextGoalAt(newGoal.getEndingStation());
		}
	}

	/**
	 * Checks if a player can accomplish a goal or not.
	 * @return	True if the goal can be accomplished. False otherwise.
	 */
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
