package graph;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import util.Message;

/**
 * Use LinkedList to implement the directed graph
 * @param <V>
 * @author wangs and you
 */
public class ListDGraph<V> implements DGraph<V>{
   
    /**list of the vertices in a graph*/
    private LinkedList<Vertex<V>> vList;
    
    /**
     * constructor
     */
    public ListDGraph() {
        vList = new LinkedList<Vertex<V>>();
    }
    
    @Override
    public int addV(V v) {
      /**
       * TODO: implement the addV function;
       */
    	
    	  /**
         * create an object of Vertex and add it into the vertex list
         * @param v
         * @return the id of new added vertex; 
         * 		   IF the vertex exits: print message `M2` and return -1; 
         */
    	Vertex vertex = new Vertex(v);
    
    	
    	Iterator<Vertex<V>> it = vList.iterator();
    	
    	// if there are no elements, add that and return id
    	if(vList.size() == 0) {
    		vList.add(vertex);
    		return vList.indexOf(vertex)+1;
    	}
    	
    	int result = 0;
    	while(it.hasNext()) {
    		// if vertex exists
    		if(it.next().getV() == v) {  
    			System.out.println(Message.M2);
        		result = -1;
        		break;
    		}
    		else {       // if vertex does not exist
        		vList.add(vertex);
        		result = vList.indexOf(vertex)+1;
        		break;
    		}
    	}
    	return result;
    	
    }

    
    @Override
    public boolean addE(Edge<V> e) {
    	/**
        * TODO: implement the addE function;
        */
    	 /**
         * add an edge into the edge list of the start vertex (i.e., 'src') of this edge;
         * IF the edge exits: print message `M3` and return false;
         * IF 'src' or 'dest' of the edge is not in the graph: print message `M5` and return false;
         * @param e
         */
    	boolean result = false;
    
    	Iterator<Vertex<V>> it = vList.iterator();

    	for(int i = 0; i < vList.size(); i++) {
    		// if src of edge is equal to v in Vertex then add it into edgeList
    		
    		if(vList.get(i).getV() == e.getSource()) {
    			
    			if(vList.get(i).getEdgeList().size() == 0) {
    	    		vList.get(i).getEdgeList().add(e);
    				result = true;
    				break;
    	    	}
    			
    			if(vList.get(i).getEdgeList().get(i).getDest() == e.getDest()){
        			System.out.println(Message.M3);
        			return false;
        			}
        		else {
        			vList.get(i).getEdgeList().add(e);
        			return true;
        			}
    		 }
    		else {
    			result = false;
    			break;
    			
    		}
    	}

    	
    	return result;

    	}
    	
    
    		
    	
    	
    
    
    @Override
    public V removeV(V v) {
    	/**
         * TODO: implement the removeV function;
         */
    	  /**
         * remove a vertex and edges linked to it
         * @param v
         * @return the removed vertex; IF v does not exist: print message `M5` and return null; 
         */
    	V result = null;
    	for(int i = 0; i < vList.size(); i++) {
    		if(vList.get(i).getV().equals(v)) {
    			result = vList.get(i).getV();
    			break;
    		}
    		else {
    			System.out.println(Message.M5);
    			result = null;
    		}
    	}
    	    return result;
    }

    @Override
    public Edge<V> removeE(Edge<V> e) {
    	/**
         * TODO: implement the removeE function;
         */
    	  /**
         * remove an edge
         * @param e
         * @return the removed edge; IF cannot find `e`: print message `M6` and return null;
         */
    	Edge<V> e1 = null;
    	
    	
    	
        return e1;
    }

    @Override
    public V getV(int index) {
    	/**
         * TODO: implement the getV function;
         */
        return null;
    }

    @Override
    public Edge<V> getE(int src, int dest) {
    	/**
         * TODO: implement the getE function;
         */
        return null;
    }

	@Override
	public ArrayList<ArrayList<V>> branches(V v) {
		/**
		 * TODO: iterate the Graph from a given vertex and return all the branches from it;
		 */
		return null;
	}
	
    @Override
    public int [][] matrix() {
    	/**
    	 * TODO: generate the adjacency matrix of a graph;
    	 */
    	return null;
 
    }	
}