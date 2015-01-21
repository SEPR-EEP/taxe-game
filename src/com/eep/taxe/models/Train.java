package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

/**
 * This class represents a Train. This could either be in the map
 * or still in the player's trains collection.
 * 
 * A train has a model/name, a cost in gold and metal,
 * an age and a base speed.
 * 
 * To place the train on the map, create a new Journey
 * for this train. The Journey will automatically
 * add itself to the train instance of creation.
 * 
 * 
 *
 */
public class Train implements TrainInterface, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private String trainModel;
	private String trainImage;
	private Ages trainAge;
	private int costInMetal, costInGold;
	private final float baseSpeed;	//Speed of the train without modifiers
	private float actualSpeed;		//Speed of the train with modifiers applied, if non applied then baseSpeed == actualSpeed

	private Journey journey;
	
	private Station stationToStartNextGoalAt; // The station the train will start its next goal from
	
	/**
	 * Creates a new train (not placed on the map).
	 * 
	 * @param	model		Model name of the train
	 * @param	trainImage	Image icon for the train
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
	
	/** Get the Image URI for the Train */
	@Override
	public String getTrainImage(){
		return this.trainImage;
	}
	
	/** Set the Image URI for the Train */
	@Override
	public void setTrainImage(String newImage){
		this.trainImage = newImage;
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
	
	public void setStationToStartNextGoalAt(Station station){
		this.stationToStartNextGoalAt = station;
	}
	
	@Override
	public Boolean startAGoal(Goal goal, Journey journey) {
		
		if (goal.willJourneyAccomplishGoal(journey) ){
			this.journey = journey;
			this.stationToStartNextGoalAt = goal.getEndingStation();
			return true;
		}
		
		return false;
	}
	
	/**
	 * If the train is placed on the map and is moving,
	 * move it forward by one turn. This method is calling
	 * by the computeEndOfTurn method in the Game class.
	 */
	@Override
	public void moveForward() {
		
		if ( journey == null ) {
			return;
		}
		
		journey.incrementProgressByTurn();	
		
	}
	
	public float getActualSpeed() {
		return actualSpeed;
	}

	/**
	 * Creates a clone of this train. This method is used when generating a new
	 * train as it needs to be a different one from the one in the store
	 * (otherwise you would place on the map the store's train and, in the worst
	 * case scenario, if another player buys the same train, share the train with
	 * your opponent).
	 * @return		A clone of the current instance.
	 */
	public Train clone() {
		try {
			return (Train) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Impossible to clone train.");
			System.exit(1);
		}
		return null;
	}

	public Station getStationToStartNextGoalAt(){
		return this.stationToStartNextGoalAt;
	}
	
	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}
}
