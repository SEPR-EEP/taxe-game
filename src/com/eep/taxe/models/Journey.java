package com.eep.taxe.models;

import java.io.Serializable;

public class Journey extends Path implements JourneyInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Train train;
	private Edge currentEdge;
	private float progressOnEdge;
	private float distanceTravelledOnEdge;
	private float distanceTravelledOnJourney;
	private Boolean journeyStarted, journeyComplete;
	
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
		this.journeyStarted = false;
		this.journeyComplete = false;
	}
	
	/**
	 * Check if the train is moving
	 * @return	True if the train is moving
	 */
	@Override
	public Boolean isMoving() {
		return (train.getSpeed() == 0);
	}
	
	/**
	 * Check if the train's journey along the path is complete
	 * @return	True if the journey is complete
	 */
	@Override
	public Boolean isJourneyComplete(){
		if (getDistanceTravelledOnJourney() >= getTotalLength() ){
			this.journeyComplete = true;
		}
		return this.journeyComplete;
	}
	
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
	 * Start the train's journey and increment its progress for the first turn
	 */
	@Override
	public void startJourney(){
		if ( ! isJourneyStarted() && ! this.isEmpty()){
			this.journeyStarted = true;
			this.currentEdge = this.firstElement();
			incrementProgressOnEdge();
		}
	}
	
	
	/**
	 * Increment a train's progress on an edge for a turn.
	 * The train's journey must be started first
	 * If the train's distance travelled exceeds the length of the current edge, move on to the next edge in the path
	 * */
	@Override
	public void incrementProgressOnEdge() {
		int lengthOfEdge = this.getCurrentEdge().getLength();
		this.distanceTravelledOnEdge += train.getSpeed();	//Speed is distance travelled per turn
		this.distanceTravelledOnJourney += train.getSpeed();
		
		if (! isJourneyComplete() && isJourneyStarted()){
			
			if (distanceTravelledOnEdge >= lengthOfEdge){
				
				float initialProgress = distanceTravelledOnEdge - lengthOfEdge; //Progress is carried through to next edge
				
				setCurrentEdge(this.getNextEdge(), initialProgress);
				lengthOfEdge = this.getCurrentEdge().getLength(); //Update to be length of next edge
			}
			
			this.progressOnEdge = this.distanceTravelledOnEdge / lengthOfEdge;
		}
		
	}
	
	
	
	
	

}
