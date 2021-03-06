package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;


public class RightPanel extends JPanel {
	private final int WIDTH;
	private final int HEIGHT;
	private JButton regionButton;
	private JButton objectiveButton;
	private JTextPane infoPane;
	
	public RightPanel(final int width, final int height){
	    this.WIDTH = width;
		this.HEIGHT = height;
//		WIDTH = 1300;
//		HEIGHT = 720;
		setBackground(Color.WHITE);
		setBorder(null);
		
		this.setPreferredSize(new Dimension(WIDTH/6, HEIGHT - WIDTH*3/32));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		
		JPanel objectiveRegionsPanel = new JPanel();
		objectiveRegionsPanel.setMaximumSize(new Dimension(WIDTH/6, WIDTH/11));
		objectiveRegionsPanel.setOpaque(false);
		objectiveRegionsPanel.setLayout(new BoxLayout(objectiveRegionsPanel, BoxLayout.X_AXIS));
		
			JPanel regionPanel = new JPanel();
			regionPanel.setMaximumSize(new Dimension(WIDTH/12, WIDTH/12));
			regionPanel.setOpaque(false);
			regionPanel.setBackground(new Color(255, 255, 255));
			regionPanel.setPreferredSize(new Dimension(WIDTH/12, WIDTH/12));
			regionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			regionPanel.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));
			
				JLabel regionTitleLabel = new JLabel();
				regionTitleLabel.setMaximumSize(new Dimension(97, 27));
				regionTitleLabel.setPreferredSize(new Dimension(97, 27));
				regionTitleLabel.setFont(labelFont);
				regionTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				regionTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
				regionTitleLabel.setText("Regions");
				
				regionButton = new JButton();
				regionButton.setPreferredSize(new Dimension(WIDTH/18, WIDTH/18));
				regionButton.setIcon(new ImageIcon(getClass().getResource("/resources/gameview/RegionIcon.png")));
				regionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				regionButton.setContentAreaFilled(false);
					
			JPanel objectivePanel = new JPanel();
			objectivePanel.setMaximumSize(new Dimension(WIDTH/12, WIDTH/12));
			objectivePanel.setOpaque(false);
			objectivePanel.setBackground(new Color(255, 255, 255));
			objectivePanel.setPreferredSize(new Dimension(WIDTH/12, WIDTH/12));
			objectivePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			objectivePanel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0)));
			
				JLabel objectiveTitleLabel = new JLabel();
				objectiveTitleLabel.setMaximumSize(new Dimension(97, 27));
				objectiveTitleLabel.setPreferredSize(new Dimension(97, 27));
				objectiveTitleLabel.setFont(labelFont);
				objectiveTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				objectiveTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
				objectiveTitleLabel.setText("Objectives");
				
				objectiveButton = new JButton();
				objectiveButton.setPreferredSize(new Dimension(WIDTH/18, WIDTH/18));
				objectiveButton.setIcon(new ImageIcon(getClass().getResource("/resources/gameview/GoalIcon.png")));
				objectiveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				objectiveButton.setContentAreaFilled(false);					
						
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		infoPanel.setBackground(new Color(255, 255, 255));
		infoPanel.setPreferredSize(new Dimension(WIDTH/6, HEIGHT - (WIDTH*4/32 + WIDTH/12)));
				
			JPanel infoPanePanel = new JPanel();
			infoPanePanel.setLayout(new MigLayout("", "[196px]", "[470px]"));
			infoPanePanel.setPreferredSize(new Dimension(WIDTH/6-20, HEIGHT - (WIDTH*4/32 + WIDTH/12)-40));
			infoPanePanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Details", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.SANS_SERIF,Font.BOLD,12), new Color(0,0,0)));
			infoPanePanel.setBackground(Color.WHITE);
		
				infoPane = new JTextPane();
				infoPane.setFont(new Font("SansSerif", Font.PLAIN, 12));
				infoPane.setPreferredSize(new Dimension(WIDTH/6-40, HEIGHT - (WIDTH*4/32 + WIDTH/12)-40));
				infoPane.setEditable(false);
				infoPane.setText("Web sites - High density hosting of web sites. This feature was "
						+ "announced in preview form in June 2012 at the Meet Microsoft Azure event.[3] "
						+ "Customers can create web sites in PHP, ASP.NET, Node.js, or Python, or select "
						+ "from several open source applications from a gallery to deploy. This comprises "
						+ "one aspect of the Platform as a Service (PaaS) offerings for the Windows Azure Platform.");
				infoPanel.setLayout(new BorderLayout());

				
				regionPanel.add(regionTitleLabel);
				regionPanel.add(regionButton);
				objectivePanel.add(objectiveTitleLabel);
				objectivePanel.add(objectiveButton);
			objectiveRegionsPanel.add(regionPanel);
			objectiveRegionsPanel.add(objectivePanel);
		this.add(objectiveRegionsPanel);
			
				infoPanePanel.add(infoPane, "cell 0 0,alignx center,growy");
			infoPanel.add(infoPanePanel, BorderLayout.CENTER);			
			infoPanel.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.NORTH);
			infoPanel.add(Box.createRigidArea(new Dimension(10, 20)), BorderLayout.WEST);
			infoPanel.add(Box.createRigidArea(new Dimension(10, 20)), BorderLayout.EAST);
			infoPanel.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.SOUTH);
		this.add(infoPanel);
		
	}
	
	public void setInfoText(String text){
        infoPane.setContentType("text/html"); // Enable HTML formatting
		infoPane.setText(text);
	}
	
	public void addRegionButtonActionListener(ActionListener buttonListener){
		regionButton.addActionListener(buttonListener);
	}
	
	public void addObjectiveButtonActionListener(ActionListener buttonListener){
		objectiveButton.addActionListener(buttonListener);
	}
	
}
