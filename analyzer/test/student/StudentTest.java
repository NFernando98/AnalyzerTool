package student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import graph.DGraph;
import graph.Edge;
import graph.ListDGraph;
import graph.Vertex;
import junit.framework.Assert;

public class StudentTest {
 /**
  * TODO: 
  * 	write Junit test cases to test your implementation of ListDGraph;
  */

 
    
    @Test
    public void vertex_addEdgeTester() {
    
        Edge e1 = new Edge("1", "2");
        Edge e2 = new Edge("1", "3");
        Edge e3 = new Edge("2", "3");
        Edge e4 = new Edge("2", "4");
        
        Vertex vertex = new Vertex("1");
        
        // Testing if it adds when trying to add edge with different src value compared to Vertex v
        Assert.assertEquals(false, vertex.addEdge(e4));
        
        // check if it can actually add an edge by checking size, and not just send a boolean value back
        vertex.addEdge(e1);
        vertex.addEdge(e2);
        Assert.assertEquals(2, vertex.getEdgeList().size());
    	
    }
    
    
    @Test
    public void vertex_getEdgeTester() {
    	
  
        Edge e1 = new Edge("1", "2");
        Edge e2 = new Edge("1", "3");
        Edge e3 = new Edge("2", "3");
        Edge e4 = new Edge("2", "4");
        
        Vertex vertex = new Vertex("1");
        
        vertex.addEdge(e1);
        vertex.addEdge(e2);
        
        // comparing using e2 and getEdge() method
        Assert.assertEquals(e2.getDest(), vertex.getEdge("3").getDest());
        Assert.assertEquals(e2.getSource(), vertex.getEdge("3").getSource());

        
    }
    
    @Test
    public void vertex_removeEdgeTester() {
        Edge e1 = new Edge("1", "2");
        Edge e2 = new Edge("1", "3");
        
        Vertex vertex = new Vertex("1");
        
        vertex.addEdge(e1);
        vertex.addEdge(e2);
        
        // checking if it removed properly by checking size before and after removal
        Assert.assertEquals(2, vertex.getEdgeList().size());
        
        // removal
        vertex.removeEdge("3");
        Assert.assertEquals(1, vertex.getEdgeList().size());
    }
    
    @Test
    public void vertex_equalsTester() {
        Edge e1 = new Edge("1", "2");
        Edge e2 = new Edge("1", "3");
        Edge e3 = new Edge("1", "4");
        
        Vertex vertex = new Vertex("1");
        vertex.addEdge(e1);
        vertex.addEdge(e2);
        
        Vertex vertex1 = new Vertex("1");
        vertex1.addEdge(e1);
        vertex1.addEdge(e2);
     
        
        // in the case where vertex does equal vertex1
        Assert.assertEquals(true, vertex.equals(vertex1));
        
        // in the case where vertex's v does not equal vertex3's v
        Vertex vertex3 = new Vertex("2");
        Assert.assertEquals(false, vertex.equals(vertex3));
        
        // case where an edge in there differs
        Vertex vertex100 = new Vertex("1");
        vertex100.addEdge(e1);
        vertex100.addEdge(e3);  //  <- the different edge 
        Assert.assertEquals(false, vertex.equals(vertex100));

    }
    
    @Test
    public void listDGraph_addVTester() {
        DGraph<String> graph = new ListDGraph<String>();

		int i = graph.addV("1");
		Assert.assertEquals(1, i);
		graph.addV("2");
        graph.addV("3");
        graph.addV("4");
        
        int index = graph.addV("5");
        Assert.assertEquals(5, index);
        
        // tells us that the vertex has in fact been added to the list, also tests getV()
        Assert.assertEquals("3", graph.getV(2));
        
    }
    
    @Test
    public void listDGraph_addE() {
        DGraph<String> graph = new ListDGraph<String>();
        
        graph.addV("1");
    	Edge e1 = new Edge("1","2");
    	Assert.assertEquals(true, graph.addE(e1));
    	Edge e2 = new Edge("1","2");
    	Assert.assertEquals(false, graph.addE(e2));
             
    }
    
    @Test
    public void listDGraph_removeVTester() {
     DGraph<String> graph = new ListDGraph<String>();
        
        graph.addV("1");
    	Edge e1 = new Edge("1","2");
    	Edge e2 = new Edge("1","3");
    	Edge e3 = new Edge("1","4");
    	Edge e4 = new Edge("1","5");
    	graph.addE(e1);
    	graph.addE(e2);
    	graph.addE(e3);
    	graph.addE(e4);

    	graph.removeE(e3);
    	
    	// after e2 has been removed 
    	Assert.assertEquals(e1, graph.getE(0, 0));
    	Assert.assertEquals(e2, graph.getE(0, 1));
    	Assert.assertEquals(e4, graph.getE(0, 2));
    	Assert.assertEquals(null, graph.getE(0, 3));
    	
    }
    
    @Test
    public void listDGraph_getVTester() {
    	 DGraph<String> graph = new ListDGraph<String>();
         
         graph.addV("1");
         graph.addV("2");
         graph.addV("3");
         graph.addV("4");
         
     	Assert.assertEquals("3", graph.getV(2));
    	
    }
    
    @Test
    public void listDGraph_getETester() {
   	 DGraph<String> graph = new ListDGraph<String>();

    	 graph.addV("1");
         graph.addV("2");

     	Edge e1 = new Edge("1","2");
     	Edge e2 = new Edge("1","3");
     	Edge e3 = new Edge("1","4");
     	Edge e4 = new Edge("1","5");
     	graph.addE(e1);
     	graph.addE(e2);
     	graph.addE(e3);
     	graph.addE(e4);
     	
     	Assert.assertEquals(e3, graph.getE(0,2));

    	
    }
    
    @Test
    public void listDGraph_branchesTester() {
    	
    	
    }
    @Test
    public void listDGraph_matrixTester() {
    	
    	
    	
    }

}
