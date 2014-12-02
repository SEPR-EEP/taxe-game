package com.eep.taxe.mvc.game;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
			inventoryAndMapPanel.setLayout(new GridBagLayout());
			inventoryAndMapPanel.setPreferredSize(new Dimension(WIDTH, WIDTH/8));
//			inventoryAndMapPanel.setOpaque(false);
			
				JPanel inventoryPanel = new JPanel();
				inventoryPanel.setPreferredSize(new Dimension(WIDTH - WIDTH/6, WIDTH/32));
				inventoryPanel.setBackground(Color.CYAN);

			
				JPanel miniMapPanel = new JPanel();
				miniMapPanel.setBackground(Color.MAGENTA);
		
			JPanel topPanel = new JPanel();
			topPanel.setPreferredSize(new Dimension(WIDTH, WIDTH/32));
			topPanel.setBackground(Color.PINK);
//			topPanel.setOpaque(false);

		
			JPanel leftPanel = new JPanel();
			leftPanel.setPreferredSize(new Dimension(WIDTH/8, HEIGHT));
			leftPanel.setBackground(Color.GREEN);
//			topPanel.setOpaque(false);

		
			gameMenuPanel.add(leftPanel, BorderLayout.WEST);
			gameMenuPanel.add(topPanel, BorderLayout.NORTH);
			gameMenuPanel.add(infoPanel, BorderLayout.EAST);

			gameMenuPanel.add(inventoryAndMapPanel, BorderLayout.SOUTH);
		mainMapPanel.add(gameMenuPanel);
		getContentPane().add(mainMapPanel);
		
		
		

		this.setVisible(true);

	}
}
