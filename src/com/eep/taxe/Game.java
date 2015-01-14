package com.eep.taxe;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.eep.taxe.GameClient.Role;
import com.eep.taxe.mvc.game.*;
import com.eep.taxe.mvc.menu.*;
import com.eep.taxe.mvc.menu.MenuController.StartGameListener;
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
		
		System.out.print("Connected, starting GUI...");
		
		// Start Menu MVC
		MenuView 		menuView 		= new MenuView();
		MenuModel		menuModel		= new MenuModel(client);
		MenuController	menuController	= new MenuController(menuView, menuModel);
		
		System.out.println(" DONE.");
		
		// On Game Start, load Game MVC
		menuController.addStartGameListener(
			new StartGameEvent()
		);
	
	}
	
	// Event to catch the Start Game event.
	// It sets up the whole Game MVC.
	public static class StartGameEvent implements StartGameListener {
				
		public void run(
				com.eep.taxe.models.Game data,
				Role role, String nickname
		) {
			System.out.print("Game initiated, starting GUI...");

			GameView 		gameView 		= new GameView();
			GameModel		gameModel 		= new GameModel(client, data);
							gameModel		  .setMyRole(role);
							gameModel		  .setMyNickname(nickname);
			GameController	gameController	= new GameController(gameView, gameModel);
			System.out.println(" DONE.");
		}
	}


}
