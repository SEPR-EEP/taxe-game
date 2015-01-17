package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.eep.taxe.GameClient.MoveEvent;
import com.eep.taxe.GameClient.Role;
import com.eep.taxe.GameClient.StatusItem;
import com.eep.taxe.GameClient.StatusResponse;
import com.eep.taxe.GameData;
import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Game;
import com.eep.taxe.models.Path;
import com.eep.taxe.models.Player;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Train;
import com.eep.taxe.models.Vertex;

/**
 * This is the Controller of the Game. All of the Game Logic related to the
 * user interaction goes here. This handles the events triggered by the interaction
 * of the user with the view and the events triggered by the client (moves received
 * from the opponent).
 */
public class GameController {

	private GameView	view		= null;
	private GameModel	model 		= null;
	
	private MapGraphics graphics	= null;
	
	private Path 		currentPath = null;
	
	/**
	 * These are the possible game states.
	 */
	private enum GameState {
		WAITING, 			// Waiting for other player to move,
		STANDBY, 			// My turn, still doing nothing,
		BUILDING_PATH, 		// Selected a train, I am now building a path
	}
	
	/**
	 * This contains the current state of the game.
	 */
	private GameState currentState = GameState.STANDBY;
	
	/**
	 * Stuff needed to keep track of the current state
	 */
	private Train	 		buildingTrain; 		// The train being used to build a path
	private	Vector<Vertex>	buildingVertices; 	// The list of vertices that have been selected
	 
	public GameController(GameView gameView, GameModel gameModel) {
		this.setView(gameView);
		this.setModel(gameModel);

		// If I'm master player, I start the game waiting for opponent to move
		if ( model.getMyRole() == Role.MASTER ) {
			this.currentState = GameState.WAITING;
		}
		
		// Add to the window title " - Playing as NAME (ROLE)"
		view.setTitle(
			view.getTitle() + 
			" - Playing as: " + model.getMyNickname() + " (" +
			(model.getMyRole() == Role.MASTER ? "Master" : "Slave") + ")"
		);
		
		// Instantiates a new MapGraphics object to take care of the map
		this.graphics = new MapGraphics(
				view.getMapLabel(),
				new MapMouseListener()
		);
		
		// Sets a MoveListener to handle the data received from the other player
		model.getClient().setOnMove(new MoveListener());
		
		//Add all of the Listeners for Events generated in the view
		this.view.addDetailsButtonActionListener(new DetailsButtonActionListener());
		this.view.addMenuButtonActionListener(new MenuButtonActionListener());
		
		// Draw the interface
		this.updateView();
		
	}
	
	
	
	
	/**
	 * This methods is called everything something happens (some data for the game
	 * is updated or the window is resized. This redraws the map - as this may have 
	 * disappeared - and updates all of the information shown on the view).
	 * -- Please keep as less expensive to compute as possible. --
	 */
	private void updateView() {
		
		if ( currentState == GameState.WAITING ) {
			System.out.println("Chill, you're waiting for the other player.");
		} else {
			System.out.println("It's your turn to play.");
		}
		
		// TODO Get and display game data
		this.graphics.drawMap();
	}
	
	private class DetailsButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class MenuButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.mainMapPanel.setVisible(false);
			
		}
	}
	
	private class MoveListener implements MoveEvent {
		@Override
		public void receive(GameData data) {
			
			// Receive and update Game data
			Game g = (Game) data;
			model.setData(g);
			
			// It is my turn
			currentState = GameState.STANDBY;
			updateView();
			
		}
	}
	
	/**
	 * End my turn, compute end of turn and send data to
	 * the server. If something goes wrong, just die.
	 */
	public void endTurn() {
		
		System.out.print("Computing end of turn... ");
		model.getData().endTurn();
		System.out.println("DONE.");
		
		System.out.print("Sending data to server... ");
		model.getClient().sendMove(
			model.getData(),
			new StatusResponse() {
				@Override
				public void response(StatusItem item) {
					if ( !item.ok ) {
						System.out.println("FATAL ERROR:\n " 
											+ item.error);
						System.exit(0);
					}
					System.out.println("DONE.");
				}
			}
		);
		this.currentState = GameState.WAITING;
	}
	


	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public GameModel getModel() {
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
	
	/**
	 * This is the class responsible for the management graphics of
	 * the game map. It draws the map, objects on the map and
	 * catches mouse events - recognising objects.
	 */
	private class MapGraphics {
		
		Graphics g;
		int height, width;
		
		public final double 	SCALE_FACTOR_X		= 10;
		public final double 	SCALE_FACTOR_Y		= 10;
		public final double 	OFFSET_X			= 0;
		public final double 	OFFSET_Y			= 0;
		
		public final double 	CLICK_PRECISION			= 1.2;
		public final double 	LABEL_RELATIVE_PADDING	= 1.4;
		
		public final double 	VERTEX_SIZE 		= 2;
		public final Color 		VERTEX_COLOR 		= new Color(0xFF0000);
		public final Color 		VERTEX_TEXT_COLOR 	= new Color(0x111111);
		
		public final Color EDGE_COLOR		 	= new Color(0x000000);
		public final Color EDGE_COLOR_BUILDING 	= new Color(0xFF0000);
		
		public final String BACKGROUND_IMAGE 	= "src/resources/MainMap.jpg";
		
		public final String TRAIN_MINE 			= "src/resources/MyTrain.png";
		public final String TRAIN_OPPONENT 		= "src/resources/OpponentTrain.png";
		public final int	TRAIN_SIZE	 		= 32;
		
		
		MapGraphics (JLabel jLabel, MouseListener mouseListener) {
			this.g = jLabel.getGraphics();
			
			jLabel.setSize(920, 560);

			height = (int) jLabel.getSize().getHeight();
			width  = (int) jLabel.getSize().getWidth();
			jLabel.paintComponents(this.g);
			
			
			// Enable text anti-aliasing
	        ((Graphics2D) this.g).setRenderingHint(
	                RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
	        );
	        
	        // Enable graphics anti-aaliasing
	        ((Graphics2D) this.g).setRenderingHint(
	        		RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON
	        );

	        jLabel.addMouseListener(mouseListener);
	        jLabel.addComponentListener(new ComponentListener() {

				@Override
				public void componentResized(ComponentEvent e) {
					drawMap();
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					drawMap();
				}

				@Override
				public void componentShown(ComponentEvent e) {
					drawMap();
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					
				}

	        	
	        });

		}
		
		private boolean drawing = false;
		
		public void drawMap() {
			if ( drawing ) {
				return;
			}
			drawing = true;
			this.drawBackgroudImage();
			this.drawEdges();
			this.drawVertices();
			this.drawTrains();
			drawing = false;
		}

		private void drawTrains() {
			
			Role myRole = model.getMyRole();
			

			Vector<Train> myTrains  = model.getData().getPlayerByRole(myRole).getTrains();
			Vector<Train> oppTrains = model.getData().getPlayerByRole(myRole == Role.MASTER ? Role.SLAVE : Role.MASTER).getTrains();

			Vector<Train> allTrains = new Vector<Train>(); allTrains.addAll(myTrains); allTrains.addAll(oppTrains);

			BufferedImage myTrainImage;
			BufferedImage oppTrainImage;
			try {
				myTrainImage 	= ImageIO.read(new File(TRAIN_MINE));
				oppTrainImage 	= ImageIO.read(new File(TRAIN_OPPONENT));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			for (Train t: allTrains) {
				
				boolean mine = myTrains.contains(t);
				Point p = getTrainCoordinates(t);
				if ( p == null ) {	// If train is not on the map
					continue;		// Ignore and draw the next train
				}

				g.drawImage(
					mine ? myTrainImage : oppTrainImage,
					(int) (x(p.getX()) + OFFSET_X - myTrainImage.getWidth()  / 2),
					(int) (y(p.getY()) + OFFSET_Y - myTrainImage.getHeight() / 2),
					(int) (x(p.getX()) + OFFSET_X + myTrainImage.getWidth()  / 2),
					(int) (y(p.getY()) + OFFSET_Y + myTrainImage.getHeight() / 2),
					0, 0,
					myTrainImage.getWidth(),
					myTrainImage.getHeight(),
					null
				);
				
			}
			
		}
		
		private Point getTrainCoordinates(Train t) {
			
			
			if ( t.getJourney() == null ) {
				// Train is not on the map, skip
				return null;
			}
			
			Edge edge = t.getJourney().getCurrentEdge();
			if ( edge == null ) {
				return null;
			}

			int 	Ax, Ay, Bx, By;
			float 	Tx,	Ty;


			Vertex A  = t.getJourney().getStartingVertexOfEdge(edge);
			Vertex B  = t.getJourney().getEndingVertexOfEdge  (edge);

			Ax = A.getX(); Ay = A.getY();
			Bx = B.getX(); By = B.getY();
			
			float Bweight = t.getJourney().getProgressOnEdge();
			float Aweight = 1 - Bweight;

			Tx = ( Ax * Aweight + Bx * Bweight );
			Ty = ( Ay * Aweight + By * Bweight );
			
			return new Point((int) Tx, (int) Ty);
			
		}

		public void drawBackgroudImage() {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(BACKGROUND_IMAGE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(
				img,
				(int) OFFSET_X,
				(int) OFFSET_Y, 
				width,
				height,
				0, 0,
				img.getWidth(), 
				img.getHeight(),
				null
			);
		}
		
		private void drawEdges() {
			
			// Create a set (to avoid repetitions)
			HashSet<Edge> edges = new HashSet<Edge>();
			// For each vertex, add all the edges
			for ( Vertex v: model.getData().getVertices() ) {
				edges.addAll(v.getEdges());
			}
			
			for ( Edge e: edges ) {
				
				if ( buildingVertices != null && (new Path(buildingVertices)).contains(e) ) {
					g.setColor(EDGE_COLOR_BUILDING);
				} else {
					g.setColor(EDGE_COLOR);
				}
				
				int x1, y1, x2, y2;
				x1 = (int) (x(e.getVertices().get(0).getX()) + OFFSET_X);
				y1 = (int) (y(e.getVertices().get(0).getY()) + OFFSET_Y);
				x2 = (int) (x(e.getVertices().get(1).getX()) + OFFSET_X);
				y2 = (int) (y(e.getVertices().get(1).getY()) + OFFSET_Y);
				
				g.drawLine(x1, y1, x2, y2);
				
			}
			
		}

		private void drawVertices() {
			for ( Vertex v: model.getData().getVertices() ) {
				
				// Draw the vertex
				g.setColor(VERTEX_COLOR);
				g.fillOval(
						(int) (x(v.getX()) - x(VERTEX_SIZE)/2 + OFFSET_X),
						(int) (y(v.getY()) - y(VERTEX_SIZE)/2 + OFFSET_Y),
						(int) x(VERTEX_SIZE),
						(int) y(VERTEX_SIZE)
				);
				
				if ( v instanceof Station ) {
					drawTextWithShadow(
							((Station) v).getName(),
							(int) (x(v.getX()) + OFFSET_X + VERTEX_SIZE/2 * SCALE_FACTOR_X * LABEL_RELATIVE_PADDING),
							(int) (y(v.getY()) + OFFSET_Y),
							VERTEX_TEXT_COLOR
					);
				}
								
			}		
		}	
		
		private void drawTextWithShadow(String string, int x, int y, Color textColor ) {
			g.setColor(textColor);
			g.drawString(string, x+1, y+1);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(string, x, y);
		}
		
		private int x(double px) {
			return (int) (px * SCALE_FACTOR_X);
		}
		
		private int y(double py) {
			return (int) (py * SCALE_FACTOR_Y);
		}
		
		/**
		 * Try and find a Vertex where the user has clicked.
		 * @param 	e	The Click event
		 * @return		Either a Vertex or null.
		 */
	    public Vertex findVertex(MouseEvent e) {
	        double cx = ( (double) e.getX() - OFFSET_X ) / SCALE_FACTOR_X;
	        double cy = ( (double) e.getY() - OFFSET_Y ) / SCALE_FACTOR_Y;
	        
	        for ( Vertex x: model.getData().getVertices() ) {
	        	
	        	/*
	        	 * Check if the click of coordinates (cx, cy) is inside
	        	 * the square with the vertex circle inscribed with center
	        	 * (x.getX(), x.getY()) and size VERTEX_SIZE * (2 * CLICK_PRECISION)
	        	 */
	        	if (
	        		cx >= ( x.getX() - VERTEX_SIZE/2 * CLICK_PRECISION ) &&
	        		cx <= ( x.getX() + VERTEX_SIZE/2 * CLICK_PRECISION ) &&
	        		cy >= ( x.getY() - VERTEX_SIZE/2 * CLICK_PRECISION ) &&
	        		cy <= ( x.getY() + VERTEX_SIZE/2 * CLICK_PRECISION )
	        	) {
	        		return x;
	        	}
	        		
	        }
	        return null;
	    }
	    
	    /**
		 * Try and find one of my trains where the user has clicked.
		 * @param 	e	The Click event
		 * @return		Either a Train or null.
		 */
	    public Train findMyTrain(MouseEvent e) {
	        double cx = ( (double) e.getX() - OFFSET_X ) / SCALE_FACTOR_X;
	        double cy = ( (double) e.getY() - OFFSET_Y ) / SCALE_FACTOR_Y;

	        for ( Train x: getPlayer().getTrains() ) {
	        	
	        	Point p = getTrainCoordinates(x);
	        	if ( p == null ) {
	        		continue;
	        	}
	        	
	        	/*
	        	 * Check if the click of coordinates (cx, cy) is inside
	        	 * (p.getX(), p.getY()) and size (IMAGE_SIZE) * (2 * CLICK_PRECISION)
	        	 */
	        	if (
	        		cx >= ( p.getX() - TRAIN_SIZE/2 * CLICK_PRECISION ) &&
	        		cx <= ( p.getX() + TRAIN_SIZE/2 * CLICK_PRECISION ) &&
	        		cy >= ( p.getY() - TRAIN_SIZE/2 * CLICK_PRECISION ) &&
	        		cy <= ( p.getY() + TRAIN_SIZE/2 * CLICK_PRECISION )
	        	) { 
	        		return x;
	        	}
	        }
	        return null;
	    }

	
	}
	
	
	/**
	 * For rapidity, gets the player playing on this instance
	 * @return 
	 */
	private Player getPlayer() {
		return model.getData().getPlayerByRole(
			model.getMyRole()
		);
	}
	
	/**
	 * For rapidity, gets the player playing on the other instance
	 * @return 
	 */
	private Player getOpponent() {
		return model.getData().getPlayerByRole(
			model.getMyRole() == Role.MASTER ? Role.SLAVE : Role.MASTER
		);
	}
	
	/**
	 * This method is called every time the player clicks somewhere 
	 * on the map. This method should - depending on the current state -
	 * address the click differently, trying to understand what's been
	 * clicked and what to do with it.
	 * @param e
	 */
	private void clickOnMap(MouseEvent e) {
		
		switch ( currentState ) {
		
		case WAITING:	// NOT MY TURN
			// Not my turn, do nothing.
			System.out.println("It's not your turn - you're not supposed to click anywhere. Ignoring.");
			break;
			
		case STANDBY:	// MY TURN, DOING NOTHING
			
			// If a click on a train, start building path
			Train t = graphics.findMyTrain(e);
			if ( t == null ) {
				break;
			}
			
			this.buildingTrain 		= t;
		
			// Add the vertex where the train is located
			this.buildingVertices 	= new Vector<Vertex>(); 
			this.buildingVertices.add ( t.getJourney().getLastVisitedVertex() );
			System.out.println("Journey starting at " + t.getJourney().getCurrentLastVertex().getVertexName() );
			
			this.currentState = GameState.BUILDING_PATH;
			
			break;
						
			
		case BUILDING_PATH:
			
            Vertex v = graphics.findVertex(e);
            if ( v == null ) {
            	break;
            }
            
            if ( v == this.buildingVertices.lastElement() ) {
            	// Clicked on the last element added, remove it
            	
            	System.out.println("Removed " + this.buildingVertices.lastElement());
            	this.buildingVertices.removeElementAt(this.buildingVertices.size() - 1);
            	
            	// If you removed the starting vertex, return to standby.
            	if ( this.buildingVertices.size() == 0 ) {
            		this.currentState 		= GameState.STANDBY;
            		this.buildingTrain 		= null;
            		this.buildingVertices	= null;
            		System.out.println("Journey cancelled - returning to standby.");
            	}
            	
            	break;
            }
            
            if ( !this.buildingVertices.add(v) ) {
            	view.showErrorMessage("Can't reach that vertex.");
            }

            System.out.println("Added vertex");
            System.out.println("Last: " + v.getVertexName());
            
			break;
			
		default:
			break;
		}
		
		/*System.out.println("Current building path --");
		if ( buildingVertices != null ) {
			for ( Edge e: buildingVertices ) {
				e.printVerticesNames();
			}
		}*/
		updateView();
		
	}
	
	private class MapMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		  
			clickOnMap(e);


		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}


}
