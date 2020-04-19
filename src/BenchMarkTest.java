import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class BenchMarkTest {

    public static void main(String[] args) throws Exception {
        runBenchmarkTest("./BenchmarkGraphs/email-enron-only.mtx");
        runBenchmarkTest("./BenchmarkGraphs/rt-twitter-copen.mtx");
        runBenchmarkTest("./BenchmarkGraphs/socfb-Caltech36.mtx");
        runBenchmarkTest("./BenchmarkGraphs/socfb-Reed98.mtx");
        runBenchmarkTest("./BenchmarkGraphs/web-polblogs.mtx");
    }

    public static void runBenchmarkTest(String filePath) throws Exception {
        Graph g = new Graph();

        //Read a graph from a mtx file, it will whether the graph is Bipartite Graph or not
        g.read(filePath);

        //Convert a graph to a Bipartite Graph Using Duplicate Method
        MakeBipartite makeBipartite = new MakeBipartite();
        makeBipartite.duplicateBipartiteMap(g);

        //Convert a graph to a Bipartite Graph Using Random Method
        MakeBipartite makeBipartite1 = new MakeBipartite();
        makeBipartite1.randomBipartiteMap(g);

        BipartiteGraph graph = new BipartiteGraph();
        graph.setAdjacentVertices(makeBipartite1.makeGraphMap);
        graph.setVertexVSet(makeBipartite1.vertexVSet);
        graph.setVertexUSet(makeBipartite1.vertexUSet);

        if (!isBipartite(graph.adjacentVertices)) {
            throw new Exception("bipartite conversion failed");
        }

        FileWriter fw1 = new FileWriter("src/FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("src/CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("src/NewAlgorithm.txt", true);
        PrintWriter pw3 = new PrintWriter(fw3);
        pw1.println("random method make bipartite " + filePath);
        pw2.println("random method make bipartite " + filePath);
        pw3.println("random method make bipartite " + filePath);
        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();

        LinkedHashMap<String, Set<String>> VAMPHInput1 = GenerateVAMPHOrder.generateVAMPHOrder1(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput1, "order1", graph);

        LinkedHashMap<String, Set<String>> VAMPHInput2 = GenerateVAMPHOrder.generateVAMPHOrder2(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput2, "order2", graph);

        LinkedHashMap<String, Set<String>> VAMPHInput3 = GenerateVAMPHOrder.generateVAMPHOrder3(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput3, "order3", graph);

        LinkedHashMap<String, Set<String>> VAMPHInput4 = GenerateVAMPHOrder.generateVAMPHOrder4(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput4, "order4", graph);
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
        for (int i = 0; i < colorGroup.length; i++) {
            colorGroup[i] = -1;
        }

        //In case there is a vertex not connecting any other vertex but self loop
        for (int i = 0; i < colorGroup.length; i++) {
            if (colorGroup[i] == -1) {
                boolean rowCheck = isBipartiteAlgo(graph, i, colorGroup);
                if (rowCheck == false) {
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
        while (vertexQ.size() != 0) {
            //FIFO vertex
            String vertex = vertexQ.pop();
            //check if the vertex has a self loop
            if (graph.get(vertex).contains(vertex)) {
                return false;
            }
            //Go through all adjacent vertex
            Iterator<String> it = graph.get(vertex).iterator();
            while (it.hasNext()) {
                String adj = it.next();
                int adjIndex = Integer.parseInt(adj) - 1;
                int vertexIndex = Integer.parseInt(vertex) - 1;
                if (!visited.contains(adj)) {
                    visited.add(adj);
                    vertexQ.add(adj);
                    //assign the adjacent vertex to opposite color
                    colGup[adjIndex] = 1 - colGup[vertexIndex];

                }
                //if the the vertex and neighbor has the same color, then return False;
                boolean hasEdge = graph.get(vertex).contains(adj);
                if (hasEdge && colGup[adjIndex] == colGup[vertexIndex]) {
                    return false;
                }

            }
        }


        return checker;
    }
}
