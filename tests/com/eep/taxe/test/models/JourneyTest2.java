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
		steamTrain 			= new Train("Steam", null, 20, 30, null, 25, null); //Travels 25 km per turn
		
		// 4. Create an empty journey
		trainJourney		= new Journey(steamTrain);
		

	}
	
	@Test
	public void testAddTwoStationsToJourney(){
		
		//Player adds train to map by clicking on starting station
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Station is added to journey
		Boolean firstAdded = trainJourney.add(Rome);
		
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
		
		//Player clicks on a station that train will start its journey
		Station startingStation = Rome;
		trainJourney.setStartingStation(startingStation);
				
		//Player adds train to map by clicking on starting station
		steamTrain.setStationToStartNextGoalAt(Rome);
		
		//Click on an vertex that is not adjacent
		Vertex nextVertex = Berlin;
				
		//Add vertex
		Boolean result = trainJourney.add(nextVertex);
		
		//The journey should not have successfully added Berlin
		if (result == false){
			fail("There should not exist an edge between Rome and Berlin");
		}
	}
	
	@Test
	public void testTravelFromParisToRome(){
		
		//Player clicks on Paris
		Station startingStation = Paris;
		trainJourney.setStartingStation(startingStation);
		
		//Player clicks on JN1
		Vertex nextVertex = JN1;
		Edge connectingEdge = startingStation.getEdge(nextVertex);
		
		
	}

}
