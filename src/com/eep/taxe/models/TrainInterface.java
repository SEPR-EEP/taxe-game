package com.eep.taxe.models;

import java.io.Serializable;

public interface TrainInterface extends AgeRelatedInterface, Serializable {
	
	public String		getModel();									//Get model of the train
	public void			setModel(String model);						//Set model of the train
	
	public int			getCostInGold();							//Get cost of train in gold
	public int			getCostInMetal();							//Get cost of train in metal
	
	public float		getSpeed();									//Get the actual speed of the train (km/turn)
	public float		getBaseSpeed();								//Get the base (unmodified) speed of the train (km/turn)
	public void			applyModifierToSpeed(float speedFactor);	//Apply a modifier to the base speed to get a new actual speed
	public void			removeModifierToSpeed(float speedFactor);	//Remove a modifier from the base speed to revert to the previous actual speed
	public void			removeAllModifiersToSpeed();				//Remove all modifiers from the actual speed to revert back to base speed
	
	public void			startAGoal(Goal goal);						//Start a goal by creating a new journey for train
	public void			moveForward();								//Move the train forward by a turn
}
