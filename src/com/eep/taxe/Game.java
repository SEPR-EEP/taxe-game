package com.eep.taxe;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.eep.taxe.mvc.game.*;
import com.eep.taxe.mvc.menu.*;
import com.jayway.awaitility.Awaitility;



public class Game extends Awaitility {

	private static GameClient client 	= null;
	
	public final static int TIMEOUT = 5;
	
	public static void main(String[] args) {

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
