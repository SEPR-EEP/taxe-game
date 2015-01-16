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
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;

public class Connect extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";

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
					Connect frame = new Connect();
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
	public Connect() {			
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
		
		JPanel connect = new JPanel();
		connect.setBounds(0, 0, 784, 562);
		contentPane.add(connect);
		connect.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(593, 68, 140, 36);
		connect.add(comboBox);
		comboBox.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
		comboBox.setToolTipText("");
		comboBox.setMaximumRowCount(4);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"  Filter Games", "  \u25CF Sort by difficulty", "  \u25CF Sort by Creator", "  \u25CF Sort by Game Name"}));
		comboBox.setSelectedIndex(0);
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(Color.GRAY);
		
		
		JLabel lblHG = new JLabel("Host Game", SwingConstants.CENTER);
		lblHG.setBounds(593, 392, 140, 36);
		connect.add(lblHG);
		lblHG.setForeground(Color.WHITE);
		lblHG.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		
		JLabel hgbg = new JLabel("");
		hgbg.setBounds(593, 392, 140, 36);
		connect.add(hgbg);
		hgbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		
		JLabel lblBack = new JLabel("Back", SwingConstants.CENTER);
		lblBack.setBounds(593, 462, 140, 36);
		connect.add(lblBack);
		lblBack.setForeground(Color.WHITE);
		lblBack.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(593, 462, 140, 36);
		connect.add(label_1);
		label_1.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\btbg.jpg"));
		
		//BACKGOUND 
		JLabel background = new JLabel("New label");
		background.setBounds(0, 0, 784, 562);
		connect.add(background);
		background.setIcon(new ImageIcon("C:\\Users\\yd695\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\EGXEZJH4\\connect_-_Tunnel[1].jpg"));
		

		

		
		
	}
}
