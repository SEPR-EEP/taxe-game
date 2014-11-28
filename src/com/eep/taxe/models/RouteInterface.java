package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface RouteInterface extends Comparable<Route>, Serializable {
	
	public Vector<Vertex>		getVertices();					// Get the list of 2 vertices of the route
	public void					setVertices(Vector<Vertex> v);	// Set the list of 2 vertices of the route
	
	public int					getLength();					// Get the length of the route
	public void					setLength(int newLength);		// Set the length of the route

}
