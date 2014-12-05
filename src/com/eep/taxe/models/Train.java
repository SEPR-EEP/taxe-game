package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

public class Train implements TrainInterface {

	private String trainModel;
	private Ages trainAge;
	private final float baseSpeed;
	private float actualSpeed;
	private Journey trainJourney;
	
	public Train(String model, Ages trainAge, float baseSpeed){
		this.trainModel = model;
		this.trainAge = trainAge;
		this.baseSpeed = baseSpeed;
		this.actualSpeed = baseSpeed;
		this.trainJourney = null;
	}
	
	@Override
	public String getModel() {
		return trainModel;
	}

	@Override
	public void setModel(String model) {
		this.trainModel = model;
	}
	
	@Override
	public Age getAge() {
		return new Age(this.trainAge);
	}

	@Override
	public void setAge(Ages age) {
		trainAge = age;
	}

	@Override
	public float getSpeed() {
		return this.actualSpeed;
	}

	@Override
	public float getBaseSpeed() {
		return this.baseSpeed;
	}

	@Override
	public void applyModifierToSpeed(float speedFactor) {
		this.actualSpeed = this.baseSpeed * speedFactor;
	}
	
	@Override
	public void removeAllModifiersToSpeed() {
		this.actualSpeed = this.baseSpeed;
	}
	
	@Override
	public void startAGoal(Goal goal) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int moveForward() {
		// TODO Auto-generated method stub
		return 0;
	}

}
