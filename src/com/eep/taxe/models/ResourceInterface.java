package com.eep.taxe.models;

import java.io.Serializable;

public interface ResourceInterface extends Serializable {

	public String 				getName();					// Get the name of the Resource
	public void					setName(String newName);	// Set the name of the Resource
	
	public String 				getImage();					// Get the Image URI for the Resource
	public void					setImage(String newImage);	// Set the Image URI for the Resource
		
}
