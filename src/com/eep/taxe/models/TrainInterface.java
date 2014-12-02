package com.eep.taxe.models;

import java.io.Serializable;

public interface TrainInterface extends AgeRelatedInterface, Serializable {
	
	public int 			calculateSpeed();
	public int			moveForward();
	public float		getSpeed();
	public void			setSpeed(float newSpeed);
}
