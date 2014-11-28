package com.eep.taxe.mvc.menu;

import com.eep.taxe.models.Game;

public class MenuController {

	private MenuView 	view 	= null;
	private MenuModel	model 	= null;
	
	public MenuController(MenuView menuView, MenuModel menuModel) {

	}
	
	public void onStartGame(StartGameEvent e) {
		e.run(model.getData());
	}

	public MenuView getView() {
		return view;
	}

	public void setView(MenuView view) {
		this.view = view;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public abstract class StartGameEvent  {
		public abstract void run(Game data);
	}

}
