import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class BenchMarkTest {

    public static void main(String[] args) throws Exception {
        runBenchmarkTest("/Users/okakiko/GraphColoring/BenchmarkGraphs/email-enron-only.mtx");
        runBenchmarkTest("/Users/okakiko/GraphColoring/BenchmarkGraphs/rt-twitter-copen.mtx");
        runBenchmarkTest("/Users/okakiko/GraphColoring/BenchmarkGraphs/socfb-Caltech36.mtx");
        runBenchmarkTest("/Users/okakiko/GraphColoring/BenchmarkGraphs/socfb-Reed98.mtx");
        runBenchmarkTest("BenchmarkGraphs/web-polblogs.mtx");
    }

    public static void runBenchmarkTest(String filePath) throws Exception {
        Graph graph = new Graph();

        //Read a graph from a mtx file, it will whether the graph is Bipartite Graph or not
        graph.read(filePath);

        //Convert a graph to a Bipartite Graph Using Duplicate Method
        MakeBipartite makeBipartite = new MakeBipartite();
        makeBipartite.duplicateBipartiteMap(graph);

        //Convert a graph to a Bipartite Graph Using Random Method
        MakeBipartite makeBipartite1 = new MakeBipartite();
        makeBipartite1.randomBipartiteMap(graph);

        BipartiteGraph bipartiteGraph = new BipartiteGraph();
        bipartiteGraph.setAdjacentVertices(makeBipartite1.makeGraphMap);
        bipartiteGraph.setVertexVSet(makeBipartite1.vertexVSet);
        bipartiteGraph.setVertexUSet(makeBipartite1.vertexUSet);

        if (!isBipartite(bipartiteGraph.adjacentVertices)) {
            throw new Exception("bipartite conversion failed");
        }

        FileWriter fw1 = new FileWriter("src/FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("src/CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("src/NewAlgorithm.txt", true);
        PrintWriter pw3 = new PrintWriter(fw3);

        pw1.println("random method make bipartite" + filePath);
        pw2.println("random method make bipartite" + filePath);
        pw3.println("random method make bipartite" + filePath);

        for (int i = 0; i < 5; i++) {
            int num = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input = GenerateVAMPHOrder.generateVAMPHOrder1(bipartiteGraph);
            for (Map.Entry<String, Set<String>> entry : input.entrySet()) {
                num++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
            }
            if (FirstFit.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw1.println("error");
            }
            if (CBIP.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw2.println("error");
            }
            if (NewAlgorithm.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw3.println("error");
            }
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num + ", input order is order1, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num + ", input order is order1, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num + ", input order is order1, the color number used is " + newAlgorithm.getColorNum());
        }

        for (int i = 0; i < 5; i++) {
            int num2 = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input2 = GenerateVAMPHOrder.generateVAMPHOrder2(bipartiteGraph);
            for (Map.Entry<String, Set<String>> entry : input2.entrySet()) {
                num2++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
            }

            if (FirstFit.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw1.println("error");
            }
            if (CBIP.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw2.println("error");
            }
            if (NewAlgorithm.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw3.println("error");
            }
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num2 + ", input order is order2, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num2 + ", input order is order2, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num2 + ", input order is order2, the color number used is " + newAlgorithm.getColorNum());
        }

        for (int i = 0; i < 5; i++) {
            int num3 = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input3 = GenerateVAMPHOrder.generateVAMPHOrder3(bipartiteGraph);
            for (Map.Entry<String, Set<String>> entry : input3.entrySet()) {
                num3++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
            }
            if (FirstFit.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw1.println("error");
            }
            if (CBIP.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw2.println("error");
            }
            if (NewAlgorithm.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw3.println("error");
            }
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num3 + ", input order is order3, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num3 + ", input order is order3, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num3 + ", input order is order3, the color number used is " + newAlgorithm.getColorNum());
        }

        for (int i = 0; i < 5; i++) {
            int num4 = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input4 = GenerateVAMPHOrder.generateVAMPHOrder4(bipartiteGraph);
            for (Map.Entry<String, Set<String>> entry : input4.entrySet()) {
                num4++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
            }

            if (FirstFit.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw1.println("error");
            }
            if (CBIP.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw2.println("error");
            }
            if (NewAlgorithm.checkDup(bipartiteGraph.getAdjacentVertices())) {
                pw3.println("error");
            }
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num4 + ", input order is order4, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num4 + ", input order is order4, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num4 + ", input order is order4, the color number used is " + newAlgorithm.getColorNum());
        }

        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();
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
