package com.eep.taxe.models;

import java.util.Vector;

public class Edge implements EdgeInterface {

	private static final long serialVersionUID = 4282570702049403650L;
	
	/**
	 * The list of vertices
	 */
	private Vector<Vertex> vertices; 
	
	/**
	 * The length of the edge
	 */
	private int length = 0;
	
	/**
	 * Instantiate a new edge
	 */
	public Edge() {
		this.vertices = new Vector<Vertex>();
	}
	
	/**
	 * Instantiate a new edge, with Vertices and Length
	 * @param 	from 	First Vertex
	 * @param	to		Second Vertex
	 * @param 	length	Length of the Vertex
	 */
	public Edge(Vertex from, Vertex to, int length) {
		this.vertices = new Vector<Vertex>();
		this.addVertex(from);
		this.addVertex(to);
		this.setLength(length);
	}
	
	/**
	 * Compare with a second edge by length
	 * @param o 	A second edge to compare this to
	 */
	@Override
	public int compareTo(Edge o) {
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
	 * Gets the starting/source vertex of the edge
	 * @return Starting vertex
	 */
	@Override
	public Vertex getStartingVertex(){
		if (! this.vertices.isEmpty()){
			return this.vertices.firstElement();
		}
		return null;
	}
	
	/**
	 * Gets the ending/destination vertex of the edge
	 * @return Ending vertex
	 */
	@Override
	public Vertex getEndingVertex(){
		if (! this.vertices.isEmpty()){
			return this.vertices.lastElement();
		}
		return null;
	}

	/**
	 * Set the list of vertices of the edge
	 * @param v 	A Vector of exactly 2 vertices
	 * @return 		TRUE if the vector is valid, FALSE otherwise
	 */
	@Override
	public boolean setVertices(Vector<Vertex> v) {
		if (v.size() != 2) {
			return false;
		}
		for ( Vertex x : v ){
			this.addVertex(x);
		}
		return true;
	}

	/**
	 * Check if the edge touches a given vertex
	 * @param o 	The Vertex to check for existence in the edge
	 * @return		TRUE if the Vertex is part of the edge, FALSE otherwise
	 */
	@Override
	public boolean hasVertex(Vertex o) {
		return this.vertices.contains(o);
	}

	/**
	 * Adds a Vertex to the edge
	 * @param 	v	The Vertex to add
	 * @return 		TRUE if insertion successful, FALSE otherwise
	 */
	@Override
	public boolean addVertex(Vertex v) {
		if ( this.vertices.size() > 2 ) {
			return false;
		}
		return this.vertices.add(v) && v.addEdge(this);
	}
	
	/**
	 * Given a Vertex in the edge, return the other vertex
	 * @param	o	A Vertex of this Edge
	 * @return 		The other Vertex, NULL if none or if the input Vertex does not belong to this edge
	 */
	@Override
	public Vertex other(Vertex o) {
		// If the vertex is not in the edge, return NULL
		if ( !this.hasVertex(o) ) {
			return null;
		}
		// If the edge does not have 2 vertices, return NULL
		if ( this.vertices.size() != 2 ) {
			return null;
		}
		// Check if the other is the first or the second element in the vector
		int other = this.vertices.indexOf(o);
		int index = other == 0 ? 1 : 0; 	// if other = 0, then 1, else 0
		return this.vertices.get(index);
	}

	/**
	 * Returns the length of the edge
	 */
	@Override
	public int getLength() {
		return this.length;
	}

	/**
	 * Sets the length of the edge
	 */
	@Override
	public void setLength(int newLength) {
		this.length = newLength;
	}

	/**
	 * Pritns the name of the stations aroud 
	 */
	public void printStationNames() {
		for (Vertex x: this.getVertices()) {
			Station s = (Station) x;
			System.out.println("  - " + s.getName());
		}
	}


}
