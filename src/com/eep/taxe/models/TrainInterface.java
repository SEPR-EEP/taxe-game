package com.eep.taxe.models;

import java.io.Serializable;

public interface TrainInterface extends AgeRelatedInterface, Serializable {
	
	public String		getModel();									//Get model of the train
	public void			setModel(String model);						//Set model of the train
	
	public float		getSpeed();									//Get the actual speed of the train (km/turn)
	public float		getBaseSpeed();								//Get the base (unmodified) speed of the train (km/turn)
	public void			applyModifierToSpeed(float speedFactor);	//Apply a modifier to the base speed to get a new actual speed
	public void			removeAllModifiersToSpeed();				//Remove all modifiers from the actual speed to revert back to base speed
	
	public void			startAGoal(Goal goal);						//Start a goal by creating a new journey for train
	public int			moveForward();								//Move the train forward by a turn
}
