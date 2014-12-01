package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public interface VertexInterface extends Serializable {

	public int				getX();						// Get the X-coordinate for the Vertex
	public int 				getY();						// Get the Y-coordinate for the Vertex
	public void 			setX(int newX);				// Set the X-coordinate for the Vertex
	public void 			setY(int newY);				// Set the Y-coordinate for the Vertex
	
	public Boolean 			addRoute(Route route);		// Add a route
	public Vector<Route>	getRoutes();				// Get the list of routes
	
	
}
