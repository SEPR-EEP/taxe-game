package com.eep.taxe.res;

import java.util.ArrayList;
import java.util.Vector;

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
	public static Vector<Vertex> generateMap() {
		Vector<Vertex> r = new Vector<Vertex>();
		
		Station Rome 		= new Station(60, 40, "Rome Fiumicino");
		Station Paris 		= new Station(27, 20, "Paris Centràle");
		Station Berlin 		= new Station(60, 10, "Berlin Hauptbahnhof");
		Station Sofia		= new Station(80, 25, "Sofia Station");
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
				"Travel from " +startingStation.getName() +" to " +endingStation.getName(),
				"Long description - maybe random scenario?",
				startingStation,
				endingStation); 
		return goal;
	}

	public static ArrayList<Train> generateTrains(Ages age, Game game) {
		ArrayList<Train> trainList = new ArrayList<Train>();
		
		Train steamTrain = new Train("Steam Train", "IMAGE", 100, 100, Ages.FIRST, 500, null);
		
		Train combustionTrain = new Train("Combustion Train", "IMAGE", 300, 300, Ages.SECOND, 800, null);
		
		Train electricTrain = new Train("Electric Train", "IMAGE", 600, 600, Ages.THIRD, 1000, null);
		
		Train futureTrain = new Train("Hover Train", "IMAGE", 1000, 1000, Ages.FOURTH, 1500, null);
		
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
	
	public static Vector<Usable> generateTrainSpeedModifier(Ages age) {
		Vector<Usable> trainSpeedModifierList = new Vector<Usable>();
		
		TrainSpeedModifier smallSpeedBoost 	= new TrainSpeedModifier("Small Speed Boost", 	"/resources/SpeedBoost.png", 10, 10, Ages.FIRST, 	1.2f);
		TrainSpeedModifier mediumSpeedBoost = new TrainSpeedModifier("Medium Speed Boost", 	"/resources/SpeedBoost.png", 30, 30, Ages.SECOND, 	1.5f);
		TrainSpeedModifier largeSpeedBoost	= new TrainSpeedModifier("Large Speed Boost",	"/resources/SpeedBoost.png", 60, 60, Ages.THIRD, 	1.9f);
		TrainSpeedModifier megaSpeedBoost 	= new TrainSpeedModifier("Mega Speed Boost", 	"/resources/SpeedBoost.png", 100, 100, Ages.FOURTH, 2.5f);
		
		trainSpeedModifierList.add(smallSpeedBoost);
		
		switch(age) {
			case FIRST:
				break;
			case SECOND:
				trainSpeedModifierList.add(mediumSpeedBoost);
			case THIRD:
				trainSpeedModifierList.add(mediumSpeedBoost);
				trainSpeedModifierList.add(largeSpeedBoost);
			case FOURTH:
				trainSpeedModifierList.add(mediumSpeedBoost);
				trainSpeedModifierList.add(largeSpeedBoost);
				trainSpeedModifierList.add(megaSpeedBoost);
			default:
				break;
			}
		
		return trainSpeedModifierList;
	}
	
}

