package com.eep.taxe;

import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

/**
 * The GameClient Class represents a Network Client to the Game Server.
 * @author Alfio Emanuele Fresta <aef517@york.ac.uk>
 */
public class GameClient {
	
	/**
	 * The Game Server URL (must be available at all times!)
	 */
	final static String SERVER_URL = "http://taxe.alacriter-dev.uk:8080";

	/**
	 * This holds the connection to the server (see http://socket.io)
	 */
	private Socket socket;
	
	
	/**
	 * This holds the GameID of the current Game, if any, null otherwise.
	 */
	private String gameID = null;
	
	/**
	 * This holds my name in the current Game, if any, null otherwise.
	 */
	private String myName = null;
	private String myName = null;
	

	/**
	 * Asynchronously tries to connect to the Server.
	 * After calling this method, you should use isConnected() to check for a connection.
	 */
	public void connect() {
		try {
			this.socket = IO.socket(GameClient.SERVER_URL);
		} catch (URISyntaxException e) {
			System.out.println("Fatal Error: Malformed Server URI\n" 
					+ "Please check SERVER_URL in the GameClient Class, terminating.");
			System.exit(1);
		}
		this.socket.connect();
	}
	
	/**
	 * Immediately disconnect from the server
	 */
	public void disconnect() {
		if (this.isConnected()) {
			this.socket.disconnect();
		}
	}
	
	/**
	 * Checks whether there is a working connection to the Server.
	 * @return TRUE if there is a connection, FALSE otherwise.
	 */
	public Boolean isConnected() {
		return this.socket != null && this.socket.connected();
	}
	
	
	/**
	 * Checks whether there is connection and a game is in progress.
	 * @return TRUE if connected and in game, FALSE otherwise.
	 */
	public Boolean isConnectedAndPlaying() {
		return this.isConnected() && this.gameID != null;
	}
	
	/*
	 * ==================================
	 * ========   LIST GAMES   ==========
	 * ==================================
	 * Request...: "LG", null
	 * Response..: [
	 * 	{ "name":       "Jack's Game",
	 *    "difficulty": 10 },
	 *    ...
	 * ]
	 */
	
	/**
	 * Async operation. Ask for a Game List.
	 * @param Class with definition of what to do with the game list.
	 * @return TRUE if connected and request succeeds, FALSE otherwise.
	 */
	public Boolean listGames( final GameListResponse callback ) {
		if ( !this.isConnected() ) {
			return false;
		}
		this.socket.emit("LG", null, new ResponseHandler() {
			public void call(JSONArray response) {
				ArrayList<GameListItem> gameList = new ArrayList<GameListItem>();
				for ( int i = 0; i <= response.length(); i++ ) {
					try {
						gameList.add( new GameListItem(response.getJSONObject(i)) );
					} catch (JSONException e) { continue; }
				}
				callback.response(gameList);
			}
		});
		return true;
	}
	
	/**
	 * This represents an abstract response callback.
	 * The response({response}) method must be defined.
	 */
	abstract class ResponseHandler implements Ack {
		@Override
		public void call(Object... arg0) {
			System.out.println("Fatal Error: Unexpected response format.\n"
					+ "The Server is talking gibberish to me, terminating.");
			System.exit(1);
		}
	}
	
	/**
	 * Represents an item in the Game List (a Game that can be joined).
	 */
	static class GameListItem {
		public String 	id, name;
		public int 		difficulty;
		public GameListItem(JSONObject item) throws JSONException {
			this.id 		= item.getString("id");
			this.name 		= item.getString("name");
			this.difficulty = item.getInt("difficulty");
		}
	}
	
	/**
	 * Represents the an abstract Callback for the Game List response.
	 * Implement response(ArrayList<GameListItem> gameList) to get the data.
	 */
	interface GameListResponse {
		abstract void response(ArrayList<GameListItem> gameList);
	}


	
	/*
	 * TODO ACTIONS
	 */
	//public void 		createGame	( GameData data, final CreateGameResponse response )
	//public void 		joinGame	( String gameID, String myName )
	//public void 		sendMove	( GameData data )  // this sends "ET" or "M" depending on "amIFirst()"
	
	/*
	 * TODO CALLBACK
	 */
	//GameEvent				.catch()
	//GameEventWithData		.catch(GameData data)
	
	/*
	 * TODO EVENTS		
	 */
	//public void 		pleasePlay 	( final GameEvent event )
	//public void 		pleaseWait  ( final GameEvent event )
	
	/*
	 * TODO CHECKS		
	 */
	//public Boolean	isMyTurn	()
	//public Boolean	amIFirst	() 


}
