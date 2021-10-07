package graph;

public class My_VertexTester {
	public static void main(String[] args) {
		Vertex v1 = new Vertex("1");


		v1.addEdge(new Edge("1","3"));
		v1.addEdge(new Edge("1","6"));
		v1.addEdge(new Edge("1","9"));

		
		System.out.println(v1.getEdge("6"));
		
//		v1.removeEdge("6");
//		System.out.println("getEdge() after this edge being removed: " + v1.getEdge("6"));

		
		
		
	}
}
