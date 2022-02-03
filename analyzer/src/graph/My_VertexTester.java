package graph;

public class My_VertexTester {
	public static void main(String[] args) {
		Vertex v1 = new Vertex("1");
		
		v1.addEdge(new Edge("1","3"));
		v1.addEdge(new Edge("1","6"));
		v1.addEdge(new Edge("1","9"));
	
		System.out.println("EdgeList of v1: " + v1.getEdgeList());
		
		System.out.println("remove edge: " + v1.getEdge("6"));
		
		// test remove method in Vertex
		v1.removeEdge("6");
		System.out.println("after this edge being removed: " + v1.getEdgeList() + "\n");
		
		// test equals method in Vertex
		Vertex v2 = new Vertex("1");
		Edge edddgge = new Edge("1","3");
		v2.addEdge(edddgge);
		v2.addEdge(new Edge("1","6"));
		v2.addEdge(new Edge("1","9"));
		
		// remove same edge as i did with v1 to see if they equal
		v2.removeEdge("6");
		System.out.println("EdgeList of v2: " + v2.getEdgeList());

		System.out.println("v1 equals v2? " + v1.equals(v2));
		
		System.out.println("=====getEdge()======");
		System.out.println("v2.getEdge(): " + v2.getEdge("9"));	
		Edge edddgge1 = new Edge("1","3");
		System.out.println("edddgge.equals(edddgge1): "+ edddgge.equals(edddgge1));
		System.out.println("v2.addEdge(edddgge1): " + v2.addEdge(edddgge1));		
		System.out.println(v2.getEdgeList());

		
		
	}
}
