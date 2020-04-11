import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * This class is for reading the graph from the file
 */
public class Graph {
	int verNum = 0;
	int edgeNum = 0;
	
    Set<String> vertexUSet = new HashSet<String>();
    Set<String> vertexVSet = new HashSet<String>();
    //int[] colorGroup;
    
//	ArrayList<ArrayList<Integer>> graphList = new ArrayList<ArrayList<Integer>>(verNum);
	
	LinkedHashMap<String, Set<String>> graphMap = new LinkedHashMap<String, Set<String>>();
	
/*	
 * function name: read
 * parameter: (String) path
 * read the file in MMC format
 */
	public void read(String path) throws IOException{
		FileReader reader = new FileReader(path);
		BufferedReader textReader = new BufferedReader(reader);
		
		List<String> textData = new LinkedList<String>();
		String line;
		while((line = textReader.readLine()) != null) {
			if(!line.contains("%")) textData.add(line);
		}
		textReader.close();
		String[] filestr = textData.toArray(new String[textData.size()]);
		
//		System.out.println(filestr[0]);
//		System.out.println(filestr[1]);
		System.out.println("vertices: " + filestr[0].split(" ")[0]);
		System.out.println("edges: " + filestr[0].split(" ")[2]);
		
		//get the total number of vertices and number of edges.
		verNum = Integer.parseInt(filestr[0].split(" ")[0]);
		edgeNum = Integer.parseInt(filestr[0].split(" ")[2]);
		
//	    for (int i = 0; i < verNum; i++) 
//	    	graphList.add(new ArrayList<Integer>());
//		makeGraph(filestr);
		
	    for (int i = 1; i <= verNum; i++) 
	    	graphMap.put(Integer.toString(i),new HashSet<String>());
		makeGraphMap(filestr);
		
	}
	

/*
 * function name: makeGraphMap
 * parameter: (String[]) filestr
 * Represent the undirected graph as LinkedHashMap	
 */
	public void makeGraphMap(String[] filestr) {
		
		for(int i = 1; i<filestr.length;i++) {
			String u = filestr[i].split(" ")[0];
			String v = filestr[i].split(" ")[1];
			
			graphMap.get(u).add(v);
			graphMap.get(v).add(u);			
		}
		if(isBipartiteFullCheck(graphMap)) {
			System.out.println("The Graph you read is a Bipartite Graph");
		}
		else {
			System.out.println("The Graph you read is not a Bipartite Graph");
		}
	}

/*
 * function name: isBipartiteFullCheck
 * parameter: (LinkedHashMap<String, Set<String>>) graph
 * Return true if the graph is a Bipartite Graph and save the U,V vertex sets
 */
	public boolean isBipartiteFullCheck(LinkedHashMap<String, Set<String>> graph) {
		boolean check = true;
		
		//color group, -1 means not assigned, 0 means red, 1 means blue;
		int[] colorGroup = new int[verNum];
		for(int i = 0; i<colorGroup.length;i++) {
			colorGroup[i] = -1;
		}

		
		for(int i = 0; i<colorGroup.length; i++) {
			if(colorGroup[i] == -1) {
				boolean rowCheck = isBipartite(graph, i, colorGroup);
				if(rowCheck == false) {
					return false;
				}
			}
		}
		//get the U Set and V set
		for(int i = 0; i<colorGroup.length; i++) {
			if(colorGroup[i] == 0) {
				vertexUSet.add(Integer.toString(i+1));
				
			}
			else if(colorGroup[i]==1){
				vertexVSet.add(Integer.toString(i+1));
			}
			else {
				return false;
			}
		}
		return check;
	}
	
/*
 * function name: isBipartite
 * parameter: (LinkedHashMap<String, Set<String>>) graph
 * Check whether the graph is a bipartite using BFS coloring
 * Assigned Red color with the target vertex, and Blue color on its neighbor. 
 * Assigned the red color on the neighbor of its neighbor and 
 * The graph is not a bipartite graph if two adjacency vertex maintain same color
 */
	public boolean isBipartite(LinkedHashMap<String, Set<String>> graph, int tar, int colGup[]) {
		boolean checker = true;
		//assign the vertex "1" to color red
		colGup[tar] = 0;
//		//color group, -1 means not assigned, 0 means red, 1 means blue;
//		colorGroup = new int[graph.size()];
//		for(int i = 0; i<colorGroup.length;i++) {
//			colorGroup[i] = -1;
//		}
//		//assign the vertex "1" to color red
//		colorGroup[0] = 0;

		//BFS
		LinkedList<String> vertexQ = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		String tarVertex = Integer.toString(tar + 1);
		//vertex "1" visited
		visited.add(tarVertex);
		//vertex "1" into queue
		vertexQ.add(tarVertex);
		while(vertexQ.size()!=0) {
			//FIFO vertex
			String vertex = vertexQ.pop();
			//check if the vertex has a self loop
			if(graph.get(vertex).contains(vertex)) {
				return false;
			}
			//Go through all adjacent vertex
			Iterator<String> it = graph.get(vertex).iterator();
			while(it.hasNext()) {
				String adj = it.next();
				int adjIndex = Integer.parseInt(adj)-1;
			    int vertexIndex = Integer.parseInt(vertex)-1;
				if(!visited.contains(adj)) {
					visited.add(adj);
					vertexQ.add(adj);
					//assign the adjacent vertex to opposite color
					colGup[adjIndex] = 1 - colGup[vertexIndex];
					
				}
				//if the the vertex and neighbor has the same color, then return False;
				boolean hasEdge = graph.get(vertex).contains(adj);
				if(hasEdge && colGup[adjIndex] == colGup[vertexIndex]) {
					return false;
				}

			}
		}

		
		return checker;
	}
	
	public void printGraphMap(LinkedHashMap<String, Set<String>> graph) {
		System.out.println("testing GraphMap");
		
		for (int i = 0; i < graph.size(); i++) { 
			String vertex = Integer.toString(i+1);
			System.out.println("vertex adjancy list " + vertex); 
			Iterator<String> it = graphMap.get(vertex).iterator();
			while(it.hasNext()) {
				System.out.print(" -> "+it.next()); 
			}
			System.out.println(); 
		} 
	}

	
//	//convert MMC file into Graph as ArrayList
//	public void makeGraph(String[] filestr) {
//
//		for(int i = 1; i<filestr.length;i++) {
//			int u = Integer.parseInt(filestr[i].split(" ")[0])-1;
//			int v = Integer.parseInt(filestr[i].split(" ")[1])-1;
//			
//			graphList.get(u).add(v);
//			graphList.get(v).add(u);			
//		}
//		
//	}
	
//	//output the graph
//	public void printGraph(ArrayList<ArrayList<Integer>> graph) {
//		System.out.println("testing GraphList");
//		for (int i = 0; i < graph.size(); i++) { 
//			int vertex = i +1;
//			System.out.println("vertex adjancy list " + vertex); 
//			for (int j = 0; j < graphList.get(i).size(); j++) { 
//				int list_v = graphList.get(i).get(j)+1;
//				System.out.print(" -> "+list_v); 
//			} 
//			System.out.println(); 
//		} 
//		
//	}
	
	
}
	
