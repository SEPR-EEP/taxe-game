package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public class Path extends Vector<Edge> implements Serializable {
	
	private static final long serialVersionUID = 7893406019421382154L;
	
	private Station startingStation;
	private Station endingStation;
	private Vertex currentLastVertex = null;
	
	/**
	 * Calculate the total length of the Path
	 * @return	The total length of the path
	 */
	public int getTotalLength() {
		int t = 0;
		for ( Edge x: this ) {
			t += x.getLength();
		}
		return t;
	}
	
	/**
	 * Estimate total duration given a speed
	 * @param	speed	Speed of the train
	 * @return 	The duration of the journey
	 */
	public double getDuration(double speed) {
		return this.getTotalLength() / speed;
	}
	
	
	/**
	 * Estimate total duration at normal speed (1)
	 * @return 	The duration of the journey
	 */
	public double getDuration() {
		return this.getDuration(1.0);
	}
	
	public void setStartingStation(Station startingStation){
		this.startingStation = startingStation;
	}
	
	public void setEndingStation(Station endingStation){
		this.endingStation = endingStation;
	}
	
	public Station getStartingStation(){
		return this.startingStation;
	}
	
	public Station getEndingStation(){
		return this.endingStation;
	}
	
	public void setCurrentLastVertex(Vertex vertex){
		this.currentLastVertex = vertex;
	}
	
	public Vertex getCurrentLastVertex(){
		return this.currentLastVertex;
	}
	
	/*
	@Override
	public boolean add(Edge e){
		return super.add(e);
	}*/
	
	public boolean add(Vertex vertex){
		if (this.getCurrentLastVertex() == null && vertex instanceof Station){
			this.setStartingStation((Station) vertex);
			this.setCurrentLastVertex(vertex);
			return true;
		}
		
		Edge connectingEdge = this.getCurrentLastVertex().getEdge(vertex);
		
		if (connectingEdge != null) {
			this.add(connectingEdge);
			return true;
		}
		
		return false;
	}

	/**
	 * Returns the starting Vertex of the Edge with the given index
	 * @param edge	Index of the edge in the path
	 * @return		The Starting Vertex of this edge
	 */
	public Vertex startingVertexOfEdge(int edge) {
		
		// Out of range indices are ignored
		if ( edge < 0 || this.size() <= edge ) {
			return null;
		}
		
		if ( this.size() == 1 ) {
			return this.startingStation;
		}
		
		// If this is the last edge
		if ( edge == this.size() - 1 ) {
			// It is the vertex that is not the ending point
			return	this.get(edge).other(this.endingStation);
		}
		
		return this.get(edge).vertexNotInCommonWithAdjacentEdge(
			this.get(edge+1)
		);	
		
	}
	
	/**
	 * Returns the ending Vertex of the Edge with the given index
	 * @param edge	Index of the edge in the path
	 * @return		The Ending Vertex of this edge
	 */
	public Vertex endingVertexOfEdge(int edge) {
		
		// Out of range indices are ignored
		if ( edge < 0 || this.size() <= edge ) {
			return null;
		}
		
		if ( this.size() == 1 ) {
			return this.endingStation;
		}
		
		// If this is the first edge
		if ( edge == 0 ) {
			// It is the vertex that is not the ending point
			return	this.get(edge).other(this.startingStation);
		}
		
		return this.get(edge).vertexNotInCommonWithAdjacentEdge(
			this.get(edge-1)
		);	
		
	}
}
