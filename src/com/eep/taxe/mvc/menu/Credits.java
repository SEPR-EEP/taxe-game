package com.eep.taxe.mvc.menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Credits extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Credits";

	private JPanel menu 	= null;
	private JPanel lobby	= null;
	
	
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Credits frame = new Credits();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Credits() {			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//SCREEN SIZE
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.lobby	= this.createLobby();
		contentPane.setLayout(null);
		
		JLabel lblC = new JLabel("Credits", SwingConstants.CENTER);
		lblC.setForeground(Color.BLACK);
		lblC.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
		lblC.setBounds(319, 74, 181, 36);
		contentPane.add(lblC);
		lblC.setSize(bwc, bhc);
		
		//BACKGOUND 
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\XZHU1TB6\\C_D(g)-Railroad_Tracks[1].jpg"));
		background.setSize(784, 562);
		background.setLocation(0, 0);
		contentPane.add(background);
		

		

		
		
	}
}
