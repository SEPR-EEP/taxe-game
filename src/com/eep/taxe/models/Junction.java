package com.eep.taxe.models;

/**
 * This represents a simple Junction on the map.
 * A Junction is a particular type of Vertex that...
 * does not do much at the moment - but can be used
 * to implement the junction failures.
 * 
 * Differences between a Junction and a Station are:
 *  - Junctions do not have names
 *  - In some instances, such as Goals, only Stations can be used
 *
 */
public class Junction extends Vertex {

	private static final long serialVersionUID = 1508752708419913298L;

	/**
	 * Instantiates a Junction given the coordinates
	 * @param cx	X-Coordinate of the Junction
	 * @param cy	X-Coordinate of the Junction
	 */
	public Junction(int cx, int cy) {
		super(cx, cy);
	}
	
	/**
	 * Check if a Junction failure happened.
	 * Not yet implemented.
	 * @return	True if there is a Junction Failure
	 */
	public Boolean junctionFailure(){
		
		//TODO
		
		
		
		return false;
	}

}
