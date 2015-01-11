package com.eep.taxe.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Vertex;

public class Dijkstra {
	

	public static final float INFINITY = Float.MAX_VALUE;

	Map<Vertex, Float> 		distance;
	Map<Vertex, Vertex> 	previous;
	Map<Vertex, Boolean>	scanned;
	Map<Vertex, Float>		priority;
	PriorityQueue<Vertex>	q;
	
	/**
	 * Create a new instance of the Dijkstra's Algorithm and 
	 * computes shortest path from a given source vertex
	 * @param 	graph 	The list of vertices (the graph)
	 * @param 	source 	The source vertex to compute distances from
	 */
	Dijkstra(Vector<Vertex> graph, Vertex source) {
		
		distance 		= new HashMap<Vertex, Float>();
		previous		= new HashMap<Vertex, Vertex>();
		scanned 		= new HashMap<Vertex, Boolean>();
		q 				= new PriorityQueue<Vertex>(graph.size(), new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				return priority.get(o1).compareTo(
					priority.get(o2)	
				);
			}
		});
		
		distance.put(source, (float) 0.0);
		
		for ( Vertex v : graph ) {
			if ( v != source ) {
				distance.put(v, INFINITY);
				previous.put(v, null);
			}
			q.add(v);
			priority.put(v, distance.get(v));
		}
		
		float alt;
		
		while (!q.isEmpty()) {
			Vertex u = q.poll();
			scanned.put(u, true);
			for ( Edge e : u.getEdges() ) {
				Vertex v = e.other(u);
				if ( !scanned.get(v) ) {
					alt = distance.get(u) + e.getLength();
					if ( alt < distance.get(v) ) {
						distance.put(v, alt);
						previous.put(v, u);
						priority.put(v, alt);
					}
				}
			}
		}
	}
	
	/**
	 * Returns the path to reach a given target vertex
	 * @param target	The vertex to reach
	 * @return			The shortest path
	 */
	public Vector<Edge> getShortestPathTo(Vertex target) {
		Vector<Edge> path = new Vector<Edge>();
		Vertex v = target;
		
	    // First, check if there is a path
		if (previous.get(v) == null) {
	      return null;
	    }
		
	    path.add(target.getEdge(v));
	    while (previous.get(v) != null) {
	      path.add(v.getEdge(previous.get(v)));
	      v = previous.get(v);
	    }
	    
		Collections.reverse(path);
		return path;
	}
	
	
	

} 