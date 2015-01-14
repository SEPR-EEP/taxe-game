package com.eep.taxe.mvc.game;

import com.eep.taxe.GameClient.Role;

public class GameController {

	private GameView	view	= null;
	private GameModel	model 	= null;
	
	public GameController(GameView gameView, GameModel gameModel) {
		this.setView(gameView);
		this.setModel(gameModel);

		// Add to the window title " - Playing as NAME (ROLE)"
		view.setTitle(
			view.getTitle() + 
			" - Playing as: " + model.getMyNickname() + " (" +
			(model.getMyRole() == Role.MASTER ? "Master" : "Slave") + ")"
		);
		
	}

	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public GameModel getModel() {
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

}
