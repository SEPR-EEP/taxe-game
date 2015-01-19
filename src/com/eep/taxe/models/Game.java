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

		this.setVertices(Generator.generateMap());

		// Add a train to each the players
		this.addFirstTrainToPlayer(master);
		this.addFirstTrainToPlayer(slave);
		
		//Generate a goal for each player's first train
		master.generateGoal(master.getTrains().get(0), this);
		slave.generateGoal(slave.getTrains().get(0), this);

	}
	
	private void addFirstTrainToPlayer(Player who) {

		//Create a clone of a generated train with a random starting station
		Train 	train = Generator.generateTrains(who.getCurrentAge().age, this).get(0).clone();
		Station starting = this.getRandomStation();
		train.setStationToStartNextGoalAt(starting);
		
		who.getTrains().add(train);
		
		//Get an ending station that is not starting
		Station ending;
		do {
			ending = this.getRandomStation();
		} while ( starting == ending );
		
		//Have the train travel the journey so it has an initialised place on the map
		Journey journey = new Journey(train, getShortestPath(starting, ending).getVerticesInOrder(starting));
		journey.start();
		while(! journey.isJourneyComplete()){
			journey.incrementProgressByTurn();
		}
		train.setStationToStartNextGoalAt(ending);
		
		

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
		

		Player masterPlayer = getPlayerByRole(Role.MASTER);
		Player slavePlayer = getPlayerByRole(Role.SLAVE);
		
		// Move trains
		Vector<Train> allTrains = new Vector<Train>();
		allTrains.addAll(masterPlayer.getTrains());
		allTrains.addAll(slavePlayer.getTrains());
		
		for ( Train t: allTrains ) {
			t.moveForward();
		}
		
		// Calculate scores
		masterPlayer.incrementGoalsCompleted(
			masterPlayer.cashInCompletedGoals(this)
		);
		slavePlayer.incrementGoalsCompleted(
			slavePlayer.cashInCompletedGoals(this)
		);
		
		
		//Generate new goals
		//masterPlayer.generateGoal(this);
		//slavePlayer.generateGoal(this);
		
		// Add up to 2 random resources to each player
		masterPlayer.addRandomUsables(2);
		slavePlayer.addRandomUsables(2);
		
		
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
