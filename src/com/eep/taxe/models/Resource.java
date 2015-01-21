package com.eep.taxe.models;

/**
 * Represents a generic resource of the game.
 * 
 * This is an abstract class and can't be instantiated.
 * Sub classes are Usable resources and Spendable resources.
 * 
 * It contains generic methods to get the name and the image
 * of a resource.
 */
@SuppressWarnings("serial")
public abstract class Resource implements ResourceInterface {
	
	private String name;
	private String image;
	
	/**
	 * Instantiates a Resource
	 * @param name Name of Resource
	 * @param image Image URI for the Resource
	 */
	public Resource(String name, String image){
		this.name = name;
		this.image = image;
	}
	
	/** Get the name of the Resource */
	@Override
	public String getName(){
		return name;
	}
	
	/** Set the name of the Resource */
	@Override
	public void setName(String newName){
		this.name = newName;
	}
	
	/** Get the Image URI for the Resource */
	@Override
	public String getImage(){
		return image;
	}
	
	/** Set the Image URI for the Resource */
	@Override
	public void setImage(String newImage){
		this.image = newImage;
	}
	
}
