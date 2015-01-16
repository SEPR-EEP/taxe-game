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

	JPanel mainMapPanel;
	public JPanel getMainMapPanel() {
		return mainMapPanel;
	}

	public void setMainMapPanel(JPanel mainMapPanel) {
		this.mainMapPanel = mainMapPanel;
	}

	JPanel gameMenuPanel;
	
	public JPanel getGameMenuPanel() {
		return gameMenuPanel;
	}

	public void setGameMenuPanel(JPanel gameMenuPanel) {
		this.gameMenuPanel = gameMenuPanel;
	}

	TopPanel topPanel;
	LeftPanel leftPanel;
	RightPanel rightPanel;
	BottomPanel bottomPanel;
	
	public GameView() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		mainMapPanel = new JPanel();
		mainMapPanel.setPreferredSize(getContentPane().getSize());
//		mainMapPanel.setBackground(Color.CYAN);
		mainMapPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel mapLabel = new JLabel();
			mapLabel.setIcon(new ImageIcon(getClass().getResource("src/resources/MainMap.jpg")));
			mapLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			mapLabel.setText("BACKGROUND FAILED TO LOAD");
			
		
		gameMenuPanel = new JPanel();
		gameMenuPanel.setPreferredSize(getContentPane().getSize());
		gameMenuPanel.setLayout(new BorderLayout(0, 0));
		gameMenuPanel.setOpaque(false);
						
			topPanel = new TopPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(topPanel, BorderLayout.NORTH);
						
			leftPanel = new LeftPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(leftPanel, BorderLayout.WEST);
			
			rightPanel = new RightPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(rightPanel, BorderLayout.EAST);
			
			bottomPanel = new BottomPanel(WIDTH, HEIGHT);
			gameMenuPanel.add(bottomPanel, BorderLayout.SOUTH);
			
//		mainMapPanel.add(mapLabel, BorderLayout.CENTER);
//		gameMenuPanel.add(mapLabel);
//		mainMapPanel.add(gameMenuPanel);
//		getContentPane().add(mainMapPanel);
//		getContentPane().add(gameMenuPanel);
		
		getContentPane().add(mapLabel);
		

		this.setVisible(true);

	}
	
	public void setTimer(String time){
		
	}
	

}
