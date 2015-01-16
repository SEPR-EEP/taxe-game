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
	private int expectedTrainJourneyLength;
	
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
		Boolean firstAdded = trainJourney.add(Rome);
		
		//The journey should have successfully added Rome
		if (firstAdded == false){
			fail("Rome should be added");
		}	
		
		//Click on an adjacent vertex
		Vertex nextVertex = Sofia;
		
		//Add vertex
		Boolean secondAdded = trainJourney.add(nextVertex);
		
		//The journey should have successfully added Sofia
		if (secondAdded == false){
			fail("There should exist an edge between Rome and Sofia");
		}	
		
	}

	@Test
	public void testAddTwoStationsNotAdjacent(){
		
		//Station is added to journey
		Boolean firstAdded = trainJourney.add(Rome);
		
		//The journey should have successfully added Rome
		if (firstAdded == false){
			fail("Rome should be added");
		}	
		
		//Click on an vertex that is not adjacent
		Vertex nextVertex = Berlin;
				
		//Add vertex
		Boolean secondAdded = trainJourney.add(nextVertex);
		
		//The journey should not have successfully added Berlin
		if (secondAdded == true){
			fail("There should not exist an edge between Rome and Berlin");
		}
	}
	
	@Test
	public void testAddJunctionAsStartingStation(){
		
		Boolean junctionAdded = trainJourney.add(JN1);
		
		if (junctionAdded == true){
			fail("A junction should not be the starting vertex of of a journey");
		}
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
		
		if (! trainJourney.isJourneyStarted()){
			fail("Train journey should have started");
		}
		
		//Get length of journey
		int lengthOfJourney = trainJourney.getTotalLength(); //Should be 1120km
		
		if (lengthOfJourney != 1120){
			fail("Length of journey has not been calculated correctly");
		}
		
		//Player has first turn
		trainJourney.incrementProgressByTurn();
		
		if (trainJourney.getDistanceTravelledOnJourney() != 500){
			fail("Train should have travelled 500km by end of first turn");
		}
		
		//Player has second turn
		trainJourney.incrementProgressByTurn();
		
		//Player has third turn
		trainJourney.incrementProgressByTurn();
		
		//Journey should have completed
		if (! trainJourney.isJourneyComplete()){
			fail("Journey should have completed");
		}
		
	}

}
