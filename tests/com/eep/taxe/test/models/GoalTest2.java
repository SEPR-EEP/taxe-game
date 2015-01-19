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
			goal = Generator.generateGoal(steamTrain, vertices, player, game);
			
			//The generated goal's starting station should be Rome
			if (! vertices.contains(goal.getStartingStation())){
				fail("Starting station of goal is not Rome");
			}
			
			//The generated goal's ending station should be on the map
			if (! vertices.contains(goal.getEndingStation())){
				fail("Ending station is not on the map");
			}
			
			//The generated goal's starting station must not be the same as ending station
			if (goal.getStartingStation() == goal.getEndingStation()){
				fail("Ending station is same as starting station");
			}
		}
	}
	
	@Test public void testGenerateGoalFromNowhere(){
		//Train has not been placed on map, i.e. stationToStartNextGoalAt = null
		
		goal = Generator.generateGoal(steamTrain, vertices, player, game);
		
		//Goal should be null as the train is not on the map
		if (goal != null){
			fail("A goal should not be generated if the train is not on the map");
		}
	}
	
	@Test
	public void testAccomplishGoalFromRomeToSofia() {
		//Place train on map at Rome
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Generate a goal for player's train based upon the game's map
		goal = Generator.generateGoal(steamTrain, vertices, player, game);
		
		//For sake of test case set ending station to Sofia - rather than a random one
		goal.setEndingStation(Sofia);
		
		//Player creates a path
		trainJourney.add(Rome);
		trainJourney.add(Sofia);
		
		//Journey should be able to accomplish goal
		Boolean canAccomplish = goal.willJourneyAccomplishGoal(trainJourney);
		
		if (! canAccomplish){
			fail("Goal should recognise journey as accomplishable");
		}
		
		//Start Journey
		trainJourney.start();
		
		//Keep journey going until it is complete
		while(! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Goal should be completed - so get reward based upon journey and game
		int score = goal.calculateReward(trainJourney, vertices);
		
		System.out.println(score);
		
		//Score should be greater than 0
		if (score <= 0){
			fail("No score has been given");
		}
		
		//As journey is an optimal journey score should equal distance
		if (score != trainJourney.getTotalLength()){
			fail("Score should equal journey distance for optimal journey");
		}
	}

}
