package com.eep.taxe.models;

public interface UsableInterface extends ResourceInterface {

	public int				getCostInGold();				// Get the cost in Gold for the Resource
	public void 			setCostInGold(int newCost);		// Set the cost in Gold for the Resource
	
	public int 				getCostInMetal();				// Get the cost in Metal for the Resource
	public void				setCostInMetal(int newCost);	// Set the cost in Metal for the Resource
	
}
