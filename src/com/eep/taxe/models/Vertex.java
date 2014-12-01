package com.eep.taxe.models;

import java.util.Vector;

public abstract class Vertex implements VertexInterface {
	

	private static final long serialVersionUID = -8107239853009459192L;
	
	private int				x, y;
	private Vector<Route>	list;
	
	/**
	 * Creates a Vertex, given the coordinates
	 * @param cx	The X-Coordinate of the Vertex
	 * @param xy	The Y-Coordinate of the Vertex
	 */
	public Vertex(int cx, int cy) {
		this.list = new Vector<Route>();
		this.setX(cx);
		this.setY(cy);
	}
	
	/**
	 * Gets the X-coordinate of the Vertex
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y-coordinate of the Vertex
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * Set the X-coordinate of the Vertex
	 */
	@Override
	public void setX(int newX) {
		this.x = newX;
	}

	/**
	 * Set the Y-coordinate of the Vertex
	 */
	@Override
	public void setY(int newY) {
		this.y = newY;
	}
	
	/**
	 * Add a Route to the adjacency list of the Vertex
	 * @param	A route containing this Vertex
	 * @return 	TRUE if the Route is valid and could be inserted
	 */
	public Boolean addRoute(Route route) {
		if ( !route.hasVertex(this) ) {
			return false;
		}
		return this.list.add(route);
	}
	
	/**
	 * Gets the adjacency list of the vertex (the list of Routes)
	 */
	public Vector<Route> getRoutes() {
		return this.list;
	}
	
	
}
