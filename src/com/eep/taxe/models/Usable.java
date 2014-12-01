package com.eep.taxe.models;

public abstract class Usable extends Resource implements UsableInterface{
	
	private int costInGold;
	private int costInMetal;
	private Age age;

	/**
	 * Instantiates a Usable Resource
	 * @param name Name of Resource
	 * @param image Image URI for the Resource
	 * @param costInGold Cost in Gold for the Resource
	 * @param costInMetal Cost in Metal for the Resource
	 * @param age Associated Age for the Resource
	 */
	public Usable(String name, String image, int costInGold, int costInMetal, Age age) {
		super(name, image);
		this.costInGold = costInGold;
		this.costInMetal = costInMetal;
		this.age = age;
	}
	
	/** Get the cost in Gold for the Resource */
	@Override
	public int getCostInGold() {
		return costInGold;
	}
	
	/** Set the cost in Gold for the Resource */
	@Override
	public void setCostInGold(int newCost) {
		this.costInGold = newCost;
	}

	/** Get the cost in Metal for the Resource */
	@Override
	public int getCostInMetal() {
		return costInMetal;
	}

	/** Set the cost in Metal for the Resource */
	@Override
	public void setCostInMetal(int newCost) {
		this.costInMetal = newCost;
	}

	/** Get the associated Age for the Resource */
	@Override
	public Age getAge() {
		return age;
	}

	/** Set the associated Age for the Resource */
	@Override
	public void setAge(Age age) {
		this.age = age;
	}
	
}
