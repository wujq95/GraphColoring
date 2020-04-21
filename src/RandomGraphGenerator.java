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

    //generate the random graph, if N&P = 0, then generate random N and P, other than that, use the user-defined N and P.
    public BipartiteGraph generateRandomGraph(int verticesNumber, double P) {
        int N;
        if (verticesNumber == 0 && P == 0) {
            N = generateN();
            P = generateP();
        } else {
            N = verticesNumber / 2;
        }

        BipartiteGraph graph = new BipartiteGraph();
        Set<String> USet = new HashSet<>();
        Set<String> VSet = new HashSet<>();
        LinkedHashMap<String, Set<String>> adjacentVertices = new LinkedHashMap<>();

        graph.setN(N);
        graph.setP(P);

        for (int i = 1; i <= N; i++) {
            String U = String.valueOf(i);
            adjacentVertices.put(U, new HashSet<>());
            for (int j = 1; j <= N; j++) {
                String V = String.valueOf(N + j);
                adjacentVertices.put(V, new HashSet<>());
            }
        }

        for (int i = 1; i <= N; i++) {
            String U = String.valueOf(i);
            USet.add(U);

            for (int j = 1; j <= N; j++) {
                String V = String.valueOf(N + j);
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
}
