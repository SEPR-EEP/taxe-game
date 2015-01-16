package com.eep.taxe.res;

import java.util.Vector;

import com.eep.taxe.models.Age;
import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Goal;
import com.eep.taxe.models.Junction;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.Vertex;

/**
 * Class used to generate the map
 */
public class Generator {
/* static list of Trains by age
 * vector of usables trainSpeedModifier
 * generateGoal
 */
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

	public static Goal generateGoal(Train train, Vector<Vertex> map, Player player) {

		//Starting station is where train is currently located
		Station startingStation = train.getStationToStartNextGoalAt();
	
		//Station that is randomly chosen must not be the same as the starting station
		Station endingStation = startingStation;
		while (endingStation == startingStation){
			endingStation = Generator.getRandomStationFromMap();
		}
	
		//Create goal based upon starting and ending station
		Goal goal = new Goal(player.getCurrentAge().age, 
				"Travel from " +startingStation.getName() +"to " +endingStation.getName(),
				"Long description - maybe random scenario?",
				startingStation,
				endingStation); 
		return goal;
	}
	
	private static Station getRandomStationFromMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Train trainGenerator(Age age) {
		// TODO
		return null;
	}
	
}

