package com.eep.taxe.models;

import java.util.Vector;

/**
 * Represents an abstract Vertex on the map graph.
 * This class can't be instantiated.
 * Sub classes are Stations and Junctions.
 */
public abstract class Vertex implements VertexInterface {
	

	private static final long serialVersionUID = -8107239853009459192L;
	
	private int				x, y;
	private Vector<Edge>	edges;
	
	/**
	 * Creates a Vertex, given the coordinates
	 * @param cx	The X-Coordinate of the Vertex
	 * @param xy	The Y-Coordinate of the Vertex
	 */
	public Vertex(int cx, int cy) {
		this.edges = new Vector<Edge>();
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
	 * Add an Edge to the adjacency list of the Vertex
	 * @param	An Edge containing this Vertex
	 * @return 	TRUE if the Edge is valid and could be inserted
	 */
	public boolean addEdge(Edge edge) {
		if ( !edge.hasVertex(this) ) {
			return false;
		}
		return this.edges.add(edge);
	}
	
	/**
	 * Gets the adjacency list of the vertex (the list of Edges)
	 */
	public Vector<Edge> getEdges() {
		return this.edges;
	}
	
	/**
	 * Gets the Edge to a given Vertex (or null if none)
	 * @param 	The target Vertex
	 */
	public Edge getEdge(Vertex target) {
		if ( target == this ) {
			return null;
		}
		for ( Edge e : this.getEdges() ) {
			if (e.hasVertex(target)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Gets the name of the vertex (either a Junction or a Station)
	 */
	public String getVertexName() {
		if ( this instanceof Station ) {
			return ((Station)this).getName();
		} else {
			return "Junction";
		}
	}

}
