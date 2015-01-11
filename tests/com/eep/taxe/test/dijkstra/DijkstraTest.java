package com.eep.taxe.test.dijkstra;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Path;
import com.eep.taxe.models.Station;
import com.eep.taxe.utils.Dijkstra;

public class DijkstraTest {

	private Vector<Station> graph;
	private Station			source;
	private Station 		target;
	
	@Before
	public void setUp() throws Exception {
		
		// 1. Create some Railway Station
		Station 	Rome 		= new Station(30, 40, "Rome Fiumicino");
		Station		Paris 		= new Station(10, 30, "Paris Centràle");
		Station 	York 		= new Station(20, 10, "York Railway Station");
		Station 	Edinburgh	= new Station(20, 00, "Edinburgh Railway Station");
		Station		KingsX		= new Station(20, 20, "London King's Cross Station");
		Station		Victoria	= new Station(20, 25, "London Victoria");
		
		// 2. Create some edges
		new Edge(Edinburgh, 		York,		 	20);
		new Edge(York, 				KingsX,			60);
		new Edge(KingsX,			Victoria,		5);
		new Edge(Victoria, 			Paris,	 		15);
		new Edge(Victoria, 			Rome,			50);
		new Edge(Paris, 			Rome,			30);
		
		Rome.printAdjacentStations();
		
		// 3. Set our route
		this.source = York;
		this.target = Rome;
		
		// 3. Finally, add all the stations to the graph
		graph = new Vector<Station>();
		graph.add(Rome);
		graph.add(Paris);
		graph.add(York);
		graph.add(Edinburgh);
		graph.add(KingsX);
		graph.add(Victoria);	
		
	}

	@Test
	public void test() {
		
		Dijkstra d 	= new Dijkstra(this.graph, this.source);
		Path p 		= d.getShortestPathTo(this.target);
		
		if (p == null) {
			fail("No path was found");
		}
		
		System.out.println("Source: " + this.source.getName());
		for (Edge e: p) {
			Station s1 = (Station) e.getVertices().get(0);
			Station s2 = (Station) e.getVertices().get(1);
			System.out.println("- " + e.getLength() + " km path from " +
								s1.getName() + " to " + s2.getName());
		}
		System.out.println("Target: " + this.target.getName());
		
		fail("Not yet implemented");
	}

}
