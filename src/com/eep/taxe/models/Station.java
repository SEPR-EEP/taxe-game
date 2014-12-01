package com.eep.taxe.models;

public class Station extends Vertex implements StationInterface {

	private static final long serialVersionUID = -8649295820110021862L;
	
	/**
	 * The Name of the Station
	 */
	private String name;
	
	/**
	 * Creates a Station
	 * @param 	X		The X-Coordinate of the Station
	 * @param	Y 		The Y-Coordinate of the Station
	 * @param	name	The name of the Station
	 */
	public Station(int X, int Y, String name) {
		super(X, Y);
		this.setName(name);
	}
	
	/**
	 * Return the name of the Station
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the Station
	 * @param newName	The new name
	 */
	@Override
	public void setName(String newName) {
		this.name = newName;
	}
	
	
}
