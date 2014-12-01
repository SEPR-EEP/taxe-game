package com.eep.taxe.models;

import java.util.Vector;

import com.eep.taxe.GameClient.Role;


public class Game extends com.eep.taxe.GameData implements GameInterface {

	private static final long serialVersionUID = -1630426694034385387L;

	public static enum Difficulty {
		EASY, MEDIUM, HARD;
	}
	
	private Player master, slave;
	private Difficulty difficulty;
	private String name;
	private Vector<Vertex> vertices;
	
	private int		currentTurn	= 0;
	private Role	currentRole;
	
	/**
	 * Gets the "Master" Player for the Game
	 * @return Player object
	 */
	@Override
	public Player getMasterPlayer() {
		return this.master;
	}

	/**
	 * Gets the "Slave" Player for the Game
	 * @return Player object
	 */
	@Override
	public Player getSlavePlayer() {
		return this.slave;
	}

	/**
	 * Given a Role, returns a Player object
	 * @param 	r 	The role of the Player to get
	 * @return 		The Player for that role
	 */
	@Override
	public Player getPlayerByRole(Role r) {
		if ( r == Role.MASTER ) {
			return this.getMasterPlayer();
		} else {
			return this.getSlavePlayer();
		}
	}

	/**
	 * Get the Difficulty of the Game
	 */
	@Override
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Return Set the difficulty of the game
	 * @param	d	New Difficulty
	 */
	@Override
	public void setDifficulty(Difficulty d) {
		this.difficulty = d;
	}

	/**
	 * Get the name of the current game
	 * @return 	The name of the current game
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name for the current Game
	 * @param	newName	The name of the current game
	 */
	@Override
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * 
	 */
	@Override
	public void endTurn() {
		
		// TODO Game logic
		
		// Finally, increment role and turn
		this.incrementRoleAndTurn();
		
	}
	
	/**
	 * Increment the Role and the Turn
	 */
	private void incrementRoleAndTurn() {
		if ( this.getCurrentRole() == Role.SLAVE ) {
			this.setCurrentRole(Role.MASTER);
		} else {
			this.setCurrentRole(Role.SLAVE);
			this.incrementTurn();
		}
	}

	/**
	 * Increments the internal current turn counter by 1
	 */
	private void incrementTurn() {
		this.setCurrentTurn(
			this.getCurrentTurn() + 1
		);
	}

	@Override
	public Boolean hasFinished() {
		// TODO Check if the game has ended
		return null;
	}


	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public Role getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(Role currentRole) {
		this.currentRole = currentRole;
	}

	/**
	 * Get the Vector of vertices of the map
	 * @return 	Vector of vertices of the map
	 */
	@Override
	public Vector<Vertex> getVertices() {
		return this.vertices;
	}

	/**
	 * Add a Vertex to the map
	 * @param	v 	The vertex to add (must be unique)
	 * @return 		TRUE if insertion successful, FALSE otherwise
	 */
	@Override
	public Boolean addVertex(Vertex v) {
		if ( this.getVertices().contains(v) ) {
			return false;
		}
		return this.vertices.add(v);
	};
	
}
