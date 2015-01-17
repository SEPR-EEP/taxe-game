package com.eep.taxe.res;

import java.awt.List;
import java.util.ArrayList;
import java.util.Vector;

import com.eep.taxe.models.Age;
import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Game;
import com.eep.taxe.models.Goal;
import com.eep.taxe.models.Junction;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.TrainSpeedModifier;
import com.eep.taxe.models.Usable;
import com.eep.taxe.models.Vertex;
import com.eep.taxe.models.Age.Ages;

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
		
		Station Rome 		= new Station(60, 40, "Rome Fiumicino");
		Station Paris 		= new Station(20, 10, "Paris Centràle");
		Station Berlin 		= new Station(60, 10, "Berlin Hauptbahnhof");
		Station Sofia		= new Station(80, 25, "Sofia Railway Station");
		Station Madrid		= new Station(20, 40, "Madrid Atocha Station");
		
		Junction JN1		= new Junction(40, 25);
		Junction JN2		= new Junction(50, 20);
		
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

	public static Goal generateGoal(Train train, Vector<Vertex> map, Player player, Game game) {
		if (train.getStationToStartNextGoalAt() == null) {
			return null;
		}
		//Starting station is where train is currently located
		Station startingStation = train.getStationToStartNextGoalAt();
	
		//Station that is randomly chosen must not be the same as the starting station
		Station endingStation = startingStation;
		while (endingStation == startingStation){
			endingStation = game.getRandomStation();
		}
	
		//Create goal based upon starting and ending station
		Goal goal = new Goal(player.getCurrentAge().age, 
				"Travel from " +startingStation.getName() +"to " +endingStation.getName(),
				"Long description - maybe random scenario?",
				startingStation,
				endingStation); 
		return goal;
	}

	public static ArrayList<Train> trainGenerator(Ages age, Game game) {
		ArrayList<Train> trainList = new ArrayList<Train>();
		
		Train steamTrain = new Train("Steam Train", "IMAGE", 100, 100, Ages.FIRST, 100, null);
		
		Train combustionTrain = new Train("Combustion Train", "IMAGE", 300, 300, Ages.SECOND, 200, null);
		
		Train electricTrain = new Train("Electric Train", "IMAGE", 600, 600, Ages.THIRD, 400, null);
		
		Train futureTrain = new Train("Hover Train", "IMAGE", 1000, 1000, Ages.FOURTH, 800, null);
		
		trainList.add(steamTrain);
		
		switch(age) {
			case FIRST:
				break;
			case SECOND:
				trainList.add(combustionTrain);
			case THIRD:
				trainList.add(combustionTrain);
				trainList.add(electricTrain);
			case FOURTH:
				trainList.add(combustionTrain);
				trainList.add(electricTrain);
				trainList.add(futureTrain);
			default:
				break;
		}
		
		return trainList;
	}
	
	public static Vector<TrainSpeedModifier> trainSpeedModifierGenerator(Ages age) {
		Vector<TrainSpeedModifier> u = new Vector<TrainSpeedModifier>();
		
		TrainSpeedModifier speedBoost = new TrainSpeedModifier("Speed Boost", "IMAGE", 10, 10, Ages.FIRST, 10);
		
		return u;
	}
	
	
}

