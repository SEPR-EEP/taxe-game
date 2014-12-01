package com.eep.taxe.models;

public abstract class Resource implements ResourceInterface {
	
	private static final long serialVersionUID = -347324730007983264L;
	
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
