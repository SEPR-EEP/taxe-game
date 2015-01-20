package com.eep.taxe.mvc.menu;

import com.eep.taxe.GameClient;
import com.eep.taxe.models.Game;

/**
 * This is the Model class of the Menu of the Game.
 * It wraps the Game data and the instance of the Game Client.
 */
public class MenuModel {

	private GameClient client = null;
	private Game	   data   = null;
	private String	   nickname;
	
	public MenuModel(GameClient client) {
		this.client = client;
	}

	/**
	 * Gets the instance of the Client.
	 */
	public GameClient getClient() {
		return client;
	}

	public void setClient(GameClient client) {
		this.client = client;
	}

	public Game getData() {
		return data;
	}

	public void setData(Game data) {
		this.data = data;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
