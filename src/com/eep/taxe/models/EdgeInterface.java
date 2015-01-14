package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface EdgeInterface extends Comparable<Edge>, Serializable {
	
	public Vector<Vertex>		getVertices();					// Get the list of 2 vertices of the edge
	public Vertex 				getStartingVertex();			// Get the starting vertex of the edge
	public Vertex 				getEndingVertex();				// Get the ending vertex of the edge
	public boolean				setVertices(Vector<Vertex> v);	// Set the list of 2 vertices of the edge
	public boolean				addVertex(Vertex v);			// Add a Vertex to the route (max. 2)
	
	public boolean				hasVertex(Vertex o);			// Tell if this edge contains a given vertex
	public Vertex 				other(Vertex o);				// Given a Vertex of the edge, return the other
	
	public int					getLength();					// Get the length of the edge
	public void					setLength(int newLength);		// Set the length of the edge

}
