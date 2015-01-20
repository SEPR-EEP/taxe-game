package com.eep.taxe.test.models;
import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Game;
import com.eep.taxe.models.Goal;
import com.eep.taxe.models.Journey;
import com.eep.taxe.models.Junction;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.Vertex;
import com.eep.taxe.models.Game.Difficulty;
import com.eep.taxe.res.Generator;


public class GoalTest2 {

	//The game of the test
	private Game game;
	
	//The player of the test
	private Player player;
	
	//The stations and junctions
	private Station Rome, Paris, Berlin, Sofia, Madrid;
	private Junction JN1, JN2;
	
	// The train of the test
	private Train steamTrain;
	
	//The vertices of the test
	private Vector<Vertex> vertices;
	
	// The journey of the test
	private Journey trainJourney;
	
	//A goal to be completed
	private Goal goal;
	
	
	@Before
	public void setUp() throws Exception {
		
		//1. Create a game
		game = new Game("Test", Difficulty.EASY);
		
		//2. Create a player
		player = new Player();
		
		//3. Get stations and junctions
		vertices 		= game.getVertices();
		Rome 			= (Station) vertices.get(0);
		Paris 			= (Station) vertices.get(1);
		Berlin 			= (Station) vertices.get(2);
		Sofia 			= (Station) vertices.get(3);
		Madrid 			= (Station) vertices.get(4);
		JN1				= (Junction) vertices.get(5);
		JN2				= (Junction) vertices.get(6);
		
		// 4. Create a train
		steamTrain 			= new Train("Steam", null, 20, 30, null, 500, null); //Travels 1000 km per turn (Not very realistic)
		
		// 5. Create an empty journey
		trainJourney		= new Journey(steamTrain);

	}

	@Test
	public void testGenerateGoalFromRome() {
		
		//Place train on map at Rome
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Due to random nature of generator test multiple times
		for (int testNo = 0; testNo < 1000; testNo++){
			//Generate a goal for player's train based upon the game's map
			goal = Generator.generateGoal(steamTrain, vertices, player, steamTrain.getStationToStartNextGoalAt(), game);
			
			//The generated goal's starting station should be Rome
			assertSame("Starting station of goal is not Rome", goal.getStartingStation(), Rome );
			
			//The generated goal's ending station should be on the map
			assertTrue("Ending station is not on the map", vertices.contains(goal.getEndingStation() ) );
			
			//The generated goal's starting station must not be the same as ending station
			assertNotSame("Ending station is same as starting station", goal.getStartingStation(), goal.getEndingStation() );
		}
	}
	
	@Test
	public void testGenerateGoalFromRandomStationOnMap(){
		//Due to random nature of generator test multiple times
		for (int testNo = 0; testNo < 1000; testNo++){
			//Generate a goal for player's train based upon the game's map
			goal = Generator.generateGoal(steamTrain, vertices, player, game.getRandomStation(), game);
			
			//The generated goal's starting station should be on the map
			assertTrue("Starting station of goal is not on map", vertices.contains(goal.getStartingStation() ) );
			
			//The generated goal's ending station should be on the map
			assertTrue("Ending station is not on the map", vertices.contains(goal.getEndingStation() ) );
			
			//The generated goal's starting station must not be the same as ending station
			assertNotSame("Ending station is same as starting station", goal.getStartingStation(), goal.getEndingStation() );
		}
	}
	
	@Test 
	public void testGenerateGoalFromNowhere(){
		//Train has not been placed on map, i.e. stationToStartNextGoalAt = null
		
		goal = Generator.generateGoal(steamTrain, vertices, player, steamTrain.getStationToStartNextGoalAt(), game);
		
		//Goal should be null as the train is not on the map
		assertNull("A goal should not be generated if the train is not on the map", goal);
	}
	
	@Test
	public void testAccomplishGoalFromRomeToSofia() {
		//Place train on map at Rome
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Generate a goal for player's train based upon the game's map
		goal = Generator.generateGoal(steamTrain, vertices, player, steamTrain.getStationToStartNextGoalAt(), game);
		
		//For sake of test case set ending station to Sofia - rather than a random one
		goal.setEndingStation(Sofia);
		
		//Player creates a path
		trainJourney.add(Rome);
		trainJourney.add(Sofia);
		
		//Journey should be able to accomplish goal
		assertTrue("Goal should recognise journey as accomplishable", goal.willJourneyAccomplishGoal(trainJourney) );
		
		//Start Journey
		trainJourney.start();
		
		//Keep journey going until it is complete
		while(! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Goal should be completed
		assertTrue("Goal should have been accomplished by journey", goal.hasJourneyAccomplishedGoal(trainJourney) );
		
	}
	
	@Test
	public void testDoNotAccomplishGoalWithWrongJourneyOne(){
		//Place train on map at Rome
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Generate a goal for player's train based upon the game's map
		goal = Generator.generateGoal(steamTrain, vertices, player, steamTrain.getStationToStartNextGoalAt(), game);
		
		//For sake of test case set ending station to Berlin - rather than a random one
		goal.setEndingStation(Berlin);
		
		//Player creates a path (Berlin to Sofia)
		trainJourney.add(Rome);
		trainJourney.add(Sofia);
		
		//Start Journey
		trainJourney.start();
		
		//Keep journey going until it is complete
		while(! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Goal should not be completed
		assertFalse("Goal should not have been accomplished by journey", goal.hasJourneyAccomplishedGoal(trainJourney));
	}
	
	@Test
	public void testDoNotAccomplishGoalWithWrongJourneyTwo(){
		//Place train on map at Rome
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Generate a goal for player's train based upon the game's map
		goal = Generator.generateGoal(steamTrain, vertices, player, steamTrain.getStationToStartNextGoalAt(), game);
		
		//For sake of test case set ending station to Berlin - rather than a random one
		goal.setEndingStation(Berlin);
		
		//Player creates a path (Berlin to Sofia)
		trainJourney.add(Sofia);
		trainJourney.add(Rome);
		
		//Start Journey
		trainJourney.start();
		
		//Keep journey going until it is complete
		while(! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Goal should not be completed
		assertFalse("Goal should not have been accomplished by journey", goal.hasJourneyAccomplishedGoal(trainJourney));
	}

	
	@Test
	public void testDoNotAccomplishGoalWithWrongJourneyThree(){
		//Place train on map at Rome
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Generate a goal for player's train based upon the game's map
		goal = Generator.generateGoal(steamTrain, vertices, player, steamTrain.getStationToStartNextGoalAt(), game);
		
		//For sake of test case set ending station to Berlin - rather than a random one
		goal.setEndingStation(Berlin);
		
		//Player creates a path (Berlin to Rome)
		trainJourney.add(Berlin);
		trainJourney.add(Sofia);
		trainJourney.add(Rome);
		
		//Start Journey
		trainJourney.start();
		
		//Keep journey going until it is complete
		while(! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Goal should not be completed
		assertFalse("Goal should not have been accomplished by journey", goal.hasJourneyAccomplishedGoal(trainJourney));
		
	}
}
