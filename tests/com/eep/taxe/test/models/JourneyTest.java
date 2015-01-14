package com.eep.taxe.test.models;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Journey;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.Vertex;
import com.eep.taxe.utils.Dijkstra;

public class JourneyTest {
	
	// The stations of the test
	private Station Rome, Paris, York, Edinburgh, KingsX, Victoria, Nowhere;
	
	// The trains of the test
	private Train steamTrain;
	
	//The edges of the test
	private Edge e1, e2, e3, e4, e5, e6, e7;
	
	// The journey of the test
	private Journey trainJourney;
	private int expectedTrainJourneyLength;
	
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
		
		//5. Add edges to the journey (in-order)
		trainJourney.add(e1);
		trainJourney.add(e2);
		trainJourney.add(e3);
		trainJourney.add(e4);
		trainJourney.add(e5);
		
		//6. Sum of length of all edges
		expectedTrainJourneyLength = 20+60+5+15+30;
				
	}

	@Test
	public void testLengthOfJourney() {
		if (trainJourney.getTotalLength() != expectedTrainJourneyLength){	
			fail("Length of journey not expected value");
		}
	}
	
	@Test
	public void testTravellingOnEmptyJourney(){
		//Checks for error
		Journey emptyTrainJourney = new Journey(steamTrain);
		emptyTrainJourney.incrementProgressByTurn();
	}
	
	@Test
	public void testTravelToYork(){
		//Train is travelling at 25km per turn
		//Distance between the start station (Edinburgh) and KingsX is 20km
		//Thus after first turn the train should be 5km along the York to KingsX edge
		
		trainJourney.incrementProgressByTurn(); //First turn
		
		if (trainJourney.getDistanceTravelledOnJourney() != 25){
			fail("Train is not travelling 25km on journey");
		}
		
		if (trainJourney.getCurrentEdge() != e2){
			fail("Train is not travelling along edge from York to KingsX");
		}
	}
	
	@Test
	public void testTravelToParis(){
		//Train is travelling at 25km per turn
		//Distance between the start station (Edinburgh) and Paris is 100km
		//Thus after fourth turn the train should be 0km along the Paris to Rome edge
		
		trainJourney.incrementProgressByTurn(); //First turn
		
		if (trainJourney.getDistanceTravelledOnJourney() != 25){
			fail("Train has not travelled 25km on journey");
		}
		
		trainJourney.incrementProgressByTurn(); //Second turn
		
		if (trainJourney.getDistanceTravelledOnJourney() != 50){
			fail("Train has not travelled 50km on journey");
		}
		
		trainJourney.incrementProgressByTurn(); //Third turn
		
		if (trainJourney.getDistanceTravelledOnJourney() != 75){
			fail("Train has not travelled 75km on journey");
		}
		
		trainJourney.incrementProgressByTurn(); //Fourth turn
		
		if (trainJourney.getDistanceTravelledOnJourney() != 100){
			fail("Train has not travelled 100km on journey");
		}
		
		if (trainJourney.getCurrentEdge() != e5){
			fail("Train is not travelling along edge from Paris to Rome");
		}
		
		if (trainJourney.getDistanceTravelledOnEdge() != 0){
			fail("Train has not travelled 0km on edge");
		}
		
		
	}
	
	@Test
	public void testTravelFullJourney(){
		//Full distance of journey is 130km
		//Train travels at 25km per turn
		//Journey should be complete by turn turn #6 and no earlier
		
		trainJourney.incrementProgressByTurn(); //First turn
		trainJourney.incrementProgressByTurn(); //Second turn
		trainJourney.incrementProgressByTurn(); //Third turn
		trainJourney.incrementProgressByTurn(); //Fourth turn
		trainJourney.incrementProgressByTurn(); //Fifth turn
		
		if (trainJourney.isJourneyComplete() ){
			fail("Train journey should not have completed yet");
		}
		
		trainJourney.incrementProgressByTurn(); //Sixth turn
		
		if (! trainJourney.isJourneyComplete() ){
			fail("Train journey should have completed");
		}
		
		
		
		trainJourney.incrementProgressByTurn(); //Seventh turn
		
		if (! trainJourney.isJourneyComplete() ){
			fail("Train journey should have completed");
		}
	}
	
	

}
