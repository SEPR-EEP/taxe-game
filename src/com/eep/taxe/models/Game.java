package com.eep.taxe.models;

import java.util.Random;
import java.util.Vector;

import com.eep.taxe.GameClient.Role;
import com.eep.taxe.res.Generator;
import com.eep.taxe.utils.Dijkstra;

/**
 * Represents a Game (match) between any two players.
 * If you are looking for the main Game entry point, please look at com.eep.taxe.Game.
 * To avoid confusion, you may want to specify the package when referring to this class.
 * 
 * This class extends the GameData class and is used as a container for the network
 * data exchange. This class should instantiated only once in the life of the program.
 * 
 * Every time a player sends their move to the server, this object is serialised 
 * into wibbly wobbly timey wimey binary stuff and sent to the other side of the world,
 * where the other client deserialeses it back into a com.eep.taxe.Game object.
 * 
 * IMPORTANT. All of the properties contained in this object will be transferred
 * 		over to the other player. You can use the Java 'transient' keyword to mark
 * 		something as 'mine and only mine' and it won't be sent to the server.
 * 		However, you are warned that this data will be immediately discarded once
 * 		the other player moves and sends back their version of the Game object.
 * 
 * 		Please note that if you send any object to the server and it gets
 * 		back to you untouched, it won't be '==' to the object prior to the end
 * 		of the turn. This is because every time an object is deserialised, 
 * 		a new object is created with the same properties.
 * 
 * 		TLDR; Store everything here.
 * 
 * Every object used as a property, sub-property, or sub-sub-property, etc., of this
 *  object MUST implement the 'Serializable' interface. That means that it should have
 *  that serialVersionUID that you can see to a random long number, for obscure reasons
 *  you may want to understand or just ignore and live an happy life.
 *  Failure to comply with this well hidden comment WILL bring pain and random bugs.
 *  
 *  As a secondary note. Everything 
 */
public class Game extends com.eep.taxe.GameData implements GameInterface {

	private static final long serialVersionUID = -1630426694034385387L;

	/**
	 * This enumeration type is used to represent the levels of 
	 * difficulty in the game. At the time of writing, this isn't really
	 * used anywhere, but you may want to implemenet it in the future 
	 * - e.g. make random junction failures more likely in an hard game.
	 */
	public static enum Difficulty {
		EASY, MEDIUM, HARD;
	}
	
	/*
	 * Here are all of the properties of the Game that will be serialised
	 * and sent over to the other player. Remember they all NEED to
	 * implement the 'Serializable' interface.
	 */
	
	private Player master, slave;		// The two player
	private Difficulty difficulty;		
	private String name;
	private Vector<Vertex> vertices;	// The map
	
	private int		currentTurn	= 0;
	private Role	currentRole = Role.SLAVE;
	
	/**
	 * Creates a Game and fills it with some data.
	 * @param name		The Name of the Game.
	 * @param d			The Difficulty of the Game.
	 */
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
		master.generateGoalToStartAtCurrentStation(master.getTrains().get(0), this);
		slave.generateGoalToStartAtCurrentStation(slave.getTrains().get(0), this);

	}
	
	/**
	 * This method generates a random train, assigns a random journey to it
	 * and completes the journey. Doing this way the Player will find a nice and
	 * non-moving train conveniently placed on the map, that in reality has 
	 * just finished a long journey.
	 * 
	 * @param who		The player to add the train to.
	 */
	private void addFirstTrainToPlayer(Player who) {

		//Create a clone of a generated train with a random starting station
		Train 	train = Generator.generateTrains(who.getCurrentAge().age, this).get(0).clone();
		Station starting = this.getRandomStation();
		train.setStationToStartNextGoalAt(starting);
		
		// Add the train to the player's trains
		who.getTrains().add(train);
		
		// Get an ending station that is not the starting
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
	 * This function ends a turn of the game.
	 * 
	 * It increments the internal turn counter of the game and, 
	 * if it is the end of the MASTER player's turn, also call 
	 * computeEndOfTurn() to calculate scores and do other stuff.
	 */
	@Override
	public void endTurn() {
		
		if ( this.getCurrentRole() == Role.MASTER ) {
			this.computeEndOfTurn();
		}
		
		// Finally, increment role and turn
		this.incrementRoleAndTurn();
		
	}
	
	/**
	 * Here all of the End of turn computation is carried away.
	 * Trains are moved, scores are calculated, random resources
	 * are generated and what not. 
	 * 
	 * Please note that this method is only called every time 
	 * the MASTER client ends his turns, before sending the data
	 * back to the SLAVE client.
	 */
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
		
		
		//Generate new goals (currently only working for 1 train)
		masterPlayer.generateGoal(masterPlayer.getTrains().get(0), this);
		slavePlayer.generateGoal(slavePlayer.getTrains().get(0), this);
		
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

	/**
	 * Checks if the game has finished yet.
	 * 
	 * This has not yet been implemented. A simple 
	 * implementation would be to check if any of the 
	 * two players have reached a the final number of 
	 * accomplished objectives, or whatever other
	 * condition you may like.
	 */
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
	 * This methods returns the shortest path between any two vertices on the map.
	 * 
	 * It internally uses an optimised version of the Dijkstra's algorithm
	 * (with priority queues), so you can assume it to be pretty efficient.
	 * 
	 * @param from		The starting vertex.
	 * @param to		The ending vertex.
	 * @return			A directed path between the vertices (as a list of edges).
	 */
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
