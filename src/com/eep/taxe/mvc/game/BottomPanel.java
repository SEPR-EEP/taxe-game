package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.eep.taxe.models.Train;
import com.eep.taxe.models.Usable;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/**
 * Custom JPanel which contains the inventory with all it's slots
 * the train slots for the trains a player owns, the market button
 * and the menu button. 
 *
 */
public class BottomPanel extends JPanel{
	private final int WIDTH;
	private final int HEIGHT;

	private InventorySlot inventorySlot1;
	private InventorySlot inventorySlot2;
	private InventorySlot inventorySlot3;
	private InventorySlot inventorySlot4;
	private InventorySlot inventorySlot5;
	
	private TrainSlot trainSlot1;
	private TrainSlot trainSlot2;
	private TrainSlot trainSlot3;
	private TrainSlot trainSlot4;
	
	private JButton marketButton;
	private JButton menuButton;
	
	
	/**Constructs the custom JPanle - BottomPanel object providing the WIDTH and HEIGHT
	 * of the container in which BottomPanel is added
	 * @param width - the width of the container in which this object will be added
	 * @param height - the height of the container in which this object will be added
	 */
	public BottomPanel(int width, int height){
		setBorder(null);
		this.WIDTH = width;
		this.HEIGHT = height;
//		WIDTH =1300;
//		HEIGHT = 720;
		
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setPreferredSize(new Dimension(WIDTH, WIDTH/16));
		
				
			JPanel inventoryPanel = new JPanel();
			inventoryPanel.setBackground(new Color(211, 211, 211, 150));
			inventoryPanel.setBorder(new TitledBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)), "Inventory", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.SANS_SERIF,Font.BOLD,12), Color.BLACK));
			inventoryPanel.setPreferredSize(new Dimension(WIDTH/3+80 , WIDTH/16));
			inventoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 0));
			
				inventorySlot1 = new InventorySlot(WIDTH, HEIGHT);
				inventorySlot2 = new InventorySlot(WIDTH, HEIGHT);
				inventorySlot3 = new InventorySlot(WIDTH, HEIGHT);
				inventorySlot4 = new InventorySlot(WIDTH, HEIGHT);
				inventorySlot5 = new InventorySlot(WIDTH, HEIGHT);
					
				inventoryPanel.add(inventorySlot1);
				inventoryPanel.add(inventorySlot2);
				inventoryPanel.add(inventorySlot3);
				inventoryPanel.add(inventorySlot4);
				inventoryPanel.add(inventorySlot5);
				
			JPanel trainPanel = new JPanel();
			trainPanel.setPreferredSize(new Dimension(WIDTH/3 , WIDTH/16));
			trainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 0));
			trainPanel.setBorder(new TitledBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)), "Trains", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.SANS_SERIF,Font.BOLD,12), new Color(0, 0, 0)));
			trainPanel.setBackground(new Color(169, 169, 169, 150));
			
				trainSlot1 = new TrainSlot(WIDTH, HEIGHT);
				trainSlot2 = new TrainSlot(WIDTH, HEIGHT);
				trainSlot3 = new TrainSlot(WIDTH, HEIGHT);
				trainSlot4 = new TrainSlot(WIDTH, HEIGHT);
				
				trainPanel.add(trainSlot1);
				trainPanel.add(trainSlot2);
				trainPanel.add(trainSlot3);
				trainPanel.add(trainSlot4);
				
			marketButton = new JButton();
			marketButton.setIcon(new ImageIcon("DefaultMarketIcon"));
			marketButton.setPreferredSize(new Dimension(WIDTH/20, WIDTH/20));
			marketButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
			marketButton.setContentAreaFilled(false);
			
			menuButton = new JButton();
			menuButton.setText("Menu");
			menuButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
			menuButton.setPreferredSize(new Dimension(WIDTH/10, WIDTH/45));
			menuButton.setBorderPainted(false);
			menuButton.setForeground(Color.BLACK);


		this.add(inventoryPanel);
		this.add(trainPanel);
		this.add(Box.createRigidArea(new Dimension(WIDTH/32, WIDTH/16)));
		this.add(marketButton);
		this.add(Box.createRigidArea(new Dimension(WIDTH/20, WIDTH/16)));
		this.add(menuButton);
		this.add(Box.createRigidArea(new Dimension(WIDTH/32, WIDTH/16)));
	}
	
	// InventorySlot Listeners
	/**Add action listener to inventory slot
	 * @param inventoryListener - InventorySlotsLister
	 */
	public void addInventorySlotsActionListener(final InventorySlotsListener listener) {
		inventorySlot1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 1);
			}
		});		
		inventorySlot2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 2);
			}
		});
		inventorySlot3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 3);
			}
		});		
		inventorySlot4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 4);
			}
		});		
		inventorySlot5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 5);
			}
		});		
	}
	
	/**
	 * Interface for InventorySlotActionListener
	 *
	 */
	public interface InventorySlotsListener  {
		public void actionPerformed(ActionEvent e, int slot);
	}
	
	
	// Train Slots Listeners
	/**Add action listener to train slot
	 * @param inventoryListener - TrainSlotsLister
	 */
	public void addTrainSlotsActionListener(final TrainSlotsListener listener) {
		trainSlot1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 1);
			}
		});		
		trainSlot2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 2);
			}
		});
		trainSlot3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 3);
			}
		});		
		trainSlot4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, 4);
			}
		});		

	}
	
	/**
	 * Interface for TrainSlotActionListener
	 *
	 */
	public interface TrainSlotsListener  {
		public void actionPerformed(ActionEvent e, int slot);
	}
	
	
	
	//Getters and Setters for Inventory Slot 1
	/**Get item from inventory slot 1
	 * @return Usable Item
	 */
	public Usable getItemInventorySlot1(){
		return inventorySlot1.getItem();
	}
	
	/**Set item in inventory slot 1
	 * @param item - Usable Item
	 */
	public void setItemInventorySlot1(Usable item){
		inventorySlot1.setItem(item);
	}
	
	/**Returns weather or not inventory slot 1 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedInventorySlot1(){
		return inventorySlot1.getSelected();
	}
	
	/**Set inventory slot 1 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedInventorySlot1(boolean selected){
		inventorySlot1.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 2
	/**Get item from inventory slot 2
	 * @return Usable Item
	 */
	public Usable getItemInventorySlot2(){
		return inventorySlot2.getItem();
	}
	
	/**Set item in inventory slot 2
	 * @param item - Usable Item
	 */
	public void setItemInventorySlot2(Usable item){
		inventorySlot2.setItem(item);
	}
	
	/**Returns weather or not inventory slot 2 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedInventorySlot2(){
		return inventorySlot2.getSelected();
	}
	
	/**Set inventory slot 2 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedInventorySlot2(boolean selected){
		inventorySlot2.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 3
	/**Get item from inventory slot 3
	 * @return Usable Item
	 */
	public Usable getItemInventorySlot3(){
		return inventorySlot3.getItem();
	}
	
	/**Set item in inventory slot 3
	 * @param item - Usable Item
	 */
	public void setItemInventorySlot3(Usable item){
		inventorySlot3.setItem(item);
	}
	
	/**Returns weather or not inventory slot 3 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedInventorySlot3(){
		return inventorySlot3.getSelected();
	}
	
	/**Set inventory slot 3 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedInventorySlot3(boolean selected){
		inventorySlot3.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 4 
	/**Get item from inventory slot 4
	 * @return Usable Item
	 */
	public Usable getItemInventorySlot4(){
		return inventorySlot4.getItem();
	}
	
	/**Set item in inventory slot 4
	 * @param item - Usable Item
	 */
	public void setItemInventorySlot4(Usable item){
		inventorySlot4.setItem(item);
	}
	
	/**Returns weather or not inventory slot 4 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedInventorySlot4(){
		return inventorySlot4.getSelected();
	}
	
	/**Set inventory slot 4 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedInventorySlot4(boolean selected){
		inventorySlot4.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 5
	/**Get item from inventory slot 5
	 * @return Usable Item
	 */
	public Usable getItemInventorySlot5(){
		return inventorySlot5.getItem();
	}
	
	/**Set item in inventory slot 5
	 * @param item - Usable Item
	 */
	public void setItemInventorySlot5(Usable item){
		inventorySlot5.setItem(item);
	}
	
	/**Returns weather or not inventory slot 5 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedInventorySlot5(){
		return inventorySlot5.getSelected();
	}
	
	/**Set inventory slot 5 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedInventorySlot5(boolean selected){
		inventorySlot5.setSelected(selected);
	}
	
	
	//Getters and Setters for Train Slot 1
	/**Get train from train slot 1
	 * @return Train train
	 */
	public Train getTrainTrainSlot1(){
		return trainSlot1.getTrain();
	}
	
	/**Set train in train slot 1
	 * @param item - Train train
	 */
	public void setTrainTrainSlot1(Train train){
		trainSlot1.setTrain(train);
	}
	
	/**Returns weather or not train slot 1 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedTrainSlot1(){
		return trainSlot1.getSelected();
	}
	
	/**Set train slot 1 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedTrainSlot1(boolean selected){
		trainSlot1.setSelected(selected);
	}
	
	//Getters and Setters for Train Slot 2
	/**Get train from train slot 2
	 * @return Train train
	 */
	public Train getTrainTrainSlot2(){
		return trainSlot2.getTrain();
	}
	
	/**Set train in train slot 2
	 * @param item - Train train
	 */
	public void setTrainTrainSlot2(Train train){
		trainSlot2.setTrain(train);
	}
	
	/**Returns weather or not train slot 2 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedTrainSlot2(){
		return trainSlot2.getSelected();
	}
	
	/**Set train slot 2 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedTrainSlot2(boolean selected){
		trainSlot2.setSelected(selected);
	}
	
	//Getters and Setters for Train Slot 3
	/**Get train from train slot 3
	 * @return Train train
	 */
	public Train getTrainTrainSlot3(){
		return trainSlot3.getTrain();
	}
	
	/**Set train in train slot 3
	 * @param item - Train train
	 */
	public void setTrainTrainSlot3(Train train){
		trainSlot3.setTrain(train);
	}
	
	/**Returns weather or not train slot 3 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedTrainSlot3(){
		return trainSlot3.getSelected();
	}
	
	/**Set train slot 3 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedTrainSlot3(boolean selected){
		trainSlot3.setSelected(selected);
	}
	
	//Getters and Setters for Train Slot 4
	/**Get train from train slot 4
	 * @return Train train
	 */
	public Train getTrainTrainSlot4(){
		return trainSlot1.getTrain();
	}
	
	/**Set train in train slot 4
	 * @param item - Train train
	 */
	public void setTrainTrainSlot4(Train train){
		trainSlot1.setTrain(train);
	}
	
	/**Returns weather or not train slot 4 is selected
	 * @return - true if slot is selected
	 * 			 false if not selected
	 */
	public boolean getSelectedTrainSlot4(){
		return trainSlot1.getSelected();
	}
	
	/**Set train slot 4 selected or not
	 * @param selected boolean - selected if true
	 *                           not selected if false
	 */
	public void setSelectedTrainSlot4(boolean selected){
		trainSlot1.setSelected(selected);
	}
	
	
	//Market button Action Listener
	/**Add action listener to market button
	 * @param buttonListener
	 */
	public void addMarketButtonActionListener(ActionListener buttonListener){
		marketButton.addActionListener(buttonListener);
	}
	
	
	//Menu button Action Listener
	/**Add action listener to market button
	 * @param buttonListener
	 */
	public void addMenuButtonActionListener(ActionListener buttonListener){
		menuButton.addActionListener(buttonListener);
	}
	
}
