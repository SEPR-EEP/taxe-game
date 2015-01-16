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

public class MenuView3 extends JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Main Menu";

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
					MenuView3 frame = new MenuView3();
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
	public MenuView3() {			
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
		
		//quit button
		JLabel lblQuit = new JLabel("QUIT", SwingConstants.CENTER);
		lblQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuView3.dispatchEvent(new WindowEvent(MenuView2, WindowEvent.WINDOW_CLOSING));
			}
		});
		lblQuit.setForeground(Color.WHITE);
		lblQuit.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		lblQuit.setBounds(298, 404, 181, 36);
		contentPane.add(lblQuit);
		
		JLabel quitbuttonbg = new JLabel("");
		quitbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\BLACK.png"));
		quitbuttonbg.setLocation(new Point(224, 280));
		quitbuttonbg.setBounds(298, 440, 181, 36);
		contentPane.add(quitbuttonbg);
		
		//set location
		
		//SET Button
		JLabel lblSG = new JLabel("Start Game", SwingConstants.CENTER);
		lblSG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame = new JFrame("new frame");
			       frame.setVisible(true);
			}
		});
		lblSG.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		lblSG.setForeground(Color.WHITE);
		lblSG.setBounds(298, 104, 182, 36);
		contentPane.add(lblSG);
		lblSG.setSize(enterbuttonbg.get, height);
		
		JLabel SGbuttonbg = new JLabel("");
		SGbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\BLACK.png"));
		SGbuttonbg.setBounds(298, 83, 181, 36);
		contentPane.add(SGbuttonbg);
		
		
		JLabel lblHTP = new JLabel("How to play", SwingConstants.CENTER);
		lblHTP.setForeground(Color.WHITE);
		lblHTP.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		lblHTP.setBounds(298, 202, 181, 36);
		contentPane.add(lblHTP);
		
		JLabel HTPbuttonbg = new JLabel("");
		HTPbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\BLACK.png"));
		HTPbuttonbg.setBackground(Color.BLACK);
		HTPbuttonbg.setLocation(new Point(298, 144));
		HTPbuttonbg.setBounds(298, 202, 181, 36);
		contentPane.add(HTPbuttonbg);
		
		JLabel lblC = new JLabel("Credits", SwingConstants.CENTER);
		lblC.setForeground(Color.WHITE);
		lblC.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 18));
		lblC.setBounds(298, 305, 181, 36);
		contentPane.add(lblC);
		
		JLabel Cbuttonbg = new JLabel("");
		Cbuttonbg.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\BLACK.png"));
		Cbuttonbg.setLocation(new Point(298, 144));
		Cbuttonbg.setBackground(Color.BLACK);
		Cbuttonbg.setBounds(298, 321, 181, 36);
		contentPane.add(Cbuttonbg);
		
		
		
		///set  button location
		int bwe = SGbuttonbg.getWidth();
		int bhe = SGbuttonbg.getHeight();
		lblSG.setSize(bwe, bhe);
		SGbuttonbg.setLocation(lblSG.getLocation());
		
		int bwq = quitbuttonbg.getWidth();
		int bhq = quitbuttonbg.getHeight();
		lblQuit.setSize(bwq, bhq);
		quitbuttonbg.setLocation(lblQuit.getLocation());
		
		int bwh = HTPbuttonbg.getWidth();
		int bhh = HTPbuttonbg.getHeight();
		lblHTP.setSize(bwh, bhh);
		HTPbuttonbg.setLocation(lblHTP.getLocation());
		
		int bwc = Cbuttonbg.getWidth();
		int bhc = Cbuttonbg.getHeight();
		lblC.setSize(bwc, bhc);
		Cbuttonbg.setLocation(lblC.getLocation());
		
		//BACKGOUND 
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon("H:\\SEPR Pratical\\img\\B1a-Railroad Tracks(1).jpg"));
		background.setSize(784, 562);
		background.setLocation(0, 0);
		contentPane.add(background);
		

		

		
		
	}
}
