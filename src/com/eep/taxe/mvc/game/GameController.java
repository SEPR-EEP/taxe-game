package com.eep.taxe.mvc.game;

public class GameController {

	private GameView	view	= null;
	private GameModel	model 	= null;
	
	public GameController(GameView gameView, GameModel gameModel) {
		this.setView(gameView);
		this.setModel(gameModel);
		
		System.out.println("Data received: " + gameModel.getData());
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
