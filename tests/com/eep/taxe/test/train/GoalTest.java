package com.eep.taxe.test.train;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Goal;
import com.eep.taxe.models.Journey;
import com.eep.taxe.models.Path;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.TrainSpeedModifier;
import com.eep.taxe.models.Vertex;
import com.eep.taxe.utils.Dijkstra;

public class GoalTest {
	
	private Vector<Vertex>	graph;
	private Station			startingStation, endingStation;
	private Dijkstra		d;
	
	// The stations of the test
	private Station Rome, Paris, York, Edinburgh, KingsX, Victoria, Nowhere;
	
	// The trains of the test
	private Train steamTrain;
	
	//The edges of the test
	private Edge e1, e2, e3, e4, e5, e6, e7;
	
	// The journey of the test
	private Journey trainJourney;
	
	// The goal of the test
	private Goal goal;
	
	@Before
	public void setUp() throws Exception {
		
		// 1. Create some Railway Station
		Rome 		= new Station(30, 40, "Rome Fiumicino");
		Paris 		= new Station(10, 30, "Paris Centràle");
		York 		= new Station(20, 10, "York Railway Station");
		Edinburgh	= new Station(20, 00, "Edinburgh Railway Station");
		KingsX		= new Station(20, 20, "London King's Cross Station");
		Victoria	= new Station(20, 25, "London Victoria");
		Nowhere		= new Station(00, 00, "The Phantom Spaceship");
		
		
		// 2. Create a train
		steamTrain 			= new Train("Steam", 20, 30, null, 25); //Travels 25 km per turn
		
		// 3. Create an empty journey
		trainJourney		= new Journey(steamTrain);
		
		//4. Create edges
		e1 = new Edge(Edinburgh, 		York,		 	20);
		e2 = new Edge(York, 				KingsX,			60);
		e3 = new Edge(KingsX,			Victoria,		5);
		e4 = new Edge(Victoria, 			Paris,	 		15);
		e5 = new Edge(Paris, 			Rome,			30);
		e6 = new Edge(Victoria, 			Rome,			50);

		//5. Add vertices to graph
		graph = new Vector<Vertex>();
		graph.add(Rome);
		graph.add(Paris);
		graph.add(York);
		graph.add(Edinburgh);
		graph.add(KingsX);
		graph.add(Victoria);	
		graph.add(Nowhere);	
		
	}
	
	@Test
	public void testYorkToKingsXInOptimalTurns(){
		
		//Player is given goal of travelling from York to KingsX
		goal = new Goal(null, "Travel from York to KingsX", "Descriptive story", York, KingsX);
		steamTrain.startAGoal(goal);
		
		//Compute dijkstra for graph with starting station
		d 	= new Dijkstra(this.graph, York);
		float shortestDistance = d.getDistanceTo(KingsX);
				
		//Compute optimal number of turns for train travelling at its base speed
		int optimalTurns = goal.optimalNumberOfTurns(shortestDistance, steamTrain.getBaseSpeed());
		
		//Player adds edge to their planned journey
		trainJourney.add(e2);
		
		//Keep journey going until KingsX is reached
		while (! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Calculate reward for player
		int score = goal.calculateReward(trainJourney, optimalTurns);
	}
	
	@Test
	public void testEdinburghToRomeInSuboptimalTurns(){
		
		//Player is given goal of travelling from Edinburgh
		goal = new Goal(null, "Travel from Edinburgh to Rome", "Descriptive story", Edinburgh, Rome);
		steamTrain.startAGoal(goal);
		
		//Compute dijkstra for graph with starting station
		d 	= new Dijkstra(this.graph, Edinburgh);
		float shortestDistance = d.getDistanceTo(Rome);
		
		//Compute optimal number of turns for train travelling at its base speed
		int optimalTurns = goal.optimalNumberOfTurns(shortestDistance, steamTrain.getBaseSpeed());
		
		//Player adds edges to their planned journey
		trainJourney.add(e1);
		trainJourney.add(e2);
		trainJourney.add(e3);
		trainJourney.add(e6);
		
		//Keep journey going until Rome is reached
		while (! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Calculate reward for player
		int score = goal.calculateReward(trainJourney, optimalTurns);
	
	}
	
	
	@Test
	public void testEdinburghToRomeInOptimalTurns(){
		
		//Player is given goal of travelling from Edinburgh
		goal = new Goal(null, "Travel from Edinburgh to Rome", "Descriptive story", Edinburgh, Rome);
		steamTrain.startAGoal(goal);
		
		//Compute dijkstra for graph with starting station
		d 	= new Dijkstra(this.graph, Edinburgh);
		float shortestDistance = d.getDistanceTo(Rome);
		
		//Compute optimal number of turns for train travelling at its base speed
		int optimalTurns = goal.optimalNumberOfTurns(shortestDistance, steamTrain.getBaseSpeed());
		
		//Player adds edges to their planned journey
		trainJourney.add(e1);
		trainJourney.add(e2);
		trainJourney.add(e3);
		trainJourney.add(e4);
		trainJourney.add(e5);
		
		//Keep journey going until Rome is reached
		while (! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Calculate reward for player
		int score = goal.calculateReward(trainJourney, optimalTurns);
		
		if (score != trainJourney.getTotalLength()){
			fail("In optimal number of turns score should equal distance of journey");
		}
	}
	
	
	@Test
	public void testWrongJourney(){
	
		//Player is given goal of travelling from Edinburgh
		goal = new Goal(null, "Travel from Edinburgh to Rome", "Descriptive story", Edinburgh, Rome);
		steamTrain.startAGoal(goal);
		
		//Compute dijkstra for graph with starting station
		d 	= new Dijkstra(this.graph, Edinburgh);
		float shortestDistance = d.getDistanceTo(Rome);
		
		//Compute optimal number of turns for train travelling at its base speed
		int optimalTurns = goal.optimalNumberOfTurns(shortestDistance, steamTrain.getBaseSpeed());
		
		//Player adds edges to their planned journey
		trainJourney.add(e4);
		
		//Initial check
		if (goal.willJourneyAcomplishGoal(trainJourney)){
			fail("Starting station and ending station of journey are not those of goal so it cannot be acomplished");
		}
		
		//Keep journey going until Paris is reached
		while (! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Calculate reward for player
		int score = goal.calculateReward(trainJourney, optimalTurns);
		
		if (score != 0){
			fail("A score of 0 should be given as journey did not acomplish goal");
		}
		
		
	}
	
	
	@Test
	public void testJourneyWithSpeedBoostedTrain(){
		
		//Player is given goal of travelling from Edinburgh
		goal = new Goal(null, "Travel from Edinburgh to Rome", "Descriptive story", Edinburgh, Rome);
		steamTrain.startAGoal(goal);
		
		//Compute dijkstra for graph with starting station
		d 	= new Dijkstra(this.graph, Edinburgh);
		float shortestDistance = d.getDistanceTo(Rome);
		
		//Compute optimal number of turns for train travelling at its base speed
		int optimalTurns = goal.optimalNumberOfTurns(shortestDistance, steamTrain.getBaseSpeed());
		
		//Player adds edges to their planned journey
		trainJourney.add(e1);
		trainJourney.add(e2);
		trainJourney.add(e3);
		trainJourney.add(e4);
		trainJourney.add(e5);
		
		//Player adds a booster to their train
		TrainSpeedModifier steamBoost = new TrainSpeedModifier("Booster", "", 5, 0, null, 2);	//Doubles speed
		steamBoost.useOnTrain(steamTrain);
		
		//Keep journey going until Rome is reached
		while (! trainJourney.isJourneyComplete()){
			trainJourney.incrementProgressByTurn();
		}
		
		//Calculate reward for player
		int score = goal.calculateReward(trainJourney, optimalTurns);
		
		if (score != 2*  trainJourney.getTotalLength()){
			fail("If speed is doubled then score should be doubled");
		}
		
	}

}
