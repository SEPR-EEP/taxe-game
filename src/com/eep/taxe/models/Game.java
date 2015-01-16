package com.eep.taxe.models;

import java.util.Random;
import java.util.Vector;

import com.eep.taxe.GameClient.Role;
import com.eep.taxe.models.Age.Ages;
import com.eep.taxe.res.Generator;
import com.eep.taxe.utils.Dijkstra;


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
	private Role	currentRole = Role.SLAVE;
	
	public Game(String name, Difficulty d) {
		this.setName(name);
		this.setDifficulty(d);
		
		this.master = new Player();
		this.slave  = new Player();
		
		// Add a train to each the players
		this.addFirstTrainToPlayer(master);
		this.addFirstTrainToPlayer(slave);
		
		this.setVertices(Generator.generateMap());
	}
	
	private void addFirstTrainToPlayer(Player who) {
		Train 	a = new Train("Your First Train", null, 0, 0, Ages.FIRST, (float) 1.0, null);
		Journey j = new Journey(a);
		
		Station starting = this.getRandomStation();
		Station ending;
		do {
			ending = this.getRandomStation();
		} while ( starting == ending );
		
		j.addAll(getShortestPath(starting, ending));	
		j.start();
	}
	
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
		
		if ( this.getCurrentRole() == Role.MASTER ) {
			this.computeEndOfTurn();
		}
		
		// Finally, increment role and turn
		this.incrementRoleAndTurn();
		
	}
	
	private void computeEndOfTurn() {
		// CALCULATE SCORES
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
	
	public Path getShortestPath(Vertex from, Vertex to) {
		Dijkstra d = new Dijkstra(this.getVertices(), from);
		return d.getShortestPathTo(to);
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
	 * Set the vertices of the map
	 * @param		The list of vertices
	 */
	public void setVertices(Vector<Vertex> v) {
		this.vertices = v;
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
	
	/**
	 * Get the vector of stations displayed on the map
	 * @return vector of station
	 */
	public Vector<Station> getStations(){

		Vector<Station> stations = new Vector<Station>();
		
		for (Vertex vertex : this.vertices){
			if (vertex instanceof Station){
				stations.add( (Station) vertex );
			}
		}
		return stations;
	}
	
	/**
	 * Get a randomly selected station from the map
	 * @return station
	 */
	public Station getRandomStation(){
		Vector<Station> stations = getStations();
		int numberOfStations = stations.size();
		Random picker = new Random();
		int randomStationNumber = picker.nextInt(numberOfStations);
		return stations.get(randomStationNumber);
	}
}
