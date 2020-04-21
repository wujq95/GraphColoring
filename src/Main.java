import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        RandomGraphGenerator rg = new RandomGraphGenerator();

        runBenchmarkTestUsingRandomConversion("BenchmarkGraphs/email-enron-only.mtx");
        runBenchmarkTestUsingRandomConversion("BenchmarkGraphs/rt-twitter-copen.mtx");
        runBenchmarkTestUsingRandomConversion("BenchmarkGraphs/socfb-Caltech36.mtx");
        runBenchmarkTestUsingRandomConversion("BenchmarkGraphs/socfb-Reed98.mtx");
        runBenchmarkTestUsingRandomConversion("BenchmarkGraphs/web-polblogs.mtx");
        runBenchmarkTestUsingDuplicateConversion("BenchmarkGraphs/email-enron-only.mtx");
        runBenchmarkTestUsingDuplicateConversion("BenchmarkGraphs/rt-twitter-copen.mtx");
        runBenchmarkTestUsingDuplicateConversion("BenchmarkGraphs/socfb-Caltech36.mtx");
        runBenchmarkTestUsingDuplicateConversion("BenchmarkGraphs/socfb-Reed98.mtx");
        runBenchmarkTestUsingDuplicateConversion("BenchmarkGraphs/web-polblogs.mtx");

        BipartiteGraph randomGraph1 = rg.generateRandomGraph(934, 0.1372095577);
        runTest(randomGraph1);
        BipartiteGraph randomGraph2 = rg.generateRandomGraph(538, 0.6235786112);
        runTest(randomGraph2);
        BipartiteGraph randomGraph3 = rg.generateRandomGraph(912, 0.1067702834);
        runTest(randomGraph3);
        BipartiteGraph randomGraph4 = rg.generateRandomGraph(224, 0.1026790743);
        runTest(randomGraph4);
        BipartiteGraph randomGraph5 = rg.generateRandomGraph(544, 0.4029546478);
        runTest(randomGraph5);
        BipartiteGraph randomGraph6 = rg.generateRandomGraph(792, 0.09515924825);
        runTest(randomGraph6);
        BipartiteGraph randomGraph7 = rg.generateRandomGraph(862, 0.9955404936);
        runTest(randomGraph7);
        BipartiteGraph randomGraph8 = rg.generateRandomGraph(274, 0.02748643578);
        runTest(randomGraph8);
        BipartiteGraph randomGraph9 = rg.generateRandomGraph(472, 0.3253844811);
        runTest(randomGraph9);
        BipartiteGraph randomGraph10 = rg.generateRandomGraph(322, 0.9085477341);
        runTest(randomGraph10);
        BipartiteGraph randomGraph11 = rg.generateRandomGraph(676, 0.2485958962);
        runTest(randomGraph11);
        BipartiteGraph randomGraph12 = rg.generateRandomGraph(740, 0.7910185249);
        runTest(randomGraph12);
        BipartiteGraph randomGraph13 = rg.generateRandomGraph(726, 0.3942049577);
        runTest(randomGraph13);
        BipartiteGraph randomGraph14 = rg.generateRandomGraph(804, 0.773811221);
        runTest(randomGraph14);
        BipartiteGraph randomGraph15 = rg.generateRandomGraph(852, 0.3497169374);
        runTest(randomGraph15);
        BipartiteGraph randomGraph16 = rg.generateRandomGraph(630, 0.4544677138);
        runTest(randomGraph16);
        BipartiteGraph randomGraph17 = rg.generateRandomGraph(58, 0.3349828104);
        runTest(randomGraph17);
        BipartiteGraph randomGraph18 = rg.generateRandomGraph(294, 0.5530407452);
        runTest(randomGraph18);
        BipartiteGraph randomGraph19 = rg.generateRandomGraph(56, 0.7588546877);
        runTest(randomGraph19);
        BipartiteGraph randomGraph20 = rg.generateRandomGraph(944, 0.1360809436);
        runTest(randomGraph20);
        BipartiteGraph randomGraph21 = rg.generateRandomGraph(944, 0.6605921085);
        runTest(randomGraph21);
        BipartiteGraph randomGraph22 = rg.generateRandomGraph(944, 0.1150660251);
        runTest(randomGraph22);
        BipartiteGraph randomGraph23 = rg.generateRandomGraph(944, 0.233760325);
        runTest(randomGraph23);
        BipartiteGraph randomGraph24 = rg.generateRandomGraph(944, 0.8856993893);
        runTest(randomGraph24);
        BipartiteGraph randomGraph25 = rg.generateRandomGraph(944, 0.0518228636);
        runTest(randomGraph25);
    }

    public static void runBenchmarkTestUsingRandomConversion(String filePath) throws Exception {
        Graph g = new Graph();

        //Read a graph from a mtx file, it will whether the graph is Bipartite Graph or not
        g.read(filePath);

        //Convert a graph to a Bipartite Graph Using Random Method
        MakeBipartite makeBipartite = new MakeBipartite();
        makeBipartite.randomBipartiteMap(g);

        BipartiteGraph graph = new BipartiteGraph();
        graph.setAdjacentVertices(makeBipartite.makeGraphMap);
        graph.setVertexVSet(makeBipartite.vertexVSet);
        graph.setVertexUSet(makeBipartite.vertexUSet);

        if (!isBipartite(graph.adjacentVertices)) {
            throw new Exception("bipartite conversion failed");
        }

        FileWriter fw1 = new FileWriter("FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("NewAlgorithm.txt", true);
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

        runTest(graph);
    }

    public static void runBenchmarkTestUsingDuplicateConversion(String filePath) throws Exception {
        Graph g = new Graph();

        //Read a graph from a mtx file, it will whether the graph is Bipartite Graph or not
        g.read(filePath);

        //Convert a graph to a Bipartite Graph Using Duplicate Method
        MakeBipartite makeBipartite = new MakeBipartite();
        makeBipartite.duplicateBipartiteMap(g);

        BipartiteGraph graph = new BipartiteGraph();
        graph.setAdjacentVertices(makeBipartite.makeGraphMap);
        graph.setVertexVSet(makeBipartite.vertexVSet);
        graph.setVertexUSet(makeBipartite.vertexUSet);

        if (!isBipartite(graph.adjacentVertices)) {
            throw new Exception("bipartite conversion failed");
        }

        FileWriter fw1 = new FileWriter("FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("NewAlgorithm.txt", true);
        PrintWriter pw3 = new PrintWriter(fw3);
        pw1.println("duplicate method make bipartite " + filePath);
        pw2.println("duplicate method make bipartite " + filePath);
        pw3.println("duplicate method make bipartite " + filePath);
        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();

        runTest(graph);
    }

    public static void runTest(BipartiteGraph graph) throws Exception {
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
