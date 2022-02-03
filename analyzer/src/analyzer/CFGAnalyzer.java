package analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import graph.DGraph;
import graph.Edge;
import graph.ListDGraph;

/**
 * @desc build and analyze CFG of a given method.
 * @author you
 */
public class CFGAnalyzer {
	/**
	 * TODO: build and analyze CFG of a given method. 
	 * 		 You can create auxiliary classes/functions if needed. 
	 */

	public DGraph<Integer> graph = new ListDGraph<Integer>();
	public ArrayList<Integer> indents;
	public ArrayList<Integer> index;


	int vertex;
	String faults;
	
	int n;

	
//	public void readLines(String file) {
//		try(BufferedReader br = new BufferedReader(new FileReader(file))){
//			String line;
//			while((line = br.readLine()) != null) {
//				// process line
//				System.out.println(line);
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	
//	public ArrayList<Integer> makeLinesIntoV(String file) {
//		
//		String[] faults = {"def", "if", "for"};
//		ArrayList<Integer> nodes = new ArrayList<Integer>();
//		
//		int counter = 1;
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//			String line = reader.readLine();
//			while(line !=null){
//				for(int i = 0 ; i<faults.length;i++){
//					if(line.contains(faults[i])){
//						// do something
//						System.out.println(faults[i]);
//						nodes.add(counter);
//						counter++;
//					}
//					else if() {
//						
//					}
//				}
//				line=reader.readLine();
//			}
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		
//		
//		
//		return nodes;
//		
//	}
	
	
	public CFGAnalyzer() {
		indents = new ArrayList<Integer>();
		
		index = new ArrayList<Integer>();
	}
	
	
	public boolean checkBlocks(String line) {
		boolean result = false;
		
		if(line.contains("def")) {
			faults = "def";
			result = true;
		}
		else if(line.contains("for")) {
			faults = "for";
			result = true;
		}
		else if(line.contains("if")) {
			faults = "if";
			result = true;
		}
		else if(line.contains("else")) {
			faults = "else";
			result = true;
		}
		
		return result;
	}
	
	// spaces
	public int blankBlocks(String next_lines) {
		int result_blanks = 0;
		while(Character.isWhitespace(next_lines.substring(result_blanks).charAt(0))) {
			result_blanks++;
        }
		return result_blanks;
	}
	
	
	
	public void CFG(String path) throws FileNotFoundException {
    	String b = "";

		File text = new File(path.substring(path.indexOf('/')+1));
    	int lastIf = 0;
        Map<Integer, String> h1 = new HashMap<Integer, String>();
        Set<Integer> block = new HashSet<Integer>();
    	int first_indet = 0;
    	int parent_node = 0;
    	int if_statemtn_before = 0;

    	
    	
	    Scanner scanner = new Scanner(text);

        while(scanner.hasNextLine()){
            String next = scanner.nextLine();
            int indent = blankBlocks(next);
           
            if(this.checkBlocks(next)) {
            	if(b.equals("if")) {
            		lastIf = vertex;
        		}
            	first_indet = blankBlocks(next);
            	vertex++;
        		graph.addV(vertex);
        		
        		
        		if(faults.equals("if")) {
        			if_statemtn_before = vertex;
        		}
        		else if(faults.equals("else")) {
        			graph.addE(new Edge<Integer>(if_statemtn_before, vertex));
        			System.out.print("" + lastIf+ "\n");
        		}
        		else {
        			graph.addE(new Edge<Integer>(vertex-1, vertex));
        			parent_node = vertex;
        		}
        		
        		b = faults;
        		block.clear();
        		
            }
            // else if
            else if(faults.equals("if")) {
            	if(!next.contains("if")) {
            		if(indent > first_indet) {
            			if(next.contains("return")) {
            				vertex++;
            				graph.addV(vertex);
            				graph.addE(new Edge<Integer>(vertex-1, vertex));
            				h1.put(vertex, "return");
            			}
            			if(!block.contains(indent)) {
            				vertex++;
            				graph.addV(vertex);
            				graph.addE(new Edge<Integer>(vertex-1, vertex));
            			}
            				block.add(indent);
            			}
            			else {
            				vertex++;
            				graph.addV(vertex);
            				graph.addE(new Edge<Integer>(vertex-1, vertex));
            				
            					if(next.contains("return")) {
            						h1.put(vertex, "return");
            					}	}
	            	}
             
            // next else if
            }
            else if(faults.equals("else")){
            	if(!next.contains("else")) {
            		if(indent <= first_indet) {
            			if(!block.contains(indent)) {
            				vertex++;
            				graph.addV(vertex);
            				graph.addE(new Edge<Integer>(vertex - 1, vertex));
            			}
            			block.add(indent);
            		}
            	}
             }else if(faults.equals("for")){
            	int startNode = vertex;
            	vertex++;
     			graph.addV(vertex);
     			graph.addE(new Edge<Integer>(startNode, vertex));
     			
     			int lastElement = vertex;
     			//left vertex
     			vertex ++;
     			graph.addV(vertex);
     			graph.addE(new Edge<Integer>(lastElement, vertex));
     			//right vertex
     			vertex ++;
     			graph.addV(vertex);
     			graph.addE(new Edge<Integer>(lastElement, vertex));
     			graph.addE(new Edge<Integer>(vertex, lastElement));
     			
     		
     			
             }else {
            	vertex++;
  				graph.addV(vertex);
  				graph.addE(new Edge<Integer>(vertex-1, vertex));
             }
            if(!scanner.hasNext()) {
            	if(h1.containsKey(lastIf)) {
            		if(!h1.get(lastIf).equals("return")) {
            			graph.addE(new Edge<Integer>(lastIf, vertex));
            		}
            	}else {
            		if(!faults.equals("for")) {
	            		if(lastIf != 0) {
	        				graph.addE(new Edge<Integer>(lastIf, vertex));
	        			}
	            		
	            	int v = vertex + 1;
	            	graph.addV(v);
	            	graph.addE(new Edge<Integer>(vertex, v));
            		}
            	 }
               }
            }

     
        
	}
	
	public void printMatrix() {
    	int[][] vvv = graph.matrix();
    	System.out.print("matrix: " + vvv[0].length + "\n");
    	int[][] vi = new int[vvv[0].length + 1][vvv[0].length + 1];
    	
    	
    	
    	
    	String row = "";
    	for(int i = 0; i<vi.length; i++) {

    		for(int j = 0; j<vi[i].length; j++) {
    			if(!(i == 0 && j == 0)) {
    			if(i == 0) {
    				vi[i][j] = j;
    			}
    			else if(j == 0) {
    				vi[i][j] = i;
    			}
    			else {
    				for(int p = 0; p<vvv.length; p++) {
        				for(int k = 0; k<vvv[p].length; k++) {
        					vi[p + 1][k + 1] = vvv[p][k];
        				}
    				  }
    				
    			}
        		System.out.printf("%2d", vi[i][j]);
    			}
    			else {
    				System.out.print(" ");
    			}
    		}
    	}
    	 for (int[] x : vvv){
            for (int y : x){
                 System.out.print(y + " ");
            }
         }
		
	}
	
	
}
