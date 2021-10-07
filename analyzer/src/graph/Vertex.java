package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import util.Message;

/**
 * The vertex class; a Vertex object has a vertex and a list of edges started from it;
 * @author wangs and you
 * @param <V> 
 */
public class Vertex<V> {
	
	/**vertex */
    private V v;
    /** edges started from this vertex*/
    private List<Edge<V>> edgeList;
    
    /**
     * constructor
     * @param v
     */
    public Vertex(V v) {
        this.v = v;
        this.edgeList = new LinkedList<Edge<V>>();
    }
    
    public V getV() {
		return v;
	}

	public List<Edge<V>> getEdgeList() {
		return edgeList;
	}

	/**
     * add an edge to the edge list of this vertex.
     * if add successfully return true;
     * if edge exists: print `M3` and return false;
     * if `e`'s src is not this vertex: print `M5` and return false;
     * @param e
     */
    public boolean addEdge(Edge<V> e) {
       /**
        * TODO: add an edge to the edge list;
        */
    	// if edgeList is empty
    	Iterator<Edge<V>> it = edgeList.iterator();
    	
    	if(edgeList.size() == 0 && e.getSource() == this.v){
    		edgeList.add(e);
    		return true;
    	}
    	else if(e.getSource() != this.v) {
			System.out.println(Message.M5);
			return false;
		}
    	
    	
    	
    	while(it.hasNext()) {
    		// if edge exists
    		if((it.next().getDest() == e.getDest())) {
    			System.out.println(Message.M3);
    			return false;
    		}
    		// if `e`'s src is not this vertex
    		else if(e.getSource() != this.v) {
    			System.out.println(Message.M5);
    			return false;
    		}
    		// otherwise just add edge into edgeList
    		else {
    			edgeList.add(e);
    			return true;
    		}
    	}
    
    	return false;
    }
    
    /**
     * get an edge between this vertex and the destination V "dest";
     * if 'dest' does not exist: print `M5` and return null; 
     * if edge does not exist: print `M6` and return null; 
     * @param dest
     * @return 
     */
    public Edge<V> getEdge(V dest) {
    	/**
         * TODO: get the edge between this vertex and the destination V "dest";
         */
    	Edge<V> edge_to_be_returned = null;
    	
    	for(Edge<V> edge: edgeList) {
    		if(edge.getDest() == dest) {
    			edge_to_be_returned = edge;
    			break;
    		}
    		else {
    			edge_to_be_returned = null;
    		}
    	}
    	
    	if(edge_to_be_returned == null) {
			System.out.println(Message.M6);
    	}
    	
     	return edge_to_be_returned;
    }
    
    /**
     * remove an edge from the edge list of this vertex
     * if 'dest' exists return the removed edge;
     * if 'dest' does not exist: print `M5` and return null; 
     * if edge does not exist: print `M6` and return null; 
     * @param dest
     * @return removed Edge<V>
     */
    public Edge<V> removeEdge(V dest) {
    	/**
         * TODO: removed an edge
         */
    	
    	Edge<V> edge_to_be_removed = null;
    	for(int i = 0; i < edgeList.size(); i++) {
    		if(edgeList.get(i).getDest() == dest) {
    			edgeList.remove(i);
    			edge_to_be_removed = edgeList.get(i);
    			break;
    		}
    		else {
    			edge_to_be_removed = null;
    		}
    	}
    	
    	if(edge_to_be_removed == null) {
			System.out.println("M6");
		}
    	
     	return edge_to_be_removed;
    }
    

    public boolean equals(Vertex<V> o) { 
		/**
		 * TODO: implement the comparison between two vertices
		 * IFF `v` and `edgeList` are the same return true
		 */
    	boolean result = false;
  
		return false;
	}
    
    @Override
    public String toString() {
        String ret = String.format("v : %s , edges: %s", v, edgeList.toString());
        return ret;
    }    
}