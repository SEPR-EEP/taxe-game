package com.eep.taxe.mvc.game;

import com.eep.taxe.GameClient;
import com.eep.taxe.models.Game;

public class GameModel {
	
	private GameClient 	client;
	private Game 		data;	
	
	/**
	 * Initialise a model for the Game 
	 * @param client	The GameClient object
	 * @param data 
	 */
	public GameModel( GameClient client, Game data ) {
		this.setClient(client);
		this.setData(data);
	}

	public Game getData() {
		return data;
	}

	public void setData(Game data) {
		this.data = data;
	}

	public GameClient getClient() {
		return client;
	}

	public void setClient(GameClient client) {
		this.client = client;
	}

}
