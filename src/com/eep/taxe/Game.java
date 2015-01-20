package com.eep.taxe;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.eep.taxe.GameClient.Role;
import com.eep.taxe.mvc.game.*;
import com.eep.taxe.mvc.menu.*;
import com.eep.taxe.mvc.menu.MenuController.StartGameListener;
import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.core.ConditionTimeoutException;

/**
 * This is the Game entry point. This class is responsible to 
 * create an instance of the client, test for the network connection,
 * the generate an instance of the Menu (MVC) and listen for the start
 * game event. When this occurs, it builds the Game (MVC).
 */
public class Game extends Awaitility {

	private static GameClient client 	= null;
	
	public final static int TIMEOUT = 5;
	
	public static void main(String[] args) {

		// Create game client
		client = new GameClient();

		// Ask for the server URI to connect to
		String serverURI = Game.askForServer(client.getServer());
		
		if ( serverURI == null ) {
			System.out.println("User clicked cancel. Terminating.");
			System.exit(0);
		}
		
		// Set server URI
		client.setServer(serverURI);
		
		System.out.println("Connecting to the server...");
		try {
			client.connect();

		} catch ( Exception e ) {
			// Connection will fail immediately for malformed server URIs.
			System.out.println("ERROR!");
			System.out.println("The URI is not valid. Terminating.");
			showURIError();
			System.exit(1);
		}
		
		// Wait for connection, at most TIMEOUT seconds
		try {
			await().atMost(TIMEOUT, TimeUnit.SECONDS)
			.until(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return client.isConnected();
				}
			});
		} catch (ConditionTimeoutException e ) {
			
			// Timeout occurred :(
			System.out.println("ERROR!");
			System.out.println("Timeout occurred while trying to connect to '"
					+ client.getServer() + "'\nTerminating.");
			showConnectionError();
			System.exit(1);
			
		}
		
		System.out.print("Starting Menu Graphical User Interface...");
		
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
			System.out.print("Game initiated, starting Game Graphical User Interface... ");

			System.out.print("V..");
			GameView 		gameView 		= new GameView();
			System.out.print("M..");
			GameModel		gameModel 		= new GameModel(client, data);
							gameModel		  .setMyRole(role);
							gameModel		  .setMyNickname(nickname);
			System.out.print("C.. ");
			GameController	gameController	= new GameController(gameView, gameModel);
			System.out.println("DONE.");
		}
	}
	
	/**
	 * Shows a dialog asking for a server URI.
	 * The box is pre-filled with the suggested server URI.
	 * @param suggested	The suggested (default) server URI.
	 * @return			The server URI to connect to.
	 */
	private static String askForServer(String suggested) {
		String s = (String) JOptionPane.showInputDialog(
			new JFrame(),
        	"To use the default server and start the game, click OK.\n"
			+ "Otherwise, insert server URI and click OK.",
        	"Choose Game Server",
        	JOptionPane.PLAIN_MESSAGE,
        	null,
        	null,
			suggested
        );
		return s;
	}

	/**
	 * Shows a connection (timeout) error.
	 */
	private static void showConnectionError() {
		JOptionPane.showMessageDialog(
			new JFrame(),
		    "Could not connect to the specified URI.\n" +
		    		"Please check the Server URI and try again.",
		    "Connection timeout",
		    JOptionPane.ERROR_MESSAGE
		);

	}
	
	/**
	 * Shows an URI error.
	 */
	private static void showURIError() {
		JOptionPane.showMessageDialog(
			new JFrame(),
		    "The specified URI is invalid.\n" +
		    		"Please check the Server URI and try again.",
		    "Invalid URI",
		    JOptionPane.ERROR_MESSAGE
		);

	}

}
