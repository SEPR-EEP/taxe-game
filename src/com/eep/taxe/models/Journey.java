package com.eep.taxe.models;

import java.io.Serializable;
import java.util.Vector;

public class Journey extends Path implements JourneyInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Train train;
	private Edge currentEdge;
	private float progressOnEdge;
	private float distanceTravelledOnEdge;
	private float distanceTravelledOnJourney;
	private int turnsElapsedSinceStart;
	private Boolean journeyStarted, journeyComplete, isMoving;
	
	/**
	 * Instantiates a Journey
	 * @param train The train that will be travelling on this journey
	 */
	public Journey(Train train){
		this.train = train;
		this.currentEdge = null;
		this.progressOnEdge = 0;
		this.distanceTravelledOnEdge = 0;
		this.distanceTravelledOnJourney = 0;
		this.turnsElapsedSinceStart = 0;
		this.journeyStarted = false;
		this.journeyComplete = false;
		this.isMoving = false;
		train.setJourney(this);
	}
	
	/**
	 * Create e Journey given a Train and a list of Vertices to add
	 * @param The Train for the Journey
	 * @param The List of vertices to add
	 */
	public Journey(Train train, Vector<Vertex> vertices) {
		this(train);
		for ( Vertex v: vertices ) {
			this.add(v);
		}
	}
	
	/**
	 * Check if the train is moving
	 * @return	True if the train is moving
	 */
	@Override
	public Boolean isMoving() {
		return (this.isMoving);
	}
	
	/**
	 * Check if the train's journey along the path is complete
	 * @return	True if the journey is complete
	 */
	@Override
	public Boolean isJourneyComplete(){
		if (getDistanceTravelledOnJourney() >= getTotalLength() ){
			this.journeyComplete = true;
			this.isMoving = false;
			this.distanceTravelledOnJourney = getTotalLength();	//So distance travelled does not exceed length of journey
			
		}
		return this.journeyComplete;
	}
	
	/**
	 * Check if the train's journey along the path has started yet
	 * @return	True if the journey has started
	 */
	@Override
	public Boolean isJourneyStarted(){
		return this.journeyStarted;
	}

	/**
	 * Get the current edge the train is travelling on
	 * @return	Edge the train is travelling on
	 */
	@Override
	public Edge getCurrentEdge() {
		return currentEdge;
	}

	/**
	 * Get the edge that comes after the edge the train is travelling on
	 * @return	Next edge that the train will travel on
	 */
	@Override
	public Edge getNextEdge(){
		
		if (! this.isEmpty() && currentEdge != this.lastElement()){
		
			int nextEdgeIndex = this.indexOf(currentEdge) + 1; //Get vector position of next edge
			return (this.get(nextEdgeIndex) );
		}
		
		return null;
	}
	
	
	/**
	 * Set the current edge to be the first edge and set the train to isMoving
	 */
	public void start() {
		if ( !this.isEmpty() ) {
			this.currentEdge = this.get(0);
			this.isMoving    = true;
			this.journeyStarted = true;
		}
	}
	
	/**
	 * Set the current edge the train is travelling on
	 * @param	edge				Edge to make current edge in journey
	 * @param	initialProgress		Distance already travelled on edge
	 */
	@Override
	public void setCurrentEdge(Edge edge, float initialProgress) {
		
		if (edge != null){
			this.currentEdge = edge;
			this.distanceTravelledOnEdge = initialProgress;
			
			//If the current edge's distance is less than or equal to initial progress set current edge to next
			while (this.currentEdge.getLength() <= initialProgress){
				setCurrentEdge(getNextEdge(), initialProgress - this.currentEdge.getLength() );
			}
		}
	}

	/**
	 * Get the progress of the train's distance on the edge
	 * @return	A value between 0 and 1
	 */
	@Override
	public float getProgressOnEdge() {
		return progressOnEdge;
	}
	
	/**
	 * Get the distance travelled by the train on the current edge
	 * @return	Distance travelled on current edge
	 */
	@Override
	public float getDistanceTravelledOnEdge(){
		return distanceTravelledOnEdge;
	}
	
	/**
	 * Get the distance travelled by the train on the journey so far
	 * @return	Distance travelled on journey
	 */
	@Override
	public float getDistanceTravelledOnJourney(){
		return distanceTravelledOnJourney;
	}
	
	/**
	 * Get the number of turns that the journey has so far taken
	 * @return	Number of turns
	 */
	@Override
	public int getTurnsElapsedSinceStart(){
		return this.turnsElapsedSinceStart;
	}
	
	public Train getTrain(){
		return this.train;
	}
	
	/**
	 * Increment a train's progress on an edge for a turn.
	 * If the train's distance travelled exceeds the length of the current edge, move on to the next edge in the path
	 * */
	@Override
	public void incrementProgressByTurn() {
		
		//If progress cannot be incremented
		if (this.isEmpty() || this.isJourneyComplete() ){
			this.isMoving = false;
			return;
		}
		
		//Start journey if not yet started
		if (! isJourneyStarted() ) {
			this.start();
		}
		
		this.isMoving = true;
		this.turnsElapsedSinceStart++;
		
		int lengthOfEdge = this.getCurrentEdge().getLength();
		this.distanceTravelledOnEdge += train.getSpeed();	//Speed is distance travelled per turn
		this.distanceTravelledOnJourney += train.getSpeed();
		
		//If the train has travelled beyond the current edge
		if (this.distanceTravelledOnEdge >= lengthOfEdge){
				
			float remainingProgress = this.distanceTravelledOnEdge - lengthOfEdge; //Remaining progress is carried through to next edge
			setCurrentEdge(this.getNextEdge(), remainingProgress);
			lengthOfEdge = this.getCurrentEdge().getLength(); //Update to be length of next edge
		}
			
		this.progressOnEdge = this.distanceTravelledOnEdge / lengthOfEdge;
		this.isJourneyComplete();
	}
	
	/**
	 * Get the 
	 */
	public Vertex getLastVisitedVertex() {
		if ( this.currentEdge == null ) {
			return this.getStartingStation();
		}
		
		return this.getStartingVertexOfEdge(this.currentEdge);
	}

	
}
