import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BenchMarkTest {

    public static void main(String[] args) throws IOException {
        FirstFit ff = new FirstFit();
        CBIP cbip = new CBIP();
        FirstFit.colorNum = 0;
        CBIP.colorNum = 0;
        NewAlgorithm newAlgorithm = new NewAlgorithm();

        Graph graph = new Graph();

        //Read a graph from a mtx file, it will whether the graph is Bipartite Graph or not
        graph.read("SampleMMC.mtx");
        System.out.println("Original Graph ");

        //Convert a graph to a Bipartite Graph Using Duplicate Method
        MakeBipartite makeBipartite = new MakeBipartite();
        makeBipartite.duplicateBipartiteMap(graph);
        System.out.println("Duplicate Bipartite Graph: ");
        System.out.println(isBipartite(makeBipartite.makeGraphMap));
        System.out.println("U Set " + makeBipartite.vertexUSet);
        System.out.println("V Set " + makeBipartite.vertexVSet);
        
        //Convert a graph to a Bipartite Graph Using Random Method
        MakeBipartite makeBipartite1 = new MakeBipartite();
        makeBipartite1.randomBipartiteMap(graph);
        System.out.println("Random Bipartite Graph: ");
        System.out.println(isBipartite(makeBipartite1.makeGraphMap));
        System.out.println("U Set " + makeBipartite1.vertexUSet);
        System.out.println("V Set " + makeBipartite1.vertexVSet);
        
        //Random Generate a Graph
        RandomGraphGenerator randomGraphGenerator = new RandomGraphGenerator();
        BipartiteGraph bipartiteGraph = new BipartiteGraph();
        bipartiteGraph.setAdjacentVertices(makeBipartite.makeGraphMap);
        
        System.out.println("Random Generate Graph: ");
        System.out.println(isBipartite(bipartiteGraph.adjacentVertices));
        
        
        LinkedHashMap<String, Set<String>> list = randomGraphGenerator.generateVAMPHOrder1(bipartiteGraph);
        int num = 0;
        for (Map.Entry<String, Set<String>> entry : list.entrySet()){
            num++;
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            //System.out.println("vertex: " + vertex + " neighbors: " + neighbor);

            ff.FirstFit(vertex, neighbor);
            cbip.CBIP(vertex, neighbor);
            newAlgorithm.newAlgorithm(vertex, neighbor);


        }
        System.out.println(num + " " + FirstFit.colorNum);
        System.out.println(num + " " + CBIP.colorNum);
        System.out.println(num + " " + newAlgorithm.getColorNum());
    }
    
    
    /*
     * function name: isBipartite
     * parameter: (LinkedHashMap<String, Set<String>>) graph
     * Check whether the graph is a bipartite using BFS coloring
     * Assigned Red color with the target vertex, and Blue color on its neighbor. 
     * Assigned the red color on the neighbor of its neighbor and 
     * The graph is not a bipartite graph if two adjacency vertex maintain same color
     */
    public static boolean isBipartite(LinkedHashMap<String, Set<String>> graph) {
    	boolean check = true;

    	//color group, -1 means not assigned, 0 means red, 1 means blue;
    	int[] colorGroup = new int[graph.size()];
    	for(int i = 0; i<colorGroup.length;i++) {
    		colorGroup[i] = -1;
    	}

    	//In case there is a vertex not connecting any other vertex but self loop
    	for(int i = 0; i<colorGroup.length; i++) {
    		if(colorGroup[i] == -1) {
    			boolean rowCheck = isBipartiteAlgo(graph, i, colorGroup);
    			if(rowCheck == false) {
    				return false;
    			}
    		}
    	}

    	return check;
    }
    	
    /*
     * function name: isBipartiteAlgo
     * parameter: (LinkedHashMap<String, Set<String>>) graph
     * Check whether the graph is a bipartite using BFS coloring
     * Assigned Red color with the target vertex, and Blue color on its neighbor. 
     * Assigned the red color on the neighbor of its neighbor and 
     * The graph is not a bipartite graph if two adjacency vertex maintain same color
     */
    public static boolean isBipartiteAlgo(LinkedHashMap<String, Set<String>> graph, int tar, int colGup[]) {
    	boolean checker = true;
    	//assign the vertex "1" to color red
    	colGup[tar] = 0;

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
}
