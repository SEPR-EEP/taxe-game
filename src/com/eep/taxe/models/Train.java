package com.eep.taxe.models;

import com.eep.taxe.models.Age.Ages;

public class Train implements TrainInterface {

	private Ages trainAge;
	
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

	
}
