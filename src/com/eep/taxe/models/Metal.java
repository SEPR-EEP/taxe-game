package com.eep.taxe.models;

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
