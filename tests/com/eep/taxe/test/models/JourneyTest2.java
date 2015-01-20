package com.eep.taxe.test.models;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Game;
import com.eep.taxe.models.Game.Difficulty;
import com.eep.taxe.models.Journey;
import com.eep.taxe.models.Junction;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.TrainSpeedModifier;
import com.eep.taxe.models.Vertex;

public class JourneyTest2 {

	//The game
	private Game game;
	
	//The stations and junctions
	private Station Rome, Paris, Berlin, Sofia, Madrid;
	private Junction JN1, JN2;
	
	// The train of the test
	private Train steamTrain;
	
	//The vertices of the test
	private Vector<Vertex> vertices;
	
	// The journey of the test
	private Journey trainJourney;
	
	@Before
	public void setUp() throws Exception {
		
		//1. Create a game
		game = new Game("Test", Difficulty.EASY);
		
		//2. Get stations and junctions
		vertices 		= game.getVertices();
		Rome 			= (Station) vertices.get(0);
		Paris 			= (Station) vertices.get(1);
		Berlin 			= (Station) vertices.get(2);
		Sofia 			= (Station) vertices.get(3);
		Madrid 			= (Station) vertices.get(4);
		JN1				= (Junction) vertices.get(5);
		JN2				= (Junction) vertices.get(6);
		
		// 3. Create a train
		steamTrain 			= new Train("Steam", null, 20, 30, null, 500, null); //Travels 1000 km per turn (Not very realistic)
		
		// 4. Create an empty journey
		trainJourney		= new Journey(steamTrain);
		

	}
	
	@Test
	public void testAddTwoStationsToJourney(){
		
		//Station is added to journey
		//The journey should have successfully added Rome
		assertTrue("Rome should be added", trainJourney.add(Rome) );

		//Click on an adjacent vertex
		Vertex nextVertex = Sofia;
		
		//Add vertex
		//The journey should have successfully added Sofia
		assertTrue("There should exist an edge between Rome and Sofia", trainJourney.add(nextVertex) );
	}

	@Test
	public void testAddTwoStationsNotAdjacent(){
		
		//Station is added to journey
		//The journey should have successfully added Rome
		assertTrue("Rome should be added", trainJourney.add(Rome));
		
		//Click on an vertex that is not adjacent
		Vertex nextVertex = Berlin;
				
		//Add vertex	
		//The journey should not have successfully added Berlin
		assertFalse("There should not exist an edge between Rome and Berlin", trainJourney.add(nextVertex) );
		
		
	}
	
	@Test
	public void testAddJunctionAsStartingStation(){
		
		assertFalse("A junction should not be the starting vertex of of a journey", trainJourney.add(JN1) );
	}
	
	
	@Test
	public void testTravelFromParisToRome(){
		
		//Player adds Paris
		trainJourney.add(Paris);
		
		//Player adds junction number 1
		trainJourney.add(JN1);
		
		//Player adds Rome
		trainJourney.add(Rome);
		
		//Player starts journey
		trainJourney.start();
		
		assertTrue("Train journey should have started", trainJourney.isJourneyStarted() );
		
		//Get length of journey
		//Should be 1120km - with 100% accuracy
		assertEquals("Length of journey has not been calculated correctly", 1120, trainJourney.getTotalLength() , 0 );
		
		//Player has first turn
		trainJourney.incrementProgressByTurn();
		
		//Distance travelled should be 500km - with 100% accuracy
		assertEquals("Train should have travelled 500km by end of first turn", 500, trainJourney.getDistanceTravelledOnJourney(), 0);
		
		//Player has second turn
		trainJourney.incrementProgressByTurn();
		
		//Player has third turn
		trainJourney.incrementProgressByTurn();
		
		//Journey should have completed
		assertTrue("Journey should have completed", trainJourney.isJourneyComplete() );
		
	}
	
	@Test
	public void testTravelFromParisToRomeWithSpeedBooster(){
		//With a speed booster that doubles the trains speed it should only take two turns rather than three
		
		//Player adds Paris
		trainJourney.add(Paris);
		
		//Player adds junction number 1
		trainJourney.add(JN1);
		
		//Player adds Rome
		trainJourney.add(Rome);
		
		//Player starts journey
		trainJourney.start();
		
		//Use booster on train
		TrainSpeedModifier steamBoost = new TrainSpeedModifier("Booster", "", 5, 0, null, 2);	//Doubles speed
		steamBoost.useOnTrain(steamTrain);

		//Player has first turn
		trainJourney.incrementProgressByTurn();
		
		//Distance should be 1000km (500 x 2) - with 100% accuracy
		assertEquals("Train should have travelled 1000km by end of first turn", 1000, trainJourney.getDistanceTravelledOnJourney(), 0);
		
		//Player has second turn
		trainJourney.incrementProgressByTurn();
		
		//Journey should have completed
		assertTrue("Journey should have completed", trainJourney.isJourneyComplete());
		
	}

}
