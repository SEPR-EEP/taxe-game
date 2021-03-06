package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.Box;



public class TopPanel extends JPanel{
	private final int WIDTH;
	private final int HEIGHT;
	private JTextField timerTextField;
	private JButton endTurnButton;
	private JTextField missionTextField;
	private JButton detailsButton;
	private JLabel goldLabel;
	private JLabel metalLabel;
	private Component rigidArea;
	
	
	
	public TopPanel(int width, int height){
		this.WIDTH = width;
    	this.HEIGHT = height;
//		WIDTH =1300;
//		HEIGHT = 720;
		this.setPreferredSize(new Dimension(WIDTH, WIDTH/32));
		this.setLayout(new GridLayout(0, 3));
		this.setOpaque(false);
			
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
			
			JPanel timerMenuPanel = new JPanel();
			timerMenuPanel.setOpaque(false);
			timerMenuPanel.setBackground(Color.WHITE);
			FlowLayout fl_timerMenuPanel = new FlowLayout(FlowLayout.LEFT);
			timerMenuPanel.setLayout(fl_timerMenuPanel);
			timerMenuPanel.setOpaque(false);
			
				timerTextField = new JTextField();
				timerTextField.setBackground(Color.WHITE);
				timerTextField.setBorder(new LineBorder(new Color(0,0,0), 2));
				timerTextField.setText("00:00");
				timerTextField.setPreferredSize(new Dimension(WIDTH/15, WIDTH/48));
				timerTextField.setFont(font);
				timerTextField.setEditable(false);
				timerTextField.setHorizontalAlignment(JTextField.CENTER);
				FlowLayout fl_timerTextField = new FlowLayout(FlowLayout.CENTER);
				timerTextField.setLayout(fl_timerTextField);
			
				
				endTurnButton = new JButton();
				endTurnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				endTurnButton.setText("OK - End Turn");
				endTurnButton.setFont(buttonFont);
				endTurnButton.setPreferredSize(new Dimension(WIDTH/10, WIDTH/48));
				endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				endTurnButton.setBorderPainted(false);
				endTurnButton.setForeground(Color.RED);
				
			JPanel missionDetailsPanel = new JPanel();
			missionDetailsPanel.setOpaque(false);
			FlowLayout fl_missionDetailsPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
			missionDetailsPanel.setBackground(Color.WHITE);
			missionDetailsPanel.setLayout(fl_missionDetailsPanel);
			missionDetailsPanel.setOpaque(false);
			
				missionTextField = new JTextField();
				missionTextField.setBorder(new LineBorder(new Color(0,0,0), 2));
				missionTextField.setText("Current Mission");
				missionTextField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
				missionTextField.setPreferredSize(new Dimension(WIDTH/2, WIDTH/48));
				missionTextField.setEditable(false);
				missionTextField.setHorizontalAlignment(JTextField.CENTER);
				missionTextField.setLayout(new FlowLayout(FlowLayout.CENTER));
				missionTextField.setBackground(Color.WHITE);
				
				detailsButton = new JButton();
				detailsButton.setText("Details");
				detailsButton.setFont(buttonFont);
				detailsButton.setPreferredSize(new Dimension(WIDTH/16, WIDTH/48));
				detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				detailsButton.setBorderPainted(false);
				detailsButton.setForeground(Color.BLACK);

				
		JPanel goldMetalPanel = new JPanel();
		goldMetalPanel.setOpaque(false);
		FlowLayout fl_goldMetalPanel = new FlowLayout(FlowLayout.RIGHT);
		fl_goldMetalPanel.setHgap(7);
		fl_goldMetalPanel.setVgap(1);
		fl_goldMetalPanel.setAlignOnBaseline(true);
		goldMetalPanel.setLayout(fl_goldMetalPanel);
		goldMetalPanel.setBackground(Color.WHITE);
		goldMetalPanel.setOpaque(false);
		
			JLabel goldImageLabel = new JLabel();
			goldImageLabel.setIcon(new ImageIcon(getClass().getResource("/resources/gameview/GoldIcon.png")));
			goldImageLabel.setPreferredSize(new Dimension(WIDTH/48, WIDTH/48));
			
			goldLabel = new JLabel();
			goldLabel.setOpaque(true);
			goldLabel.setBackground(new Color(169,169,169,160));
			goldLabel.setAlignmentY(CENTER_ALIGNMENT);
			goldLabel.setFont(font);
			goldLabel.setHorizontalAlignment(JLabel.CENTER);
			goldLabel.setText("1350000000000000000000000000000000000000000000000");
			goldLabel.setPreferredSize(new Dimension(WIDTH/22, WIDTH/48));
			
			JLabel metalImageLabel = new JLabel();
			metalImageLabel.setIcon(new ImageIcon(getClass().getResource("/resources/gameview/MetalIcon.png")));
			metalImageLabel.setPreferredSize(new Dimension(WIDTH/48, WIDTH/48));
			
			metalLabel = new JLabel();
			metalLabel.setOpaque(true);
			metalLabel.setAlignmentY(CENTER_ALIGNMENT);
			metalLabel.setHorizontalAlignment(JLabel.CENTER);
			metalLabel.setBackground(new Color(169,169,169,160));
			metalLabel.setFont(font);
			metalLabel.setText("170000000");
			metalLabel.setPreferredSize(new Dimension(WIDTH/22, WIDTH/48));
			
			
			
			goldMetalPanel.add(goldImageLabel);
			goldMetalPanel.add(goldLabel);
			
			rigidArea = Box.createRigidArea(new Dimension(20, WIDTH/32));
			
			
			goldMetalPanel.add(rigidArea);
			goldMetalPanel.add(metalImageLabel);
			goldMetalPanel.add(metalLabel);
			missionDetailsPanel.add(missionTextField);
			//missionDetailsPanel.add(detailsButton);
			timerMenuPanel.add(timerTextField);
			timerMenuPanel.add(endTurnButton);
		this.add(timerMenuPanel);
		this.add(missionDetailsPanel);
		this.add(goldMetalPanel);
	}
	
	public void enableEndTurnButton(boolean b) {
		endTurnButton.setEnabled(b);
	}
	
	
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
}
