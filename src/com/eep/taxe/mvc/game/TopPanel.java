package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalTextFieldUI;

public class TopPanel extends JPanel{
	private final int WIDTH;
	private final int HEIGHT;
	private JTextField timerTextField;
	private JButton endTurnButton;
	private JTextField missionTextField;
	private JButton detailsButton;
	private JLabel goldLabel;
	private JLabel metalLabel;
	
	
	
	public void setTimer(String time){
		timerTextField.setText(time);
	}
	
	public void setMissionInfo(String info){
		missionTextField.setText(info);
	}
	
	public void setGold(int gold) {
		String goldString = "" + gold;
		goldLabel.setText(goldString);
	}
	
	public void setMetal(int metal){
		String metalString = "" + metal;
		metalLabel.setText(metalString);
	}
	
	public void addEndTurnButtonActionListener(ActionListener buttonListener){
		endTurnButton.addActionListener(buttonListener);
	}
	
	public void addDetailsButtonActionListener(ActionListener buttonListener){
		detailsButton.addActionListener(buttonListener);
	}
	
	
	
	public TopPanel(int width, int height){
//		this.WIDTH = width;
//		this.HEIGHT = height;
		WIDTH =1300;
		HEIGHT = 720;
		this.setPreferredSize(new Dimension(WIDTH, WIDTH/32));
		this.setLayout(new GridLayout(0, 3));
		this.setOpaque(false);
			
			Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
			
			JPanel timerMenuPanel = new JPanel();
			timerMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			timerMenuPanel.setOpaque(false);
			
			
				timerTextField = new JTextField();
				timerTextField.setText("00:00");
				timerTextField.setPreferredSize(new Dimension(WIDTH*1/20, WIDTH*1/48));
				timerTextField.setFont(font);
				timerTextField.setEditable(false);
				timerTextField.setHorizontalAlignment(JTextField.CENTER);
				timerTextField.setLayout(new FlowLayout(FlowLayout.CENTER));
				
				endTurnButton = new JButton();
				endTurnButton.setText("End Turn");
				endTurnButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
				endTurnButton.setPreferredSize(new Dimension(WIDTH*1/16, WIDTH*1/48));
				
			JPanel missionDetailsPanel = new JPanel();
//			missionDetailsPanel.setPreferredSize(new Dimension(WIDTH/4, WIDTH/32));
			missionDetailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, WIDTH/512));
			missionDetailsPanel.setOpaque(false);
			
				missionTextField = new JTextField();
				missionTextField.setText("Current Mission");
				missionTextField.setFont(font);
				missionTextField.setPreferredSize(new Dimension(WIDTH/6, WIDTH/48));
				missionTextField.setEditable(false);
				missionTextField.setHorizontalAlignment(JTextField.CENTER);
				missionTextField.setLayout(new FlowLayout(FlowLayout.CENTER));
				
				detailsButton = new JButton();
				detailsButton.setText("Details");
				detailsButton.setFont(font);
				detailsButton.setPreferredSize(new Dimension(WIDTH/16, WIDTH/40));
				
		JPanel goldMetalPanel = new JPanel();
		goldMetalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		goldMetalPanel.setOpaque(false);
		
			JLabel goldImageLabel = new JLabel();
//			goldImageLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
			goldImageLabel.setPreferredSize(new Dimension(WIDTH/48, WIDTH/48));
			
			goldLabel = new JLabel();
			goldLabel.setFont(font);
			goldLabel.setText("1350000000000000000000000000000000000000000000000");
			goldLabel.setPreferredSize(new Dimension(WIDTH/24, WIDTH/48));
			
			JLabel metalImageLabel = new JLabel();
//			metalImageLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
			metalImageLabel.setPreferredSize(new Dimension(WIDTH/48, WIDTH/48));
			
			metalLabel = new JLabel();
			metalLabel.setFont(font);
			metalLabel.setText("170000000");
			metalLabel.setPreferredSize(new Dimension(WIDTH/24, WIDTH/32));
			
			
			
			goldMetalPanel.add(goldImageLabel);
			goldMetalPanel.add(goldLabel);
			goldMetalPanel.add(metalImageLabel);
			goldMetalPanel.add(metalLabel);
			missionDetailsPanel.add(missionTextField);
			missionDetailsPanel.add(detailsButton);
			timerMenuPanel.add(timerTextField);
			timerMenuPanel.add(endTurnButton);
		this.add(timerMenuPanel);
		this.add(missionDetailsPanel);
		this.add(goldMetalPanel);
	}
}
