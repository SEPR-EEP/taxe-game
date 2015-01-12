package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameView extends javax.swing.JFrame {

	private static final long serialVersionUID = -1784329706446539462L;
	
	private final int 		WIDTH  	= 1300;
	private final int 		HEIGHT 	= 720;
	private final String	TITLE	= "TAxE Game";

	public GameView() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel mainMapPanel = new JPanel();
		mainMapPanel.setPreferredSize(getContentPane().getSize());
		mainMapPanel.setBackground(Color.BLUE);
		mainMapPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel gameMenuPanel = new JPanel();
		gameMenuPanel.setPreferredSize(getContentPane().getSize());
		gameMenuPanel.setLayout(new BorderLayout(0, 0));
		gameMenuPanel.setOpaque(false);
		
			JPanel infoPanel = new JPanel();
			infoPanel.setPreferredSize(new Dimension(WIDTH/6, HEIGHT));
			infoPanel.setBackground(Color.RED);

			
			JPanel inventoryAndMapPanel = new JPanel();
			inventoryAndMapPanel.setBackground(Color.YELLOW);
			inventoryAndMapPanel.setLayout(new BoxLayout(inventoryAndMapPanel, BoxLayout.X_AXIS));
			inventoryAndMapPanel.setPreferredSize(new Dimension(WIDTH, WIDTH/8));
			inventoryAndMapPanel.setOpaque(false);
			
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
		
			
					
						
				TopPanel topPanel = new TopPanel(WIDTH, HEIGHT);
				gameMenuPanel.add(topPanel, BorderLayout.NORTH);
						
				LeftPanel leftPanel = new LeftPanel(WIDTH, HEIGHT);
				gameMenuPanel.add(leftPanel, BorderLayout.WEST);
			


			
			gameMenuPanel.add(infoPanel, BorderLayout.EAST);
				inventoryContainer.add(inventoryPanel, BorderLayout.SOUTH);
				inventoryAndMapPanel.add(inventoryContainer);
				inventoryAndMapPanel.add(miniMapPanel);
			gameMenuPanel.add(inventoryAndMapPanel, BorderLayout.SOUTH);
			
		mainMapPanel.add(gameMenuPanel);
		getContentPane().add(mainMapPanel);
		

		this.setVisible(true);

	}
	
	public void setTimer(String time){
		
	}
	

}
