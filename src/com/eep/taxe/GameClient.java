package com.eep.taxe;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

import org.json.*;
import com.github.nkzawa.emitter.Emitter.Listener;
import com.github.nkzawa.socketio.client.*;

/**
 * The GameClient Class represents a Network Client to the Game Server.
 * @author Alfio Emanuele Fresta <aef517@york.ac.uk>
 */
public class GameClient {
	
	/**
	 * The Game Server URL (must be available at all times!)
	 */
	final static String SERVER_URL = "http://taxe-server.alacriter.co.uk:8042";

	/**
	 * This holds the connection to the server (see http://socket.io)
	 */
	public Socket socket;
	
	/**
	 * The event to trigger on move
	 */
	private MoveEvent onMove = null;
	

	public enum Role {
		MASTER, SLAVE;
	};
	private Role role;
	

	/**
	 * Asynchronously tries to connect to the Server.
	 * After calling this method, you should use isConnected() to check for a connection.
	 */
	public void connect() {
		
		// Add an option to force new connections to be always created 
		// (by default, socket.io will try and use only one connection per host,
		//  but here we want to simulate two different clients with two different sockets)
		IO.Options opts = new IO.Options();
		opts.forceNew = true;

		try {
			this.socket = IO.socket(GameClient.SERVER_URL, opts);
		} catch (URISyntaxException e) {
			System.out.println("Fatal Error: Malformed Server URI\n" 
					+ "Please check SERVER_URL in the GameClient Class, terminating.");
			System.exit(1);
		}
		this.socket.connect();
		Listener moveData = new Listener() {
			@Override
			public void call(Object... arg0) {
				if ( arg0.length != 1 ) {
					return;
				}
				GameData g = GameData.deserialise((byte[]) arg0[0]);
				if ( getOnMove() != null )
					getOnMove().receive(g);
			}
		};
		this.socket.on("PP", 	moveData);
		this.socket.on("GS", 	moveData);
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
	 * Async operation. Ask for a Game List.
	 * @param Class with definition of what to do with the game list.
	 * @return TRUE if connected and request succeeds, FALSE otherwise.
	 */
	public Boolean listGames( final GameListResponse callback ) {
		if ( !this.isConnected() ) {
			return false;
		}
		this.socket.emit("LG", null, new ResponseHandler() {
			@Override
			public void call(Object... args0) {
				JSONArray response = (JSONArray) args0[0];
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
	abstract class ResponseHandler implements Ack {	}
	
	/**
	 * Represents an item in the Game List (a Game that can be joined).
	 */
	static class GameListItem {
		public String 	id, name;
		public int 		difficulty;
		public Date		created;
		public GameListItem(JSONObject item) throws JSONException {
			this.id 		= item.getString("id");
			this.name 		= item.getString("name");
			this.difficulty = item.getInt("difficulty");	
			this.created 	= GameClient.getDateFromISOString(item.getString("created"));
		}
	}
	
	/**
	 * Represents the an abstract Callback for the Game List response.
	 * Implement response(ArrayList<GameListItem> gameList) to get the data.
	 */
	interface GameListResponse {
		abstract void response(ArrayList<GameListItem> gameList);
	}

	/**
	 * Represents a status response item
	 */
	static class StatusItem {
		public Boolean 	ok;
		public String 	error = "";
		public StatusItem(JSONObject item) throws JSONException {
			this.ok 		= item.getBoolean("ok");
			if ( !this.ok ) {
				this.error  = item.getString("error");
			}
		}
	}
	
	/**
	 * Represents the an abstract Callback for a status response (ok=bool, error=string).
	 * Implement response(StatusItem response) to get the data.
	 */
	interface StatusResponse {
		public void response(StatusItem item);
	}
	
	/**
	 * Represents the an abstract Callback for a response containing a single game information.
	 * Implement response(GameListItem response) to get the data.
	 */
	interface GameInfoResponse {
		public void response(GameListItem item);
	}
	
	/**
	 * Represents the abstract Callback for a Game Data response
	 * Implement receive(GameData data) to get the updated game data.
	 */
	interface MoveEvent {
		public void receive(GameData data);
	}
	
	/**
	 * Async operation. Create a game if possible.
	 * @param playerName	The name of the player
	 * @param difficulty	The difficulty level
	 * @param gameData		The initial game data
	 * @param callback		Response callback
	 * @return				TRUE if connected and request succeeds, FALSE otherwise.
	 */
	public Boolean createGame( String playerName, int difficulty, GameData gameData, final GameInfoResponse callback ) {
		if ( !this.isConnected() ) {
			return false;
		}
		Object[] params = {playerName, difficulty, gameData.serialise()};
		System.out.println("TYPE: " + params[2].getClass().getName());
		this.socket.emit("CG", params, new ResponseHandler() {
			
			public void call(Object... args0) {
				JSONObject response = (JSONObject) args0[0];
				GameListItem item = null;
				try {
					item = new GameListItem(response);
				} catch (JSONException e) { 
					e.printStackTrace();
				}
				setRole(GameClient.Role.MASTER);
				callback.response(item);
			}
		});
		return true;
	}


	/**
	 * Async operation. Try to join a game.
	 * @param gameID		The ID of the Game to join
	 * @param playerName	The name of the player to use to join
	 * @param callback		Response callback
	 * @return				TRUE if connected and request succeeds, FALSE otherwise.
	 */
	public Boolean joinGame( String gameID, String playerName, final StatusResponse callback ) {
		if ( !this.isConnected() ) {
			return false;
		}
		Object[] params = {gameID, playerName};
		this.socket.emit("JG", params, new ResponseHandler() {
			
			public void call(Object... args0) {
				JSONObject response = (JSONObject) args0[0];
				StatusItem item = null;
				try {
					item = new StatusItem(response);
				} catch (JSONException e) { 
					e.printStackTrace();
				}
				if ( item.ok )
					setRole(GameClient.Role.SLAVE);
				callback.response(item);
			}
		});
		return true;
	}
	
	/**
	 * Send move data to the opponent
	 * If I'm a master client, send a "ET" event, "M" otherwise
	 * @param gameData
	 * @param statusResponse
	 */
	public void sendMove(GameData gameData, final StatusResponse callback) {
		String event;
		if ( this.getRole() == GameClient.Role.MASTER ) {
			event = "ET";
		} else {
			event = "M";
		}
		this.socket.emit(event, gameData.serialise(), new ResponseHandler() {
			
			public void call(Object... args0) {
				JSONObject response = (JSONObject) args0[0];
				StatusItem item = null;
				try {
					item = new StatusItem(response);
				} catch (JSONException e) { 
					e.printStackTrace();
				}
				callback.response(item);
			}
		});
		
	}

	
	public static Date getDateFromISOString(String ISOString) {
		return javax.xml.bind.DatatypeConverter.parseDateTime(ISOString).getTime();
	}

	public MoveEvent getOnMove() {
		return onMove;
	}

	public void setOnMove(MoveEvent onMove) {
		this.onMove = onMove;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
