package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class RightPanel extends JPanel {
	private final int WIDTH;
	private final int HEIGHT;
	private JButton regionButton;
	private JButton objectiveButton;
	private JTextPane infoPane;
	private JButton startMissionButton;
	
	public RightPanel(int width, int height){
//		this.WIDTH = width;
//		this.HEIGHT = height;
		WIDTH = 1300;
		HEIGHT = 400;
		
		this.setPreferredSize(new Dimension(WIDTH/6, HEIGHT));
		this.setBackground(Color.RED);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel objectiveRegionsPanel = new JPanel();
		objectiveRegionsPanel.setPreferredSize(new Dimension(WIDTH/6, WIDTH/12));
		objectiveRegionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
			JPanel regionPanel = new JPanel();
			regionPanel.setPreferredSize(new Dimension(WIDTH/12, WIDTH/12));
			regionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			regionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
			
				JLabel regionTitleLabel = new JLabel();
				regionTitleLabel.setPreferredSize(new Dimension(WIDTH/12, WIDTH/48));
				regionTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
				regionTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				regionTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
				regionTitleLabel.setText("Regions");
				
				regionButton = new JButton();
				regionButton.setPreferredSize(new Dimension(WIDTH/18, WIDTH/18));
				regionButton.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
				regionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				regionButton.setContentAreaFilled(false);

			
			JPanel objectivePanel = new JPanel();
			objectivePanel.setPreferredSize(new Dimension(WIDTH/12, WIDTH/12));
			objectivePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			objectivePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
			
				JLabel objectiveTitleLabel = new JLabel();
				objectiveTitleLabel.setPreferredSize(new Dimension(WIDTH/12, WIDTH/48));
				objectiveTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
				objectiveTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				objectiveTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
				objectiveTitleLabel.setText("Objectives");
				
				objectiveButton = new JButton();
				objectiveButton.setPreferredSize(new Dimension(WIDTH/18, WIDTH/18));
				objectiveButton.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
				objectiveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				objectiveButton.setContentAreaFilled(false);

			
			
		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(WIDTH/6, HEIGHT - WIDTH/12));
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
			
			infoPane = new JTextPane();
			infoPane.setPreferredSize(new Dimension(WIDTH/7, HEIGHT - WIDTH/5));
			infoPane.setBackground(null); 
			infoPane.setText("dffgjkd fghklf hlfjgh jklfgjfk hjfghjkfdfghf f kjfdkldfl dflkj h jldf kgjhkfl djfdlkjhfkl  jhkfdjlkghjklfd jlfgdhjdfl");
			
			startMissionButton = new JButton();
			startMissionButton.setText("Start Mission");
			startMissionButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
			startMissionButton.setPreferredSize(new Dimension(WIDTH*1/12, WIDTH*1/48));
			
		
				
				regionPanel.add(regionTitleLabel);
				regionPanel.add(regionButton);
				objectivePanel.add(objectiveTitleLabel);
				objectivePanel.add(objectiveButton);
			objectiveRegionsPanel.add(regionPanel);
			objectiveRegionsPanel.add(objectivePanel);
			
		this.add(objectiveRegionsPanel);	

			infoPanel.add(Box.createRigidArea(new Dimension(WIDTH/6, WIDTH/64)));
			infoPanel.add(infoPane);
			infoPanel.add(startMissionButton);
		this.add(infoPanel);
			
	}
	
	public void setInfoText(String text){
		infoPane.setText(text);
	}
	
	public void setStartMissionButtonVisible(boolean visible){
		startMissionButton.setVisible(visible);
	}
	
	public void addStartMissionButtonActionListener(ActionListener buttonListener){
		startMissionButton.addActionListener(buttonListener);
	}
	
	public void addRegionButtonActionListener(ActionListener buttonListener){
		regionButton.addActionListener(buttonListener);
	}
	
	public void addObjectiveButtonActionListener(ActionListener buttonListener){
		objectiveButton.addActionListener(buttonListener);
	}
	
}
