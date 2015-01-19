package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.MatteBorder;


public class Store extends JDialog {

	private final int WIDTH =600;
	private final int HEIGHT =450;
	
	private JTextPane itemDetails_JTextPane;
	private JButton Buy_JButton;
	private JButton ReturnToGame_JButton;
	
	private String itemDetailsText;
	
	public static void main(String[] args) {
		try {
			Store dialog = new Store();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Store() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		getContentPane().setBounds(new Rectangle(0, 0, WIDTH, HEIGHT));
		setBounds(100, 100, WIDTH+20, HEIGHT+38);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(0, 5, 5, 5, (Color) new Color(128,128,128)));
			getContentPane().add(panel, BorderLayout.NORTH);
			
			panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			panel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
			panel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
			panel.setLayout(new BorderLayout(0, 0));
		
		//  TITLE ------------------------------------------

				JTextField marketTitle_JtextField = new JTextField();
				panel.add(marketTitle_JtextField, BorderLayout.NORTH);
				
				marketTitle_JtextField.setEditable(false);
				marketTitle_JtextField.setBounds(new Rectangle(15, 200, 110, 25));
				marketTitle_JtextField.setBorder(null);
				marketTitle_JtextField.setBackground(new Color(128, 128, 128));
				marketTitle_JtextField.setHorizontalAlignment(SwingConstants.CENTER);
				marketTitle_JtextField.setForeground(Color.WHITE);
				marketTitle_JtextField.setFont(new Font("Sans Serif", Font.BOLD, 20));
				marketTitle_JtextField.setText("Market");
				marketTitle_JtextField.setPreferredSize(new Dimension(WIDTH,HEIGHT/11));
				
			
		//  INNER PANEL -----------------------------------------
				JPanel innerContainer_Jpanel = new JPanel();
				panel.add(innerContainer_Jpanel, BorderLayout.CENTER);
				
				innerContainer_Jpanel.setLayout(new BorderLayout(0, 0));
				innerContainer_Jpanel.setBackground(Color.WHITE);
				
				
			//  DETAILS PANEL----------------------------------
					
					JPanel detailsPanel_JPanel = new JPanel();
					innerContainer_Jpanel.add(detailsPanel_JPanel, BorderLayout.EAST);
					
					detailsPanel_JPanel.setSize(new Dimension(80, 2));
					detailsPanel_JPanel.setMaximumSize(new Dimension(WIDTH/5, (HEIGHT*9/10)));
					detailsPanel_JPanel.setBackground(new Color(255, 255, 255));
					detailsPanel_JPanel.setLayout(new MigLayout("", "[100px, grow,center]", "[250px,grow,center][grow,center]"));
					
					
					//  ITEM DETAILS PANEL ------------------------------
							JPanel detailsContainer_JPanel = new JPanel();
							detailsPanel_JPanel.add(detailsContainer_JPanel, "flowy,cell 0 0");
							
							detailsContainer_JPanel.setLayout(new MigLayout("", "[145px]", "[360px][28px]"));
							
							
							//  ITEM DETAILS ------------------------------
									
																	
									itemDetails_JTextPane = new JTextPane();
									detailsContainer_JPanel.add(itemDetails_JTextPane, "cell 0 0,alignx center,aligny top");
																	
									itemDetails_JTextPane.setEditable(false);
									itemDetails_JTextPane.setPreferredSize(new Dimension(WIDTH/4-45, HEIGHT*9/10));
									itemDetails_JTextPane.setBorder(new LineBorder(new Color(171, 173, 179), 2));
									itemDetails_JTextPane.setMaximumSize(new Dimension(WIDTH/4-45, (HEIGHT*9/10)));
									itemDetails_JTextPane.setText(itemDetailsText);
									itemDetails_JTextPane.setFont(new Font("Sans Serif", Font.BOLD, 12));
									itemDetails_JTextPane.setAlignmentX(Component.BOTTOM_ALIGNMENT);
							
									
							//  BUY BUTTON ---------------------------------------------------
									Buy_JButton = new JButton("Buy");
									detailsContainer_JPanel.add(Buy_JButton, "cell 0 1,alignx left,aligny top");
									
									Buy_JButton.setBounds(new Rectangle(15, 200, 110, 25));
									Buy_JButton.setPreferredSize(new Dimension(145, 28));
									Buy_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
									Buy_JButton.setBorderPainted(false);
									Buy_JButton.setBackground(Color.BLACK);
									Buy_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
									Buy_JButton.setForeground(Color.BLACK);
							
					//  RETURN TO GAME BUTTON ------------------------------
							ReturnToGame_JButton = new JButton("Return to Game");
							detailsPanel_JPanel.add(ReturnToGame_JButton, "cell 0 1");
							
							ReturnToGame_JButton.setPreferredSize(new Dimension(145, 28));
							ReturnToGame_JButton.setForeground(Color.BLACK);
							ReturnToGame_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
							ReturnToGame_JButton.setBounds(new Rectangle(15, 200, 110, 25));
							ReturnToGame_JButton.setBorderPainted(false);
							ReturnToGame_JButton.setBackground(Color.BLACK);
							ReturnToGame_JButton.setAlignmentX(0.5f);
							
							
							
					
			//  ITEMS PANEL -----------------------------------------------
					JPanel itemsPanel_JPanel = new JPanel();
					innerContainer_Jpanel.add(itemsPanel_JPanel, BorderLayout.CENTER);
					
					itemsPanel_JPanel.setLayout(new BoxLayout(itemsPanel_JPanel, BoxLayout.Y_AXIS));
					
					//  POWER-UPS PANEL --------------------------------------
							JPanel powerPanel_JPanel = new JPanel();
							itemsPanel_JPanel.add(powerPanel_JPanel);
							
							powerPanel_JPanel.setLayout(new BorderLayout(0, 0));
							
						//  POWER-UPS TITLE --------------------------------------
							    JTextField powerTitle_JTextField = new JTextField();
							    powerPanel_JPanel.add(powerTitle_JTextField, BorderLayout.NORTH);
								
								powerTitle_JTextField.setEditable(false);
								powerTitle_JTextField.setBounds(new Rectangle(15, 200, 110, 25));
								powerTitle_JTextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
								powerTitle_JTextField.setBackground(new Color(128, 128, 128));
								powerTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
								powerTitle_JTextField.setForeground(new Color(255, 255, 255));
								powerTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
								powerTitle_JTextField.setText("Power-ups");
								
								
						//  POWER-UPS ICONS --------------------------------------		
								JPanel powerIcons_JPanel = new JPanel();
								powerPanel_JPanel.add(powerIcons_JPanel, BorderLayout.CENTER);
									
									// icons
							
					//  TRAPS PANEL ----------------------------------------------
							JPanel trapsPanel_JPanel = new JPanel();
							itemsPanel_JPanel.add(trapsPanel_JPanel);
							
							trapsPanel_JPanel.setLayout(new BorderLayout(0, 0));
							
						//  TRAPS TITLE --------------------------------------
							
							JTextField trapsTitle_JTextField = new JTextField();
							trapsPanel_JPanel.add(trapsTitle_JTextField, BorderLayout.NORTH);
							
							trapsTitle_JTextField.setEditable(false);
							trapsTitle_JTextField.setBounds(new Rectangle(15, 200, 110, 25));
							trapsTitle_JTextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
							trapsTitle_JTextField.setBackground(new Color(128, 128, 128));
							trapsTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
							trapsTitle_JTextField.setForeground(new Color(255, 255, 255));
							trapsTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
							trapsTitle_JTextField.setText("Traps");
							
			
						//  TRAPS ICONS -------------------------------------
							JPanel trapsIcons_JPanel = new JPanel();
							trapsPanel_JPanel.add(trapsIcons_JPanel, BorderLayout.CENTER);
								
							// icons
							
							
					//  TRAINS PANEL ------------------------------------------
							JPanel trainsPanel_JPanel = new JPanel();
							itemsPanel_JPanel.add(trainsPanel_JPanel);
							
							trainsPanel_JPanel.setLayout(new BorderLayout(0, 0));
							
						//  TRAINS TITLE --------------------------------------
							
							    JTextField trainsTitle_JTextField = new JTextField();
							    trainsPanel_JPanel.add(trainsTitle_JTextField, BorderLayout.NORTH);
								
							    trainsTitle_JTextField.setEditable(false);
								trainsTitle_JTextField.setBounds(new Rectangle(15, 200, 110, 25));
								trainsTitle_JTextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
								trainsTitle_JTextField.setBackground(new Color(128, 128, 128));
								trainsTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
								trainsTitle_JTextField.setForeground(new Color(255, 255, 255));
								trainsTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
								trainsTitle_JTextField.setText("Trains");
								
							//  TRAINS ICONS -------------------------------------								
								JPanel trainsIcons_JPanel = new JPanel();
								trainsPanel_JPanel.add(trainsIcons_JPanel, BorderLayout.CENTER);
								
								// icons
								
					//  INVENTORY PANEL --------------------------------------
							JPanel inventoryPanel_JPanel = new JPanel();
							itemsPanel_JPanel.add(inventoryPanel_JPanel);
							
							inventoryPanel_JPanel.setLayout(new BorderLayout(0, 0));
							
						//  INVENTORY TITLE --------------------------------------
							
							    JTextField inventoryTitle_JTextField = new JTextField();
							    inventoryPanel_JPanel.add(inventoryTitle_JTextField, BorderLayout.NORTH);
								
							    inventoryTitle_JTextField.setEditable(false);
								inventoryTitle_JTextField.setBounds(new Rectangle(15, 200, 110, 25));
								inventoryTitle_JTextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
								inventoryTitle_JTextField.setBackground(new Color(128, 128, 128));
								inventoryTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
								inventoryTitle_JTextField.setForeground(new Color(255, 255, 255));
								inventoryTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
								inventoryTitle_JTextField.setText("Inventory");
								
							//  INVENTORY ICONS -------------------------------------
								JPanel inventoryIcons_JPanel = new JPanel();
								inventoryPanel_JPanel.add(inventoryIcons_JPanel, BorderLayout.CENTER);
								
								// icons
			}
		
		}
		public void setItemDetailsText (String text){
			this.itemDetailsText = text;
	}

}
