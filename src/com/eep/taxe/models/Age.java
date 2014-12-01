package com.eep.taxe.models;

import java.util.Vector;

public class Age implements AgeInterface {

	static enum Ages {
		FIRST, SECOND, THIRD, FOURTH;
	}
	
	Ages age;
	
	@Override
	public int compareTo(Age o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Age(int goalsCompleted) {
		
		if (goalsCompleted < 5) {
			this.age = Ages.FIRST;
		}
		else if (goalsCompleted >= 5 && goalsCompleted < 10) {
			this.age = Ages.SECOND;
		}
		else if (goalsCompleted >= 10 && goalsCompleted < 15) {
			this.age = Ages.THIRD;
		}
		else {
			this.age = Ages.FOURTH;
		}
	}

	public Age(Ages age) {
		
		switch (age) {
			case FIRST:
				this.age = Ages.FIRST;
			case SECOND:
				this.age = Ages.SECOND;
			case THIRD:
				this.age = Ages.THIRD;
			case FOURTH:
				this.age = Ages.FOURTH;
			default:
				break;
		}
	}

	@Override
	public String getName() {
		
		String name = "";
		switch (age) {
			case FIRST:
				name = "Steam Age";
			case SECOND:
				name = "Combustion Engine Age";
			case THIRD:
				name = "Electrical Age";
			case FOURTH:
				name = "Futuristic Age";
			default:
				break;
		}
		return name;
	}

	@Override
	public String getDescription() {
		
		String description = "";
		switch (age) {
			case FIRST:
				description = ""; // Needs to be written
			case SECOND:
				description = ""; // Needs to be written
			case THIRD:
				description = ""; // Needs to be written
			case FOURTH:
				description = ""; // Needs to be written
			default:
				break;
			}
		return description;
	}

	@Override
	public String getImageFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Train> getTrains() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
