package com.eep.taxe.mvc.game;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameView extends javax.swing.JFrame {

	private static final long serialVersionUID = -1784329706446539462L;
	
	private final int 		WIDTH  	= 1300;
	private final int 		HEIGHT 	= 720;
	private final String	TITLE	= "TAxE Game";

	public GameView() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new JLabel("This is the Game View"));

		this.setVisible(true);

	}
	
}
