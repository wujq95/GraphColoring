import java.util.*;

public class RandomGraphGenerator {

    //generate random number N, N is the number of vertices of U/V
    public int generateN() {
        return new Random().nextInt(500) + 1;
    }

    //generate random number P, P is the probability that edge {i, j} exists
    public double generateP() {
        return new Random().nextDouble();
    }

    //generate the random graph
    public BipartiteGraph generateRandomGraph() {
        int N = generateN();
        double P = generateP();

        BipartiteGraph graph = new BipartiteGraph();
        Set<String> USet = new HashSet<>();
        Set<String> VSet = new HashSet<>();
        LinkedHashMap<String, Set<String>> adjacentVertices = new LinkedHashMap<>();

        for (int i = 1; i <= N; i++) {
            String U = "U" + i;
            adjacentVertices.put(U, new HashSet<>());
            for (int j = 1; j <= N; j++) {
                String V = "V" + j;
                adjacentVertices.put(V, new HashSet<>());
            }
        }

        for (int i = 1; i <= N; i++) {
            String U = "U" + i;
            USet.add(U);

            for (int j = 1; j <= N; j++) {
                String V = "V" + j;
                VSet.add(V);

                //determine randomly if the edge {Ui, Vj} is included in E using probability P
                double determine = new Random().nextDouble();
                if (determine <= P) {
                    Set<String> UList = adjacentVertices.get(U);
                    UList.add(V);
                    adjacentVertices.put(U, UList);

                    Set<String> VList = adjacentVertices.get(V);
                    VList.add(U);
                    adjacentVertices.put(V, VList);
                }
            }
        }

        graph.setVertexUSet(USet);
        graph.setVertexVSet(VSet);
        graph.setAdjacentVertices(adjacentVertices);

        return graph;
    }

    //input order presentation: (a) generate the input sequence in random order;
    public LinkedHashMap<String, Set<String>> generateVAMPHGraph(BipartiteGraph graph) {
        Set<String> originalvertexSet = graph.getAdjacentVertices().keySet();
        List<String> vertexList = new ArrayList<>(originalvertexSet);
        Collections.shuffle(vertexList);

        return generateVAMPHModel(graph, vertexList);
    }

    //input order presentation: (b) generate the input sequence by alternatively presenting one node from U and one node from V until all nodes are presented;
    public LinkedHashMap<String, Set<String>> generateVAMPHOrder2(BipartiteGraph graph) {
        Set<String> USet = graph.getVertexUSet();
        Set<String> VSet = graph.getVertexVSet();
        List<String> UList = new ArrayList<>(USet);
        Collections.shuffle(UList);
        List<String> VList = new ArrayList<>(VSet);
        Collections.shuffle(VList);

        List<String> vertexList = new LinkedList<>();
        for (int i = 0; i < graph.N; i++) {
            vertexList.add(UList.get(i));
            vertexList.add(VList.get(i));
        }

        return generateVAMPHModel(graph, vertexList);
    }

    //input order presentation: (c) generate the input sequence by alternatively presenting 5 nodes from U and 5 nodes from V;
    public LinkedHashMap<String, Set<String>> generateVAMPHOrder3(BipartiteGraph graph) {
        Set<String> USet = graph.getVertexUSet();
        Set<String> VSet = graph.getVertexVSet();
        List<String> UList = new ArrayList<>(USet);
        Collections.shuffle(UList);
        List<String> VList = new ArrayList<>(VSet);
        Collections.shuffle(VList);

        List<String> vertexList = new LinkedList<>();
        int index = (graph.N) / 5;
        int lastRound = (graph.N) % 5;
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < 5; j++) {
                vertexList.add(UList.get(5 * i + j));
            }
            for (int j = 0; j < 5; j++) {
                vertexList.add(VList.get(5 * i + j));
            }
        }
        for(int i = 0;i<lastRound;i++){
            vertexList.add(UList.get(5 * index + i));
        }
        for(int i = 0;i<lastRound;i++){
            vertexList.add(VList.get(5 * index + i));
        }

        return generateVAMPHModel(graph, vertexList);
    }

    //generate VAM-PH model based on input order
    public LinkedHashMap<String, Set<String>> generateVAMPHModel(BipartiteGraph graph, List<String> vertexList) {
        Set<String> appeared = new HashSet<>();
        LinkedHashMap<String, Set<String>> adjacentVertices = graph.getAdjacentVertices();
        LinkedHashMap<String, Set<String>> graphOrderingInput = new LinkedHashMap<>();
        for (String vertex : vertexList) {
            appeared.add(vertex);

            Set<String> neighbors = adjacentVertices.get(vertex);
            Set<String> appearedNeighbors = findAppearedNeighbors(appeared, neighbors);
            graphOrderingInput.put(vertex, appearedNeighbors);
        }

        return graphOrderingInput;
    }

    public Set<String> findAppearedNeighbors(Set<String> appeared, Set<String> neighbors) {
        Set<String> intersection = new HashSet<>(neighbors);
        intersection.retainAll(appeared);

        return intersection;
    }
}
