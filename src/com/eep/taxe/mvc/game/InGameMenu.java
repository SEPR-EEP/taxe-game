package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class InGameMenu extends JDialog {

	private final int WIDTH =450;
	private final int HEIGHT =450;
	
	private JButton resume_JButton;
	private JButton save_JButton;
	private JButton how_JButton;
	private JButton quit_JButton;
	
	public void open() {
		try {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setLocation(1300/2-WIDTH/2, 720/2-HEIGHT/2);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InGameMenu() {
		
		setBounds(100, 100, WIDTH, HEIGHT);
		{
			
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			
			panel.setSize(new Dimension(WIDTH, HEIGHT));
			panel.setBorder(null);
			panel.setBackground(Color.WHITE);
			panel.setForeground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
		
	// ----------------- TITLE -------------

			JTextField gameMenuTitle_JtextField = new JTextField();
			panel.add(gameMenuTitle_JtextField, BorderLayout.NORTH);
			
			gameMenuTitle_JtextField.setBounds(new Rectangle(15, 200, 110, 25));
			gameMenuTitle_JtextField.setBorder(null);
			gameMenuTitle_JtextField.setBackground(Color.BLACK);
			gameMenuTitle_JtextField.setHorizontalAlignment(SwingConstants.CENTER);
			gameMenuTitle_JtextField.setForeground(Color.WHITE);
			gameMenuTitle_JtextField.setFont(new Font("Sans Serif", Font.BOLD, 20));
			gameMenuTitle_JtextField.setText("Menu");
			gameMenuTitle_JtextField.setPreferredSize(new Dimension(WIDTH,HEIGHT/11));
			
		// ----------------- INNER PANEL -------------
				JPanel innerContainer_Jpanel = new JPanel();
				innerContainer_Jpanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
				innerContainer_Jpanel.setBackground(Color.WHITE);
				getContentPane().add(innerContainer_Jpanel, BorderLayout.CENTER);
				FlowLayout fl_innerContainer_Jpanel = new FlowLayout(FlowLayout.CENTER, 500, 55);
				innerContainer_Jpanel.setLayout(fl_innerContainer_Jpanel);
		
			// ----------------- RESUME GAME -------------
					resume_JButton = new JButton("Resume Game");
					innerContainer_Jpanel.add(resume_JButton);
					
					resume_JButton.setBounds(new Rectangle(15, 200, 110, 25));
					resume_JButton.setPreferredSize(new Dimension(145, 28));
					resume_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					resume_JButton.setBorderPainted(false);
					resume_JButton.setBackground(Color.BLACK);
					resume_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
					resume_JButton.setForeground(Color.WHITE);
					
			// ----------------- SAVE GAME -------------
					
					save_JButton = new JButton("Save Game");
					innerContainer_Jpanel.add(save_JButton);
				
					save_JButton.setBounds(new Rectangle(200, 110, 12, 2));
					save_JButton.setPreferredSize(new Dimension(145, 28));
					save_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					save_JButton.setBorderPainted(false);
					save_JButton.setBackground(Color.BLACK);
					save_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
					save_JButton.setForeground(Color.WHITE);
					
			// ----------------- HOW TO PLAY -------------
					
					how_JButton = new JButton("How to Play");
					innerContainer_Jpanel.add(how_JButton);
					
					how_JButton.setPreferredSize(new Dimension(145, 28));
					how_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					how_JButton.setBorderPainted(false);
					how_JButton.setBackground(Color.BLACK);
					how_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
					how_JButton.setForeground(Color.WHITE);
					
			// ----------------- QUIT -------------
					
					quit_JButton = new JButton("Quit");
					innerContainer_Jpanel.add(quit_JButton);
					
					quit_JButton.setPreferredSize(new Dimension(145, 28));
					quit_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
					quit_JButton.setBorderPainted(false);
					quit_JButton.setBackground(Color.BLACK);
					quit_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
					quit_JButton.setForeground(Color.WHITE);
					
		}
	}
	
	public void close (){
		this.dispose();
	}
	
	
	public void addResumeActionListener(ActionListener actionListener){
		resume_JButton.addActionListener(actionListener);
	}		
	
	
	public void addSaveActionListener(ActionListener actionListener){
		save_JButton.addActionListener(actionListener);
	}
	
	public void addHowActionListener(ActionListener actionListener){
		how_JButton.addActionListener(actionListener);
	}		
	
	
	public void addQuitActionListener(ActionListener actionListener){
		quit_JButton.addActionListener(actionListener);
	}

}
