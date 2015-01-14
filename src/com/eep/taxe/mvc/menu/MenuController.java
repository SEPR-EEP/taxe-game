package com.eep.taxe.mvc.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import javax.swing.JTable;

import com.eep.taxe.GameClient.GameInfoResponse;
import com.eep.taxe.GameClient.GameListItem;
import com.eep.taxe.GameClient.GameListResponse;
import com.eep.taxe.GameClient.MoveEvent;
import com.eep.taxe.GameClient.StatusItem;
import com.eep.taxe.GameClient.StatusResponse;
import com.eep.taxe.GameData;
import com.eep.taxe.models.Game;
import com.eep.taxe.models.Game.Difficulty;
import com.eep.taxe.models.Player;
import com.eep.taxe.res.Map;
import com.eep.taxe.utils.RunnableArgs;

public class MenuController {

	private MenuView 	view 	= null;
	private MenuModel	model 	= null;
	
	
	private final int 	refreshEveryMs		= 1000;
	private boolean		refreshEnabled 		= false;
	
	private StartGameListener startGameListener = null;

	public MenuController(MenuView menuView, MenuModel menuModel) {
		this.view 	= menuView;
		this.model 	= menuModel;
		
		this.view.addMainButtonListener(new MainButtonListener());
		this.view.addBackButtonListener(new BackButtonListener());
		this.view.addCreditsButtonListener(new CreditsButtonListener());
		this.view.addCreateButtonListener(new CreateButtonListener());
		this.view.addTableMouseListener(new TableMouseListener());
		
		this.startGameListRefresher();
		
		this.model.getClient().setOnMove(new StartGame());
		
	}
	
	private class StartGame implements MoveEvent {
		@Override
		public void receive(GameData data) {
			view.setVisible(false);
			refreshEnabled = false;
			if ( data == null )
				data = model.getData();
			startGameListener.run((Game) data);
		}
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
			createNewGame();
		}		
	}
	
	private class TableMouseListener extends MouseAdapter {
	    public void mousePressed(MouseEvent me) {
	        JTable table =(JTable) me.getSource();
	        Point p = me.getPoint();
	        int row = table.rowAtPoint(p);
	        if (me.getClickCount() == 2) {
	            String gameID = view.getGameAtRow(row);
	            joinGame(gameID);
	        }
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
		
		// Ask for player's name
		view.askForName(new RunnableArgs(){
			@Override
			public void run() {
				
				final String name = (String) this.getArgs()[0];
				
				if ( name == null ) {
					view.showErrorMessage("Aborted.");
					return;
				}
				
				// Ask for difficulty
				view.askForDifficulty(new RunnableArgs() {

					@Override
					public void run() {
					
						if ( this.getArgs()[0] == null ) {
							view.showErrorMessage("Aborted.");
							return;
						}
						
						Integer d = Integer.valueOf((String) this.getArgs()[0]);
						Game data = generateGameData(name, d);
						model.setData(data);
						
						// Create the actual game
						model.getClient().createGame(name, d, data, new GameInfoResponse() {
							@Override
							public void response(GameListItem item) {
								
								view.showWaitScreen();
								System.out.println("Game #" + item.id + " created.");
								System.out.println("Ok, waiting for other player.");
								
							}
						});						
					}
				});
			}
		});
	}
	
	private void joinGame(final String gameID) {
		
		this.view.askForName(new RunnableArgs(){
			@Override
			public void run() {
				
				final String name = (String) this.getArgs()[0];

				if ( name == null ) {
					view.showErrorMessage("Aborted.");
					return;
				}
				
				model.getClient().joinGame(gameID, name, new StatusResponse() {
					@Override
					public void response(StatusItem item) {
						
						if ( !item.ok ) {
							view.showErrorMessage("Can't join: " + item.error);
							return;
						}
						
						view.showWaitScreen();
						System.out.println("Game #" + gameID + " joined.");
						System.out.println("Ok, waiting for other player.");
						
					}
				});

				
			}
		});

	}
	
	private Game generateGameData(String name, int i) {
		Difficulty d = null;
		switch (i) {
			case 1:
				d = Difficulty.EASY;
				break;
			case 2:
				d = Difficulty.MEDIUM;
				break;
			case 3:
				d = Difficulty.HARD;
				break;
		}
		
		// Create game
		Game g = new Game(name, d);
		
		g.getMasterPlayer() .setNickname(name);
		g.getSlavePlayer()  .setNickname("Waiting for Player 2 to Join");

		return g;
	}
	
	private void startGameListRefresher() {
        Timer autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
            	
            	if ( !refreshEnabled )
            		return;
            	
            	listGames();
            	
            }
        }, 0, refreshEveryMs);
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

	public void addStartGameListener(StartGameListener e) {
		this.startGameListener = e;
	}

	public interface StartGameListener  {
		public void run(com.eep.taxe.models.Game data);
	}

}
