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
 * This class is used to generate static content for the game
 * (such as the initial map) and get all of the possible object
 * resources - e.g. trains, goals, goats and trains.
 */
public class Generator {
	
	/**
	 * Generates a map as a list of vertices. The vertices are
	 * already connected by a number of edges.
	 * @return	The list of vertices generated - a.k.a. a map.
	 */
	public static Vector<Vertex> generateMap() {
		Vector<Vertex> r = new Vector<Vertex>();
		
		Station Rome 		= new Station(37, 41, "Rome Fiumicino");
		Station Paris 		= new Station(22, 23, "Paris Centràle");
		Station Berlin 		= new Station(38, 19, "Berlin Hauptbahnhof");
		Station Sofia		= new Station(55, 35, "Sofia Station");
		Station Madrid		= new Station(15, 40, "Madrid Atocha Station");
		
		Junction JN1		= new Junction(20, 30);
		Junction JN2		= new Junction(35, 30);
		
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

	/**
	 * This method is used to generate a Goal. 
	 * @param train				The train for which you want to generate the goal.
	 * @param map				The map, to give context, and allow the generator to pick random stations.
	 * @param player			The player who you want to generate the goal for.
	 * @param startingStation	The starting station for the Goal - so maybe the latest station the train visited.
	 * @param game				The Game instance. 
	 * @return					A brand new goal for your player's train.
	 */
	public static Goal generateGoal(Train train, Vector<Vertex> map, Player player, Station startingStation, Game game) {
		if (startingStation == null) {
			return null;
		}
		
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

	/**
	 * This method returns the list of possible trains at a given age.
	 * @param age		The Age. More recent ages unlock more recent trains.
	 * @param game		The game.
	 * @return			All of the trains you may want to acquire at this age.
	 */
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
	
	/**
	 * Returns the list of train speed modifier unlocked at a given age.
	 * @param age		The age.
	 * @return			The list of train speed modifiers unlocked at the given age.
	 */
	public static Vector<Usable> generateTrainSpeedModifier(Ages age) {
		Vector<Usable> trainSpeedModifierList = new Vector<Usable>();
		
		TrainSpeedModifier smallSpeedBoost 		= new TrainSpeedModifier("Small Speed Boost", 	"/resources/SpeedBoost.png", 10, 10, Ages.FIRST, 	1.2f);
		TrainSpeedModifier smallSpeedSlowdown 	= new TrainSpeedModifier("Small Speed Slowdown","/resources/SlowDown.png", 10, 10, Ages.FIRST, 		0.8f);
		
		TrainSpeedModifier mediumSpeedBoost 	= new TrainSpeedModifier("Medium Speed Boost", 	"/resources/SpeedBoost.png", 30, 30, Ages.SECOND, 	1.5f);
		TrainSpeedModifier mediumSpeedSlowdown 	= new TrainSpeedModifier("Medium Speed Slowdown","/resources/SlowDown.png", 30, 30, Ages.SECOND, 	0.6f);
		
		TrainSpeedModifier largeSpeedBoost		= new TrainSpeedModifier("Large Speed Boost",	"/resources/SpeedBoost.png", 60, 60, Ages.THIRD, 	1.9f);
		TrainSpeedModifier largeSpeedSlowdown	= new TrainSpeedModifier("Large Speed Slowdown",	"/resources/SlowDown.png", 60, 60, Ages.THIRD, 	.4f);
		
		TrainSpeedModifier megaSpeedBoost	 	= new TrainSpeedModifier("Mega Speed Boost", 	"/resources/SpeedBoost.png", 100, 100, Ages.FOURTH, 2.5f);
		TrainSpeedModifier megaSpeedSlowdown 	= new TrainSpeedModifier("Mega Speed Slowdown", "/resources/SlowDown.png", 100, 100, Ages.FOURTH, 	.2f);
		
		trainSpeedModifierList.add(smallSpeedBoost);
		trainSpeedModifierList.add(smallSpeedSlowdown);
		
		switch(age) {
		
		case FOURTH:
			trainSpeedModifierList.add(megaSpeedBoost);
			trainSpeedModifierList.add(megaSpeedSlowdown);
		case THIRD:
			trainSpeedModifierList.add(largeSpeedBoost);
			trainSpeedModifierList.add(largeSpeedSlowdown);
		case SECOND:
			trainSpeedModifierList.add(mediumSpeedBoost);
			trainSpeedModifierList.add(mediumSpeedSlowdown);
		default:
			break;
		}
		
		return trainSpeedModifierList;
	}
	
}

