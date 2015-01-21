package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.eep.taxe.models.Train;
import com.eep.taxe.models.Usable;
import com.eep.taxe.mvc.game.BottomPanel.InventorySlotsListener;
import com.eep.taxe.mvc.game.BottomPanel.TrainSlotsListener;

/** Custom JFrame class which contains all the containers in the game GUI
 *
 */
public class GameView extends javax.swing.JFrame {

	private static final long serialVersionUID = -1784329706446539462L;
	
	private final int 		WIDTH  	= 1300;
	private final int 		HEIGHT 	= 720;
	private final String	TITLE	= "TAxE Game";
	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private BottomPanel bottomPanel;
	private JLabel mapLabel;
	private JPanel gameMenuPanel;
	
	
	
	/**
	 * Creates a GameView Object containing all the compononets in the GUI
	 */
	public GameView() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setLocation(screenWidth/2-WIDTH/2, screenHeight/2-HEIGHT/2);
		
		
		gameMenuPanel = new JPanel();
		gameMenuPanel.setBackground(new Color(245, 245, 245));
		gameMenuPanel.setPreferredSize(getContentPane().getSize());
		gameMenuPanel.setLayout(new BorderLayout(0, 0));
		
			mapLabel = new JLabel();
			mapLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 21));
			mapLabel.setHorizontalAlignment(JLabel.CENTER);
			mapLabel.setText("Please, click to play.");
			mapLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

			gameMenuPanel.add(mapLabel);
			
			topPanel = new TopPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(topPanel, BorderLayout.NORTH);

			leftPanel = new LeftPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(leftPanel, BorderLayout.WEST);

			rightPanel = new RightPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(rightPanel, BorderLayout.EAST);

			bottomPanel = new BottomPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(bottomPanel, BorderLayout.SOUTH);

		getContentPane().add(gameMenuPanel);
		
		this.setVisible(true);
	}

	/**Shows dialog displaying the given error message.
	 * @param message
	 */
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    "Game error",
			    JOptionPane.ERROR_MESSAGE);
	}	
	
	/**Shows a dialog displaying the given message.
	 * @param message
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    "Game Info",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * @return mapLabel The JLabel on which the map and graphics is drawn.
	 */

	public JLabel getMapLabel() {
		return mapLabel;
	}

	/**Sets the JLabel on which the map and graphics are drawn to the
	 * given label.
	 * @param mapLabel
	 */
	public void setMapLabel(JLabel mapLabel) {
		this.mapLabel = mapLabel;
	}
	
	/** (non-Javadoc)
	 * @see java.awt.Component#repaint()
	 */
	public void repaint() {
		this.gameMenuPanel.repaint();
	}
	
	/**
	 * @return the JPanel which contains all of the menu containers
	 */
	public JPanel getGameMenuPanel() {
		return gameMenuPanel;
	}

	/**Sets the JPanel which contains all of the menu containers to the given JPanel
	 * @param gameMenuPanel
	 */
	public void setGameMenuPanel(JImagePanel gameMenuPanel) {
		this.gameMenuPanel = gameMenuPanel;
	}
	
	/**Enables or disables the EndTurnButton in the TopPanel.
	 * @param b - boolean value when true button is enabled when false it is disabled
	 */
	public void enableEndTurnButton(boolean b) {
		this.topPanel.enableEndTurnButton(b);
	}
	
	
	//----------------------METHODS FOR MANIPULATING TOP PANEL MENU----------------------------------------
		/**Sets the timer textfiel in the topPanel whith the given value.
		 * @param time
		 */
		public void setTimer(String time){
			topPanel.setTimer(time);
		}
		
		/**Sets the mission info in the mission info panel with the given value.
		 * @param info 
		 */
		public void setMissionInfo(String info){
			topPanel.setMissionInfo(info);
		}
		
		/**Sets the the value shown in the gold JLabel in the top Panel
		 * @param gold - integer value indicating amount of gold
		 */
		public void setGold(int gold) {
			topPanel.setGold(gold);
		}
		
		/**Sets the the value shown in the metal JLabel in the top Panel
		 * @param metal - integer value indicating amount of gold
		 */
		public void setMetal(int metal){
			topPanel.setMetal(metal);
		}
		
		/**Add action listener to end turn button
		 * @param buttonListener
		 */
		public void addEndTurnButtonActionListener(ActionListener buttonListener){
			topPanel.addEndTurnButtonActionListener(buttonListener);
		}
	//----------------------------------------------------------------------------------------------------
	
	
	//----------------------METHODS FOR MANIPULATING LEFT PANEL MENU---------------------------------------
		/**Set the age of the age label
		 * @param age - String
		 */
		public void setAge (String age){
			leftPanel.setAge(age);
		}
		
		/**Set the name of the first player (Master)
		 * @param name - String Name of player
		 */
		public void setPlayer1Name(String name){
			leftPanel.setPlayer1Name(name);
		}
		
		/**Set the name of the second player (Slave)
		 * @param name - String Name of player
		 */
		public void setPlayer2Name(String name){
			leftPanel.setPlayer2Name(name);
		}
		
		/**Set the score of the first player (Master)
		 * @param score - String score of player
		 */
		public void setPlayer1Score(String score){
			leftPanel.setPlayer1Score(score);
		}
		
		/**Set the score of the second player
		 * @param score - String score of player
		 */
		public void setPlayer2Score(String score){
			leftPanel.setPlayer2Score(score);
		}
	//----------------------------------------------------------------------------------------------------
	
	
	//----------------------Methods for manipulating RightPanel menu--------------------------------------
		/**Set the text in the right Panel info pane
		 * @param text - String Text information
		 */
		public void setInfoText(String text){
			rightPanel.setInfoText(text);
		}
		
		/**Add button listener to regions tab button
		 * @param buttonListener
		 */
		public void addRegionButtonActionListener(ActionListener buttonListener){
			rightPanel.addRegionButtonActionListener(buttonListener);
		}
		
		/**Add button listener to objective tab button
		 * @param buttonListener
		 */
		public void addObjectiveButtonActionListener(ActionListener buttonListener){
			rightPanel.addObjectiveButtonActionListener(buttonListener);
		}
	//----------------------------------------------------------------------------------------------------
	
	
	//----------------------Methods for manipulating BottomPanel menu-------------------------------------
		//InventorySlot Listeners
		/**Add action listener to inventory slot
		 * @param inventoryListener - InventorySlotsLister
		 */
		public void addInventorySlotsActionListener(final InventorySlotsListener inventoryListener){
			bottomPanel.addInventorySlotsActionListener(inventoryListener);
		}
		
		//TrainSlots Listeners
		/**Add action listener to train slot
		 * @param trainListener - TrainSlotsListener
		 */
		public void addTrainSlotsActionListener(final TrainSlotsListener trainListener){
			bottomPanel.addTrainSlotsActionListener(trainListener);
		}
		
		//Getters and Setters for Inventory Slot 1
		/**Get item from inventory slot 1
		 * @return Usable Item
		 */
		public Usable getItemInventorySlot1(){
			return bottomPanel.getItemInventorySlot1();
		}
		
		/**Set item in inventory slot 1
		 * @param item - Usable Item
		 */
		public void setItemInventorySlot1(Usable item){
			bottomPanel.setItemInventorySlot1(item);
		}
		
		/**Returns weather or not inventory slot 1 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedInventorySlot1(){
			return bottomPanel.getSelectedInventorySlot1();
		}
		
		/**Set inventory slot 1 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedInventorySlot1(boolean selected){
			bottomPanel.setSelectedInventorySlot1(selected);
		}
		
		//Getters and Setters for Inventory Slot 2
		/**Get item from inventory slot 2
		 * @return Usable Item
		 */
		public Usable getItemInventorySlot2(){
			return bottomPanel.getItemInventorySlot2();
		}
		
		/**Set item in inventory slot 2
		 * @param item - Usable Item
		 */
		public void setItemInventorySlot2(Usable item){
			bottomPanel.setItemInventorySlot2(item);
		}
		
		/**Returns weather or not inventory slot 2 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedInventorySlot2(){
			return bottomPanel.getSelectedInventorySlot2();
		}
		
		/**Set inventory slot 2 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedInventorySlot2(boolean selected){
			bottomPanel.setSelectedInventorySlot2(selected);
		}
		
		//Getters and Setters for Inventory Slot 3
		/**Get item from inventory slot 3
		 * @return Usable Item
		 */
		public Usable getItemInventorySlot3(){
			return bottomPanel.getItemInventorySlot3();
		}
		
		/**Set item in inventory slot 3
		 * @param item - Usable Item
		 */
		public void setItemInventorySlot3(Usable item){
			bottomPanel.setItemInventorySlot3(item);
		}
		
		/**Returns weather or not inventory slot 3 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedInventorySlot3(){
			return bottomPanel.getSelectedInventorySlot3();
		}
		
		/**Set inventory slot 3 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedInventorySlot3(boolean selected){
			bottomPanel.setSelectedInventorySlot3(selected);
		}
		
		//Getters and Setters for Inventory Slot 4 
		/**Get item from inventory slot 4
		 * @return Usable Item
		 */
		public Usable getItemInventorySlot4(){
			return bottomPanel.getItemInventorySlot4();
		}
		
		/**Set item in inventory slot 4
		 * @param item - Usable Item
		 */
		public void setItemInventorySlot4(Usable item){
			bottomPanel.setItemInventorySlot4(item);
		}
		
		/**Returns weather or not inventory slot 4 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedInventorySlot4(){
			return bottomPanel.getSelectedInventorySlot4();
		}
		
		/**Set inventory slot 4 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedInventorySlot4(boolean selected){
			bottomPanel.setSelectedInventorySlot4(selected);
		}
		
		//Getters and Setters for Inventory Slot 5
		/**Get item from inventory slot 5
		 * @return Usable Item
		 */
		public Usable getItemInventorySlot5(){
			return bottomPanel.getItemInventorySlot5();
		}
		
		/**Set item in inventory slot 5
		 * @param item - Usable Item
		 */
		public void setItemInventorySlot5(Usable item){
			bottomPanel.setItemInventorySlot5(item);
		}
		
		/**Returns weather or not inventory slot 5 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedInventorySlot5(){
			return bottomPanel.getSelectedInventorySlot5();
		}
		
		/**Set inventory slot 5 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedInventorySlot5(boolean selected){
			bottomPanel.setSelectedInventorySlot5(selected);
		}
		
		
		//Getters and Setters for Train Slot 1
		/**Get train from train slot 1
		 * @return Train train
		 */
		public Train getTrainTrainSlot1(){
			return bottomPanel.getTrainTrainSlot1();
		}
		
		/**Set train in train slot 1
		 * @param item - Train train
		 */
		public void setTrainTrainSlot1(Train train){
			bottomPanel.setTrainTrainSlot1(train);
		}
		
		/**Returns weather or not train slot 1 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedTrainSlot1(){
			return bottomPanel.getSelectedTrainSlot1();
		}
		
		/**Set train slot 1 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedTrainSlot1(boolean selected){
			bottomPanel.setSelectedTrainSlot1(selected);
		}
		
		//Getters and Setters for Train Slot 2
		/**Get train from train slot 2
		 * @return Train train
		 */
		public Train getTrainTrainSlot2(){
			return bottomPanel.getTrainTrainSlot2();
		}
		
		/**Set train in train slot 2
		 * @param item - Train train
		 */
		public void setTrainTrainSlot2(Train train){
			bottomPanel.setTrainTrainSlot2(train);
		}
		/**Returns weather or not train slot 2 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedTrainSlot2(){
			return bottomPanel.getSelectedTrainSlot2();
		}
		
		/**Set train slot 2 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedTrainSlot2(boolean selected){
			bottomPanel.setSelectedTrainSlot2(selected);
		}
		
		//Getters and Setters for Train Slot 3
		/**Get train from train slot 3
		 * @return Train train
		 */
		public Train getTrainTrainSlot3(){
			return bottomPanel.getTrainTrainSlot3();
		}
		/**Set train in train slot 3
		 * @param item - Train train
		 */
		public void setTrainTrainSlot3(Train train){
			bottomPanel.setTrainTrainSlot3(train);
		}
		/**Returns weather or not train slot 3 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedTrainSlot3(){
			return bottomPanel.getSelectedTrainSlot3();
		}
		/**Set train slot 3 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedTrainSlot3(boolean selected){
			bottomPanel.setSelectedTrainSlot3(selected);
		}
			
		//Getters and Setters for Train Slot 4
		/**Get train from train slot 4
		 * @return Train train
		 */
		public Train getTrainTrainSlot4(){
			return bottomPanel.getTrainTrainSlot4();
		}
		/**Set train in train slot 4
		 * @param item - Train train
		 */
		public void setTrainTrainSlot4(Train train){
			bottomPanel.setTrainTrainSlot4(train);
		}
		/**Returns weather or not train slot 4 is selected
		 * @return - true if slot is selected
		 * 			 false if not selected
		 */
		public boolean getSelectedTrainSlot4(){
			return bottomPanel.getSelectedTrainSlot4();
		}
		/**Set train slot 4 selected or not
		 * @param selected boolean - selected if true
		 *                           not selected if false
		 */
		public void setSelectedTrainSlot4(boolean selected){
			bottomPanel.setSelectedTrainSlot4(selected);
		}
		
		
		//Market button Action Listener
		/**Add action listener to market button
		 * @param buttonListener
		 */
		public void addMarketButtonActionListener(ActionListener buttonListener){
			bottomPanel.addMarketButtonActionListener(buttonListener);
		}
		
		//Menu button Action Listener
		/**Add action listener to market button
		 * @param buttonListener
		 */
		public void addMenuButtonActionListener(ActionListener buttonListener){
			bottomPanel.addMenuButtonActionListener(buttonListener);
		}
	//----------------------------------------------------------------------------------------------------
}
