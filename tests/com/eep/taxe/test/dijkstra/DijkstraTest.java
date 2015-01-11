package com.eep.taxe.test.dijkstra;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Path;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Vertex;
import com.eep.taxe.utils.Dijkstra;

public class DijkstraTest {

	private Vector<Vertex>	graph;
	private Station			source;
	private Dijkstra		d;
	
	// The stations of the test
	private Station Rome;
	private Station Paris;
	private Station York;
	private Station Edinburgh;
	private Station KingsX;
	private Station Victoria;
	private Station Nowhere;
	
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
		
		// 2. Create some edges
		new Edge(Edinburgh, 		York,		 	20);
		new Edge(York, 				KingsX,			60);
		new Edge(KingsX,			Victoria,		5);
		new Edge(Victoria, 			Paris,	 		15);
		new Edge(Victoria, 			Rome,			50);
		new Edge(Paris, 			Rome,			30);
			
		// 3. Set our route
		this.source = Edinburgh;
		
		// 4. Finally, add all the stations to the graph
		graph = new Vector<Vertex>();
		graph.add(Rome);
		graph.add(Paris);
		graph.add(York);
		graph.add(Edinburgh);
		graph.add(KingsX);
		graph.add(Victoria);	
		graph.add(Nowhere);	
		
		// 5. Compute
		d 	= new Dijkstra(this.graph, this.source);
		
	}

	@Test
	public void testTrainToRome() {
		
		Path p 		= d.getShortestPathTo(Rome);
		
		if (p == null) {
			fail("No path was found");
		}
		
		if ( d.getDistanceTo(Rome) != 130) {
			fail("Wrong distance");
		}
				
	}

	@Test
	public void testTrainToNowhere() {
		
		Path p 		= d.getShortestPathTo(Nowhere);
		
		if (p != null) {
			fail("Found an inexistent path");
		}
		
		if ( d.getDistanceTo(Nowhere) != Dijkstra.INFINITY) {
			fail("Inexistent path with non-null length");
		}
				
	}

}
