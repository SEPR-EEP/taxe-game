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
 * be compared to another Gold object using the Gold.compareTo(Gold) method.
 */
public class Gold extends Spendable {

	private static final long serialVersionUID = 5922318445995842094L;
	
	private final static String GOLD_IMG = "images/gold.png";
	
	/**
	 * Instantiates a Gold Spendable Resource
	 */
	public Gold() {
		super("Gold", GOLD_IMG);
	}

}
