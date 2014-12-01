package com.eep.taxe.models;

public abstract class Spendable extends Resource {

	/**
	 * Instantiates a Spendable Resource
	 * @param name Name of Resource
	 * @param image Image URI for the Resource
	 */
	public Spendable(String name, String image) {
		super(name, image);
	}

}
