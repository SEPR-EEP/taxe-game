package com.eep.taxe.models;

import java.io.Serializable;

public interface TrainSpeedModifierInterface extends UsableInterface, Serializable {

	public float		getSpeedFactor();					// Get the Speed Factor of the Modifier
	public void			setSpeedFactor(float newFactor);	// Set the Speed Factor of the Modifier
	
	public void			useOnTrain(Train train);			// Use the Resource on a train
	public void			stopUseOnTrain();					// Stop the Resource being used on a train, NULL if not in use
	public Boolean		isInUse();							// Check if the Resource is in use
	public Train		usedOnTrain();						// Return the Train the resource is used on, NULL if not in use
	
	public int			getTurnsRemaining();				// Get the number of turns remaining for the resource
	public void			setTurnsRemaining(int newTurns);	// Set the number of turns remaining for the resource
	public void			decrementTurnsRemaining();			// Decrement the number of turns remaining for the resource by 1
	
}
