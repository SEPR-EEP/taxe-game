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
		
		Station Rome 		= new Station(60, 60, "Rome Fiumicino");
		Station Paris 		= new Station(20, 30, "Paris Centràle");
		Station Berlin 		= new Station(60, 30, "Berlin Hauptbahnhof");
		Station Sofia		= new Station(80, 45, "Sofia Railway Station");
		Station Madrid		= new Station(20, 60, "Madrid Atocha Station");
		
		Junction JN1		= new Junction(40, 45);
		Junction JN2		= new Junction(50, 40);
		
		new Edge(Paris,		JN2,	400);
		new Edge(Paris, 	JN1, 	520);
		new Edge(Madrid,    JN1,    800);
		new Edge(Rome,      JN1,    600);
		new Edge(Rome,      JN2,    700);
		new Edge(JN1, 		JN2,	400);
		new Edge(Berlin, 	JN2,	700);
		new Edge(Berlin, 	Sofia,  1321);
		new Edge(Sofia,     Rome,   895);
					
		r.add(Rome);
		r.add(Paris);
		r.add(Berlin);
		r.add(Sofia);
		r.add(Madrid);
		
		r.add(JN1);
		r.add(JN2);
		
		return r;
	}
	
	public static Vector<Station> getStationsFromMap(){
		Vector<Vertex> map = generateMap();
		Vector<Station> stations = new Vector<Station>();
		
		for (Vertex vertex : map){
			if (vertex instanceof Station){
				stations.add( (Station) vertex );
			}
		}
		
		return stations;
	}
	
}
