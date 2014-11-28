package com.eep.taxe.models;

import java.io.Serializable;

public interface VertexInterface extends Serializable {

	public int		getX();						// Get the X-coordinate for the Vertex
	public int 		getY();						// Get the Y-coordinate for the Vertex
	public void 	setX(int newX);				// Set the X-coordinate for the Vertex
	public void 	setY(int newY);				// Set the Y-coordinate for the Vertex
	
}
