package com.eep.taxe.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

import com.eep.taxe.models.Edge;
import com.eep.taxe.models.Path;
import com.eep.taxe.models.Vertex;

public class Dijkstra {
	

	public static final float INFINITY = Float.MAX_VALUE;

	private Map<Vertex, Float> 		distance;
	private Map<Vertex, Vertex> 	previous;
	private Map<Vertex, Boolean>	scanned;
	private Map<Vertex, Float>		priority;
	private PriorityQueue<Vertex>	q;
	
	/**
	 * Create a new instance of the Dijkstra's Algorithm and 
	 * computes shortest path from a given source Vertex
	 * @param 	graph 	The list of vertices (the graph)
	 * @param 	source 	The source Vertex to compute distances from
	 */
	public Dijkstra(Vector<Vertex> graph, Vertex source) {
		
		distance 		= new HashMap<Vertex, Float>();
		previous		= new HashMap<Vertex, Vertex>();
		scanned 		= new HashMap<Vertex, Boolean>();
		priority 		= new HashMap<Vertex, Float>();

		DijkstraComparator c = new DijkstraComparator();
		c.setPriorityMap(priority);
		
		q = new PriorityQueue<Vertex>(graph.size(), c);
		
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
				Vertex v = (Vertex) e.other(u);
				if ( !scanned.containsKey(v) ) {
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
	
	public class DijkstraComparator implements Comparator<Vertex> {
		
		Map<Vertex, Float> priorityMap;
		public void setPriorityMap(Map<Vertex, Float> pMap) {
			this.priorityMap = pMap;
		};
		
		@Override
		public int compare(Vertex o1, Vertex o2) {
			if (priorityMap.get(o1) == null) {
				return -1;
			}
			if (priorityMap.get(o2) == null) {
				return 1;
			}
			return priorityMap.get(o1).compareTo(
					priorityMap.get(o2)	
			);
		}
	}
	
	/**
	 * Returns the path to reach a given target Vertex
	 * @param target	The Vertex to reach
	 * @return			The shortest path
	 */
	public Path getShortestPathTo(Vertex target) {
		Path path = new Path();
		Vertex v = target;
		
	    // First, check if there is a path
		if (previous.get(v) == null) {
	    	return null;
	    }
			    
	    while (previous.get(v) != null) {
	      path.add(v.getEdge(previous.get(v)));
	      v = previous.get(v);
	    }
	    
		Collections.reverse(path);
		return path;
	}
	
	/**
	 * Gets the distance from the origin to a Vertex
	 * @param target
	 * @return
	 */
	public Float getDistanceTo(Vertex target) {
		return distance.get(target);
	}
	
	

} 