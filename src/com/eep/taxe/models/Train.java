package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

public class Train implements TrainInterface {

	private Ages trainAge;
	private float speed;
	
	@Override
	public Age getAge() {
		return new Age(this.trainAge);
	}

	@Override
	public void setAge(Ages age) {
		trainAge = age;
	}

	@Override
	public int calculateSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moveForward() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public void setSpeed(float newSpeed) {
		this.speed = newSpeed;
	}

	
}
