package com.eep.taxe.res;

import java.util.Vector;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Junction;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Vertex;

/**
 * Class used to generate the map
 */
public class Map {

	public static Vector<Vertex> generateMap() {
		Vector<Vertex> r = new Vector<Vertex>();
		
		Station Rome 		= new Station(30, 40, "Rome Fiumicino");
		Station Paris 		= new Station(10, 30, "Paris Centràle");
		Station York 		= new Station(20, 10, "York Railway Station");
		Station Edinburgh	= new Station(20, 00, "Edinburgh Railway Station");
		Station KingsX		= new Station(20, 20, "London King's Cross Station");
		Station Victoria	= new Station(20, 25, "London Victoria");
		
		Junction JN1		= new Junction(20, 05);
		Junction JN2		= new Junction(30, 30);
		
		new Edge(Edinburgh, 		JN1,		 	10);
		new Edge(JN1, 				York,		 	5);
		new Edge(York, 				KingsX,			60);
		new Edge(KingsX,			Victoria,		5);
		new Edge(Victoria, 			Paris,	 		15);
		new Edge(Victoria, 			JN2,			50);
		new Edge(JN2,				Rome,			10);
		new Edge(Paris, 			Rome,			30);
					
		r.add(Rome);
		r.add(Paris);
		r.add(York);
		r.add(Edinburgh);
		r.add(KingsX);
		r.add(Victoria);	
		
		return r;
	}
	
}
