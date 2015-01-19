package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.eep.taxe.models.Train;
import com.eep.taxe.models.Usable;

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
	
	// InventorySlot Listeners
	
	public void addInventorySlotsActionListener(final InventorySlotsListener listener) {
		inventorySlot1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, inventorySlot1);
			}
		});		
		inventorySlot2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, inventorySlot2);
			}
		});
		inventorySlot3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, inventorySlot3);
			}
		});		
		inventorySlot4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, inventorySlot4);
			}
		});		
		inventorySlot5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, inventorySlot5);
			}
		});		
	}
	
	public interface InventorySlotsListener  {
		public void actionPerformed(ActionEvent e, final InventorySlot slot);
	}
	
	
	// Train Slots Listeners

	public void addTrainSlotsActionListener(final TrainSlotsListener listener) {
		trainSlot1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, trainSlot1);
			}
		});		
		trainSlot2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, trainSlot2);
			}
		});
		trainSlot3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, trainSlot3);
			}
		});		
		trainSlot4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e, trainSlot4);
			}
		});		

	}
	
	public interface TrainSlotsListener  {
		public void actionPerformed(ActionEvent e, TrainSlot slot);
	}
	
	
	
	//Getters and Setters for Inventory Slot 1
	public Usable getItemInventorySlot1(){
		return inventorySlot1.getItem();
	}
	
	public void setItemInventorySlot1(Usable item){
		inventorySlot1.setItem(item);
	}
	
	public boolean getSelectedInventorySlot1(){
		return inventorySlot1.getSelected();
	}
	
	public void setSelectedInventorySlot1(boolean selected){
		inventorySlot1.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 2
	public Usable getItemInventorySlot2(){
		return inventorySlot2.getItem();
	}
	
	public void setItemInventorySlot2(Usable item){
		inventorySlot2.setItem(item);
	}
	
	public boolean getSelectedInventorySlot2(){
		return inventorySlot2.getSelected();
	}
	
	public void setSelectedInventorySlot2(boolean selected){
		inventorySlot2.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 3
	public Usable getItemInventorySlot3(){
		return inventorySlot3.getItem();
	}
	
	public void setItemInventorySlot3(Usable item){
		inventorySlot3.setItem(item);
	}
	
	public boolean getSelectedInventorySlot3(){
		return inventorySlot3.getSelected();
	}
	
	public void setSelectedInventorySlot3(boolean selected){
		inventorySlot3.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 4 
	public Usable getItemInventorySlot4(){
		return inventorySlot4.getItem();
	}
	
	public void setItemInventorySlot4(Usable item){
		inventorySlot4.setItem(item);
	}
	
	public boolean getSelectedInventorySlot4(){
		return inventorySlot4.getSelected();
	}
	
	public void setSelectedInventorySlot4(boolean selected){
		inventorySlot4.setSelected(selected);
	}
	
	//Getters and Setters for Inventory Slot 5
	public Usable getItemInventorySlot5(){
		return inventorySlot5.getItem();
	}
	
	public void setItemInventorySlot5(Usable item){
		inventorySlot5.setItem(item);
	}
	
	public boolean getSelectedInventorySlot5(){
		return inventorySlot5.getSelected();
	}
	
	public void setSelectedInventorySlot5(boolean selected){
		inventorySlot5.setSelected(selected);
	}
	
	
	//Getters and Setters for Train Slot 1
	public Train getTrainTrainSlot1(){
		return trainSlot1.getTrain();
	}
	
	public void setTrainTrainSlot1(Train train){
		trainSlot1.setTrain(train);
	}
	
	public boolean getSelectedTrainSlot1(){
		return trainSlot1.getSelected();
	}
	
	public void setSelectedTrainSlot1(boolean selected){
		trainSlot1.setSelected(selected);
	}
	
	//Getters and Setters for Train Slot 2
	public Train getTrainTrainSlot2(){
		return trainSlot2.getTrain();
	}
	
	public void setTrainTrainSlot2(Train train){
		trainSlot2.setTrain(train);
	}
	
	public boolean getSelectedTrainSlot2(){
		return trainSlot2.getSelected();
	}
	
	public void setSelectedTrainSlot2(boolean selected){
		trainSlot2.setSelected(selected);
	}
	
	//Getters and Setters for Train Slot 3
	public Train getTrainTrainSlot3(){
		return trainSlot3.getTrain();
	}
	
	public void setTrainTrainSlot3(Train train){
		trainSlot3.setTrain(train);
	}
	
	public boolean getSelectedTrainSlot3(){
		return trainSlot3.getSelected();
	}
	
	public void setSelectedTrainSlot3(boolean selected){
		trainSlot3.setSelected(selected);
	}
	
	//Getters and Setters for Train Slot 4
	public Train getTrainTrainSlot4(){
		return trainSlot1.getTrain();
	}
	
	public void setTrainTrainSlot4(Train train){
		trainSlot1.setTrain(train);
	}
	
	public boolean getSelectedTrainSlot4(){
		return trainSlot1.getSelected();
	}
	
	public void setSelectedTrainSlot4(boolean selected){
		trainSlot1.setSelected(selected);
	}
	
	
	//Market button Action Listener
	public void addMarketButtonActionListener(ActionListener buttonListener){
		marketButton.addActionListener(buttonListener);
	}
	
	
	//Menu button Action Listener
	public void addMenuButtonActionListener(ActionListener buttonListener){
		menuButton.addActionListener(buttonListener);
	}
	
	
	public BottomPanel(int width, int height){
		setBorder(null);
		//this.WIDTH = width;
		//this.HEIGHT = height;
		WIDTH =1300;
		HEIGHT = 720;
		
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		this.setPreferredSize(new Dimension(WIDTH, WIDTH/16));
		
				
			JPanel inventoryPanel = new JPanel();
			inventoryPanel.setBackground(new Color(211, 211, 211));
			inventoryPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			inventoryPanel.setPreferredSize(new Dimension(WIDTH/3 , WIDTH/16));
			inventoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, WIDTH/64, WIDTH/96));
			
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
			trainPanel.setPreferredSize(new Dimension(WIDTH/4 , WIDTH/16));
			trainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, WIDTH/64, WIDTH/96));
			trainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			trainPanel.setBackground(new Color(169, 169, 169));
			
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
			menuButton.setBackground(Color.BLACK);
			menuButton.setForeground(Color.BLACK);


		this.add(inventoryPanel);
		this.add(trainPanel);
		this.add(Box.createRigidArea(new Dimension(WIDTH/32, WIDTH/16)));
		this.add(marketButton);
		this.add(Box.createRigidArea(new Dimension(WIDTH/31, WIDTH/16)));
		this.add(menuButton);
		this.add(Box.createRigidArea(new Dimension(WIDTH/32, WIDTH/16)));
	}
}