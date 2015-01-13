package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BottomPanel extends JPanel{
	private final int WIDTH;
	private final int HEIGHT;
	
	public BottomPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.setBackground(Color.YELLOW);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setPreferredSize(new Dimension(WIDTH, WIDTH/8));
		this.setOpaque(false);
		
			JPanel inventoryContainer = new JPanel();
			inventoryContainer.setPreferredSize(new Dimension(WIDTH - WIDTH/6, WIDTH/8));
			inventoryContainer.setLayout(new BorderLayout(0, 0));
			inventoryContainer.setOpaque(false);
		
			JPanel inventoryPanel = new JPanel();
			inventoryPanel.setPreferredSize(new Dimension(WIDTH - WIDTH/6, WIDTH/16));
			inventoryPanel.setAlignmentX(LEFT_ALIGNMENT);
			inventoryPanel.setAlignmentY(BOTTOM_ALIGNMENT);
			inventoryPanel.setBackground(Color.CYAN);
	
		
			JPanel miniMapPanel = new JPanel();
			miniMapPanel.setPreferredSize(new Dimension(WIDTH/6, WIDTH/8));
			miniMapPanel.setBackground(Color.MAGENTA);
			
			inventoryContainer.add(inventoryPanel, BorderLayout.SOUTH);
			this.add(inventoryContainer);
			this.add(miniMapPanel);
	}
}
