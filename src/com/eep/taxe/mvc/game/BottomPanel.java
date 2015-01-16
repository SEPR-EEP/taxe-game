package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
			
			
//		this.add(inventoryPanel);
			
	}
	
	
}


