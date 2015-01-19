package com.eep.taxe.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Station;
import com.eep.taxe.models.Vertex;

public class MapGraphics {
	
	Graphics g;
	
	int h, w;
	
	private final int 		SCALE_FACTOR_X		= 10;
	private final int 		SCALE_FACTOR_Y		= 10;
	private final double	SCALE_FACTOR_COMP	= Math.sqrt(Math.pow(SCALE_FACTOR_X, 2) + Math.pow(SCALE_FACTOR_Y, 2));
	private final int 		OFFSET_X			= 10;
	private final int 		OFFSET_Y			= 50;
	
	private final int 	VERTEX_SIZE 		= 2;
	private final Color VERTEX_COLOR 		= new Color(0xFF0000);
	private final Color VERTEX_TEXT_COLOR 	= new Color(0xAA0000);
	
	private final Color EDGE_COLOR		 	= new Color(0x000000);
	
	private final String BACKGROUND_IMAGE 	= "src/resources/MainMap.jpg";
	
	private Vector<Vertex> map = null;
	
	public MapGraphics(JPanel container) {
		
		this.g = container.getGraphics();
		container.paintComponents(g);
		
		// Get size of the point
		this.h = container.getSize().height;
		this.w = container.getSize().width;
		
		container.addMouseListener(new MapMouseListener());
		
		((Graphics2D)g).setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	private int x(int px) {
		return px * SCALE_FACTOR_X;
	}
	
	private int y(int py) {
		return py * SCALE_FACTOR_Y;
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
	

	public void drawMap(Vector<Vertex> map) {
		
		this.map = map;
		HashSet<Edge> edges = new HashSet<Edge>();
		
		for ( Vertex v: map ) {
			
			// Draw the vertex
			g.setColor(VERTEX_COLOR);
			g.drawOval(
					x(v.getX()) - x(VERTEX_SIZE)/2 + OFFSET_X,
					y(v.getY()) - y(VERTEX_SIZE)/2 + OFFSET_Y,
					x(VERTEX_SIZE),
					y(VERTEX_SIZE)
			);
			
			if ( v instanceof Station ) {
				g.setColor(VERTEX_TEXT_COLOR);
				g.drawString(
						((Station) v).getName(),
						x(v.getX()) + OFFSET_X,
						y(v.getY()) + OFFSET_Y
				);
			}
			
			edges.addAll(v.getEdges());
			
		}
		
		g.setColor(EDGE_COLOR);

		for ( Edge e: edges ) {
			
			int x1, y1, x2, y2;
			x1 = x(e.getVertices().get(0).getX()) + OFFSET_X;
			y1 = y(e.getVertices().get(0).getY()) + OFFSET_Y;
			x2 = x(e.getVertices().get(1).getX()) + OFFSET_X;
			y2 = y(e.getVertices().get(1).getY()) + OFFSET_Y;
			
			g.drawLine(x1, y1, x2, y2);
			
		}
	}
	

	private class MapMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {		
		}

		@Override
		public void mousePressed(MouseEvent e) {			
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			System.out.println("Click " + e.getX() + ", " + e.getY());
			Vertex c = clickOnVertex(e);
			
			if ( c != null ) {
				if ( c instanceof Station ) {
					System.out.println("Click on station: " + ((Station)c).getName());
				} else {
					System.out.println("Click on a Junction");
				}
			} else {
				System.out.println("Clicked nowhere");
			}
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {			
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}


	public Vertex clickOnVertex(MouseEvent e) {
		int cx = ( e.getX() - OFFSET_X ) / SCALE_FACTOR_X;
		int cy = ( e.getY() - OFFSET_Y ) / SCALE_FACTOR_Y;
		for ( Vertex x: map ) {
			double d = Math.sqrt(
				Math.pow(Math.abs( cx - x.getX() ), 2) +
				Math.pow(Math.abs( cy - x.getY() ), 2)
			);
			if ( d < VERTEX_SIZE )
				return x;
		}
		return null;
	}


}
