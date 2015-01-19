package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.eep.taxe.models.Train;
import com.eep.taxe.models.Usable;
import com.eep.taxe.mvc.game.BottomPanel.InventorySlotsListener;
import com.eep.taxe.mvc.game.BottomPanel.TrainSlotsListener;

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
	JPanel mainMapPanel;
	

	
	
	public GameView() {

		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		mainMapPanel = new JPanel();
		mainMapPanel.setPreferredSize(getContentPane().getSize());
//		mainMapPanel.setBackground(Color.CYAN);
		mainMapPanel.setLayout(new BorderLayout(0, 0));

			mapLabel = new JLabel();
			mapLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
			mapLabel.setText("BACKGROUND FAILED TO LOAD");
			
		
		gameMenuPanel = new JPanel();
		gameMenuPanel.setBackground(new Color(245, 245, 245));
		gameMenuPanel.setPreferredSize(getContentPane().getSize());
		gameMenuPanel.setLayout(new BorderLayout(0, 0));
	
			topPanel = new TopPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(topPanel, BorderLayout.NORTH);

			leftPanel = new LeftPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(leftPanel, BorderLayout.WEST);

			rightPanel = new RightPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(rightPanel, BorderLayout.EAST);

			bottomPanel = new BottomPanel(WIDTH, HEIGHT);
			bottomPanel.setBackground(new Color(245, 245, 220));
			gameMenuPanel.add(bottomPanel, BorderLayout.SOUTH);


		mainMapPanel.add(mapLabel);
		gameMenuPanel.add(mainMapPanel);
		getContentPane().add(gameMenuPanel);
		
		this.setVisible(true);

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
	
	public JLabel getMapLabel() {
		return mapLabel;
	}

	public void setMapLabel(JLabel mapLabel) {
		this.mapLabel = mapLabel;
	}

	
	public JPanel getMainMapPanel() {
		return mainMapPanel;
	}

	public void setMainMapPanel(JPanel mainMapPanel) {
		this.mainMapPanel = mainMapPanel;
	}

	public JPanel getGameMenuPanel() {
		return gameMenuPanel;
	}

	public void setGameMenuPanel(JPanel gameMenuPanel) {
		this.gameMenuPanel = gameMenuPanel;
	}
	
	
	//----------------------Methods for manipulating TopPanel menu----------------------------------------
		public void setTimer(String time){
			topPanel.setTimer(time);
		}
		
		public void setMissionInfo(String info){
			topPanel.setMissionInfo(info);
		}
		
		public void setGold(int gold) {
			topPanel.setGold(gold);
		}
		
		public void setMetal(int metal){
			topPanel.setMetal(metal);
		}
		
		public void addEndTurnButtonActionListener(ActionListener buttonListener){
			topPanel.addEndTurnButtonActionListener(buttonListener);
		}
		
		public void addDetailsButtonActionListener(ActionListener buttonListener){
			topPanel.addDetailsButtonActionListener(buttonListener);
		}
	//----------------------------------------------------------------------------------------------------
	
	
	//----------------------Methods for manipulating LeftPanel menu---------------------------------------
		public void setAge (String age){
			leftPanel.setAge(age);
		}
		
		public void setPlayer1Name(String name){
			leftPanel.setPlayer1Name(name);
		}
		
		public void setPlayer2Name(String name){
			leftPanel.setPlayer2Name(name);
		}
		
		public void setPlayer1Score(String string){
			leftPanel.setPlayer1Score(string);
		}
		
		public void setPlayer2Score(String score){
			leftPanel.setPlayer2Score(score);
		}
	//----------------------------------------------------------------------------------------------------
	
	
	//----------------------Methods for manipulating RightPanel menu--------------------------------------
		public void setInfoText(String text){
			rightPanel.setInfoText(text);
		}
		
		public void addRegionButtonActionListener(ActionListener buttonListener){
			rightPanel.addRegionButtonActionListener(buttonListener);
		}
		
		public void addObjectiveButtonActionListener(ActionListener buttonListener){
			rightPanel.addObjectiveButtonActionListener(buttonListener);
		}
	//----------------------------------------------------------------------------------------------------
	
	
	//----------------------Methods for manipulating BottomPanel menu-------------------------------------
		//InventorySlot Listeners
		public void addInventorySlotsActionListener(final InventorySlotsListener inventoryListener){
			bottomPanel.addInventorySlotsActionListener(inventoryListener);
		}
		
		//TrainSlots Listeners
		public void addTrainSlotsActionListener(final TrainSlotsListener trainListener){
			bottomPanel.addTrainSlotsActionListener(trainListener);
		}
		
		//Getters and Setters for Inventory Slot 1
		public Usable getItemInventorySlot1(){
			return bottomPanel.getItemInventorySlot1();
		}
		
		public void setItemInventorySlot1(Usable item){
			bottomPanel.setItemInventorySlot1(item);
		}
		
		public boolean getSelectedInventorySlot1(){
			return bottomPanel.getSelectedInventorySlot1();
		}
		
		public void setSelectedInventorySlot1(boolean selected){
			bottomPanel.setSelectedInventorySlot1(selected);
		}
		
		//Getters and Setters for Inventory Slot 2
		public Usable getItemInventorySlot2(){
			return bottomPanel.getItemInventorySlot2();
		}
		
		public void setItemInventorySlot2(Usable item){
			bottomPanel.setItemInventorySlot2(item);
		}
		
		public boolean getSelectedInventorySlot2(){
			return bottomPanel.getSelectedInventorySlot2();
		}
		
		public void setSelectedInventorySlot2(boolean selected){
			bottomPanel.setSelectedInventorySlot2(selected);
		}
		
		//Getters and Setters for Inventory Slot 3
		public Usable getItemInventorySlot3(){
			return bottomPanel.getItemInventorySlot3();
		}
		
		public void setItemInventorySlot3(Usable item){
			bottomPanel.setItemInventorySlot3(item);
		}
		
		public boolean getSelectedInventorySlot3(){
			return bottomPanel.getSelectedInventorySlot3();
		}
		
		public void setSelectedInventorySlot3(boolean selected){
			bottomPanel.setSelectedInventorySlot3(selected);
		}
		
		//Getters and Setters for Inventory Slot 4 
		public Usable getItemInventorySlot4(){
			return bottomPanel.getItemInventorySlot4();
		}
		
		public void setItemInventorySlot4(Usable item){
			bottomPanel.setItemInventorySlot4(item);
		}
		
		public boolean getSelectedInventorySlot4(){
			return bottomPanel.getSelectedInventorySlot4();
		}
		
		public void setSelectedInventorySlot4(boolean selected){
			bottomPanel.setSelectedInventorySlot4(selected);
		}
		
		//Getters and Setters for Inventory Slot 5
		public Usable getItemInventorySlot5(){
			return bottomPanel.getItemInventorySlot5();
		}
		
		public void setItemInventorySlot5(Usable item){
			bottomPanel.setItemInventorySlot5(item);
		}
		
		public boolean getSelectedInventorySlot5(){
			return bottomPanel.getSelectedInventorySlot5();
		}
		
		public void setSelectedInventorySlot5(boolean selected){
			bottomPanel.setSelectedInventorySlot5(selected);
		}
		
		
		//Getters and Setters for Train Slot 1
		public Train getTrainTrainSlot1(){
			return bottomPanel.getTrainTrainSlot1();
		}
		
		public void setTrainTrainSlot1(Train train){
			bottomPanel.setTrainTrainSlot1(train);
		}
		
		public boolean getSelectedTrainSlot1(){
			return bottomPanel.getSelectedTrainSlot1();
		}
		
		public void setSelectedTrainSlot1(boolean selected){
			bottomPanel.setSelectedTrainSlot1(selected);
		}
		
		//Getters and Setters for Train Slot 2
		public Train getTrainTrainSlot2(){
			return bottomPanel.getTrainTrainSlot2();
		}
		
		public void setTrainTrainSlot2(Train train){
			bottomPanel.setTrainTrainSlot2(train);
		}
		
		public boolean getSelectedTrainSlot2(){
			return bottomPanel.getSelectedTrainSlot2();
		}
		
		public void setSelectedTrainSlot2(boolean selected){
			bottomPanel.setSelectedTrainSlot2(selected);
		}
		
		//Getters and Setters for Train Slot 3
		public Train getTrainTrainSlot3(){
			return bottomPanel.getTrainTrainSlot3();
		}
		
		public void setTrainTrainSlot3(Train train){
			bottomPanel.setTrainTrainSlot3(train);
		}
		
		public boolean getSelectedTrainSlot3(){
			return bottomPanel.getSelectedTrainSlot3();
		}
		
		public void setSelectedTrainSlot3(boolean selected){
			bottomPanel.setSelectedTrainSlot3(selected);
		}
		
		//Getters and Setters for Train Slot 4
		public Train getTrainTrainSlot4(){
			return bottomPanel.getTrainTrainSlot4();
		}
		
		public void setTrainTrainSlot4(Train train){
			bottomPanel.setTrainTrainSlot4(train);
		}
		
		public boolean getSelectedTrainSlot4(){
			return bottomPanel.getSelectedTrainSlot4();
		}
		
		public void setSelectedTrainSlot4(boolean selected){
			bottomPanel.setSelectedTrainSlot4(selected);
		}
		
		
		//Market button Action Listener
		public void addMarketButtonActionListener(ActionListener buttonListener){
			bottomPanel.addMarketButtonActionListener(buttonListener);
		}
		
		//Menu button Action Listener
		public void addMenuButtonActionListener(ActionListener buttonListener){
			bottomPanel.addMenuButtonActionListener(buttonListener);
		}
	//----------------------------------------------------------------------------------------------------
}
