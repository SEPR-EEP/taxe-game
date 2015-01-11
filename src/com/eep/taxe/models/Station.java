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
	
	/**
	 * Prints adjacent stations (and routes)
	 */
	public void printAdjacentStations() {
		
		for ( Edge e: this.getEdges() ) {
			Station s1 = null, s2 = null;
			if ( e.getVertices().size() >= 1 ) {
				s1 = (Station) e.getVertices().get(0);
			}
			if ( e.getVertices().size() >= 2 ) {
				s2 = (Station) e.getVertices().get(1);
			}
			System.out.println("- " + e.getLength() + " km path from " +
					(s2 == null ? "NOTHING" : s1.getName() ) + " to " + (s2 == null ? "NOTHING" : s2.getName()));
		
		}
	}
}
