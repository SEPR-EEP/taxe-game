package com.eep.taxe.models;

import java.io.Serializable;

public interface StationInterface extends VertexInterface, Serializable {
	
	public String			getName();					// Get the name of the Station
	public void				setName(String newName);	// Set the name of the Station
	
}
