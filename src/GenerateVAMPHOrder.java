import java.util.*;

public class GenerateVAMPHOrder {

    //input order presentation: (a) generate the input sequence in random order;
    public static LinkedHashMap<String, Set<String>> generateVAMPHOrder1(BipartiteGraph graph) {
        calculateandsetN(graph);

        Set<String> originalvertexSet = graph.getAdjacentVertices().keySet();
        List<String> vertexList = new ArrayList<>(originalvertexSet);
        Collections.shuffle(vertexList);

        return generateVAMPHModel(graph, vertexList);
    }

    //input order presentation: (b) generate the input sequence by alternatively presenting one node from U and one node from V until all nodes are presented;
    public static LinkedHashMap<String, Set<String>> generateVAMPHOrder2(BipartiteGraph graph) {
        calculateandsetN(graph);

        Set<String> USet = graph.getVertexUSet();
        Set<String> VSet = graph.getVertexVSet();
        List<String> UList = new ArrayList<>(USet);
        Collections.shuffle(UList);
        List<String> VList = new ArrayList<>(VSet);
        Collections.shuffle(VList);

        List<String> vertexList = new LinkedList<>();
        for (int i = 0; i < graph.getN(); i++) {
            vertexList.add(UList.get(i));
            vertexList.add(VList.get(i));
        }
        if (!checkIfTwosidesEqual(graph)) {
            vertexList.add(UList.get(UList.size() - 1));
        }

        return generateVAMPHModel(graph, vertexList);
    }

    //input order presentation: (c) generate the input sequence by alternatively presenting 5 nodes from U and 5 nodes from V;
    public static LinkedHashMap<String, Set<String>> generateVAMPHOrder3(BipartiteGraph graph) {
        calculateandsetN(graph);
        Set<String> USet = graph.getVertexUSet();
        Set<String> VSet = graph.getVertexVSet();
        List<String> UList = new ArrayList<>(USet);
        Collections.shuffle(UList);
        List<String> VList = new ArrayList<>(VSet);
        Collections.shuffle(VList);

        List<String> vertexList = new LinkedList<>();
        int index = (graph.getN()) / 5;
        int lastRound = (graph.getN()) % 5;
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < 5; j++) {
                vertexList.add(UList.get(5 * i + j));
            }
            for (int j = 0; j < 5; j++) {
                vertexList.add(VList.get(5 * i + j));
            }
        }
        for (int i = 0; i < lastRound; i++) {
            vertexList.add(UList.get(5 * index + i));
        }
        if (!checkIfTwosidesEqual(graph)) {
            vertexList.add(UList.get(UList.size() - 1));
        }
        for (int i = 0; i < lastRound; i++) {
            vertexList.add(VList.get(5 * index + i));
        }

        return generateVAMPHModel(graph, vertexList);
    }

    //input order presentation: (d) generate the input sequence by alternatively presenting 1 nodes from U
    // (nodes with even index starting from 0) and 2 nodes from V,
    //if the N is odd, then 3 nodes from V in the last round, and the left nodes from U ;
    public static LinkedHashMap<String, Set<String>> generateVAMPHOrder4(BipartiteGraph graph) {
        calculateandsetN(graph);

        Set<String> USet = graph.getVertexUSet();
        Set<String> VSet = graph.getVertexVSet();
        List<String> UList = new ArrayList<>(USet);
        Collections.shuffle(UList);
        List<String> VList = new ArrayList<>(VSet);
        Collections.shuffle(VList);

        List<String> vertexList = new LinkedList<>();
        int index = (graph.getN()) / 2;
        int lastRound = (graph.getN()) % 2;
        for (int i = 0; i < index; i++) {
            vertexList.add(UList.get(2 * i));

            for (int j = 0; j < 2; j++) {
                vertexList.add(VList.get(2 * i + j));
            }
        }

        for (int i = 0; i < lastRound; i++) {
            vertexList.add(VList.get(2 * index + i));
        }

        for (int i = 0; i < index; i++) {
            vertexList.add(UList.get(2 * i + 1));
        }
        for (int i = 0; i < lastRound; i++) {
            vertexList.add(UList.get(2 * index + i));
        }

        if (!checkIfTwosidesEqual(graph)) {
            vertexList.add(UList.get(UList.size() - 1));
        }

        return generateVAMPHModel(graph, vertexList);
    }

    //generate VAM-PH model based on input order
    public static LinkedHashMap<String, Set<String>> generateVAMPHModel(BipartiteGraph graph, List<String> vertexList) {
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

    public static Set<String> findAppearedNeighbors(Set<String> appeared, Set<String> neighbors) {
        Set<String> intersection = new HashSet<>(neighbors);
        intersection.retainAll(appeared);

        return intersection;
    }

    private static BipartiteGraph calculateandsetN(BipartiteGraph graph) {
        if (graph.getN() == 0) {
            graph.setN(graph.getVertexVSet().size());
            return graph;
        } else {
            return graph;
        }
    }

    private static boolean checkIfTwosidesEqual(BipartiteGraph graph) {
        return graph.getVertexVSet().size() == graph.getVertexUSet().size();
    }
}
