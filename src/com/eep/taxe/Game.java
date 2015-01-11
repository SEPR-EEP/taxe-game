package com.eep.taxe;

import static org.junit.Assert.fail;

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Path;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Vertex;
import com.eep.taxe.mvc.game.*;
import com.eep.taxe.mvc.menu.*;
import com.eep.taxe.utils.Dijkstra;
import com.jayway.awaitility.Awaitility;



public class Game extends Awaitility {

	private static GameClient client 	= null;
	
	public final static int TIMEOUT = 5;
	
	public static void main(String[] args) {

		//// SORRY - I'll take this away from here ASAP
		
		// 1. Create some Railway Station
		Station 	Rome 		= new Station(30, 40, "Rome Fiumicino");
		Station		Paris 		= new Station(10, 30, "Paris Centràle");
		Station 	York 		= new Station(20, 10, "York Railway Station");
		Station 	Edinburgh	= new Station(20, 00, "Edinburgh Railway Station");
		Station		KingsX		= new Station(20, 20, "London King's Cross Station");
		Station		Victoria	= new Station(20, 25, "London Victoria");
		
		// 2. Create some edges
		new Edge(Edinburgh, 	York,		 	20);
		new Edge(York, 			KingsX,			60);
		new Edge(KingsX,		Victoria,		5);
		new Edge(Victoria, 		Paris,	 		15);
		new Edge(Victoria, 		Rome,			50);
		new Edge(Paris, 		Rome,			10);
		
		Station source = Paris;
		Station target = Rome;
				
		// 3. Finally, add all the stations to the graph
		Vector<Vertex> graph = new Vector<Vertex>();
		graph.add(Rome);
		graph.add(Paris);
		graph.add(York);
		graph.add(Edinburgh);
		graph.add(KingsX);
		graph.add(Victoria);	
		
		Dijkstra d 	= new Dijkstra(graph, source);
		Path p 		= d.getShortestPathTo(target);
		System.out.println("Min. distance is " + d.getDistanceTo(target));
		
		if (p == null) {
			System.out.println("No path was found from " + source.getName() 
					+ " to " + target.getName());
		} else {

			System.out.println("Source: " + source.getName());
			for (Edge e: p) {
				Station s1 = (Station) e.getVertices().get(0);
				Station s2 = (Station) e.getVertices().get(1);
				System.out.println("- " + e.getLength() + " km path from " +
									s1.getName() + " to " + s2.getName());
			}
			System.out.println("Target: " + target.getName());
			
		}
		
		///// --- Here it ends. 
				
		// Try to connect to the server
		client = new GameClient();
		System.out.println("Connecting to the server...");
		client.connect();
		
		// Wait for connection, at most TIMEOUT seconds
		await().atMost(TIMEOUT, TimeUnit.SECONDS).until(new Callable<Boolean>() {@Override
			public Boolean call() {
				return client.isConnected();
			}
		});
		
		// Check if a connection is established
		if ( !client.isConnected() ) {
			System.out.println("Timeout, connection failed. Terminating.");
			System.exit(1);
		}
		
		System.out.println("Conected, starting GUI...");
		
		// Start Menu MVC
		MenuView 		menuView 		= new MenuView();
		MenuModel		menuModel		= new MenuModel(client);
		MenuController	menuController	= new MenuController(menuView, menuModel);
		
		// On Game Start, load Game MVC
		menuController.onStartGame(menuController.new StartGameEvent() {
			@Override
			public void run(com.eep.taxe.models.Game data) {
				GameView 		gameView 		= new GameView();
				GameModel		gameModel 		= new GameModel(client, data);
				GameController	gameController	= new GameController(gameView, gameModel);
			}

		});
		
		
		
	
	}


}
