import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.lang.Math;

//This class is for generating a graph for 
public class MakeBipartite {
	int verNum = 0;
	LinkedHashMap<String, Set<String>> makeGraphMap = new LinkedHashMap<String, Set<String>>();
//	ArrayList<ArrayList<Integer>> dupGraph = new ArrayList<ArrayList<Integer>>(verNum);
    Set<String> vertexUSet = new HashSet<String>();
    Set<String> vertexVSet = new HashSet<String>();
	
/*
* function name: duplicateBipartiteMap
* parameter: GraphRead
* Convert a graph to Bipartite Graph using Duplicate vertex methods.
*/
	public void duplicateBipartiteMap(Graph graph) {
		
		//The Bipartite graph will be double size of the original graph.
		int orgGraph_size = graph.graphMap.size();
		verNum = orgGraph_size*2;
		
	    for (int i = 1; i <= verNum; i++) 
	    	makeGraphMap.put(Integer.toString(i),new HashSet<String>());
	    
	    //Go through each vertex in the original graph
	    //First vertex copy v and second copy dup_v
	    //Connect vertex and dup_v if and only if they were connected in the Graph G
		for(int i = 0; i<graph.graphMap.size();i++) {
			
			String vertex = Integer.toString(i+1);

			Iterator<String> it = graph.graphMap.get(vertex).iterator();
			while(it.hasNext()) {
				int v = Integer.parseInt(it.next());
				int dup_v = v + orgGraph_size;
				String newV = Integer.toString(dup_v);
				makeGraphMap.get(vertex).add(newV);
				makeGraphMap.get(newV).add(vertex);
				vertexUSet.add(vertex);
				vertexVSet.add(newV);
			}
					
		}
		
		
	}
	
/*
 *	function name: randomBipartiteMap
 *	parameter: GraphRead
 *	Convert a graph to Bipartite Graph using randomize partition method	
 */
	public void randomBipartiteMap(Graph graph) {
		
		//get the total number of vertices from origional graph
		int orgGraph_size = graph.graphMap.size();
		verNum = orgGraph_size;

		//partition the VERTEX into two blocks v = ceil(V/2); u = floor(V/2)
		//
		int v_num = (int) Math.ceil((double)orgGraph_size/2);
		int u_num = (int) Math.floor((double)orgGraph_size/2);
		

		ArrayList<Integer> vSet = new ArrayList<Integer>(v_num);
		ArrayList<Integer> uSet = new ArrayList<Integer>(u_num);
		ArrayList<Integer> orgSet = new ArrayList<Integer>(orgGraph_size);
		
		//put vertices into an ArrayList and shuffle
		for(int i = 1; i<= orgGraph_size; i++) {
			orgSet.add(i);
			makeGraphMap.put(Integer.toString(i),new HashSet<String>());
		}
	
		//Shuffle the G VERTEX List
		Collections.shuffle(orgSet);
		//partition G vertex into two V block and U block
		for(int i = 0; i < orgGraph_size; i++) {
			if(i<v_num) {
				vSet.add(orgSet.get(i));
				vertexUSet.add(Integer.toString(i+1));
			}
			else {
				uSet.add(orgSet.get(i));
				vertexVSet.add(Integer.toString(i+1));
			}
		}

		//go through edges set in graph G, and connect the Bipartite Graph V, U
		for(int i = 0; i<vSet.size();i++) {
			//retrieve each vertex from vSet
			String vVertex = Integer.toString(vSet.get(i));
			
		     for (String temp : graph.graphMap.get(vVertex)) {
		    	 int uVertex = Integer.parseInt(temp);
				 if(uSet.contains(uVertex)) {
					makeGraphMap.get(vVertex).add(temp);
					makeGraphMap.get(temp).add(vVertex);
				 }
		      }
		}
		
//		System.out.println("vSet: " + vSet.toString());
//		System.out.println("uSet: " + uSet.toString()); 
//		orgSet.size();

		
	}
	
/*
 *	function name: DisplayBipartiteGraph
 *	Display the Bipartite Graph
 */	
	public void DisplayBipartiteGraph() {
	System.out.println("Displaying Bipartite Graph: ");
	
	for (int i = 0; i < makeGraphMap.size(); i++) { 
		String vertex = Integer.toString(i+1);
		System.out.println("vertex adjancy list " + vertex); 
		Iterator<String> it = makeGraphMap.get(vertex).iterator();
		while(it.hasNext()) {
			System.out.print(" -> "+it.next()); 
		}
		System.out.println(); 
		} 
	}
	
	
	
	/*
	 * Implement Duplicate Bipartite Graph Method with ArrayList Graph Representation
	 */
//	public void duplicateBipartite(GraphRead graph) {
//		int orgGraph_size = graph.graphList.size();
//		verNum = orgGraph_size*2;
//	    for (int i = 0; i < verNum; i++) 
//	    	dupGraph.add(new ArrayList<Integer>());
//	    
//		for(int i = 0; i<graph.graphList.size();i++) {
//			for(int j = 0; j < graph.graphList.get(i).size(); j++) {
//				//find each vertex neighbor to the vertex
//				int u = i;
//				int v = graph.graphList.get(i).get(j);
//				
//				int dup_v = v+orgGraph_size;
//				dupGraph.get(u).add(dup_v);
//				dupGraph.get(dup_v).add(u);
//				
//			}
//					
//		}
//		
//	}
	
	/*
	 * Implement Random Bipartite Graph Method with ArrayList Graph Representation
	 */
//	public void randomBipartite(GraphRead graph) {
//		int orgGraph_size = graph.graphMap.size();
//		verNum = orgGraph_size;
//		//randomize the graph vertex
//		//Collections.shuffle(graph.graphList);
//
//		//partition the Graph into two sets
//		int v_num = (int) Math.ceil((double)orgGraph_size/2);
//		int u_num = (int) Math.floor((double)orgGraph_size/2);
//		
//		ArrayList<Integer> vSet = new ArrayList<Integer>(v_num);
//		ArrayList<Integer> uSet = new ArrayList<Integer>(u_num);
//		ArrayList<Integer> orgSet = new ArrayList<Integer>(orgGraph_size);
//		
//		for(int i = 0; i< orgGraph_size; i++) {
//			orgSet.add(i);
//			dupGraph.add(new ArrayList<Integer>());
//		}
//		//randomize the graph G
//		Collections.shuffle(orgSet);
//		//partition G vertex into two V block and U block
//		for(int i = 0; i < orgGraph_size; i++) {
//			if(i<v_num) {
//				vSet.add(orgSet.get(i));
//			}
//			else {
//				uSet.add(orgSet.get(i));
//			}
//		}
//		//go through edges in graph G, and connect the Bipartite Graph
//		for(int i = 0; i<vSet.size();i++) {
//			int vElement = vSet.get(i);
//			int e_size = graph.graphList.get(vElement).size();
//			for(int j = 0; j<e_size; j++) {
//				int ele = graph.graphList.get(vElement).get(j);
//				if(uSet.contains(ele)) {
//					dupGraph.get(vElement).add(ele);
//					dupGraph.get(ele).add(vElement);
//				}
//			}
//		}
//		orgSet.size();
//	}
	
//	public void DisplayGraphAL() {
//	System.out.println("Testing Make Bipartite Graph");
//	for (int i = 0; i < dupGraph.size(); i++) { 
//		int vertex = i +1;
//		System.out.println("vertex adjancy list " + vertex); 
//		for (int j = 0; j < dupGraph.get(i).size(); j++) { 
//			int list_v = dupGraph.get(i).get(j)+1;
//			System.out.print(" -> "+list_v); 
//		} 
//		System.out.println(); 
//	} 
//}
	
	
}
