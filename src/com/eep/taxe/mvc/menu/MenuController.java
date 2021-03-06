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

/**
 * This is the Controller class (MVC) for the Menu of the Game.
 * The role of the Controller is to allow all of the Lobby operations,
 * including the creation of a game, listing of the available games and
 * joining a new game. Moreover it handles smaller opeartions such as
 * displaying the game credits.
 */
public class MenuController {

	private MenuView 	view 	= null;
	private MenuModel	model 	= null;
	
	private String 		name 	= "";
	
	private final String	HOWTOPLAY_URL 	= "http://sepr-eep.github.io/usermanual/";
	
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

	public MenuController(MenuView menuView, MenuModel menuModel) {
		this.view 	= menuView;
		this.model 	= menuModel;
		
		/**
		 * This adds all of the listeners for any significant action triggered
		 * by the user in the Menu UI.
		 */
		this.view.addQuitButtonMouseListener(new QuitLabelMouseListener());
		this.view.addEnterButtonMouseListener(new EnterButtonMouseListener());
		
		this.view.addCreditsButtonMouseListener(new CreditsButtonMouseListener());
		this.view.addHowToPlayButtonMouseListener(new HowToPlayButtonMouseListener());
		this.view.addBackButtonMouseListener(new BackButtonMouseListener());
		this.view.addStartGameButtonMouseListener(new StartGameButtonMouseListener());
		
		this.view.addHostGameButtonMouseListener(new HostGameButtonMouseListener());
		
		this.view.addCancelButtonMouseListener(new CancelButtonMouseListener());
		this.view.addCreateGameButtonMouseListener(new CreateGameButtonMouseListener());
		
		this.view.addTableMouseListener(new TableMouseListener());

		/**
		 * This method is responsible of starting to periodically
		 * refresh the games list. 
		 */
		this.startGameListRefresher();
		
		/**
		 * As we still are in the Menu, when a move is detected, it means
		 * it is the first move and that the game has started. So we listen for
		 * the onMove event and assign the StartGame() class to handle the case.
		 */
		this.model.getClient().setOnMove(
			new StartGame()
		);
		
	}
	
	/**
	 * ==========================================================
	 *   <BORING> - HERE ALL OF THE CLICK LISTENERS ARE DEFINED
	 * ==========================================================
	 */
	
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
		// TODO You may want to do more checks on valid user names
		return true;
	}
	

	/*
	 * ==== EVENTS: MENU GAME SCREEN ====
	 */
	private class CreditsButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			view.showCredits();
		}
	}
	
	private class HowToPlayButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			view.openWebpage(HOWTOPLAY_URL);
		}
	}
	
	private class BackButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			view.showMainMenu();
			refreshEnabled = false;
		}
	}	
	
	private class StartGameButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			view.showConnect();
			refreshEnabled = true;

		}
	}


	/*
	 * ==== EVENTS: CONNECT GAME SCREEN ====
	 */
	private class HostGameButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			refreshEnabled = false;
			view.showCreateGame();
		}
	}

	/**
	 * ==== EVENTS: CREATE GAME SCREEN ====
	 */
	private class CancelButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			refreshEnabled = true;
			view.showConnect();
		}
	}
	
	private class CreateGameButtonMouseListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			createNewGame();
		}
	}

	/**
	 * ==========================================================
	 *   </BORING> - SO HERE ALL OF THE INTERESTING CODE STARTS
	 * ==========================================================
	 */
	
	/**
	 * This class catches any MoveEvent. As we still are in the menu, the first
	 * move event can only be triggered when the Game starts, and the server sends the
	 * players the initial game data, if I'm the joining player, or an empty move
	 * notification just to inform me that someone joined my game if I'm the one 
	 * who created the game and, therefore, also the initial game data.
	 * If I created the Game data myself so I will not receive any "data"
	 * parameter (data==null). If I am the Slave, instead, I will
	 * receive from the Server the initial Game State as "data".
	 */
	private class StartGame implements MoveEvent {
		@Override
		public void receive(GameData data) {
			
			// Let's hide this window.
			view.setVisible(false);
			
			// Stop wasting network I/O on refreshing the Game List.
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

	
	/**
	 * This Mouse Listener catches all of the clicks on any point
	 * of the table. I then figure out which row was clicked from the
	 * coordinates of the click, finally filter for double clicks.
	 * If a Double click is finally detected, I get the code of the
	 * game from the first row and call the joinGame method.
	 */
	private class TableMouseListener extends MouseAdapter {
	    public void mousePressed(MouseEvent me) {
	        JTable table = (JTable) me.getSource();
	        Point p = me.getPoint();
	        int row = table.rowAtPoint(p);
	        if (me.getClickCount() == 2) { // I want a double click
	           String gameID = view.getGameAtRow(row);
	           joinGame(gameID);
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
	
	/**
	 * Procedure to create a new game. First, ask the player
	 * for their name and the difficulty of the game they want 
	 * to create. Secondly, generate the Initial Game Data.
	 * Thirdly, contact the server and ask to create a Game.
	 * Once I get an answer from the server, I show the Player
	 * the Wait Screen - to wait for an opponent.
	 */
	private void createNewGame() {
		
		int difficulty = view.getSelectedDifficulty();
		Game data = generateGameData(name, difficulty);
		model.setData(data);
		model.setNickname(name);
		
		// Create the actual game
		model.getClient().createGame(name, difficulty, data, new GameInfoResponse() {
			@Override
			public void response(GameListItem item) {
				
				view.showWaitingScreen();
				System.out.println("Game #" + item.id + " created.");
				System.out.println("Ok, waiting for other player.");
				
			}
		});	

			
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
		
		model.setNickname(name);
		
		model.getClient().joinGame(gameID, name, new StatusResponse() {
			@Override
			public void response(StatusItem item) {
				
				/**
				 * Sometimes the server does not allow a player to join.
				 * At the time of writing this, it can only happen when
				 * a player wants to join a game created by someone with 
				 * the same name. The server would otherwise get confused 
				 * while serving cold beverages to the thirsty players. 
				 * If this happens, item.error will explain the reason for
				 * the refusal to the player.
				 */
				if ( !item.ok ) {
					view.showErrorMessage("Can't join: " + item.error);
					return;
				}
				
				/**
				 * If everything goes well, let's just take the user
				 * to the waiting screen. This should last very little time,
				 * I only need to wait for the initial game data to 
				 * reach me.
				 */
				view.showWaitingScreen();
				System.out.println("Game #" + gameID + " joined.");
				System.out.println("Ok, waiting for other player.");
				
			}
		});

			
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
		public void run(com.eep.taxe.models.Game data, Role role, String nickname);
	}

}
