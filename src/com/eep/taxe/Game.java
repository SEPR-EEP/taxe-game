package com.eep.taxe;

import java.util.ArrayList;

import com.eep.taxe.GameClient.GameListItem;
import com.eep.taxe.GameClient.GameListResponse;

public class Game {

	private static GameClient client = null;
	
	public static void main(String[] args) {


		// Initialise the Game Client
		client = new GameClient();
		
		// Try and connect to the server
		System.out.println("Connecting...");
		client.connect();
		
		// Check for connection
		if ( client.isConnected() ) {
			System.out.println("Yeah, I connected successfully.");
		} else {
			System.out.println("Sorry, I could not connect.");
		}
		
		// Try and obtain a games list (async)
		client.listGames(new GameListResponse() {			
			@Override
			public void response(ArrayList<GameListItem> gameList) {
				
				// Prints out the list of games
				System.out.println("List of games:");
				for ( GameListItem game : gameList ) {
					System.out.println("* " + game.name
							+ " (difficulty " + game.difficulty + ","
							+ " id " + game.id + "),");
				}
				System.out.println("End of list.");
				
			}
		});
			
		// Okay, we're done here, disconnect
		client.disconnect();
		System.out.println("Disconnected.");
		
	}
	


}
