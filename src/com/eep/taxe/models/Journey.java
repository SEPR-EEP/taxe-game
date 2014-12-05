package com.eep.taxe.models;

public class Journey extends Path implements JourneyInterface {

	private float trainSpeed;
	private Edge currentEdge;
	private float progressOnEdge;
	private int distanceTravelledOnEdge;
	
	public Journey(float trainSpeed, Edge currentEdge){
		this.trainSpeed = trainSpeed;
		this.currentEdge = currentEdge;
		this.progressOnEdge = 0;
		this.distanceTravelledOnEdge = 0;
	}
	
	@Override
	public Boolean isMoving() {
		return (getTrainSpeed() != 0);
	}

	@Override
	public float getTrainSpeed() {
		return this.trainSpeed;
	}

	@Override
	public void setTrainSpeed(float trainSpeed) {
		this.trainSpeed = trainSpeed;
	}

	@Override
	public Edge getCurrentEdge() {
		return currentEdge;
	}

	@Override
	public void setCurrentEdge(Edge currentEdge) {
		this.currentEdge = currentEdge;
		
	}

	@Override
	public float getProgressOnEdge() {
		return progressOnEdge;
	}

	@Override
	public void incrementProgressOnEdge() {
		int lengthOfEdge = this.getCurrentEdge().getLength();
		this.distanceTravelledOnEdge += this.getTrainSpeed();
		this.progressOnEdge = this.distanceTravelledOnEdge / lengthOfEdge;
	}

}
