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
import com.eep.taxe.GameClient.Role;
import com.eep.taxe.GameClient.StatusItem;
import com.eep.taxe.GameClient.StatusResponse;
import com.eep.taxe.GameData;
import com.eep.taxe.models.Game;
import com.eep.taxe.models.Game.Difficulty;
import com.eep.taxe.models.Player;
import com.eep.taxe.res.Generator;
import com.eep.taxe.utils.RunnableArgs;

/**
 * This is the Controller class (MVC) for the Menu of the Game.
 * The role of the Controller is to allow all of the Lobby operations,
 * including the creation of a game, listing of the available games and
 * joining a new game. Moreover it handles smaller opeartions such as
 * displaying the game credits.
 */
public class MenuController {

	private MenuView1 	view 	= null;
	private MenuModel	model 	= null;
	
	private String 		name 	= "";
	
	/*
	 * These properties are used to configure the 
	 * automatic games list refresher.
	 */
	
	/**
	 * How often to refresh the games list (in milliseconds).
	 */
	private final int 	refreshEveryMs		= 1000;
	
	/**
	 * Enable or disable the automatic refreshing of the games list.
	 */
	private boolean		refreshEnabled 		= false;
	
	private StartGameListener startGameListener = null;

	public MenuController(MenuView1 menuView, MenuModel menuModel) {
		this.view 	= menuView;
		this.model 	= menuModel;
		
		// Add all of the Listeners for Events generated in the View
		//this.view.addMainButtonListener(new MainButtonListener());
		//this.view.addBackButtonListener(new BackButtonListener());
		//this.view.addCreditsButtonListener(new CreditsButtonListener());
		//this.view.addCreateButtonListener(new CreateButtonListener());
		//this.view.addTableMouseListener(new TableMouseListener());
		
		this.view.addQuitButtonMouseListener(new QuitLabelMouseListener());
		this.view.addEnterButtonMouseListener(new EnterButtonMouseListener());
		
		// Start the Refresher for the Game List
		this.startGameListRefresher();
		
		// Assign the StartGame class to catch any Move Event (see below)
		this.model.getClient().setOnMove(new StartGame());
		
	}
	
	/*
	 * ==== EVENTS: ENTER GAME SCREEN ====
	 */
	
	// QUIT button
	private class QuitLabelMouseListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			System.exit(1);
		}
	}
	
	// ENTER button
	private class EnterButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			name = view.getUserName();
			if ( !validUserName(name) ) {
				return;
			}
			
			view.showMainMenu();
		}
	}
	
	private boolean validUserName(String name) {
		if ( name == null || name.length() < 2 ) {
			view.showErrorMessage("Please choose a longer name.");
			return false;
		}
		// TODO More checks on valid user names
		return true;
	}
	
	/**
	 * This class catches any MoveEvent. The first move event is
	 * always triggered at the Game start. If I am the Master player,
	 * I created the Game data myself so I will not receive any "data"
	 * parameter (data==null). If I am the Slave, instead, I will
	 * receive from the Server the initial Game State as "data".
	 */
	private class StartGame implements MoveEvent {
		@Override
		public void receive(GameData data) {
			view.setVisible(false);
			refreshEnabled = false;
			
			Role myRole;
			if ( data == null ) {
				// I did not receive Game Data, as I'm the Master player
				myRole = Role.MASTER;
				data = model.getData();	// I get the data I generated myself
			} else {
				// I received Game Date generated from the Master, I'm Slave
				myRole = Role.SLAVE;
			}
			
			/**
			 * Now I wake up the Start Game Listener -
			 * this is used in the main com.eep.taxe.Game class
			 * to open the Game View (set up Game MVC).
			 */
			startGameListener.run(
					(Game) data,			// Initial Game Data
					myRole,					// My Role in the Game
					model.getNickname()		// My Nickname
			);
			
		}
	}
	
	private class MainButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//view.showLobby();
			refreshEnabled = true;
		}		
	}	
	
	private class BackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//view.showMainMenu();
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
	        JTable table = (JTable) me.getSource();
	        Point p = me.getPoint();
	        int row = table.rowAtPoint(p);
	        if (me.getClickCount() == 2) { // I want a double click
	           // String gameID = view.getGameAtRow(row);
	            //joinGame(gameID);
	        }
	    }

	}
	
	/**
	 * Procedure to list the available games to join. This function
	 * is called by the automatic game list refresher. This function
	 * contacts the server asking for the Game list. Then, contacts
	 * the view asking to empty the game list. Finally, for each game
	 * in the game list received, contacts the view and ask to add
	 * the game to the list.
	 */
	private void listGames() {
    	model.getClient().listGames(new GameListResponse() {
			public void response(ArrayList<GameListItem> gameList) {
				/*view.emptyGameList();
				for ( GameListItem i: gameList ) {
					view.addGameToList(
						i.id,
						i.name,
						i.difficulty,
						i.created
					);
				}*/
			}  
    	});
	}
	
	/**
	 * Procedure to create a new game. First, ask the player
	 * for their name and the difficulty of the game they want 
	 * to create. Secondly, generate the Initial Game Data.
	 * Thirdly, contact the server and ask to create a Game.
	 * Once I get an answer from the server, I show the Player
	 * the Wait Screen - to wait for an opponent.
	 */
	private void createNewGame() {
		
		// Ask for player's name
		//view.askForName(new RunnableArgs(){
			//@Override
			//public void run() {
				
				//final String name = (String) this.getArgs()[0];
				
				//if ( name == null ) {
					//view.showErrorMessage("Aborted.");
					//return;
				//}
				
				// Ask for difficulty
				/*view.askForDifficulty(new RunnableArgs() {

					@Override
					public void run() {
					
						if ( this.getArgs()[0] == null ) {
							view.showErrorMessage("Aborted.");
							return;
						}
						
						Integer d = Integer.valueOf((String) this.getArgs()[0]);
						Game data = generateGameData(name, d);
						model.setData(data);
						model.setNickname(name);
						
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
				});*/
			//}
		//});
	}
	
	/**
	 * Procedure to Join a Game. This is called from the function
	 * that listens for a double click on the table, passing the 
	 * ID of the Game to Join as a parameter. First, I ask the 
	 * Player for their name. Secondly, I contact the server
	 * asking to Join the Game with the Nickname. Finally,
	 * if the Server says that everything's okay, I take the
	 * Player to the Waiting screen - where they should not stay
	 * really long as I'm only waiting for the server to send me
	 * the Initial Game Data, so we can start the Game.
	 * @param gameID	The ID of the Game to Start.
	 */
	private void joinGame(final String gameID) {
		
		/*this.view.askForName(new RunnableArgs(){
			@Override
			public void run() {
				
				final String name = (String) this.getArgs()[0];

				if ( name == null ) {
					view.showErrorMessage("Aborted.");
					return;
				}
				
				model.setNickname(name);
				
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
		});*/

	}
	
	/**
	 * This function is used in the procedure to create a new game
	 * to generate the Initial Game Data, given the Name of the Player
	 * who created the Game and the difficulty value, still as an integer
	 * (as returned from the view). This creates a new Game (GameData)
	 * instance, set the nickname, the name of the Game, the difficulty
	 * and returns the Initial Game Data.
	 * @param name		The Name of the Game
	 * @param i			The difficulty (as integer)
	 * @return			The generated Initial Game Data
	 */
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
		g.getSlavePlayer()  .setNickname("Your Opponent");

		return g;
	}
	
	/**
	 * Creates a timer that periodically executes some code.
	 * The period of the refresher is set in the "refreshEveryMs"
	 * property. To enable the game list refresh, the "refreshEnabled"
	 * boolean flag needs to be set to true. This is done, for example,
	 * when the Lobby view is opened. When the flag is set to true
	 * the function "listGames" is called every "refresEveryMs" ms.
	 */
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


	public MenuView1 getView() {
		return view;
	}

	public void setView(MenuView1 view) {
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
		public void run(com.eep.taxe.models.Game data, Role role, String nickname);
	}

}
