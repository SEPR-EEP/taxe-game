package com.eep.taxe;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.lang3.RandomStringUtils;

import com.eep.taxe.GameClient.GameInfoResponse;
import com.eep.taxe.GameClient.GameListItem;
import com.eep.taxe.GameClient.GameListResponse;
import com.eep.taxe.GameClient.MoveEvent;
import com.eep.taxe.GameClient.StatusItem;
import com.eep.taxe.GameClient.StatusResponse;

public class Game {

	private static 	GameClient client1 	= null;
	private static 	GameClient client2 	= null;
	private static 	GameData   gameData = null;
	
	public GameClient getClient1() {
		return client1;
	}

	public void setClient1(GameClient client1) {
		Game.client1 = client1;
	}

	public GameClient getClient2() {
		return client2;
	}

	public void setClient2(GameClient client2) {
		Game.client2 = client2;
	}
	
	public static void main(String[] args) {

		Game game = new Game();

		// Initialise the Game Clients
		game.setClient1(new GameClient());
		game.setClient2(new GameClient());
		
		// Prepares some random Game Data
		gameData = new GameData();
		//gameData.setSomeRandomParameter("J");
		
        String  choice;
        
        // Main program loop
        while ( true ) {
        	
        	// Show the menu
        	choice = Game.menu();
        	
        	// Execute appropriate function
        	switch ( choice ) {
        	case "connect":
        	//	game.connect();
        		break;
        	case "create":
        		game.createGame();
				break;
        	case "list":
        		game.listGames();
        		break;
        	case "join":
        		game.joinGame();
        		break;        	
        	case "send1":
        		game.sendData(client1);
        		break;
        	case "send2":
        		game.sendData(client2);
        		break;
        	case "disconnect":
        		game.disconnect();
        		break;	
        	case "quit":
        		System.exit(0);
        		break;
    		default:
    			System.out.println("Please try again.");
    			break;
        	}

        	
        }
        
		
	
	}


	/**
	 * Display the menu and ask the user to choose an entry.
	 * @return User input.
	 */
	private static String menu() {
    	System.out.println("connect			Connect both clients");
    	System.out.println("create			Client 1: Create game");
    	System.out.println("list			Client 2: list games");
    	System.out.println("join			Client 2: Join game");
    	System.out.println("send1			Client 1: Send random data");
    	System.out.println("send2			Client 2: Send random data");
    	System.out.println("disconnect		Disconnect both clients");
    	System.out.println("quit			Exit application");
    	System.out.println(" Please choose an option.");
    	return Game.input();
	}
	
	/**
	 * Ask for player's name and create a new game.
	 */
	private void createGame() {
		System.out.println("> What is your name: ");
		String playerName = Game.input();
		getClient1().createGame(playerName, 42, gameData, new GameInfoResponse() {
			@Override
			public void response(GameListItem item) {
				System.out.println("< Created game");
				System.out.println("< * Game ID:   " + item.id);
				System.out.println("< * Game name: " + item.name);
				System.out.println("< * Game diff: " + item.difficulty);
			}
		});
	}
	
	/**
	 * Show a list of available games
	 */
	private void listGames() {
		getClient2().listGames(new GameListResponse() {		
			
			@Override
			public void response(ArrayList<GameListItem> gameList) {
				
				// Prints out the list of games
				JFrame list = new JFrame("Available Games");
				list.setSize(800, 600);
				list.setVisible(true);
				
				// Columns of the table
				Vector<String> columns = new Vector<String>();
				columns.add("Game ID");
				columns.add("Game name");
				columns.add("Difficulty");
				columns.add("Created");
				
				// Create data matrix 
				Vector<Vector<String>> data = new Vector<Vector<String>>();
				
				// For each element in the game list 
				for ( GameListItem game : gameList ) {

					// Add this element
					Vector<String> single = new Vector<String>();
					single.add(game.name);
					single.add(game.id);
					single.add(String.valueOf(game.difficulty));
					single.add(game.created.toString());
					data.add(single);
					
				}
				
				// Create the table
				JTable jt = new JTable(data, columns);
				jt.setPreferredScrollableViewportSize(new Dimension(800, 600));
				jt.setFillsViewportHeight(true);
				
				// Create panel w/ table and add it to the main frame
				JScrollPane scroll = new JScrollPane(jt);
				list.add(scroll);

				
			}
		});

	}
	
	
	/**
	 * Ask game ID and player name and join a game
	 */
	private void joinGame() {
		System.out.println("> Game ID: ");
		String gameID = Game.input();
		System.out.println("> Join as: ");
		String joinAs = Game.input();
		getClient2().joinGame(gameID, joinAs, new StatusResponse() {
			@Override
			public void response(StatusItem item) {
				if ( item.ok ) {
					System.out.println("< OK");
				} else {
					System.out.println("< Error: " + item.error);
				}
			}
		});

	}
	
	/** 
	 * Create and send a random string from the specified client
	 * @param The client to use to send the data 
	 */
	private void sendData(GameClient from) {
		String randomString = RandomStringUtils.random(5);
		System.out.println("> Sending random string as Game Data: " + randomString);
		//gameData.setSomeRandomParameter(randomString);
		from.sendMove(gameData, new StatusResponse() {
			@Override
			public void response(StatusItem item) {
				if ( item.ok ) {
					System.out.println("> OK");
				} else {
					System.out.println("> Error: " + item.error);
				}
			}
		});
		
	}
	
	/**
	 * Disconnect both clients
	 */
	private void disconnect() {
		getClient1().disconnect();
		getClient2().disconnect();
		System.out.println("< Disconnected");
	}
	
	/**
	 * Provides a simple utility to read a string from the CLI
	 * @return A single line string inserted by the user
	 */
	public static String input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException ioe) {
			return "";
		}
	}


}
