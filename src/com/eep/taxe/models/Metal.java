package com.eep.taxe.models;

/**
 * Represents the Gold of a player. 
 * 
 * Please note that being a Spendable, this also has a quantity.
 * In fact, each player only has a single Gold instance.
 * 
 * The quantity is stored as a property - see 'Spendable' for the 
 * quantity methods.
 * 
 * This object implements the Comparable interface, so it can easily
 * be compared to another Metal object using the Gold.compareTo(Metal) method.
 */
public class Metal extends Spendable {

	private static final long serialVersionUID = -304393010412479724L;
	
	private final static String METAL_IMG = "images/metal.png";

	/**
	 * Instantiates a Metal Spendable Resource
	 */
	public Metal() {
		super("Metal", METAL_IMG);
	}

}
