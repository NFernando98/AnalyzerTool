package graph;

import java.util.LinkedList;

public class My_ListDGraphTester {
	public static void main(String[] args) {
		
		// Dynamic binding in line below  DGraph = ListDgraph
		DGraph<String> graph = new ListDGraph<String>();
		graph.addV("1");
		graph.addV("2");
        graph.addV("3");
        graph.addV("4");
    	
    	int index = graph.addV("1");
    	System.out.println(index);
		index = graph.addV("9");
    	System.out.println(index);
 
    	System.out.println("================== AddE() ================");
    	Edge e1 = new Edge("1","2");
    	System.out.println(graph.addE(e1));
    	Edge e2 = new Edge("1","2");
    	System.out.println(graph.addE(e2));

    	
//    	  graph.addE(new Edge<String>("1", "2"));
//          graph.addE(new Edge<String>("1", "3"));
//          graph.addE(new Edge<String>("4", "3"));
//          graph.addE(new Edge<String>("4", "4"));
         
        
   	System.out.println("======================");
    	
    	LinkedList<Vertex<String>> vList  = new LinkedList<Vertex<String>>();
    	
    	Vertex vertex = new Vertex("1");
    	Vertex vertex1 = new Vertex("4");
    	Vertex vertex2 = new Vertex("5");

    	vList.add(vertex);
    	vList.add(vertex1);
    	vList.add(vertex2);
    	
    	System.out.println(vertex.getV());
    	System.out.println(vList.contains(vertex1));
    	

        System.out.println("=========interval==========");
        
        System.out.println(vList.get(0).addEdge(new Edge<String>("1", "3")));
        System.out.println("vertex edges" + vList.get(0).getEdgeList() + "\n");
        
        System.out.println("=============adding edges to vertex1 instance using addEdge function in Vertex class=============");
        // false since first argument is 1 instead of 4, src in Edge has to equal v in vertex class
        System.out.println("add new edge to vertex1: " + vertex1.addEdge(new Edge<String>("1", "3")));
        System.out.println("vertex1 edges " + vertex1.getEdgeList());
        
        System.out.println("add new edge to vertex1: " + vertex1.addEdge(new Edge<String>("4", "4")));
        System.out.println("vertex1 edges " + vertex1.getEdgeList());
        
        System.out.println("vesadfadsfaf);\n");

        System.out.println("Trying to add an existing edge to same vertex: " + vertex1.addEdge(new Edge<String>("4", "4")));
        System.out.println("vertex1 edges " + vertex1.getEdgeList());
        
//        System.out.println("=============adding edges to vertex2 instance using addE function in ListDGraph class=============");
//        System.out.println("add new edge to vertex2: " + vertex1.(new Edge<String>("4", "4")));

        
        System.out.println(vertex1);
        
    	
    	
    	
	}
}
