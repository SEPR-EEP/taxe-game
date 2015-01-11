package com.eep.taxe.models;

import java.io.Serializable;

public class Journey extends Path implements JourneyInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Train train;
	private Edge currentEdge;
	private float progressOnEdge;
	private float distanceTravelledOnEdge;
	private Boolean journeyComplete;
	
	public Journey(Train train){
		this.train = train;
		this.currentEdge = this.firstElement();
		this.progressOnEdge = 0;
		this.distanceTravelledOnEdge = 0;
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
		if (currentEdge == this.lastElement()){
			this.journeyComplete = true;
		}
		return this.journeyComplete;
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
	 * Set the current edge the train is travelling on
	 * @param	edge				Edge to make current edge in journey
	 * @param	initialProgress		Distance already travelled on edge
	 */
	@Override
	public void setCurrentEdge(Edge edge, float initialProgress) {
		this.currentEdge = edge;
		this.distanceTravelledOnEdge = initialProgress;
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
	 * Increment a train's progress on an edge.
	 * If the train's distance travelled exceeds the length of the current edge, move on to the next edge in the path
	 * */
	@Override
	public void incrementProgressOnEdge() {
		int lengthOfEdge = this.getCurrentEdge().getLength();
		this.distanceTravelledOnEdge += train.getSpeed();	//Speed is distance travelled per turn
		
		if (! isJourneyComplete()){
			
			if (distanceTravelledOnEdge > lengthOfEdge){
				
				int nextEdgeIndex = this.indexOf(currentEdge) + 1; //Get vector position of next edge
				float initialProgress = distanceTravelledOnEdge - lengthOfEdge; //Progress to pass through to next edge
				setCurrentEdge(this.get(nextEdgeIndex), initialProgress);
			}
			
			this.progressOnEdge = this.distanceTravelledOnEdge / lengthOfEdge;
			
		}
		
	}

}
