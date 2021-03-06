package com.eep.taxe.mvc.menu;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


/**
 * This is the View for the Menu, a.k.a. the Menu GUI.
 * Methods exposed by this class include those to add listeners to the buttons
 * and those needed to update the table of available games.
 */
public class MenuView extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	/**
	 * These are the strings used to display the difficulty levels both
	 * in the games list and in the difficulty combobox. 
	 * These can be changed to anything and are only used for UI purposes.
	 */
	private final String 	DIFFICULTY_EASY		= "Easy";
	private final String 	DIFFICULTY_MEDIUM	= "Medium";
	private final String 	DIFFICULTY_HARD		= "Hard";
	private final String[]  DIFFICULTY_CHOICHES = {
			DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD
	};
	
	/**
	 * The main text font used in the menu (for the normal size text, not headers).
	 */
	private final Font textFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

	/**
	 * I don't think I need to explain what these variables are.
	 */
	private final int 		WIDTH  	= 780;
	private final int 		HEIGHT 	= 560;
	private final String	TITLE	= "Game Menu";
	
	/**
	 * This is the main container.
	 */
	private JPanel contentPane;

	/**
	 * These are all of the panels that, one at a time,
	 * are added to the main container. 
	 */
	private JPanel EnterGame;
	private JPanel MainMenu;
	private JPanel Credits;
	private JPanel HTP;
	private JPanel Connect;
	private JPanel createGame;
	private JPanel waiting;
	
	/**
	 * This is - in the constructor - initialised to contain
	 * a list of all of the panels that can be shown.
	 */
	private JPanel[] screensList = null;
	
	/**
	 * A bunch of private properties.
	 */
	private JLabel lblQuit;
	private JLabel quitButtonbg;
	private JLabel lblEnter;
	private JLabel enterButtonbg;
	private JLabel askusername;
	private JLabel EnterGamebg;
	private JLabel quitLabel;
	private JLabel quitLabelBG;
	private JLabel startGameText;
	private JLabel startGameBG;
	private JLabel howToPlayText;
	private JLabel howToPlayBG;
	private JLabel creditsButtonText;
	private JLabel creditsButtonBG;
	private JLabel Mainmenubg;
	private JLabel creditsTitle;
	private JLabel Creditsbg;
	private JLabel howToPlayTitle;
	private JLabel HTPbg;
	private JLabel hostGameButtonText;
	private JLabel hostGameButtonBG;
	private JLabel backButtonText;
	private JLabel backButtonBG;
	private JLabel Connectbg;
	private JLabel cancelButtonText;
	private JLabel cancelButtonBG;
	private JLabel createButtonText;
	private JLabel createButtonBG;
	private JLabel CreatGamebg;
	private JLabel lblNewLabel;
	private JLabel Waitingingbg;
	
	/**
	 * This is the table used for the games list.
	 */
	private JTable gameTable;
	
	private JTextField usernamebox;
	
//	private JComboBox FilterGame;
	private JComboBox<String> Difficulty;
	
	/**
	 * Create the frame.
	 */
	public MenuView() {			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//SCREEN SIZE
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setLocation(screenWidth/2-WIDTH/2, screenHeight/2-HEIGHT/2);
		  
		contentPane.setLayout(null);
		
		/** INITIALISES ALL OF THE SCREENS */
		createEnterGame();
		createMainMenu();
		createCreditsScreen();
		createHowToPlayScreen();
		createConnectScreen();
		createCreateGame();
		createLoadingScreen();
		
		screensList = new JPanel[] {
				EnterGame, MainMenu, Credits, HTP, Connect, createGame, waiting
		};
		
		showEnterGame();
		
		setVisible(true);
		
	}
	
	/**
	 * Hides all of the panels (but does not pack & revalidate)
	 */
	private void hideEverything() {
		for ( JPanel x: screensList ) {
			if ( x == null ) {
				continue;
			}
			this.remove(x);
		}
	}
	
	
	/**
	 * Hides everything and shows only the specified panel.
	 * @param panel	The panel to show
	 */
	private void showPanel(JPanel panel) {
		this.hideEverything();
		this.add(panel);
		this.pack();
		this.setSize(WIDTH, HEIGHT);
		this.validate();
	}
	
	public void showEnterGame() {
		this.showPanel(EnterGame);
	}
	
	public void showMainMenu() {
		this.showPanel(MainMenu);
	}
	
	public void showCredits() {
		this.showPanel(Credits);
	}
	
	public void showHTP() {
		this.showPanel(HTP);
	}
	
	public void showConnect() {
		this.showPanel(Connect);
	}
	
	public void showCreateGame() {
		this.showPanel(createGame);
	}
	
	public void showWaitingScreen() {
		this.showPanel(waiting);
	}

		
		
	public void createEnterGame() {
		EnterGame = new JPanel();
		contentPane.add(EnterGame);
		
		EnterGame.setSize(WIDTH, HEIGHT);
		EnterGame.setLayout(null);
		
		createQuitButton();
		createEnterButton();
		askUserName();
		setBackground();
	}
	
	public void createQuitButton() {
		lblQuit = new JLabel("Quit", SwingConstants.CENTER);
		lblQuit.setBounds(419, 280, 140, 36);
		lblQuit.setLocation(WIDTH/2-lblQuit.getBounds().width/2+125, 300);
		EnterGame.add(lblQuit);
		lblQuit.setForeground(Color.WHITE);
		lblQuit.setFont(textFont);
		
		quitButtonbg = new JLabel("");
		quitButtonbg.setBounds(419, 280, 140, 36);
		quitButtonbg.setLocation(WIDTH/2-quitButtonbg.getBounds().width/2+125, 300);
		EnterGame.add(quitButtonbg);
		quitButtonbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
	}
	
	public void createEnterButton() {
		lblEnter = new JLabel("Enter", SwingConstants.CENTER);
		lblEnter.setBounds(224, 280, 140, 36);
		EnterGame.add(lblEnter);
		lblEnter.setLocation(WIDTH/2-lblEnter.getBounds().width/2-125, 300);
		lblEnter.setForeground(Color.WHITE);
		lblEnter.setFont(textFont);
		
		enterButtonbg = new JLabel("");
		enterButtonbg.setBounds(224, 280, 140, 36);
		enterButtonbg.setLocation(WIDTH/2-enterButtonbg.getBounds().width/2-125, 300);
		EnterGame.add(enterButtonbg);
		enterButtonbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
	}
	
	public void askUserName() {
		usernamebox = new JTextField();
		usernamebox.setBounds(224, 202, 335, 35);
		usernamebox.setLocation(WIDTH/2-usernamebox.getWidth()/2, HEIGHT/2-usernamebox.getHeight()/2-60);
		usernamebox.setFont(textFont);
		EnterGame.add(usernamebox);
		usernamebox.setForeground(new Color(0, 0, 0));
		usernamebox.setBorder(new CompoundBorder((new LineBorder(new Color (0,0,0), 2)),(new EmptyBorder(5,5,5,5))));
		usernamebox.setColumns(10);
				
		askusername = new JLabel("What's your name?");
		askusername.setBounds(0, 0, 280, 35);
		askusername.setAlignmentX(CENTER_ALIGNMENT);
		askusername.setLocation(WIDTH/2-askusername.getBounds().width/2, 150);
		EnterGame.add(askusername);
		askusername.setFont(new Font("Sans Serif", Font.PLAIN, 24));
	}
		
	public String getUserName() {
		String username = usernamebox.getText();
		return username;
	}
	
	public void setBackground() {
		EnterGamebg = new JLabel("");
		EnterGamebg.setBounds(0, 0, WIDTH, 560);
		EnterGame.add(EnterGamebg);
		EnterGamebg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/Background.jpg")));
	}
	
	public void createMainMenu() {
		MainMenu = new JPanel();
		MainMenu.setLayout(null);
		MainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		MainMenu.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(MainMenu);
		
		createStartGameButton();
		createQuitLabel();
		createHowToPlayButton();
		createCreditsButton();
		setMainMenuBackground();
	}
	
	public void createQuitLabel() {
		quitLabel = new JLabel("Quit", SwingConstants.CENTER);
		quitLabel.setForeground(Color.WHITE);
		quitLabel.setFont(textFont);
		quitLabel.setBounds(298, 404, 181, 36);
		MainMenu.add(quitLabel);

		quitLabelBG = new JLabel("");
		quitLabelBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		quitLabelBG.setLocation(new Point(224, 280));
		quitLabelBG.setBounds(298, 404, 181, 36);
		MainMenu.add(quitLabelBG);
	}	
	
	public void createStartGameButton() {
		startGameText = new JLabel("Start Game", SwingConstants.CENTER);
		startGameText.setForeground(Color.WHITE);
		startGameText.setFont(textFont);
		startGameText.setBounds(298, 104, 181, 36);
		MainMenu.add(startGameText);
		
		startGameBG = new JLabel("");
		startGameBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		startGameBG.setLocation(new Point(298, 104));
		startGameBG.setBounds(298, 104, 181, 36);
		MainMenu.add(startGameBG);
	}
	
	public void createHowToPlayButton() {
		howToPlayText = new JLabel("How to play", SwingConstants.CENTER);
		howToPlayText.setForeground(Color.WHITE);
		howToPlayText.setFont(textFont);
		howToPlayText.setBounds(298, 202, 181, 36);
		MainMenu.add(howToPlayText);
		
		howToPlayBG = new JLabel("");
		howToPlayBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		howToPlayBG.setLocation(new Point(298, 144));
		howToPlayBG.setBackground(Color.BLACK);
		howToPlayBG.setBounds(298, 202, 181, 36);
		MainMenu.add(howToPlayBG);
	}
	
	public void createCreditsButton(){
		creditsButtonText = new JLabel("Credits", SwingConstants.CENTER);
		creditsButtonText.setForeground(Color.WHITE);
		creditsButtonText.setFont(textFont);
		creditsButtonText.setBounds(298, 305, 181, 36);
		MainMenu.add(creditsButtonText);
		
		creditsButtonBG = new JLabel("");
		creditsButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		creditsButtonBG.setLocation(new Point(298, 144));
		creditsButtonBG.setBackground(Color.BLACK);
		creditsButtonBG.setBounds(298, 305, 181, 36);
		MainMenu.add(creditsButtonBG);
	}
	
	public void setMainMenuBackground() {
		Mainmenubg = new JLabel("");
		Mainmenubg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/Background.jpg")));
		Mainmenubg.setBounds(0, 0, WIDTH, 560);
		MainMenu.add(Mainmenubg);
	}
		
		//credits
	public void createCreditsScreen() {
		Credits = new JPanel();
		Credits.setBounds(0, 0, 784, 562);
		contentPane.add(Credits);
		Credits.setLayout(null);
		Credits.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		creditsTitle = new JLabel("Credits", SwingConstants.CENTER);
		creditsTitle.setForeground(Color.BLACK);
		creditsTitle.setFont(new Font("Sans Serif", Font.BOLD, 30));
		creditsTitle.setBounds(319, 74, 181, 36);
		Credits.add(creditsTitle);
		
		String creditsString = "<html><center>"
				+ "<strong>TAxE game has been developed by the EEP team, for the Software Engineering "
				+ "Project module at the Department of Computer Science, University of York.</strong>"
				+ "<hr />"
				+ "<a href='https://github.com/sepr-eep/taxe-game'>GitHub Repository</a> &mdash; "
				+ "<a href='http://sepr-eep.github.io'>Team Website</a> &mdash; "
				+ "<a href='http://sepr-eep.github.io/usermanual'>User Manual</a>"
				+ "</center><hr >"
				+ "<h4>Authors</h4>"
				+ "<ul>"
				+ " <li><strong>Cosgrove, Richard</strong> (rc1035@york.ac.uk);</li>"
				+ " <li><strong>Dong, Yindi</strong> (yd695@york.ac.uk);</li>"
				+ " <li><strong>Fresta, Alfio Emanuele</strong> (aef517@york.ac.uk);</li>"
				+ " <li><strong>Grierson, Andy</strong> (ag1106@york.ac.uk);</li>"
				+ " <li><strong>Kokov, Stefan</strong> (sk1056@york.ac.uk);</li>"
				+ " <li><strong>Lippit, Peter</strong> (pjl513@york.ac.uk);</li>"
				+ "</ul>"
				+ "</html>";
		JLabel creditsText = new JLabel(creditsString, SwingConstants.CENTER);
		creditsText.setForeground(Color.BLACK);
		creditsText.setFont(textFont);
		creditsText.setBounds(150, 0, 500, 500);
		Credits.add(creditsTitle);
		Credits.add(creditsText);
		
		Creditsbg = new JLabel("");
		Creditsbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/C_D(g)-Railroad Tracks.jpg")));
		Creditsbg.setBounds(0, 0, WIDTH, 562);
		Credits.add(Creditsbg);
	}
		
		// how to play
	public void createHowToPlayScreen() {
		HTP = new JPanel();
		HTP.setLayout(null);
		HTP.setBorder(new EmptyBorder(5, 5, 5, 5));
		HTP.setBounds(0, 0, 784, 562);
		contentPane.add(HTP);
		
		howToPlayTitle = new JLabel("How To Play", SwingConstants.CENTER);
		howToPlayTitle.setForeground(Color.BLACK);
		howToPlayTitle.setFont(new Font("Sans Serif", Font.BOLD, 26));
		howToPlayTitle.setBounds(319, 74, 181, 36);
		HTP.add(howToPlayTitle);
		
		HTPbg = new JLabel("");
		HTPbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/C_D(g)-Railroad Tracks.jpg")));
		HTPbg.setBounds(0, 0, 784, 562);
		HTP.add(HTPbg);
		}
		

		//connect
	public void createConnectScreen() {
		Connect = new JPanel();
		Connect.setLayout(null);
		Connect.setBounds(0, 0, 784, 562);
		contentPane.add(Connect);
		
		DefaultTableModel model = new DefaultTableModel(){
			private static final long serialVersionUID = -6816770724270875081L;
			// This is to make the table non-editable
			@Override
			public boolean isCellEditable(int row, int column) {
		       return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Game Name");
		model.addColumn("Difficulty");
		model.addColumn("Creation date");
		
		gameTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(gameTable);
		gameTable.setFillsViewportHeight(true);
		scrollPane.setBounds(20, 50, 500, 450);

		/* (future idea)
		FilterGame = new JComboBox();
		FilterGame.setToolTipText("");
		FilterGame.setModel(new DefaultComboBoxModel(new String[] {"  Filter Games", "  \u25CF Sort by difficulty", "  \u25CF Sort by Creator"}));
		FilterGame.setSelectedIndex(0);
		FilterGame.setMaximumRowCount(4);
		FilterGame.setForeground(Color.BLACK);
		FilterGame.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
		FilterGame.setBackground(Color.DARK_GRAY);
		FilterGame.setBounds(593, 68, 140, 36);
		Connect.add(FilterGame);
		*/
		
		Connect.add(scrollPane);
		createHostGameButton();
		createBackButton();
		setConnectBackground();
	}
	
	public void createHostGameButton() {
		hostGameButtonText = new JLabel("Create Game", SwingConstants.CENTER);
		hostGameButtonText.setForeground(Color.WHITE);
		hostGameButtonText.setFont(textFont);
		hostGameButtonText.setBounds(580, 380, 140, 35);
		Connect.add(hostGameButtonText);
		
		hostGameButtonBG = new JLabel("");
		hostGameButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		hostGameButtonBG.setBounds(580, 380, 140, 35);
		Connect.add(hostGameButtonBG);
	}
	
	public void createBackButton() {
		backButtonText = new JLabel("Back", SwingConstants.CENTER);
		backButtonText.setForeground(Color.WHITE);
		backButtonText.setFont(textFont);
		backButtonText.setBounds(580, 450, 140, 35);
		Connect.add(backButtonText);
		
		backButtonBG = new JLabel("");
		backButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		backButtonBG.setBounds(580, 450, 140, 35);
		Connect.add(backButtonBG);
	}
	
	public void setConnectBackground() {
		Connectbg = new JLabel("");
		Connectbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/connect - Tunnel.jpg")));
		Connectbg.setBounds(0, 0, WIDTH, HEIGHT);
		Connect.add(Connectbg);
	}
		
	public void createCreateGame() {
		createGame = new JPanel();
		createGame.setBounds(0, 0, 784, 562);
		contentPane.add(createGame);
		createGame.setLayout(null);
		createDifficultySelectBox();
		createCancelButton();
		makeCreateGameButton();
		createGameBackground();
	}
		
	public void createDifficultySelectBox() {
		Difficulty = new JComboBox<String>();
		Difficulty.setToolTipText("");
		Difficulty.setModel(new DefaultComboBoxModel<String>(DIFFICULTY_CHOICHES));
		Difficulty.setSelectedIndex(0);
		Difficulty.setMaximumRowCount(4);
		Difficulty.setForeground(Color.WHITE);
		Difficulty.setFont(textFont);
		Difficulty.setBackground(Color.BLACK);
		Difficulty.setBounds(0, 0, 150, 40);
		Difficulty.setLocation(WIDTH/2-Difficulty.getWidth()/2, HEIGHT/2-Difficulty.getHeight()/2-50);
		createGame.add(Difficulty);
		
		createCancelButton();
		makeCreateGameButton();
		createGameBackground();
	}
		
	public void createCancelButton() {
		cancelButtonText = new JLabel("Cancel ", SwingConstants.CENTER);
		cancelButtonText.setForeground(Color.WHITE);
		cancelButtonText.setFont(textFont);
		cancelButtonText.setBounds(0, 0, 140, 36);
		cancelButtonText.setLocation(WIDTH/2-cancelButtonText.getBounds().width/2-125, 300);

		createGame.add(cancelButtonText);
		
		cancelButtonBG = new JLabel("");
		cancelButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		cancelButtonBG.setBounds(0, 0, 140, 36);
		cancelButtonBG.setLocation(WIDTH/2-cancelButtonBG.getBounds().width/2-125, 300);
		
		createGame.add(cancelButtonBG);
	}
	
	public void makeCreateGameButton() {
		createButtonText = new JLabel("Create", SwingConstants.CENTER);
		createButtonText.setForeground(Color.WHITE);
		createButtonText.setFont(textFont);
		createButtonText.setBounds(0, 0, 140, 36);
		createButtonText.setLocation(WIDTH/2-createButtonText.getBounds().width/2+125, 300);

		createGame.add(createButtonText);
		
		createButtonBG = new JLabel("");
		createButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		createButtonBG.setBounds(0, 0, 140, 36);
		createButtonBG.setLocation(WIDTH/2-createButtonBG.getBounds().width/2+125, 300);

		createGame.add(createButtonBG);
	}
	
	public void createGameBackground() {
		CreatGamebg = new JLabel("");
		CreatGamebg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/connect - Tunnel.jpg")));
		CreatGamebg.setBounds(0, 0, WIDTH, HEIGHT);
		createGame.add(CreatGamebg);
	}

	
	public void createLoadingScreen() {
		waiting = new JPanel();
		waiting.setBounds(0, 0, 784, 562);
		contentPane.add(waiting);
		waiting.setLayout(null);

		lblNewLabel = new JLabel("Please wait for your opponent to join the game");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
		lblNewLabel.setBounds(119, 133, 502, 38);
		lblNewLabel.setLocation(WIDTH/2-lblNewLabel.getBounds().width/2, HEIGHT/2-lblQuit.getBounds().height/2-125);
		lblNewLabel.setForeground(Color.WHITE);
		
		waiting.add(lblNewLabel);
		
		JLabel lblNewLabel2 = new JLabel("Hopefully it won't take long. Relax.");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setFont(textFont);
		lblNewLabel2.setForeground(Color.WHITE);
		lblNewLabel2.setBounds(119, 160, 502, 38);
		lblNewLabel2.setLocation(WIDTH/2-lblNewLabel2.getBounds().width/2, HEIGHT/2-lblQuit.getBounds().height/2-60);
		
		waiting.add(lblNewLabel2);
		
		
		Waitingingbg = new JLabel("");
		Waitingingbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/connect - Tunnel.jpg")));
		Waitingingbg.setBounds(0, 0, WIDTH, HEIGHT);
		waiting.add(Waitingingbg);
	}
	
	
	// MouseListeners
	
	public void addQuitButtonMouseListener(MouseAdapter mouseListener) {
		lblQuit.addMouseListener(mouseListener);
		quitButtonbg.addMouseListener(mouseListener);
		quitLabelBG.addMouseListener(mouseListener);
	}
	
	public void addEnterButtonMouseListener(MouseAdapter mouseListener) {
		enterButtonbg.addMouseListener(mouseListener);
	}
	
	public void addStartGameButtonMouseListener(MouseAdapter mouseListener) {
		startGameBG.addMouseListener(mouseListener);
	}
	
	public void addHowToPlayButtonMouseListener(MouseAdapter mouseListener) {
		howToPlayBG.addMouseListener(mouseListener);
	}
	
	public void addCreditsButtonMouseListener(MouseAdapter mouseListener) {
		creditsButtonBG.addMouseListener(mouseListener);
	}
	
	public void addHostGameButtonMouseListener(MouseAdapter mouseListener) {
		hostGameButtonBG.addMouseListener(mouseListener);
	}
	
	public void addBackButtonMouseListener(MouseAdapter mouseListener) {
		backButtonBG.addMouseListener(mouseListener);
		Creditsbg.addMouseListener(mouseListener);
		HTPbg.addMouseListener(mouseListener);
	}
		
	public void addCancelButtonMouseListener(MouseAdapter mouseListener) {
		cancelButtonBG.addMouseListener(mouseListener);
	}
	
	public void addCreateGameButtonMouseListener(MouseAdapter mouseListener) {
		createButtonBG.addMouseListener(mouseListener);
	}
	
	public void addTableMouseListener(MouseAdapter mouseListener) {
		gameTable.addMouseListener(mouseListener);
	}
	
	public int getSelectedDifficulty() {
		String s = (String) Difficulty.getSelectedItem();
		switch (s) {
			case DIFFICULTY_EASY:
				return 1;
			case DIFFICULTY_MEDIUM:
				return 2;
			case DIFFICULTY_HARD:
				return 3;
			default:
				return 1;
		}
	}
	
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    "Game error",
			    JOptionPane.ERROR_MESSAGE);
	}	
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    "Game Info",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Empties the Game List
	 */
	public void emptyGameList() {
		DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
		int n = model.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	/*
	 * Adds a Game to the Game list
	 */
	public void addGameToList(String id, String name, int difficulty, Date created) {

		SimpleDateFormat date = new SimpleDateFormat("dd/M/yyyy hh:mm");
		DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
		model.addRow(new Object[]{
			id, 
			name,
			getDifficultyString(difficulty),
			date.format(created)
		});
		
	}
	
	private String getDifficultyString(int difficulty) {
		switch (difficulty) {
		case 1:
			return DIFFICULTY_EASY;
		case 2:
			return DIFFICULTY_MEDIUM;
		case 3:
			return DIFFICULTY_HARD;
		default:
			return DIFFICULTY_EASY;
		}
	}
	
	public String getGameAtRow(int row) {
		return (String) gameTable.getValueAt(row, 0);
	}

	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
