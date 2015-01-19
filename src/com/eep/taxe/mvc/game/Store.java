package com.eep.taxe.mvc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.border.MatteBorder;
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


public final class Store extends JDialog {

	/**
	 * 
	 */
	
	private final int WIDTH =650;
	private final int HEIGHT =600;
	
	private JTextPane itemDetails_JTextPane;
	private JButton Buy_JButton;
	private JButton ReturnToGame_JButton;
	
	private TradeInSlot tradeInSlot;
	
	private MarketSlot powerUpSlot1;
	private MarketSlot powerUpSlot2;
	private MarketSlot powerUpSlot3;
	private MarketSlot powerUpSlot4;
	private MarketSlot powerUpSlot5;
		
	private MarketSlot trapsSlot1;
	private MarketSlot trapsSlot2;
	private MarketSlot trapsSlot3;
	private MarketSlot trapsSlot4;
	private MarketSlot trapsSlot5;
	
	private TrainSlot trainSlot1;
	private TrainSlot trainSlot2;
	private TrainSlot trainSlot3;
	private TrainSlot trainSlot4;
	private TrainSlot trainSlot5;
		
	private InventorySlot inventorySlot1;
	private InventorySlot inventorySlot2;
	private InventorySlot inventorySlot3;
	private InventorySlot inventorySlot4;
	private InventorySlot inventorySlot5;
	
	private TrainSlot trainInventSlot1;
	private TrainSlot trainInventSlot2;
	private TrainSlot trainInventSlot3;
	private TrainSlot trainInventSlot4;

	
	
	private String itemDetailsText;
	private Color lightColor = new Color(240,240,240);
	private Color darkColor = new Color(128,128,128);
	private JTextField metalCost_textField;
	private JTextField gold_textField;
	private Component rigidArea;
	private Component rigidArea_1;
	private Component rigidArea_2;
	
	
	public static void main(String[] args) {
		try {
			Store dialog = new Store();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocation(1300/2 - 650/2, 720/2 - 600/2);
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
			panel.setBorder(new MatteBorder(0, 5, 5, 5, (Color) darkColor));
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
				marketTitle_JtextField.setBackground(darkColor);
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
					detailsPanel_JPanel.setLayout(new MigLayout("", "[100px,grow,center]", "[450px,grow,center][grow,center]"));
					
					
					//  ITEM DETAILS PANEL ------------------------------
							JPanel detailsContainer_JPanel = new JPanel();
							detailsPanel_JPanel.add(detailsContainer_JPanel, "flowy,cell 0 0");
							
							detailsContainer_JPanel.setBackground(lightColor);
							detailsContainer_JPanel.setLayout(new MigLayout("", "[145px,grow]", "[250px][100,grow][100][30px]"));
							
							
							//  ITEM DETAILS ------------------------------
									
																	
									itemDetails_JTextPane = new JTextPane();
									detailsContainer_JPanel.add(itemDetails_JTextPane, "cell 0 0,alignx center,aligny top");
																	
									itemDetails_JTextPane.setEditable(false);
									itemDetails_JTextPane.setPreferredSize(new Dimension(WIDTH/4-45, HEIGHT*9/10));
									itemDetails_JTextPane.setBorder(new TitledBorder(new LineBorder(new Color(171,173,179), 2), "Item Details", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.SANS_SERIF, Font.BOLD, 11), new Color(171,173,179)));
									itemDetails_JTextPane.setMaximumSize(new Dimension(WIDTH/4-45, (HEIGHT*9/10)));
									itemDetails_JTextPane.setText(itemDetailsText);
									itemDetails_JTextPane.setFont(new Font("Sans Serif", Font.BOLD, 12));
									itemDetails_JTextPane.setAlignmentX(Component.BOTTOM_ALIGNMENT);
							
									JPanel costPanel_JPanel = new JPanel();
									costPanel_JPanel.setBorder(new TitledBorder(new LineBorder(new Color(171,173,179), 2), "Cost: ", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.SANS_SERIF, Font.BOLD, 11), new Color(171,173,179)));
									detailsContainer_JPanel.add(costPanel_JPanel, "cell 0 1,alignx center,aligny center");
									costPanel_JPanel.setLayout(new GridLayout(2, 2, 5, 8));
									
									Label metalCost_JLabel = new Label("Metal:");
									metalCost_JLabel.setPreferredSize(new Dimension(20, 10));
									metalCost_JLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
									costPanel_JPanel.add(metalCost_JLabel);
									
									metalCost_textField = new JTextField();
									metalCost_textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
									metalCost_textField.setPreferredSize(new Dimension(20, 10));
									metalCost_textField.setHorizontalAlignment(SwingConstants.CENTER);
									metalCost_textField.setBackground(new Color(128, 128, 128, 28));
									metalCost_textField.setBorder(null);
									metalCost_textField.setText("1314414");
									costPanel_JPanel.add(metalCost_textField);
									metalCost_textField.setColumns(10);
									
									Label goldCost_JLabel = new Label("Gold: ");
									goldCost_JLabel.setPreferredSize(new Dimension(20, 10));
									goldCost_JLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
									costPanel_JPanel.add(goldCost_JLabel);
									
									gold_textField = new JTextField();
									gold_textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
									gold_textField.setPreferredSize(new Dimension(20, 10));
									gold_textField.setHorizontalAlignment(SwingConstants.CENTER);
									gold_textField.setBackground(new Color(128, 128, 128, 28));
									gold_textField.setBorder(null);
									gold_textField.setText("636");
									costPanel_JPanel.add(gold_textField);
									gold_textField.setColumns(10);
									
									JPanel tradePanel = new JPanel();
									tradePanel.setBorder(new TitledBorder(new LineBorder(new Color(171,173,179), 2), "Trade for: ", TitledBorder.LEADING, TitledBorder.TOP, new Font(Font.SANS_SERIF, Font.BOLD, 11), new Color(171,173,179)));
									detailsContainer_JPanel.add(tradePanel, "flowx,cell 0 2,grow");
									tradePanel.setLayout(new BoxLayout(tradePanel, BoxLayout.PAGE_AXIS));
									
									tradeInSlot = new TradeInSlot (WIDTH, HEIGHT);
									tradeInSlot.setAlignmentX(Component.CENTER_ALIGNMENT);
									tradePanel.add(tradeInSlot);
									
							//  BUY BUTTON ---------------------------------------------------
									Buy_JButton = new JButton("Buy");
									detailsContainer_JPanel.add(Buy_JButton, "cell 0 3,alignx left,aligny top");
									
									Buy_JButton.setBounds(new Rectangle(15, 200, 110, 25));
									Buy_JButton.setPreferredSize(new Dimension(145, 28));
									Buy_JButton.setAlignmentX(Component.CENTER_ALIGNMENT);
									Buy_JButton.setBorderPainted(false);
									Buy_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
							
					//  RETURN TO GAME BUTTON ------------------------------
							ReturnToGame_JButton = new JButton("Return to Game");
							detailsPanel_JPanel.add(ReturnToGame_JButton, "cell 0 1");
							
							ReturnToGame_JButton.setPreferredSize(new Dimension(145, 28));
							ReturnToGame_JButton.setFont(new Font("Sans Serif", Font.BOLD, 11));
							ReturnToGame_JButton.setBounds(new Rectangle(15, 200, 110, 25));
							ReturnToGame_JButton.setBorderPainted(false);
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
								powerTitle_JTextField.setBackground(darkColor);
								powerTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
								powerTitle_JTextField.setForeground(new Color(255, 255, 255));
								powerTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
								powerTitle_JTextField.setText("Power-ups");
								
								
						//  POWER-UPS ICONS --------------------------------------		
								JPanel powerIcons_JPanel = new JPanel();
								powerPanel_JPanel.add(powerIcons_JPanel, BorderLayout.CENTER);
									
								powerIcons_JPanel.setBackground(lightColor);
								powerIcons_JPanel.setLayout(new GridLayout(0, 5, 0, 0));
									// icons
								powerUpSlot1 = new MarketSlot(WIDTH, HEIGHT);
								powerUpSlot1.setBorder(null);
								powerIcons_JPanel.add(powerUpSlot1);
								powerUpSlot2 = new MarketSlot(WIDTH, HEIGHT);
								powerUpSlot2.setBorder(null);
								powerIcons_JPanel.add(powerUpSlot2);
								powerUpSlot3 = new MarketSlot(WIDTH, HEIGHT);
								powerUpSlot3.setBorder(null);
								powerIcons_JPanel.add(powerUpSlot3);
								powerUpSlot4 = new MarketSlot(WIDTH, HEIGHT);
								powerUpSlot4.setBorder(null);
								powerIcons_JPanel.add(powerUpSlot4);
								powerUpSlot5 = new MarketSlot(WIDTH, HEIGHT);
								powerUpSlot5.setBorder(null);
								powerIcons_JPanel.add(powerUpSlot5);
							
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
							trapsTitle_JTextField.setBackground(darkColor);
							trapsTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
							trapsTitle_JTextField.setForeground(new Color(255, 255, 255));
							trapsTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
							trapsTitle_JTextField.setText("Traps");
							
			
						//  TRAPS ICONS -------------------------------------
							JPanel trapsIcons_JPanel = new JPanel();
							trapsPanel_JPanel.add(trapsIcons_JPanel, BorderLayout.CENTER);
								
							trapsIcons_JPanel.setBackground(lightColor);
							trapsIcons_JPanel.setLayout(new GridLayout(0, 5, 0, 0));
							// icons
							
							trapsSlot1 = new MarketSlot(WIDTH, HEIGHT);
							trapsSlot1.setBorder(null);
							trapsIcons_JPanel.add(trapsSlot1);
							trapsSlot2 = new MarketSlot(WIDTH, HEIGHT);
							trapsSlot2.setBorder(null);
							trapsIcons_JPanel.add(trapsSlot2);
							trapsSlot3 = new MarketSlot(WIDTH, HEIGHT);
							trapsSlot3.setBorder(null);
							trapsIcons_JPanel.add(trapsSlot3);
							trapsSlot4 = new MarketSlot(WIDTH, HEIGHT);
							trapsSlot4.setBorder(null);
							trapsIcons_JPanel.add(trapsSlot4);
							trapsSlot5 = new MarketSlot(WIDTH, HEIGHT);
							trapsSlot5.setBorder(null);
							trapsIcons_JPanel.add(trapsSlot5);
							
							
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
								trainsTitle_JTextField.setBackground(darkColor);
								trainsTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
								trainsTitle_JTextField.setForeground(new Color(255, 255, 255));
								trainsTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
								trainsTitle_JTextField.setText("Trains");
								
							//  TRAINS ICONS -------------------------------------								
								JPanel trainsIcons_JPanel = new JPanel();
								trainsPanel_JPanel.add(trainsIcons_JPanel, BorderLayout.CENTER);
								
								trainsIcons_JPanel.setBackground(lightColor);
								trainsIcons_JPanel.setLayout(new GridLayout(0, 5, 0, 0));
								// icons
								
								trainSlot1 = new TrainSlot(WIDTH, HEIGHT);
								trainsIcons_JPanel.add(trainSlot1);
								trainSlot1.setBorder(null);
								trainSlot2 = new TrainSlot(WIDTH, HEIGHT);
								trainsIcons_JPanel.add(trainSlot2);
								trainSlot2.setBorder(null);
								trainSlot3 = new TrainSlot(WIDTH, HEIGHT);
								trainsIcons_JPanel.add(trainSlot3);
								trainSlot3.setBorder(null);
								trainSlot4 = new TrainSlot(WIDTH, HEIGHT);
								trainsIcons_JPanel.add(trainSlot4);
								trainSlot4.setBorder(null);
								trainSlot5 = new TrainSlot(WIDTH, HEIGHT);
								trainsIcons_JPanel.add(trainSlot5);
								trainSlot5.setBorder(null);
								
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
								inventoryTitle_JTextField.setBackground(darkColor);
								inventoryTitle_JTextField.setHorizontalAlignment(SwingConstants.CENTER);
								inventoryTitle_JTextField.setForeground(new Color(255, 255, 255));
								inventoryTitle_JTextField.setFont(new Font("Sans Serif", Font.BOLD, 12));
								inventoryTitle_JTextField.setText("Inventory");
								
							//  INVENTORY ICONS -------------------------------------
								JPanel inventoryIcons_JPanel = new JPanel();
								inventoryIcons_JPanel.setBorder(null);
								inventoryPanel_JPanel.add(inventoryIcons_JPanel, BorderLayout.CENTER);
								
								inventoryIcons_JPanel.setBackground(lightColor);
									inventoryIcons_JPanel.setLayout(new BoxLayout(inventoryIcons_JPanel, BoxLayout.Y_AXIS));
									
									rigidArea_1 = Box.createRigidArea(new Dimension(650, 15));
									inventoryIcons_JPanel.add(rigidArea_1);
								
									JPanel inventoryPowerIcons = new JPanel();
									inventoryIcons_JPanel.add(inventoryPowerIcons);
									
									inventoryPowerIcons.setLayout(new GridLayout(0, 5, 5, 5));
									// icons
									
										inventorySlot1 = new InventorySlot(WIDTH, HEIGHT);
										inventorySlot1.setBorder(null);
										inventoryPowerIcons.add(inventorySlot1);
										inventorySlot2 = new InventorySlot(WIDTH, HEIGHT);
										inventorySlot2.setBorder(null);
										inventoryPowerIcons.add(inventorySlot2);
										inventorySlot3 = new InventorySlot(WIDTH, HEIGHT);
										inventorySlot3.setBorder(null);
										inventoryPowerIcons.add(inventorySlot3);
										inventorySlot4 = new InventorySlot(WIDTH, HEIGHT);
										inventorySlot4.setBorder(null);
										inventoryPowerIcons.add(inventorySlot4);
										inventorySlot5 = new InventorySlot(WIDTH, HEIGHT);
										inventorySlot5.setBorder(null);
										inventoryPowerIcons.add(inventorySlot5);
									
									rigidArea_2 = Box.createRigidArea(new Dimension(650, 15));
									inventoryIcons_JPanel.add(rigidArea_2);
								
									JPanel inventoryTrainIcons_JPanel = new JPanel();
									inventoryIcons_JPanel.add(inventoryTrainIcons_JPanel);
									
									inventoryTrainIcons_JPanel.setLayout(new GridLayout(0, 4, 0, 0));
									
										trainInventSlot1 = new TrainSlot(WIDTH, HEIGHT);
										trainInventSlot1.setBorder(null);
										inventoryTrainIcons_JPanel.add(trainInventSlot1);
										trainInventSlot2 = new TrainSlot(WIDTH, HEIGHT);
										trainInventSlot2.setBorder(null);
										inventoryTrainIcons_JPanel.add(trainInventSlot2);
										trainInventSlot3 = new TrainSlot(WIDTH, HEIGHT);
										trainInventSlot3.setBorder(null);
										inventoryTrainIcons_JPanel.add(trainInventSlot3);
										trainInventSlot4 = new TrainSlot(WIDTH, HEIGHT);
										trainInventSlot4.setBorder(null);
										inventoryTrainIcons_JPanel.add(trainInventSlot4);
										
										rigidArea = Box.createRigidArea(new Dimension(650, 15));
										inventoryIcons_JPanel.add(rigidArea);
								
								
			}
		
		}
	
		public void setItemDetailsText (String text){
			this.itemDetailsText = text;
	}

}
