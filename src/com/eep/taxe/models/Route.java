package com.eep.taxe.models;

import java.util.Vector;

public class Route implements RouteInterface {

	private static final long serialVersionUID = 4282570702049403650L;
	
	/**
	 * The list of vertices
	 */
	private Vector<Vertex> vertices; 
	
	/**
	 * The length of the Route
	 */
	private int length = 0;
	
	/**
	 * Instantiate a new Route
	 */
	public Route() {
		this.vertices = new Vector<Vertex>();
	}
	
	/**
	 * Instantiate a new Route, with Vertices and Length
	 * @param 	from 	First Vertex
	 * @param	to		Second Vertex
	 * @param 	length	Length of the Vertex
	 */
	public Route(Vertex from, Vertex to, int length) {
		this.vertices = new Vector<Vertex>();
		this.addVertex(from);
		this.addVertex(to);
		this.setLength(length);
	}
	
	/**
	 * Compare with a second Route by length
	 * @param o 	A second route to compare this to
	 */
	@Override
	public int compareTo(Route o) {
		return 
				Integer.valueOf(this.getLength()).compareTo(
					Integer.valueOf(o.getLength())
				);
	}

	/**
	 * Gets the vector of two vertices
	 * @return A Vector of two vertices
	 */
	@Override
	public Vector<Vertex> getVertices() {
		return this.vertices;
	}

	/**
	 * Set the list of vertices of the route
	 * @param v 	A Vector of exactly 2 vertices
	 * @return 		TRUE if the vector is valid, FALSE otherwise
	 */
	@Override
	public Boolean setVertices(Vector<Vertex> v) {
		if (v.size() != 2) {
			return false;
		}
		for ( Vertex x : v ){
			this.addVertex(x);
		}
		return true;
	}

	/**
	 * Check if the route touches a given vertex
	 * @param o 	The Vertex to check for existence in the Route
	 * @return		TRUE if the Vertex is part of the Route, FALSE otherwise
	 */
	@Override
	public Boolean hasVertex(Vertex o) {
		return this.vertices.contains(o);
	}

	/**
	 * Adds a Vertex to the route
	 * @param 	v	The Vertex to add
	 * @return 		TRUE if insertion successful, FALSE otherwise
	 */
	@Override
	public Boolean addVertex(Vertex v) {
		if ( this.vertices.size() > 2 ) {
			return false;
		}
		return ( 
				v.addRoute(this) 
				&& this.vertices.add(v) 
		);
	}
	
	/**
	 * Given a Vertex in the route, return the other vertex
	 * @param	o	A Vertex of this Route
	 * @return 		The other Vertex, NULL if none or if the input Vertex does not belong to this route
	 */
	@Override
	public Vertex other(Vertex o) {
		// If the vertex is not in the route, return NULL
		if ( !this.hasVertex(o) ) {
			return null;
		}
		// If the Route does not have 2 vertices, return NULL
		if ( this.vertices.size() != 2 ) {
			return null;
		}
		// Check if the other is the first or the second element in the vector
		int other = this.vertices.indexOf(o);
		int index = other == 0 ? 1 : 0; 	// if other = 0, then 1, else 0
		return this.vertices.get(index);
	}

	/**
	 * Returns the length of the Route
	 */
	@Override
	public int getLength() {
		return this.length;
	}

	/**
	 * Sets the length of the route
	 */
	@Override
	public void setLength(int newLength) {
		this.length = newLength;
	}



}
