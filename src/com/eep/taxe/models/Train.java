package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

public class Train implements TrainInterface {

	private static final long serialVersionUID = 1L;
	
	private String trainModel;
	private String trainImage;
	private Ages trainAge;
	private int costInMetal, costInGold;
	private final float baseSpeed;	//Speed of the train without modifiers
	private float actualSpeed;		//Speed of the train with modifiers applied, if non applied then baseSpeed == actualSpeed
	private Goal goal;
	private Journey journey;
	private Boolean hasActiveGoal;
	
	private Station stationToStartNextGoalAt; // The station the train will start its next goal from
	/**
	 * Instantiates a Train
	 * @param	model		Model name of the train
	 * @param	costInGold	Cost in gold to buy the train
	 * @param	costInMetal	Cost in metal to buy the train
	 * @param 	trainAge 	The game age that the train belongs to
	 * @param 	baseSpeed 	The speed the train travels at, without any modifiers being applied to it
	 */
	public Train(String model, String trainImage, int costInGold, int costInMetal, Ages trainAge, float baseSpeed, Station stationToStartNextGoalAt){
		this.trainModel = model;
		this.trainImage = trainImage;
		this.costInGold = costInGold;
		this.costInMetal = costInMetal;
		this.trainAge = trainAge;
		this.baseSpeed = baseSpeed;
		this.actualSpeed = baseSpeed;
		this.hasActiveGoal = false;
		this.stationToStartNextGoalAt = stationToStartNextGoalAt;
	}
	
	/** Get the Model name of the train
	 * @return Model name 
	 * */
	@Override
	public String getModel() {
		return trainModel;
	}
	
	/** Set the Model name of the train
	 * @param	model	New model name of train
	 */
	@Override
	public void setModel(String model) {
		this.trainModel = model;
	}
	
	/** Get the cost in gold to buy the train
	 * @return Cost in gold
	 * */
	@Override
	public int getCostInGold() {
		return this.costInGold;
	}

	/** Get the cost in metal to buy the train
	 * @return Cost in metal
	 * */
	@Override
	public int getCostInMetal() {
		return this.costInMetal;
	}
	
	/** Get the game age that the train belongs to
	 * @return Game age that train belongs to
	 * */
	@Override
	public Age getAge() {
		return new Age(this.trainAge);
	}

	/** Set the game age that the train belongs to
	 * @param	age		New age that the train belongs to
	 */
	@Override
	public void setAge(Ages age) {
		trainAge = age;
	}

	/** Get the actual speed of the train (i.e. speed with modifiers applied)
	 * @return Speed of the train
	 * */
	@Override
	public float getSpeed() {
		return this.actualSpeed;
	}
	
	/** Get the base speed of the train (i.e. speed without modifiers applied)
	 * @return Base speed of the train
	 * */
	@Override
	public float getBaseSpeed() {
		return this.baseSpeed;
	}

	/** Apply a speed factor to actual speed (actualSpeed = actualSpeed * speedFactor)
	 * @param	speedFactor		Modifier (multiplier) of actual speed
	 */
	@Override
	public void applyModifierToSpeed(float speedFactor) {
		this.actualSpeed = this.actualSpeed * speedFactor;
	}
	
	/** Remove a speed factor from actual speed (actualSpeed = actualSpeed / speedFactor)
	 * @param	speedFactor		Modifier (divisor) of actual speed
	 */
	@Override
	public void removeModifierToSpeed(float speedFactor){
		this.actualSpeed = this.actualSpeed / speedFactor;
	}
	
	/** Remove all modifiers from a train, i.e. actualSpeed = baseSpeed
	 */
	@Override
	public void removeAllModifiersToSpeed() {
		this.actualSpeed = this.baseSpeed;
	}
	
	
	@Override
	public void startAGoal(Goal goal, Journey journey) {
		
		if (goal.willJourneyAcomplishGoal(journey) ){
			this.hasActiveGoal = true;
			this.journey = journey;
			this.goal = goal;
			this.stationToStartNextGoalAt = goal.getEndingStation();
			
		}
	}
	
	@Override
	public void moveForward() {
		
		if (this.hasActiveGoal){
			journey.incrementProgressByTurn();
			
			if (journey.isJourneyComplete() ) {
				this.hasActiveGoal = false;
			}
		}
		
		
	}
	
	public Boolean hasActiveGoal(){
		return this.hasActiveGoal;
	}
	

	public Station getStationToStartNextGoalAt(){
		return this.stationToStartNextGoalAt;
	}
}
