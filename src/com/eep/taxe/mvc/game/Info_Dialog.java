package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;


public class Info_Dialog extends JDialog {



	private final int WIDTH =450;
	private final int HEIGHT =450;
	private JTextField WindowTitle;
	private JTextPane MissionInfo_JTextPane;
	private JButton GotIT_JButton;
	
	private String windowTitle = "Window Title";
	private String infoText;
	
	
	public void open() {
		try {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setLocation(1300/2-WIDTH/2, 720/2-HEIGHT/2);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		public Info_Dialog() {
		
			setMinimumSize(new Dimension(WIDTH, HEIGHT));
			getContentPane().setBounds(new Rectangle(0, 0, WIDTH, HEIGHT));
			setBounds(100, 100, WIDTH+17, HEIGHT+38);
			getContentPane().setLayout(new BorderLayout());
			{
				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0,0,0) ,5));
				getContentPane().add(panel, BorderLayout.CENTER);
					
					
			panel.setSize(new Dimension(WIDTH, HEIGHT));
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
			panel.setBackground(Color.WHITE);
			panel.setForeground(Color.WHITE);
			
			getContentPane().setLayout(new BorderLayout(0, 0));
				panel.setLayout(new BorderLayout(0, 0));
				
			// WINDOW TITLE ---------------------------------------
				WindowTitle = new JTextField();
				WindowTitle.setBorder(null);
				panel.add(WindowTitle, BorderLayout.NORTH);
				
				WindowTitle.setBackground(Color.BLACK);
				WindowTitle.setEditable(false);
				WindowTitle.setHorizontalAlignment(SwingConstants.CENTER);
				WindowTitle.setForeground(Color.WHITE);
				WindowTitle.setFont(new Font("Sans Serif", Font.BOLD, 20));
				WindowTitle.setText(windowTitle);		
				WindowTitle.setPreferredSize(new Dimension(WIDTH,HEIGHT/11));
				

			// INNER PANEL ---------------------------------------
				JPanel innerpanel_JPanel = new JPanel();
				panel.add(innerpanel_JPanel, BorderLayout.CENTER);
				
				innerpanel_JPanel.setBackground(Color.WHITE);
				innerpanel_JPanel.setLayout(new MigLayout("", "[392.00px,grow,center]", "[320px,grow,center][50px,grow,center]"));


				// TEXT ---------------------------------------
					MissionInfo_JTextPane = new JTextPane();
					MissionInfo_JTextPane.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 2));
					MissionInfo_JTextPane.setEditable(false);
					MissionInfo_JTextPane.setBackground(Color.WHITE);
					MissionInfo_JTextPane.setPreferredSize(new Dimension(WIDTH*7/8, HEIGHT*2/3));
					MissionInfo_JTextPane.setMaximumSize(new Dimension(WIDTH*7/8, HEIGHT*2/3));
					innerpanel_JPanel.add(MissionInfo_JTextPane, "cell 0 0,alignx center,aligny center");
				
					MissionInfo_JTextPane.setText(infoText);
					MissionInfo_JTextPane.setFont(new Font("Sans Serif", Font.BOLD, 12));
				

				// BUTTON CONTAINER ---------------------------------------
					JPanel buttonPanel_JPanel = new JPanel();
					buttonPanel_JPanel.setBorder(null);
					buttonPanel_JPanel.setBackground(Color.WHITE);
					innerpanel_JPanel.add(buttonPanel_JPanel, "cell 0 1,grow");
					buttonPanel_JPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
					

					// BUTTON ---------------------------------------
						GotIT_JButton = new JButton("GOT IT!");
						GotIT_JButton.setHorizontalTextPosition(SwingConstants.CENTER);
						
						buttonPanel_JPanel.add(GotIT_JButton);
						
						GotIT_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
						GotIT_JButton.setAlignmentY(Component.CENTER_ALIGNMENT);
						GotIT_JButton.setBorderPainted(false);
						GotIT_JButton.setBackground(Color.BLACK);
						GotIT_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
						GotIT_JButton.setForeground(Color.WHITE);
			}	
		}
			

			public void close(){
				this.dispose();
			}
		
			public void setWidnowTitle (String title){
				this.windowTitle = title;
			}
			
			public void setText (String text){
				this.infoText = text;
			}
			
			
			public void addGotITActionListener(ActionListener actionListener){
				GotIT_JButton.addActionListener(actionListener);
			}	
		
	}

