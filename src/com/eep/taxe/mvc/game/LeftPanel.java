package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftPanel extends JPanel{
	private final int WIDTH;
	private final int HEIGHT;
	private JLabel ageLabel;
	private JLabel player1NameLabel;
	private JLabel player1ScoreLabel;
	private JLabel player2NameLabel;
	private JLabel player2ScoreLabel;
	
	public void setAge(String age){
		ageLabel.setText(age);
	}
	
	public void setPlayer1Name(String name){
		player1NameLabel.setText(name);
	}
	
	public void setPlayer2Name(String name){
		player2NameLabel.setText(name);
	}
	
	public void setPlayer1Score(int score){
		String scoreString = "" + score;
		player1ScoreLabel.setText(scoreString);
	}

	public void setPlayer2Score(int score){
		String scoreString = "" + score;
		player2ScoreLabel.setText(scoreString);
	}


	public LeftPanel(int width, int height){
//		this.WIDTH = width;
//		this.HEIGHT = height;
		this.WIDTH = 1300;
		this.HEIGHT = 720;
		this.setPreferredSize(new Dimension(WIDTH/8, HEIGHT));
		this.setBackground(Color.GREEN);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setOpaque(false);
		
			JPanel agePanel = new JPanel();
			agePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, WIDTH/512));
			agePanel.setPreferredSize(new Dimension(WIDTH/9, WIDTH/48));
			agePanel.setBackground(new Color(213, 134, 145, 123));
				
				JLabel staticAgeLabel = new JLabel();
				staticAgeLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
				staticAgeLabel.setText("Age:");
				
				ageLabel = new JLabel();
				ageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
				ageLabel.setText("Stone Age");
				
			JPanel player1Panel = new JPanel();
			player1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,  0));
			player1Panel.setOpaque(false);
			
				JLabel player1IconLabel = new JLabel();
//				player1IconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
				player1IconLabel.setPreferredSize(new Dimension(WIDTH/32, WIDTH/32));
				
				JPanel player1InfoPanel = new JPanel();
				player1InfoPanel.setLayout(new BoxLayout(player1InfoPanel, BoxLayout.Y_AXIS));
				player1InfoPanel.setPreferredSize(new Dimension(WIDTH/16, WIDTH/32));
				player1InfoPanel.setOpaque(false);
				
					player1NameLabel = new JLabel();
					player1NameLabel.setText("Player 1");
					player1NameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
					player1NameLabel.setPreferredSize(new Dimension(WIDTH/32, WIDTH/64));
					
					player1ScoreLabel = new JLabel();
					player1ScoreLabel.setText("123423");
					player1ScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
					player1ScoreLabel.setPreferredSize(new Dimension(WIDTH/32, WIDTH/64));
					
			JPanel player2Panel = new JPanel();
			player2Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,  0));
			player2Panel.setOpaque(false);
					
				JLabel player2IconLabel = new JLabel();
//				player2IconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
				player2IconLabel.setPreferredSize(new Dimension(WIDTH/32, WIDTH/32));
				
				JPanel player2InfoPanel = new JPanel();
				player2InfoPanel.setLayout(new BoxLayout(player2InfoPanel, BoxLayout.Y_AXIS));
				player2InfoPanel.setPreferredSize(new Dimension(WIDTH/16, WIDTH/32));
				player2InfoPanel.setOpaque(false);
				
					player2NameLabel = new JLabel();
					player2NameLabel.setText("Player 2");
					player2NameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
					player2NameLabel.setPreferredSize(new Dimension(WIDTH/32, WIDTH/64));
					
					player2ScoreLabel = new JLabel();
					player2ScoreLabel.setText("43565432");
					player2ScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
					player2ScoreLabel.setPreferredSize(new Dimension(WIDTH/32, WIDTH/64));
					
					player1InfoPanel.add(player1NameLabel);
					player1InfoPanel.add(player1ScoreLabel);
				player1Panel.add(player1IconLabel);
				player1Panel.add(Box.createRigidArea(new Dimension(WIDTH/128, WIDTH/32)));
				player1Panel.add(player1InfoPanel);
					player2InfoPanel.add(player2NameLabel);
					player2InfoPanel.add(player2ScoreLabel);
				player2Panel.add(player2IconLabel);
				player2Panel.add(Box.createRigidArea(new Dimension(WIDTH/128, WIDTH/32)));
				player2Panel.add(player2InfoPanel);
				agePanel.add(staticAgeLabel);
				agePanel.add(ageLabel);
			this.add(agePanel);
			this.add(player1Panel);
			this.add(player2Panel);
		
			this.setVisible(true);
	}
	
}
