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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class MenuView0 extends JFrame {
	
	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";

	private JPanel menu 	= null;
	private JPanel lobby	= null;
	
	private JPanel contentPane;
	private JTextField usernamebox;
	
	private JLabel lblEnter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView2 frame = new MenuView2();
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
	public void MenuView3() {
		
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
		
		//Enter Button
		lblEnter = new JLabel("ENTER", SwingConstants.CENTER);
		lblEnter.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		lblEnter.setForeground(Color.WHITE);
		lblEnter.setBounds(234, 318, 140, 36);
		contentPane.add(lblEnter);
		lblEnter.setSize(enterbuttonbg.get, height);
		
		JLabel enterbuttonbg = new JLabel("");
		enterbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\BLACK.png"));
		enterbuttonbg.setBounds(224, 274, 140, 36);
		contentPane.add(enterbuttonbg);
		
		///set  button location
		int bw = enterbuttonbg.getWidth();
		int bh = enterbuttonbg.getHeight();
		lblEnter.setSize(bw, bh);
		lblEnter.setLocation(enterbuttonbg.getLocation());
		
		//username
		usernamebox = new JTextField();
		usernamebox.setForeground(new Color(0, 0, 0));
		usernamebox.setBounds(224, 202, 335, 36);
		contentPane.add(usernamebox);
		usernamebox.setColumns(10);
		
		String username = usernamebox.getText();
		
		JLabel askusername = new JLabel("Please enter a user name!");
		askusername.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
		askusername.setBounds(220, 101, 343, 35);
		contentPane.add(askusername);
		
		//BACKGROUND 
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(getClass().getResource("/resources/RailroadTracks.jpg")));
		background.setSize(784, 562);
		background.setLocation(0, 0);
		contentPane.add(background);
	}
	
	public void addEnterLabelListener(MouseAdapter mouseListener) {
		lblEnter.addMouseListener(mouseListener);
	}
	
	
	
}
