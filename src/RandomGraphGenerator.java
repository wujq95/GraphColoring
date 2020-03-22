import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

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

    //different ordering input
    //(a) generate the input sequence in random order;
    // (c) generate the input sequence by alternatively presenting 5 nodes from U and 5 nodes from V ;
    // (d) anything else you might want to try.
    public LinkedHashMap<String, Set<String>> generateVAMPHOrder1(BipartiteGraph graph) {
        int N = graph.N;
        int random = new Random().nextInt(2 * N) + 1;

        if (random <= N) {

        } else {

        }

        return null;
    }

    // (b) generate the input sequence by alternatively presenting one node from U and one node from V until all nodes are presented;
    public LinkedHashMap<String, Set<String>> generateVAMPHOrder2(BipartiteGraph graph) {
        int N = graph.N;
        int randomU = new Random().nextInt(N) + 1;
        int randomV = new Random().nextInt(N) + 1;

        LinkedHashMap<String, Set<String>> VAMPH = new LinkedHashMap<>();
        Set<String> neighbors = graph.getAdjacentVertices().get("U" + randomU);

        return null;
    }

    public Set<String> findAppearedNeighbors(Set<String> appeared, Set<String> neighbors) {
        Set<String> intersection = new HashSet<>(neighbors);
        intersection.retainAll(appeared);

        return intersection;
    }
}
