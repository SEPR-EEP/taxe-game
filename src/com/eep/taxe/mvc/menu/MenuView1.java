package com.eep.taxe.mvc.menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuView1 extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";

	private JPanel menu;
	private JPanel lobby;
	private JPanel EnterGame;
	private JPanel contentPane;
	private JPanel MainMenu;
	private JPanel Credits;
	private JPanel HTP;
	private JPanel Connect;
	private JPanel createGame;
	private JPanel waiting;
	
	
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
	
	private JTextField usernamebox;
	
	private JComboBox FilterGame;
	private JComboBox Difficulty;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView1 frame = new MenuView1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuView1() {			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//SCREEN SIZE
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lobby	= this.createLobby();
		contentPane.setLayout(null);
		enterGame();
		createQuitButton();
		createEnterButton();
		askUserName();
	}
		
		
	public void enterGame() {
		EnterGame = new JPanel();
		EnterGame.setBounds(0, 0, 784, 562);
		contentPane.add(EnterGame);
		EnterGame.setLayout(null);
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
				
		askusername = new JLabel("Please enter a user name!");
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
		EnterGamebg.setBounds(10, 11, 784, 562);
		EnterGame.add(EnterGamebg);
		EnterGamebg.setIcon(new ImageIcon("/resources/menuview img/B1a-Railroad Tracks(1).jpg"));
	}
	
	public void mainMenu() {
		MainMenu = new JPanel();
		MainMenu.setLayout(null);
		MainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		MainMenu.setBounds(0, 0, 784, 562);
		contentPane.add(MainMenu);
	}
	
	public void createQuitLabel() {
		quitLabel = new JLabel("QUIT", SwingConstants.CENTER);
		quitLabel.setForeground(Color.WHITE);
		quitLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		quitLabel.setBounds(298, 404, 181, 36);
		MainMenu.add(quitLabel);

		quitLabelBG = new JLabel("");
		quitLabelBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
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
		startGameBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
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
		howToPlayBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
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
		creditsButtonBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
		creditsButtonBG.setLocation(new Point(298, 144));
		creditsButtonBG.setBackground(Color.BLACK);
		creditsButtonBG.setBounds(298, 305, 181, 36);
		MainMenu.add(creditsButtonBG);
	}
	
	public void setMainMenuBackground() {
		Mainmenubg = new JLabel("");
		Mainmenubg.setIcon(new ImageIcon("/resources/menuview img/B1a-Railroad Tracks(1).jpg"));
		Mainmenubg.setBounds(0, 0, 784, 562);
		MainMenu.add(Mainmenubg);
	}
		
		//credits
	public void creditsScreen() {
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
		Creditsbg.setIcon(new ImageIcon("/resources/menuview img/C_D(g)-Railroad Tracks.jpg"));
		Creditsbg.setBounds(0, 0, 784, 562);
		Credits.add(Creditsbg);
	}
		
		// how to play
	public void howToPlayScreen() {
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
		HTPbg.setIcon(new ImageIcon("/resources/menuview img/C_D(g)-Railroad Tracks.jpg"));
		HTPbg.setBounds(0, 0, 784, 562);
		HTP.add(HTPbg);
		}
		

		//connect
	public void connectScreen() {
		Connect = new JPanel();
		Connect.setLayout(null);
		Connect.setBounds(0, 0, 784, 562);
		contentPane.add(Connect);
		
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
		createHostGameButton();
		createBackButton();
		setConnectBackground();
	}
	
	public void createHostGameButton() {
		hostGameButtonText = new JLabel("Host Game", SwingConstants.CENTER);
		hostGameButtonText.setForeground(Color.WHITE);
		hostGameButtonText.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		hostGameButtonText.setBounds(593, 392, 140, 36);
		Connect.add(hostGameButtonText);
		
		hostGameButtonBG = new JLabel("");
		hostGameButtonBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
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
		backButtonBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
		backButtonBG.setBounds(593, 462, 140, 36);
		Connect.add(backButtonBG);
	}
	
	public void setConnectBackground() {
		Connectbg = new JLabel("");
		Connectbg.setIcon(new ImageIcon("/resources/menuview img/connect - Tunnel.jpg"));
		Connectbg.setBounds(0, 0, 784, 562);
		Connect.add(Connectbg);
	}
		
	public void createGame() {
		createGame = new JPanel();
		createGame.setBounds(0, 0, 784, 562);
		contentPane.add(createGame);
		createGame.setLayout(null);
	}
		
	public void createDifficultySelectBox() {
		Difficulty = new JComboBox();
		Difficulty.setToolTipText("");
		Difficulty.setModel(new DefaultComboBoxModel(new String[] {"  Choose difficulty", "  \u25CF  Easy", "  \u25CF  Medium", "  \u25CF  Hard"}));
		Difficulty.setSelectedIndex(0);
		Difficulty.setMaximumRowCount(4);
		Difficulty.setForeground(Color.BLACK);
		Difficulty.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		Difficulty.setBackground(Color.DARK_GRAY);
		Difficulty.setBounds(302, 218, 181, 35);
		createGame.add(Difficulty);
	}
		
	public void createCancelButton() {
		cancelButtonText = new JLabel("Cancel ", SwingConstants.CENTER);
		cancelButtonText.setForeground(Color.WHITE);
		cancelButtonText.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		cancelButtonText.setBounds(179, 293, 140, 36);
		createGame.add(cancelButtonText);
		
		cancelButtonBG = new JLabel("");
		cancelButtonBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
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
		createButtonBG.setIcon(new ImageIcon("/resources/menuview img/btbg.jpg"));
		createButtonBG.setLocation(new Point(593, 392));
		createButtonBG.setBounds(480, 285, 140, 36);
		createGame.add(createButtonBG);
	}
	
	public void createGameBackground() {
		CreatGamebg = new JLabel("");
		CreatGamebg.setIcon(new ImageIcon("/resources/menuview img/connect - Tunnel.jpg"));
		CreatGamebg.setBounds(0, 0, 784, 562);
		createGame.add(CreatGamebg);
	}

	public void loadingScreen() {
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
		Waitingingbg.setIcon(new ImageIcon("/resources/menuview img/connect - Tunnel.jpg"));
		Waitingingbg.setBounds(0, 0, 784, 562);
		waiting.add(Waitingingbg);
	}
	
	
	// MouseListeners
	
	public void addQuitButtonMouseListener(MouseAdapter mouseListener) {
		quitButtonbg.addMouseListener(mouseListener);
	}
	
	public void addEnterButtonMouseListener(MouseAdapter mouseListener) {
		enterButtonbg.addMouseListener(mouseListener);
	}
	
	public void addQuitLabelMouseListener(MouseAdapter mouseListener) {
		quitLabelBG.addMouseListener(mouseListener);
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
	}
	
	public void addCancelButtonMouseListener(MouseAdapter mouseListener) {
		cancelButtonBG.addMouseListener(mouseListener);
	}
	
	public void addCreateGameButtonMouseListener(MouseAdapter mouseListener) {
		createButtonBG.addMouseListener(mouseListener);
	}
}
