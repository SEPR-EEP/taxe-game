package com.eep.taxe.models;
import com.eep.taxe.models.Age.Ages;

public class TrainSpeedModifier extends Usable implements TrainSpeedModifierInterface {

	private static final long serialVersionUID = -2301374994280460917L;
	
	private float speedFactor;
	private Boolean inUse;
	private Train train;
	private int turnsRemaining;

	/**
	 * Instantiates a Speed Modifier Resource to be used on a Train
	 * @param name Name of Resource
	 * @param image Image URI for the Resource
	 * @param costInGold Cost in Gold for the Resource
	 * @param costInMetal Cost in Metal for the Resource
	 * @param age Associated Age for the Resource
	 * @param speedFactor Speed factor for the Modifier
	 */
	public TrainSpeedModifier(String name, String image, int costInGold,
			int costInMetal, Ages age, float speedFactor) {
		super(name, image, costInGold, costInMetal, age);
		this.speedFactor = speedFactor;
		this.inUse = false;
	}
	
	/** Get the Speed Factor of the Modifier
	 * @return Speed factor of the Modifier */
	@Override
	public float getSpeedFactor() {
		return this.speedFactor;
	}

	/** Set the Speed Factor of the Modifier
	 * @param	newFactor	The new speed factor of the modifier */
	@Override
	public void setSpeedFactor(float newFactor) {
		this.speedFactor = newFactor;
		
	}

	/** Use the Modifier on a train
	 * @param 	train		The train to use the Modifier on  */
	@Override
	public void useOnTrain(Train train) {
		if (! this.isInUse()){
			this.train = train;
			this.inUse = true;
			train.applyModifierToSpeed(speedFactor);
		}
	}
	
	/** Stop the Resource being used on a train */
	@Override
	public void stopUseOnTrain(){
		if (this.isInUse()){
			
			this.inUse = false;
			train.removeModifierToSpeed(speedFactor);
		}
	}

	/** Check if the Resource is in use
	 * @return True if the Resource is in use */
	@Override
	public Boolean isInUse() {
		return this.inUse;
	}

	/** Get the train that the resource is used on
	 * @return	The train the resource is used on, NULL if not in use */
	@Override
	public Train usedOnTrain() {
		if (! this.isInUse()){
			return null;
		}
		return this.train;
	}

	/** Get the number of turns remaining for the resource */
	@Override
	public int getTurnsRemaining() {
		return this.turnsRemaining;
	}

	/** Set the number of turns remaining for the resource */
	@Override
	public void setTurnsRemaining(int newTurns) {
		this.turnsRemaining = newTurns;
	}

	/** Decrement the number of turns remaining for the resource by 1 */
	@Override
	public void decrementTurnsRemaining() {
		this.turnsRemaining--;
	}

}
