package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
		
			JPanel topPanel = new JPanel();
			topPanel.setPreferredSize(new Dimension(WIDTH, WIDTH/32));
			topPanel.setBackground(Color.PINK);
			topPanel.setLayout(new GridLayout(0, 3));
//			topPanel.setOpaque(false);
				
				Font topPanelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
				
				JPanel timerMenuPanel = new JPanel();
//				timerMenuPanel.setPreferredSize(new Dimension(WIDTH/8, WIDTH/32));
				timerMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				timerMenuPanel.setOpaque(false);
				
				
					JTextField timerTextField = new JTextField();
					timerTextField.setText("00:00");
					timerTextField.setPreferredSize(new Dimension(WIDTH*1/20, WIDTH*1/48));
					timerTextField.setFont(topPanelFont);
					timerTextField.setEditable(false);
					timerTextField.setHorizontalAlignment(JTextField.CENTER);
					timerTextField.setLayout(new FlowLayout(FlowLayout.CENTER));
					
					JButton menuButton = new JButton();
					menuButton.setText("MENU");
					menuButton.setFont(topPanelFont);
					menuButton.setPreferredSize(new Dimension(WIDTH*1/16, WIDTH*1/48));
					
				JPanel missionDetailsPanel = new JPanel();
//				missionDetailsPanel.setPreferredSize(new Dimension(WIDTH/4, WIDTH/32));
				missionDetailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
				missionDetailsPanel.setOpaque(false);
				
					JTextField missionTextField = new JTextField();
					missionTextField.setText("Current Mission");
					missionTextField.setFont(topPanelFont);
					missionTextField.setPreferredSize(new Dimension(WIDTH/6, WIDTH/56));
					missionTextField.setEditable(false);
					missionTextField.setHorizontalAlignment(JTextField.CENTER);
					missionTextField.setLayout(new FlowLayout(FlowLayout.CENTER));
					
					JButton detailsButton = new JButton();
					detailsButton.setText("Details");
					detailsButton.setFont(topPanelFont);
					detailsButton.setPreferredSize(new Dimension(WIDTH/16, WIDTH/40));
					
			JPanel goldMetalPanel = new JPanel();
			goldMetalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			goldMetalPanel.setOpaque(false);
			
				JLabel goldImageLabel = new JLabel();
//				goldImageLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
				goldImageLabel.setPreferredSize(new Dimension(WIDTH/48, WIDTH/48));
				
				JLabel goldLabel = new JLabel();
				goldLabel.setFont(topPanelFont);
				goldLabel.setText("1350000000000000000000000000000000000000000000000");
				goldLabel.setPreferredSize(new Dimension(WIDTH/24, WIDTH/48));
				
				JLabel metalImageLabel = new JLabel();
//				metalImageLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
				metalImageLabel.setPreferredSize(new Dimension(WIDTH/48, WIDTH/48));
				
				JLabel metalLabel = new JLabel();
				metalLabel.setFont(topPanelFont);
				metalLabel.setText("170000000");
				metalLabel.setPreferredSize(new Dimension(WIDTH/24, WIDTH/48));
				
		
			JPanel leftPanel = new JPanel();
			leftPanel.setPreferredSize(new Dimension(WIDTH/8, HEIGHT));
			leftPanel.setBackground(Color.GREEN);
//			leftPanel.setOpaque(false);
				
				

		
			gameMenuPanel.add(leftPanel, BorderLayout.WEST);
						goldMetalPanel.add(goldImageLabel);
						goldMetalPanel.add(goldLabel);
						goldMetalPanel.add(metalImageLabel);
						goldMetalPanel.add(metalLabel);
						missionDetailsPanel.add(missionTextField);
						missionDetailsPanel.add(detailsButton);
						timerMenuPanel.add(timerTextField);
						timerMenuPanel.add(menuButton);
					topPanel.add(timerMenuPanel);
					topPanel.add(missionDetailsPanel);
					topPanel.add(goldMetalPanel);
			gameMenuPanel.add(topPanel, BorderLayout.NORTH);
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
