package com.eep.taxe.mvc.menu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuView extends javax.swing.JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";

	private JPanel menu 	= null;
	private JPanel lobby	= null;
	
	public MenuView() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(0, 3));
		
		this.menu 	= this.createMainMenu();
		this.lobby	= this.createLobby();
		
		this.showMainMenu();
		
		this.setVisible(true);

	}
	
	private JPanel createMainMenu() {
		JPanel j = new JPanel(new GridLayout(3,0));
		//this.removeAll();
		j.add(new JLabel("This is the Menu View"));
		JButton b = new JButton("Start Game");
		j.add(b);	
		return j;
	}
	
	private JPanel createLobby() {
		JPanel j = new JPanel(new GridLayout(0,2));
		j.add(new JLabel("This is the Lobby View"));
		return j;
	}
	
	protected void showMainMenu() {
		this.remove(lobby);
		this.add(menu);
	}
	
	protected void showLobby() {
		this.remove(menu);
		this.add(lobby);
		
	}
	
}
