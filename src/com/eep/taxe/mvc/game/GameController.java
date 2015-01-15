package com.eep.taxe.mvc.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.eep.taxe.GameClient.Role;
import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Vertex;

public class GameController {

	private GameView	view		= null;
	private GameModel	model 		= null;
	
	private MapGraphics graphics	= null;
	 
	public GameController(GameView gameView, GameModel gameModel) {
		this.setView(gameView);
		this.setModel(gameModel);

		// Add to the window title " - Playing as NAME (ROLE)"
		view.setTitle(
			view.getTitle() + 
			" - Playing as: " + model.getMyNickname() + " (" +
			(model.getMyRole() == Role.MASTER ? "Master" : "Slave") + ")"
		);
		
		this.graphics = new MapGraphics(
				view.getGameMenuPanel(),
				new MapMouseListener()
		);
		this.graphics.drawMap();
		
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
		public final double 	OFFSET_X			= 30;
		public final double 	OFFSET_Y			= 30;
		
		public final double CLICK_PRECISION		= 1.2;
		
		public final double 	VERTEX_SIZE 	= 2;
		public final Color VERTEX_COLOR 		= new Color(0xFF0000);
		public final Color VERTEX_TEXT_COLOR 	= new Color(0xAA0000);
		
		public final Color EDGE_COLOR		 	= new Color(0x000000);
		
		public final String BACKGROUND_IMAGE 	= "src/resources/MainMap.jpg";
		
		
		MapGraphics (JPanel container, MouseListener mouseListener) {
			this.g = container.getGraphics();
			height = (int) container.getSize().getHeight();
			width  = (int) container.getSize().getWidth();
			container.paintComponents(this.g);
			
			// Enable text antialiasing
	        ((Graphics2D) this.g).setRenderingHint(
	                RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
	        );
	        
	        // Enable graphics antialiasing
	        ((Graphics2D) this.g).setRenderingHint(
	        		RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON
	        );

	        container.addMouseListener(mouseListener);
		}
		
		public void drawMap() {
			this.drawBackgroudImage();
			this.drawVertices();
			this.drawEdges();
		}

		public void drawBackgroudImage() {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(BACKGROUND_IMAGE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage( img, 0, 0, null );
		}
		
		private void drawEdges() {
			
			// Create a set (to avoid repetitions)
			HashSet<Edge> edges = new HashSet<Edge>();
			// For each vertex, add all the edges
			for ( Vertex v: model.getData().getVertices() ) {
				edges.addAll(v.getEdges());
			}
			
			g.setColor(EDGE_COLOR);
			for ( Edge e: edges ) {
				
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
				g.drawOval(
						(int) (x(v.getX()) - x(VERTEX_SIZE)/2 + OFFSET_X),
						(int) (y(v.getY()) - y(VERTEX_SIZE)/2 + OFFSET_Y),
						(int) x(VERTEX_SIZE),
						(int) y(VERTEX_SIZE)
				);
				
				if ( v instanceof Station ) {
					g.setColor(VERTEX_TEXT_COLOR);
					g.drawString(
							((Station) v).getName(),
							(int) (x(v.getX()) + OFFSET_X),
							(int) (y(v.getY()) + OFFSET_Y)
					);
				}
								
			}		
		}	
		
		private int x(double px) {
			return (int) (px * SCALE_FACTOR_X);
		}
		
		private int y(double py) {
			return (int) (py * SCALE_FACTOR_Y);
		}
		
	    public Vertex clickOnVertex(MouseEvent e) {
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
		  System.out.println("Click " + e.getX() + ", " + e.getY());
		  
            Vertex c = graphics.clickOnVertex(e);

            if ( c != null ) {
                if ( c instanceof Station ) {
                    System.out.println("Click on station: " + ((Station) c).getName());
                } else {
                    System.out.println("Click on a Junction");
                }
            } else {
                System.out.println("Clicked nowhere");
            }

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
