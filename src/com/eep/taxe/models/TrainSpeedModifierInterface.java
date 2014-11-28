package com.eep.taxe.models;

import java.io.Serializable;

public interface TrainSpeedModifierInterface extends Serializable {

	public float		getSpeedFactor();					// Get the Speed Factor of the Modifier
	public void			setSpeedFactor(float newFactore);	// Set the Speed Factor of the Modifier
	
	public void			useOnTrain(Train train);			// Use the Resource against a train
	public Boolean		isInUse();							// Check if the Resource is in use
	public Train		usedOnTrain();						// Return the Train the resource is used on, NULL otherwise
	
	public int			getTurnsRemaining();				// Get the number of turns remaining for the resource
	public void			setTurnsRemaining(int newTurns);	// Set the number of turns remaining for the resource
	public void			decreaseTurnsRemaining();			// Decrease the number of turns remaining for the resource by 1
	
}
