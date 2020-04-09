import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * This class is for reading the graph from the file
 */
public class Graph {
	int verNum = 0;
	int edgeNum = 0;
	
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
	
