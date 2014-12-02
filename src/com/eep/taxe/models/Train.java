package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

public class Train implements TrainInterface {

	private Ages trainAge;
	private float speed;
	
	@Override
	public Age getAge() {
		// TODO Auto-generated method stub
		return new Age(this.trainAge);
	}

	@Override
	public void setAge(Ages age) {
		// TODO Auto-generated method stub
		trainAge = age;
	}

	@Override
	public void setAge(Age age) {
		// TODO Auto-generated method stub
		trainAge = age.age;
		
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
		// TODO Auto-generated method stub
		return this.speed;
	}

	@Override
	public void setSpeed(float newSpeed) {
		// TODO Auto-generated method stub
		this.speed = newSpeed;
	}

	
}
