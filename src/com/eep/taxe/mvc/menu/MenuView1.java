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
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuView1 extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";

	private JPanel menu 	= null;
	private JPanel lobby	= null;
	
	
	
	
	private JPanel contentPane;
	private JTextField usernamebox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView1 frame = new MenuView1();
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
	public MenuView1() {			
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
		
		
		//enter game
		JPanel EnterGame = new JPanel();
		EnterGame.setBounds(0, 0, 784, 562);
		contentPane.add(EnterGame);
		EnterGame.setLayout(null);
		
		JLabel lblQuit = new JLabel("QUIT", SwingConstants.CENTER);
		lblQuit.setBounds(419, 280, 140, 36);
		EnterGame.add(lblQuit);
		lblQuit.setForeground(Color.WHITE);
		lblQuit.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		
		JLabel quitbuttonbg = new JLabel("");
		quitbuttonbg.setBounds(419, 280, 140, 36);
		EnterGame.add(quitbuttonbg);
		quitbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
	
		JLabel lblEnter = new JLabel("ENTER", SwingConstants.CENTER);
		lblEnter.setBounds(224, 280, 140, 36);
		EnterGame.add(lblEnter);
		lblEnter.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		lblEnter.setForeground(Color.WHITE);
		
		JLabel enterbuttonbg = new JLabel("");
		enterbuttonbg.setBounds(224, 280, 140, 36);
		EnterGame.add(enterbuttonbg);
		enterbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		
		
				//username
				usernamebox = new JTextField();
				usernamebox.setBounds(224, 202, 335, 36);
				EnterGame.add(usernamebox);
				usernamebox.setForeground(new Color(0, 0, 0));
				usernamebox.setColumns(10);
				
				JLabel askusername = new JLabel("Please enter a user name!");
				askusername.setBounds(220, 104, 343, 35);
				EnterGame.add(askusername);
				askusername.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
		
	
		JLabel EnterGamebg = new JLabel("");
		EnterGamebg.setBounds(0, 0, 784, 562);
		EnterGame.add(EnterGamebg);
		EnterGamebg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\B1a-Railroad Tracks(1).jpg"));
		
		String username = usernamebox.getText();
		
		
	
		//main menu
		JPanel MainMenu = new JPanel();
		MainMenu.setLayout(null);
		MainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		MainMenu.setBounds(0, 0, 784, 562);
		contentPane.add(MainMenu);
		
		JLabel label = new JLabel("QUIT", SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		label.setBounds(298, 404, 181, 36);
		MainMenu.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_1.setLocation(new Point(224, 280));
		label_1.setBounds(298, 404, 181, 36);
		MainMenu.add(label_1);
		
		JLabel label_2 = new JLabel("Start Game", SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		label_2.setBounds(298, 104, 181, 36);
		MainMenu.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_3.setLocation(new Point(298, 104));
		label_3.setBounds(298, 104, 181, 36);
		MainMenu.add(label_3);
		
		JLabel label_4 = new JLabel("How to play", SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		label_4.setBounds(298, 202, 181, 36);
		MainMenu.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_5.setLocation(new Point(298, 144));
		label_5.setBackground(Color.BLACK);
		label_5.setBounds(298, 202, 181, 36);
		MainMenu.add(label_5);
		
		JLabel label_6 = new JLabel("Credits", SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		label_6.setBounds(298, 305, 181, 36);
		MainMenu.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_7.setLocation(new Point(298, 144));
		label_7.setBackground(Color.BLACK);
		label_7.setBounds(298, 305, 181, 36);
		MainMenu.add(label_7);
		
		JLabel Mainmenubg = new JLabel("");
		Mainmenubg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\B1a-Railroad Tracks(1).jpg"));
		Mainmenubg.setBounds(0, 0, 784, 562);
		MainMenu.add(Mainmenubg);
		
		
		//credits
		JPanel Credits = new JPanel();
		Credits.setBounds(0, 0, 784, 562);
		contentPane.add(Credits);
		Credits.setLayout(null);
		Credits.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label_9 = new JLabel("Credits", SwingConstants.CENTER);
		label_9.setForeground(Color.BLACK);
		label_9.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
		label_9.setBounds(319, 74, 181, 36);
		Credits.add(label_9);
		
		JLabel Creditsbg = new JLabel("");
		Creditsbg.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\Z8QQ1A7J\\C_D(g)-Railroad_Tracks[1].jpg"));
		Creditsbg.setBounds(0, 0, 784, 562);
		Credits.add(Creditsbg);
		
		
		// how to play
		JPanel HTP = new JPanel();
		HTP.setLayout(null);
		HTP.setBorder(new EmptyBorder(5, 5, 5, 5));
		HTP.setBounds(0, 0, 784, 562);
		contentPane.add(HTP);
		
		JLabel label_11 = new JLabel("How To Play", SwingConstants.CENTER);
		label_11.setForeground(Color.BLACK);
		label_11.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 30));
		label_11.setBounds(319, 74, 181, 36);
		HTP.add(label_11);
		
		JLabel HTPbg = new JLabel("");
		HTPbg.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\7FG5I3HK\\C_D(g)-Railroad_Tracks[1].jpg"));
		HTPbg.setBounds(0, 0, 784, 562);
		HTP.add(HTPbg);

		

		//connect
		JPanel Connect = new JPanel();
		Connect.setLayout(null);
		Connect.setBounds(0, 0, 784, 562);
		contentPane.add(Connect);
		
		JComboBox FilterGame = new JComboBox();
		FilterGame.setToolTipText("");
		FilterGame.setModel(new DefaultComboBoxModel(new String[] {"  Filter Games", "  \u25CF Sort by difficulty", "  \u25CF Sort by Creator"}));
		FilterGame.setSelectedIndex(0);
		FilterGame.setMaximumRowCount(4);
		FilterGame.setForeground(Color.BLACK);
		FilterGame.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
		FilterGame.setBackground(Color.DARK_GRAY);
		FilterGame.setBounds(593, 68, 140, 36);
		Connect.add(FilterGame);
		
		JLabel label_18 = new JLabel("Host Game", SwingConstants.CENTER);
		label_18.setForeground(Color.WHITE);
		label_18.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		label_18.setBounds(593, 392, 140, 36);
		Connect.add(label_18);
		
		JLabel label_19 = new JLabel("");
		label_19.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_19.setBounds(593, 392, 140, 36);
		Connect.add(label_19);
		
		JLabel label_20 = new JLabel("Back", SwingConstants.CENTER);
		label_20.setForeground(Color.WHITE);
		label_20.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		label_20.setBounds(593, 462, 140, 36);
		Connect.add(label_20);
		
		JLabel label_21 = new JLabel("");
		label_21.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_21.setBounds(593, 462, 140, 36);
		Connect.add(label_21);
		
		JLabel Connectbg = new JLabel("");
		Connectbg.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\ONS17EGS\\connect_-_Tunnel[1].jpg"));
		Connectbg.setBounds(0, 0, 784, 562);
		Connect.add(Connectbg);
		
		
		//create game
		JPanel CreatGame = new JPanel();
		CreatGame.setBounds(0, 0, 784, 562);
		contentPane.add(CreatGame);
		CreatGame.setLayout(null);
		
		JComboBox Difficulty = new JComboBox();
		Difficulty.setToolTipText("");
		Difficulty.setModel(new DefaultComboBoxModel(new String[] {"  Choose difficulty", "  \u25CF  Easy", "  \u25CF  Medium", "  \u25CF  Hard"}));
		Difficulty.setSelectedIndex(0);
		Difficulty.setMaximumRowCount(4);
		Difficulty.setForeground(Color.BLACK);
		Difficulty.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 16));
		Difficulty.setBackground(Color.DARK_GRAY);
		Difficulty.setBounds(302, 218, 181, 35);
		CreatGame.add(Difficulty);
		
		JLabel lblCancel = new JLabel("Cancel ", SwingConstants.CENTER);
		lblCancel.setForeground(Color.WHITE);
		lblCancel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		lblCancel.setBounds(179, 293, 140, 36);
		CreatGame.add(lblCancel);
		
		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_15.setLocation(new Point(593, 392));
		label_15.setBounds(176, 285, 140, 36);
		CreatGame.add(label_15);
		
		JLabel lblCreate = new JLabel("Create", SwingConstants.CENTER);
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		lblCreate.setBounds(467, 293, 140, 36);
		CreatGame.add(lblCreate);
		
		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		label_16.setLocation(new Point(593, 392));
		label_16.setBounds(480, 285, 140, 36);
		CreatGame.add(label_16);
		
		JLabel CreatGamebg = new JLabel("");
		CreatGamebg.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\EGXEZJH4\\connect_-_Tunnel[1].jpg"));
		CreatGamebg.setBounds(0, 0, 784, 562);
		CreatGame.add(CreatGamebg);
		

		// waiting
		JPanel Waiting = new JPanel();
		Waiting.setBounds(0, 0, 784, 562);
		contentPane.add(Waiting);
		Waiting.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please wait for another player to join!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 32));
		lblNewLabel.setBounds(119, 33, 502, 38);
		Waiting.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel Waitingingbg = new JLabel("");
		Waitingingbg.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\XZHU1TB6\\connect_-_Tunnel[1].jpg"));
		Waitingingbg.setBounds(0, 0, 784, 562);
		Waiting.add(Waitingingbg);
		
		

		
		///set  button location
	
		int bwe = enterbuttonbg.getWidth();
		int bhe = enterbuttonbg.getHeight();
		lblEnter.setSize(bwe, bhe);
		enterbuttonbg.setLocation(lblEnter.getLocation());
		
		int bwq = quitbuttonbg.getWidth();
		int bhq = quitbuttonbg.getHeight();	
		lblQuit.setSize(bwq, bhq);
		quitbuttonbg.setLocation(lblQuit.getLocation());
		
		int bwsg = label_3.getWidth();
		int bhsg = label_3.getHeight();
		label_2.setSize(bwsg, bhsg);
		label_3.setLocation(label_2.getLocation());
		
		int bwc = label_5.getWidth();
		int bhc = label_5.getHeight();
		label_4.setSize(bwc, bhc);
		label_5.setLocation(label_4.getLocation());
		
		int bwh = label_7.getWidth();
		int bhh = label_7.getHeight();
		label_6.setSize(bwh, bhh);
		label_7.setLocation(label_6.getLocation());
		
		int bwhg = label_19.getWidth();
		int bhhg = label_19.getHeight();
		label_18.setSize(bwhg, bhhg);
		label_19.setLocation(label_18.getLocation());
						
		int bwqq = label_1.getWidth();
	    int bhqq = label_1.getHeight();
		label.setSize(bwqq, bhqq);
		label_1.setLocation(label.getLocation());
		
		int bwca = label_15.getWidth();
		int bhca = label_15.getHeight();
		lblCancel.setSize(bwca, bhca);
		label_15.setLocation(lblCancel.getLocation());
		
		int bwcr = label_16.getWidth();
		int bhcr = label_16.getHeight();
		lblCreate.setSize(bwcr, bhcr);
		label_16.setLocation(lblCreate.getLocation());
		
		int bwb = label_21.getWidth();
		int bhb = label_21.getHeight();
		label_20.setSize(bwb, bhb);
		label_21.setLocation(label_20.getLocation());	
		
	}
}
