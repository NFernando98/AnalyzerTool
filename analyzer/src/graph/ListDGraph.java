package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

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
    	
    	// if there are no elements, add that and return id(index)
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
    	
//    	for(int i = 0; i < vList.size(); i++) {
//    		Vertex<V> vertex = vList.get(i);
//    		
//    		if(vertex.addEdge(e) == true) {
//    			result = true;
//    		}
//    	}
    

    	for(int i = 0; i < vList.size(); i++) {
    		// if src of edge is equal to v in Vertex then add it into edgeList
    		
    		if(vList.get(i).getV() == e.getSource()) {
    			
    			// if edgeList size is 0, then add edge to list
    			if(vList.get(i).getEdgeList().size() == 0) {
    	    		vList.get(i).getEdgeList().add(e);
    				result = true;
    				break;
    	    	}
    			// checking edge list to see if there exists a e.dest
    			for(int j = 0; j < vList.get(i).getEdgeList().size(); j++) {
    				if(vList.get(i).getEdgeList().get(j).getDest() == e.getDest()){
    					System.out.println(Message.M3);
    					return false;
    				}
    				else {
    					vList.get(i).getEdgeList().add(e);
    					return true;                          
        			}
    			}
    		 }
    		else {
    			result = false;	
    		}
    	}
    	// print error message if there is no such src.
    	if(result == false) {
			System.out.println(Message.M5);
    	}

    	
    	return result;

    	}
    	
     
    
    @Override
    public V removeV(V v) {
    	/**
         * TODO: implement the removeV function;
         */
    	
    	V result = null;
    	for(int i = 0; i < vList.size(); i++) {
    		if(vList.get(i).getV().equals(v)) {
    			result = vList.get(i).getV();
    			vList.remove(i);
    			break;
    		}
    		else {
    			result = null;
    		}
    	}
    	if(result == null) {
			System.out.println(Message.M5);
    	}
    	    return result;
    }

    @Override
    public Edge<V> removeE(Edge<V> e) {
    	/**
         * TODO: implement the removeE function;
         */
    	
    	Edge<V> edge_to_return = null;
    
    	for(int i = 0; i < vList.size(); i++) {
    		// only go in if v == e.src
    		if(this.vList.get(i).getV() == e.getSource()) {
    			// using helper method to get edge in the given vertex at index i
    			if(vList.get(i).getEdge(e.getDest()) == null) {
    				edge_to_return = null;
    			}
    			else {
    				vList.get(i).removeEdge(e.getDest());
    				edge_to_return = e;
    				break;
    			}
    		}
    		else {
				edge_to_return = null;
    		}
    	}
    	
    	if(edge_to_return == null) {
    		System.out.println(Message.M6);
    	}
    	
    	
    	
        return edge_to_return;
    }

    @Override
    public V getV(int index) {
    	/**
         * TODO: implement the getV function;
         */
    	  
    	V v = null;
    	
    	if(vList.size() <= index) {
    		System.out.println(Message.M4);
    	}
    	else {
    		v = vList.get(index).getV();
    	}
        return v;
    }

    @Override
    public Edge<V> getE(int src, int dest) {
    	/**
         * TODO: implement the getE function;
         */
    	 
    	Edge<V> edge_to_return = null;
    	if(src >= vList.size() || dest >= vList.get(src).getEdgeList().size()) {
    		System.out.println(Message.M4);
    		return null;
    	}else {
        	edge_to_return = vList.get(src).getEdgeList().get(dest);

    	}
    	
        return edge_to_return;
    }  
    

    @Override
	public ArrayList<ArrayList<V>> branches(V v) {
		/**
		 * TODO: iterate the Graph from a given vertex and return all the branches from it;
		 */
		ArrayList<ArrayList<V>> branches_to_be_returned = new ArrayList<ArrayList<V>>();
		
		V startingV = v;
		Set<V> vertices_with_no_edges = new HashSet<V>();
		
		Stack<ArrayList<V>> stack1 = new Stack<ArrayList<V>>();
		ArrayList<V> Visited = new ArrayList<V>();
		Stack<V> stack2 = new Stack<>();
		Stack<V> curr = new Stack<>();
		curr.push(v);
	     
		Visited.add(v);
		
       
		Stack<V> v_withNoEdges = new Stack<>();
		
		 while (!curr.isEmpty()) {
			 int index = 0;
      	   for(int i = 0; i<vList.size(); i++) {
 				if(vList.get(i).getV() == v) {
 					index = i;
 					break;
 				}
 			}
             V currentv = curr.pop();
             // if v in stack that got popped does not equal v
             if(!currentv.equals(v)) {
            	 stack2.add(currentv);
          	   
          	   for(int i = 0; i < vList.size(); i++) {
    				if(vList.get(i).getV().equals(currentv) && vList.get(i).getEdgeList().isEmpty()) {    						
    					vertices_with_no_edges.add(currentv);   			}
    			}
          	  
             }
             for(Edge<V> adj_node : vList.get(index).getEdgeList()) {
 				V vvv = adj_node.getDest();
                     if (!Visited.contains(vvv)) {
                    	 curr.push(vvv);
                         Visited.add(vvv);            
                 }
             }
             if(curr.isEmpty()) {
 				if(!stack2.isEmpty()) {
 					curr.removeAllElements();
 					Visited.clear(); 					
 					v = stack2.pop();
 					curr.push(v);
 				}
 			}
		 }
     		for(V terminal : vertices_with_no_edges) {
     			v_withNoEdges.add(terminal);
 		}
     		
     		
		// find the separate branches
     	for(int k = 0; k < v_withNoEdges.size(); k++) {
     	    V dest = v_withNoEdges.get(k);     	         		
     		Queue<List<V> > queue = new LinkedList<>();
 	
     		// add new curent paths
     	    List<V> branches = new ArrayList<>();
     	    branches.add(startingV);
     	    queue.offer(branches);
     	    
     	    while (!queue.isEmpty()) {
     	    	branches = queue.poll();
     	        V last = branches.get(branches.size() - 1);
     	        // If vertex has no edges, then add that path to branches_to_be_returned
     	        if (last == dest){
     	        	branches_to_be_returned.add((ArrayList<V>) branches);
     	        }   	 
     
     	        // go through,traverse connected vertices
     	        int index2 = 0;
     	        for(int i = 0; i<vList.size(); i++) {
     	        	if(vList.get(i).getV().equals(last)) {
     	        		index2 = i;
     	        		break;
     	        	}
     	        }
     	        for(int i = 0; i < vList.get(index2).getEdgeList().size(); i++){
     	        	Edge<V> lastNode = vList.get(index2).getEdgeList().get(i);
     	            boolean result = false;
     	            for(int j = 0; j < branches.size(); j++){
     	                if (branches.get(j) != lastNode.getDest()){
     	                	result = true;
     	                    break;
     	                }
     	                else{
     	                	result = false;
     	                    break;
     	                }
     	            }
     	            if (result == true) {
     	                List<V> newpath = new ArrayList<>(branches);
     	                newpath.add(lastNode.getDest());
     	                queue.offer(newpath);
     	            }
     	            
     	            
     	        }

     	    }
     	    }
    
	
	return branches_to_be_returned;
	}


	
    
    
    
    

    @Override
    public int [][] matrix() {
    	/**
    	 * TODO: generate the adjacency matrix of a graph;
    	 */
    	// need to fix the size?
    	int sizeOf_vList = vList.size();
    	
    	int[][] result = new int[sizeOf_vList][sizeOf_vList];
    	
    	for(int i = 0; i < sizeOf_vList; i++) {
    		Vertex<V> vertex1 = vList.get(i);
    		
    		for(int j = 0; j < sizeOf_vList; j++) {
    			Vertex<V> vertex2 = vList.get(j);
    			
    			for(Edge edges: vertex1.getEdgeList()) {
    				if(edges.getDest() == vertex2.getV()) {
    					result[i][j] = 1;
    					break;
    				}
    				else{
    					result[i][j] = 0;
    				}
    			}
    			// convert String (dest) to int 
//                Integer number = Integer.valueOf((String) vList.get(i).getEdgeList().get(j).getDest());
//                // compare dest to j
//    			if(number == j)
//    				result[i][j] = 1;
//    		}
//    	}
//    	
//    		
    	// delete after, why isnt it printing 1s
//    	System.out.println(Arrays.deepToString(result));

    		}
    	}
    	for (int i = 0; i < result.length; i++) {
    	    for (int j = 0; j < result[i].length; j++) {
    	        System.out.print(result[i][j] + " ");
    	    }
    	    System.out.println();
    	}
    	
    	
    	
    	
    	
    	
    	return result;
    }
    
    
    
    
    
    
    
    
    
 
}




