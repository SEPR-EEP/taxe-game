package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel{
//	private final int WIDTH;
//	private final int HEIGHT;
	
	private final int 		WIDTH  	= 1300;
	private final int 		HEIGHT 	= 720;
	private InventorySlot inventorySlot1;
	private InventorySlot inventorySlot2;
	private InventorySlot inventorySlot3;
	private InventorySlot inventorySlot4;
	private InventorySlot inventorySlot5;
	private TrainSlot trainSlot1;
	private TrainSlot trainSlot2;
	private TrainSlot trainSlot3;
	private TrainSlot trainSlot4;
	
	//InventorySlot Listeners
	public void inventorySlot1ActionListener(ActionListener buttonListener){
		inventorySlot1.addActionListener(buttonListener);
	}
	
	public void inventorySlot2ActionListener(ActionListener buttonListener){
		inventorySlot2.addActionListener(buttonListener);
	}
	
	public void inventorySlot3ActionListener(ActionListener buttonListener){
		inventorySlot3.addActionListener(buttonListener);
	}
	
	public void inventorySlot4ActionListener(ActionListener buttonListener){
		inventorySlot4.addActionListener(buttonListener);
	}
	
	public void inventorySlot5ActionListener(ActionListener buttonListener){
		inventorySlot5.addActionListener(buttonListener);
	}
	
	//TrainSlots Listeners
	public void trainSlot1ActionListener(ActionListener buttonListener){
		trainSlot1.addActionListener(buttonListener);
	}
	
	public void trainSlot2ActionListener(ActionListener buttonListener){
		trainSlot2.addActionListener(buttonListener);
	}
	
	public void trainSlot3ActionListener(ActionListener buttonListener){
		trainSlot3.addActionListener(buttonListener);
	}
	
	public void trainSlot4ActionListener(ActionListener buttonListener){
		trainSlot4.addActionListener(buttonListener);
	}
	
	
	public BottomPanel(int width, int height){
//		this.WIDTH = width;
//		this.HEIGHT = height;
		this.setBackground(Color.YELLOW);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setPreferredSize(new Dimension(WIDTH, WIDTH/16));
		this.setOpaque(false);
				
			JPanel inventoryPanel = new JPanel();
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
			trainPanel.setBackground(Color.GRAY);
			
				trainSlot1 = new TrainSlot(WIDTH, HEIGHT);
				trainSlot2 = new TrainSlot(WIDTH, HEIGHT);
				trainSlot3 = new TrainSlot(WIDTH, HEIGHT);
				trainSlot4 = new TrainSlot(WIDTH, HEIGHT);
				
				trainPanel.add(trainSlot1);
				trainPanel.add(trainSlot2);
				trainPanel.add(trainSlot3);
				trainPanel.add(trainSlot4);

		this.add(inventoryPanel);
		this.add(trainPanel);
			
	}
	
	
}


