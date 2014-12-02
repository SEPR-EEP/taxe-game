package com.eep.taxe.mvc.game;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.eep.taxe.mvc.menu.MenuView2;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;

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
			topPanel.setPreferredSize(new Dimension(WIDTH, WIDTH/16));
			topPanel.setBackground(Color.PINK);
//			topPanel.setOpaque(false);

		
			JPanel leftPanel = new JPanel();
			leftPanel.setPreferredSize(new Dimension(WIDTH/8, HEIGHT));
			leftPanel.setBackground(Color.GREEN);
//			topPanel.setOpaque(false);

		
			gameMenuPanel.add(leftPanel, BorderLayout.WEST);
			gameMenuPanel.add(topPanel, BorderLayout.NORTH);
			gameMenuPanel.add(infoPanel, BorderLayout.EAST);
				inventoryContainer.add(inventoryPanel, BorderLayout.SOUTH);
				inventoryAndMapPanel.add(inventoryContainer);
				inventoryAndMapPanel.add(miniMapPanel);
			gameMenuPanel.add(inventoryAndMapPanel, BorderLayout.SOUTH);
		mainMapPanel.add(gameMenuPanel);
		getContentPane().add(mainMapPanel);
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(getClass().getResource("resources/Railroad Tacks.jpg")));
		background.setSize(784, 562);
		background.setLocation(0, 0);
		getContentPane().add(background);
		

		this.setVisible(true);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView frame = new GameView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
