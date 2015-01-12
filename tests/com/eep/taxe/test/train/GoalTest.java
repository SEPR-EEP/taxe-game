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
		
		e3 = new Edge(KingsX,			Victoria,		5);
		e4 = new Edge(Victoria, 			Paris,	 		15);
		e5 = new Edge(Paris, 			Rome,			30);
		
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
	public void testYorkToKingsX(){
		
		int numOfTurns = 0;
		int optimalTurns;
		
		//Player is given goal of travelling from York to KingsX
		goal = new Goal(null, "Travel from York to KingsX", "Descriptive story", York, KingsX);
		steamTrain.startAGoal(goal);
		
		//Compute dijkstra for graph with starting station
		d 	= new Dijkstra(this.graph, York);
		float shortestDistance = d.getDistanceTo(KingsX);
				
		//Compute optimal number of turns for train travelling at its base speed
		optimalTurns = goal.optimalDuration(shortestDistance, steamTrain.getBaseSpeed());
		
		//Player adds edge to their planned journey
		trainJourney.add(new Edge(York, 				KingsX,			60) );
		
		//Start journey
		trainJourney.startJourney();
		
		//Keep going until KingsX is reached
		while (! trainJourney.isJourneyComplete()){
			numOfTurns++;
		}
		
		
			
		
		
	}

}
