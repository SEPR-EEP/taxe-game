package com.eep.taxe.mvc.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import com.eep.taxe.GameClient.GameInfoResponse;
import com.eep.taxe.GameClient.GameListItem;
import com.eep.taxe.GameClient.GameListResponse;
import com.eep.taxe.GameData;
import com.eep.taxe.models.Game;
import com.eep.taxe.utils.RunnableArgs;

public class MenuController {

	private MenuView 	view 	= null;
	private MenuModel	model 	= null;
	
	
	private final int 	refreshEveryMs		= 3000;
	private boolean		refreshEnabled 		= false;

	public MenuController(MenuView menuView, MenuModel menuModel) {
		this.view 	= menuView;
		this.model 	= menuModel;
		
		this.view.addMainButtonListener(new MainButtonListener());
		this.view.addBackButtonListener(new BackButtonListener());
		this.view.addCreditsButtonListener(new CreditsButtonListener());
		this.view.addCreateButtonListener(new CreateButtonListener());
		
		this.startGameListRefresher();
	}
	
	private class MainButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.showLobby();
			refreshEnabled = true;
		}		
	}	
	
	private class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.showMainMenu();
			refreshEnabled = false;
		}		
	}	
	
	private class CreditsButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Yaaaay, credits!");
		}		
	}	
	
	private class CreateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("'Create New Game' button pressed.");
			createNewGame();
		}		
	}
	
	private void listGames() {
    	model.getClient().listGames(new GameListResponse() {
			public void response(ArrayList<GameListItem> gameList) {
				view.emptyGameList();
				for ( GameListItem i: gameList ) {
					view.addGameToList(
						i.id,
						i.name,
						i.difficulty,
						i.created
					);
				}
			}  
    	});
	}
	
	private void createNewGame() {
		view.askForName(new RunnableArgs(){
			@Override
			public void run() {
				
				final String name = (String) this.getArgs()[0];
				
				if ( name == null ) {
					view.showErrorMessage("Aborted.");
					return;
				}
				
				view.askForDifficulty(new RunnableArgs() {

					@Override
					public void run() {
						
						if ( this.getArgs()[0] == null ) {
							view.showErrorMessage("Aborted.");
							return;
						}
						
						Integer d = Integer.valueOf((String) this.getArgs()[0]);
						GameData data = generateGameData(name, d);
						
						model.getClient().createGame(name, d, data, new GameInfoResponse() {
							@Override
							public void response(GameListItem item) {
								
								view.showErrorMessage("Game created");
								
							}
						});						
					}
					
				
				
				});

				
			}


		});
	}
	
	private GameData generateGameData(String name, int i) {
		return new GameData();
	}
	
	private void startGameListRefresher() {
        Timer autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
            	
            	if ( !refreshEnabled )
            		return;
            	
            	System.out.println("[Lobby] Refreshing Game List...");
            	listGames();
            	
            }
        }, 0, refreshEveryMs);
	}

	public void onStartGame(StartGameEvent e) {
		//e.run(model.getData());
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
