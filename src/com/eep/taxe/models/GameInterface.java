package com.eep.taxe.models;

import java.io.Serializable;

import com.eep.taxe.GameClient;
import com.eep.taxe.models.Game.Difficulty;

public interface GameInterface extends AgeRelatedInterface, Serializable {

	public Player 			getMasterPlayer();
	public Player 			getSlavePlayer();
	public Player 			getPlayerByRole(GameClient.Role r);

	public Difficulty		getDifficulty();				// Get the difficulty of the game
	public void 			setDifficulty(Difficulty d);	// Set the difficulty of the game
	
	public String 			getName();						// Get the name of the game
	public void 			setName(String newName);		// Set the name of the game
	
	public int 				currentTurn(); 					// Get the counter for the current turn 
	public GameClient.Role 	currentRole();					// Get the current half of the turn, in order:
															// GameClient.Role.SLAVE or GameClient.Role.MASTER
	public void 			endTurn();						// Compute turn data and increment current role/turn
	
	public Boolean 			hasFinished();					// Compute if the current game has finished
	
	
	
	
}
