package com.eep.taxe.mvc.menu;

import com.eep.taxe.GameClient;
import com.eep.taxe.models.Game;

public class MenuModel {

	private GameClient client = null;
	private Game	   data   = null;
	private String	   nickname;
	
	public MenuModel(GameClient client) {
		this.client = client;
	}

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
