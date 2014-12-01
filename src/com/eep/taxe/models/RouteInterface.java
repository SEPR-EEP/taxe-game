package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface RouteInterface extends Comparable<Route>, Serializable {
	
	public Vector<Vertex>		getVertices();					// Get the list of 2 vertices of the route
	public Boolean				setVertices(Vector<Vertex> v);	// Set the list of 2 vertices of the route
	public Boolean				addVertex(Vertex v);			// Add a Vertex to the route (max. 2)
	
	public Boolean				hasVertex(Vertex o);			// Tell if this route contains a given vertex
	public Vertex 				other(Vertex o);				// Given a Vertex of the route, return the other
	
	public int					getLength();					// Get the length of the route
	public void					setLength(int newLength);		// Set the length of the route

}
