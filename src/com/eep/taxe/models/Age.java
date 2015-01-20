package com.eep.taxe.models;

import java.util.Vector;

/**
 * Represents an Age in the Game, such as the Stone Age.
 * It is used to easily categorise resources, trains, and whatever you
 * may want to relate to a particular Age in the game.
 */
public class Age implements AgeInterface {

	private static final long serialVersionUID = 2712498058645722176L;

	/**
	 * The internal representation of an Age is done using the Ages 
	 * enumerator type. It is easier to carry around a enumerator type
	 * instead of an instance of the Age class. Age objects are to
	 * be constructed when necessary. If you need to store an Age as
	 * a property, use the following Ages type and call the constructor
	 * of this class that accepts it as an argument.
	 */
	public static enum Ages {
		FIRST, SECOND, THIRD, FOURTH;
	}
	
	public Ages age;
	
	/**
	 * How long does an Age last? Every time the player accomplishes
	 * N * AGE_DURATION goals, the current age is incremented.
	 */
	private static final int 	AGE_DURATION = 3;
	
	/**
	 * Compares age of this object to age of parameter object.
	 * @param	The other object.
	 * @return 	0 if they are equal
	 *         	1 if age of this object is greater than the age of the parameter object
	 *         	-1 if age of this object is less than the age of the parameter object
	 */
	@Override
	public int compareTo(Age o) {
		switch (age) {
			case FIRST:
				switch (o.age) {
					case FIRST:
						return 0;
					case SECOND:
						return -1;
					case THIRD:
						return -1;
					case FOURTH:
						return -1;
					default:
						break;
				}
			case SECOND:
				switch (o.age) {
					case FIRST:
						return 1;
					case SECOND:
						return 0;
					case THIRD:
						return -1;
					case FOURTH:
						return -1;
					default:
						break;
				}
			case THIRD:
				switch (o.age) {
					case FIRST:
						return 1;
					case SECOND:
						return 1;
					case THIRD:
						return 0;
					case FOURTH:
						return -1;
					default:
						break;
				}
			case FOURTH:
				switch (o.age) {
					case FIRST:
						return 1;
					case SECOND:
						return 1;
					case THIRD:
						return 1;
					case FOURTH:
						return 0;
					default:
						break;
				}
			default:
				return 0;
			}
	}

	/**
	 * Creates an Age object given the number of goals completed.
	 * @param goalsCompleted	The number of goals completed by the player.
	 */
	public Age(int goalsCompleted) {
		
		if (goalsCompleted < AGE_DURATION) {
			this.age = Ages.FIRST;
		}
		else if (goalsCompleted >= AGE_DURATION && goalsCompleted < AGE_DURATION*2) {
			this.age = Ages.SECOND;
		}
		else if (goalsCompleted >= AGE_DURATION*2 && goalsCompleted < AGE_DURATION*3) {
			this.age = Ages.THIRD;
		}
		else {
			this.age = Ages.FOURTH;
		}
	}

	/**
	 * Creates an Age object given the corresponding Ages enum type.
	 * @param age	The current Age as an Ages enum variable.
	 */
	public Age(Ages age) {
		
		switch (age) {
			case FIRST:
				this.age = Ages.FIRST;
				break;
			case SECOND:
				this.age = Ages.SECOND;
				break;
			case THIRD:
				this.age = Ages.THIRD;
				break;
			case FOURTH:
				this.age = Ages.FOURTH;
				break;
			default:
				break;
		}
	}

	@Override
	public String getName() {
		
		String name = "Strange Age";
		switch (age) {
			case FIRST:
				name = "Steam Age";
				break;
			case SECOND:
				name = "Combustion Engine Age";
				break;
			case THIRD:
				name = "Electrical Age";
				break;
			case FOURTH:
				name = "Futuristic Age";
				break;
			default:
				break;
		}
		return name;
	}

	/**
	 * Gets a friendly long description for the Age.
	 */
	@Override
	public String getDescription() {
		
		String description = "";
		switch (age) {
			case FIRST:
				description = ""; // TODO Needs to be written
				break;
			case SECOND:
				description = ""; // TODO Needs to be written
				break;
			case THIRD:
				description = ""; // TODO Needs to be written
				break;
			case FOURTH:
				description = ""; // TODO Needs to be written
				break;
			default:
				break;
			}
		return description;
	}

	@Override
	public String getImageFile() {
		// TODO If you want to add images, in the future.
		return null;
	}

	@Override
	public Vector<Train> getTrains() {
		// TODO This should really call the corresponding static method in Generator.
		return null;
	}
	
}
