package com.eep.taxe.models;

public abstract class Spendable extends Resource {
	
	private int quantity;
	
	/**
	 * Instantiates a Spendable Resource
	 * @param name Name of Resource
	 * @param image Image URI for the Resource
	 * @param quantity Quantity of the Spendable Resource
	 */
	public Spendable(String name, String image, int quantity) {
		super(name, image);
		this.setQuantity(quantity);
	}

	/** Get Quantity of the Spendable Resource */
	public int getQuantity() {
		return quantity;
	}

	/** 
	 * Set Quantity of the Spendable Resource
	 * @param quantity  Quantity of the Spendable Resource
	 * */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/** Increment Quantity of the Spendable Resource */
	public void incrementQuantity(){
		this.quantity++;
	}

	/** Decrement Quantity of the Spendable Resource */
	public void decrementQuantity(){
		this.quantity--;
	}

}
