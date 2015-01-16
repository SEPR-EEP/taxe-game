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
			return true;
		}
		
		return false;
	}
	
}
