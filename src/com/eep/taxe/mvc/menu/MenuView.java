package com.eep.taxe.mvc.menu;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.eep.taxe.utils.RunnableArgs;

public class MenuView extends javax.swing.JFrame {

	private static final long serialVersionUID = 6149911984550980990L;

	private final int 		WIDTH  	= 800;
	private final int 		HEIGHT 	= 600;
	private final String	TITLE	= "Game Menu";

	private JPanel menu 	= null;
	private JPanel lobby	= null;
	
	private	JButton mainButton;
	private	JButton creditsButton;
	private	JButton backButton;
	private	JButton createButton;
	private JTable  gameTable;
	
	public MenuView() {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(0, 1));
		
		this.menu 	= this.createMainMenu();
		this.lobby	= this.createLobby();
		
		this.showMainMenu();
		
		this.setVisible(true);

	}

	public void addMainButtonListener(ActionListener buttonListener) {
		mainButton.addActionListener(buttonListener);
	}
	
	public void addCreditsButtonListener(ActionListener buttonListener) {
		creditsButton.addActionListener(buttonListener);
	}
	
	public void addBackButtonListener(ActionListener buttonListener) {
		backButton.addActionListener(buttonListener);
	}
	
	public void addCreateButtonListener(ActionListener buttonListener) {
		createButton.addActionListener(buttonListener);
	}
	
	public void addTableMouseListener(MouseAdapter mouseListener) {
		gameTable.addMouseListener(mouseListener);
	}
	
	
	
	private JPanel createMainMenu() {
		JPanel j = new JPanel(new GridLayout(3,0));
		j.add(new JLabel("This is the Menu View"));
		
		mainButton = new JButton("Start Game");
		j.add(mainButton);	
		
		creditsButton = new JButton("Credits");
		j.add(creditsButton);
		
		return j;
	}
	
	private JPanel createLobby() {
		JPanel j = new JPanel(new GridLayout(2,2));
		j.add(new JLabel("This is the Lobby View"));
		
		DefaultTableModel model = new DefaultTableModel(){
			private static final long serialVersionUID = -6816770724270875081L;
			// This is to make the table non-editable
			@Override
			public boolean isCellEditable(int row, int column) {
		       return false;
			}
		};
		model.addColumn("ID");
		model.addColumn("Game Name");
		model.addColumn("Difficulty");
		model.addColumn("Creation date");
		
		gameTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(gameTable);
		gameTable.setFillsViewportHeight(true);
		
		j.add(scrollPane);
		
		createButton = new JButton("Create New Game");
		j.add(createButton);
		
		backButton = new JButton("Back to Main Menu");
		j.add(backButton);

		return j;
	}
	
	protected void showMainMenu() {
		this.remove(lobby);
		this.add(menu);
		this.pack();
		this.validate();
	}
	
	protected void showLobby() {
		this.remove(menu);
		this.add(lobby);
		this.pack();
		this.validate();
	}

	public void emptyGameList() {
		DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
		int n = model.getRowCount();
		for (int i = n - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	public void addGameToList(String id, String name, int difficulty, Date created) {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
		model.addRow(new Object[]{
			id, name,
			difficulty, created.toString()
		});
		
	}

	public void askForName(RunnableArgs runnable) {
		String s = (String)JOptionPane.showInputDialog(
				this,
        	"What is your name?",
        	"Name",
        	JOptionPane.PLAIN_MESSAGE,
        	null,
        	null,
			"ham"
        );
		runnable.run(s);
	}
	
	public void askForDifficulty(RunnableArgs runnable) {
		Object[] possibleValues = { "1", "2", "3" };
		String s = (String)JOptionPane.showInputDialog(
				this,
        	"Select difficulty?",
        	"Difficulty",
        	JOptionPane.PLAIN_MESSAGE,
        	null,
        	possibleValues,
			possibleValues[0]
        );
		runnable.run(s);
	}
	
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    "Game error",
			    JOptionPane.ERROR_MESSAGE);
	}	
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this,
			    message,
			    "Game Info",
			    JOptionPane.PLAIN_MESSAGE);
	}

	public String getGameAtRow(int row) {
		return (String) gameTable.getValueAt(row, 0);
	}
	
}
