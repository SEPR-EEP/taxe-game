package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public class Path extends Vector<Edge> implements Serializable {
	
	private static final long serialVersionUID = 7893406019421382154L;
	
	private Station startingStation;
	private Station endingStation;
	private Vertex currentLastVertex = null;
	
	
	public Path() {
		// Main constructor
		// ... does nothing.
	}

	/**
	 * ALTERNATIVE CONSTRUCTOR
	 * Create given a shortest path and a starting point
	 */
	public Path(Path otherPath, Vertex startingPoint) {
		for ( Vertex v: otherPath.getVerticesInOrder(startingPoint) ) {
			this.add(v);
		}
	}
	
	public Path(Vector<Vertex> vertices) {
		for ( Vertex v: vertices ) {
			this.add(v);
		}
	}

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
		//If first vertex has not yet been added
		if (this.getCurrentLastVertex() == null){
			
			if (vertex instanceof Station){
				this.setStartingStation((Station) vertex);
				this.setCurrentLastVertex(vertex);
				return true;
			}
			
			return false; // First vertex must be a station
			
		}
		
		//Else find connecting edge with last vertex in journey
		Edge connectingEdge = this.getCurrentLastVertex().getEdge(vertex);
		
		if (connectingEdge != null) {
			this.add(connectingEdge);
			this.setCurrentLastVertex(vertex);
			
			//If this vertex is a station set to ending station
			if (vertex instanceof Station){
				this.setEndingStation((Station) vertex); 
			}
			
			return true;
		}
		
		return false;
	}

	/**
	 * Returns the starting Vertex of the Edge with the given index
	 * @param edge	Edge in the path
	 * @return		The Starting Vertex of this edge
	 */
	public Vertex getStartingVertexOfEdge(Edge edge) {

		if ( !this.contains(edge) ) {
			return null;
		}
		
		if ( this.size() == 1 ) {
			return this.startingStation;
		}
		
		// If this is the last edge
		if ( this.lastElement() == edge ) {
			// It is the vertex that is not the ending point
			return	edge.other(this.endingStation);
		}
		
		return edge.vertexNotInCommonWithAdjacentEdge(
			this.get(indexOf(edge) + 1)
		);	
		
	}
	
	/**
	 * Returns the ending Vertex of the Edge with the given index
	 * @param edge	The edge in the path
	 * @return		The Ending Vertex of this edge
	 */
	public Vertex getEndingVertexOfEdge(Edge edge) {
		
		if ( !this.contains(edge) ) {
			return null;
		}
		
		if ( this.size() == 1 ) {
			return this.endingStation;
		}
		
		// If this is the first edge
		if ( this.get(0) == edge ) {
			// It is the vertex that is not the ending point
			return	edge.other(this.startingStation);
		}
		
		return edge.vertexNotInCommonWithAdjacentEdge(
			this.get(indexOf(edge) - 1)
		);	
		
	}
	
	/**
	 * Get Vertices in order for the path -
	 * Starting vertex is provided in case the path is only
	 * one edge long, in that case is used to determine first vertex.
	 */
	public Vector<Vertex> getVerticesInOrder(Vertex startingPoint) {
		Vector<Vertex> r = new Vector<Vertex>();
		
		if ( this.size() == 0 ) {
			return r;
		}
		
		if ( this.size() == 1 ) {

			if ( !this.get(0).hasVertex(startingPoint) ) {
				return r;
			}
			r.add(startingPoint);
			r.add(this.get(0).other(startingPoint));
			return r;
		}
		
		r.add(startingPoint);						// Add the starting point of the first edge
		for ( Edge e: this ) {						// Add the last vertex of each edge
			r.add(
					e.other(r.lastElement())
			);
		}
		
		return r;
	}
}
