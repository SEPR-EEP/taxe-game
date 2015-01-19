package com.eep.taxe.mvc.menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final String 	DIFFICULTY_EASY		= "Easy";
	private final String 	DIFFICULTY_MEDIUM	= "Medium";
	private final String 	DIFFICULTY_HARD		= "Hard";
	private final String[]  DIFFICULTY_CHOICHES = {
			DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD
	};
	
	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";
	
	private JPanel contentPane;

	private JPanel EnterGame;
	private JPanel MainMenu;
	private JPanel Credits;
	private JPanel HTP;
	private JPanel Connect;
	private JPanel createGame;
	private JPanel waiting;
	
	private JPanel[] screensList = null;
	
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
	
	private JTable gameTable;
	
	private JTextField usernamebox;
	
	private JComboBox FilterGame;
	private JComboBox Difficulty;
	
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
		EnterGame.setBounds(0, 0, 784, 562);
		contentPane.add(EnterGame);
		EnterGame.setLayout(null);
		createQuitButton();
		createEnterButton();
		askUserName();
		setBackground();
	}
	
	public void createQuitButton() {
		lblQuit = new JLabel("QUIT", SwingConstants.CENTER);
		lblQuit.setBounds(419, 280, 140, 36);
		EnterGame.add(lblQuit);
		lblQuit.setForeground(Color.WHITE);
		lblQuit.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		
		quitButtonbg = new JLabel("");
		quitButtonbg.setBounds(419, 280, 140, 36);
		EnterGame.add(quitButtonbg);
		quitButtonbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
	}
	
	public void createEnterButton() {
		lblEnter = new JLabel("ENTER", SwingConstants.CENTER);
		lblEnter.setBounds(224, 280, 140, 36);
		EnterGame.add(lblEnter);
		lblEnter.setForeground(Color.WHITE);
		lblEnter.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		
		enterButtonbg = new JLabel("");
		enterButtonbg.setBounds(224, 280, 140, 36);
		EnterGame.add(enterButtonbg);
		enterButtonbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
	}
	
	public void askUserName() {
		usernamebox = new JTextField();
		usernamebox.setBounds(224, 202, 335, 36);
		EnterGame.add(usernamebox);
		usernamebox.setForeground(new Color(0, 0, 0));
		usernamebox.setColumns(10);
				
		askusername = new JLabel("What's your name?");
		askusername.setBounds(220, 104, 343, 35);
		EnterGame.add(askusername);
		askusername.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
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
		MainMenu.setBounds(0, 0, 784, 562);
		contentPane.add(MainMenu);
		createStartGameButton();
		createQuitLabel();
		createHowToPlayButton();
		createCreditsButton();
		setMainMenuBackground();
	}
	
	public void createQuitLabel() {
		quitLabel = new JLabel("QUIT", SwingConstants.CENTER);
		quitLabel.setForeground(Color.WHITE);
		quitLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
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
		startGameText.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
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
		howToPlayText.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
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
		creditsButtonText.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
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
		Mainmenubg.setBounds(0, 0, 784, 562);
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
		creditsTitle.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
		creditsTitle.setBounds(319, 74, 181, 36);
		Credits.add(creditsTitle);
		
		Creditsbg = new JLabel("");
		Creditsbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/C_D(g)-Railroad Tracks.jpg")));
		Creditsbg.setBounds(0, 0, 784, 562);
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
		howToPlayTitle.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
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
		scrollPane.setBounds(30, 50, 500, 480);

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
		hostGameButtonText.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		hostGameButtonText.setBounds(593, 392, 140, 36);
		Connect.add(hostGameButtonText);
		
		hostGameButtonBG = new JLabel("");
		hostGameButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		hostGameButtonBG.setBounds(593, 392, 140, 36);
		Connect.add(hostGameButtonBG);
	}
	
	public void createBackButton() {
		backButtonText = new JLabel("Back", SwingConstants.CENTER);
		backButtonText.setForeground(Color.WHITE);
		backButtonText.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		backButtonText.setBounds(593, 462, 140, 36);
		Connect.add(backButtonText);
		
		backButtonBG = new JLabel("");
		backButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		backButtonBG.setBounds(593, 462, 140, 36);
		Connect.add(backButtonBG);
	}
	
	public void setConnectBackground() {
		Connectbg = new JLabel("");
		Connectbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/connect - Tunnel.jpg")));
		Connectbg.setBounds(0, 0, 784, 562);
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
		Difficulty = new JComboBox();
		Difficulty.setToolTipText("");
		Difficulty.setModel(new DefaultComboBoxModel(DIFFICULTY_CHOICHES));
		Difficulty.setSelectedIndex(0);
		Difficulty.setMaximumRowCount(4);
		Difficulty.setForeground(Color.BLACK);
		Difficulty.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		Difficulty.setBackground(Color.DARK_GRAY);
		Difficulty.setBounds(302, 218, 181, 35);
		createGame.add(Difficulty);
		createCancelButton();
		makeCreateGameButton();
		createGameBackground();
	}
		
	public void createCancelButton() {
		cancelButtonText = new JLabel("Cancel ", SwingConstants.CENTER);
		cancelButtonText.setForeground(Color.WHITE);
		cancelButtonText.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		cancelButtonText.setBounds(179, 293, 140, 36);
		createGame.add(cancelButtonText);
		
		cancelButtonBG = new JLabel("");
		cancelButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		cancelButtonBG.setLocation(new Point(593, 392));
		cancelButtonBG.setBounds(176, 285, 140, 36);
		createGame.add(cancelButtonBG);
	}
	
	public void makeCreateGameButton() {
		createButtonText = new JLabel("Create", SwingConstants.CENTER);
		createButtonText.setForeground(Color.WHITE);
		createButtonText.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		createButtonText.setBounds(467, 293, 140, 36);
		createGame.add(createButtonText);
		
		createButtonBG = new JLabel("");
		createButtonBG.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/btbg.jpg")));
		createButtonBG.setLocation(new Point(593, 392));
		createButtonBG.setBounds(480, 285, 140, 36);
		createGame.add(createButtonBG);
	}
	
	public void createGameBackground() {
		CreatGamebg = new JLabel("");
		CreatGamebg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/connect - Tunnel.jpg")));
		CreatGamebg.setBounds(0, 0, 784, 562);
		createGame.add(CreatGamebg);
	}

	public void createLoadingScreen() {
		waiting = new JPanel();
		waiting.setBounds(0, 0, 784, 562);
		contentPane.add(waiting);
		waiting.setLayout(null);

		lblNewLabel = new JLabel("Please wait for another player to join!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 32));
		lblNewLabel.setBounds(119, 33, 502, 38);
		waiting.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);

		Waitingingbg = new JLabel("");
		Waitingingbg.setIcon(new ImageIcon(getClass().getResource("/resources/menuview img/connect - Tunnel.jpg")));
		Waitingingbg.setBounds(0, 0, 784, 562);
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



}
