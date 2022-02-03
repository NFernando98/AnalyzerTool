package graph;

public class My_Branches_methodTester {
	public static void main(String[] args) {
		ListDGraph<String> graph2 = new ListDGraph<String>();
		graph2.addV("1");
		graph2.addV("2");
        graph2.addV("3");
        graph2.addV("4");
        
        Edge e1 = new Edge("1", "2");
        Edge e2 = new Edge("1", "3");
        Edge e3 = new Edge("2", "3");
        Edge e4 = new Edge("2", "4");
        
        graph2.addE(e1);
        graph2.addE(e2);
        graph2.addE(e3);
        graph2.addE(e4);
        
        System.out.println(graph2.branches("1"));
        
        
	}
}
