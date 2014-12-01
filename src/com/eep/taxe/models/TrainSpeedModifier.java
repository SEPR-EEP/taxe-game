package com.eep.taxe.models;

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
			int costInMetal, Age age, float speedFactor) {
		super(name, image, costInGold, costInMetal, age);
		this.speedFactor = speedFactor;
		this.inUse = false;
	}
	
	/** Get the Speed Factor of the Modifier */
	@Override
	public float getSpeedFactor() {
		return this.speedFactor;
	}

	/** Set the Speed Factor of the Modifier */
	@Override
	public void setSpeedFactor(float newFactor) {
		this.speedFactor = newFactor;
		
	}

	/** Use the Resource on a train */
	@Override
	public void useOnTrain(Train train) {
		this.train = train;
		this.inUse = true;
		
		// TODO Will use a method from Train to modify its speed
				
	}
	
	/** Stop the Resource being used on a train */
	@Override
	public void stopUseOnTrain(){
		if (this.isInUse()){
			this.inUse = false;
			// TODO Will use a method from Train to modify its speed
			
		}
	}

	/** Check if the Resource is in use */
	@Override
	public Boolean isInUse() {
		return this.inUse;
	}

	/** Return the Train the resource is used on, NULL if not in use */
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
