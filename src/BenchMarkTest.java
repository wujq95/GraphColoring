import java.io.IOException;
import java.util.LinkedHashMap;
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

        graph.read("rt-twitter-copen.mtx");
        MakeBipartite makeBipartite = new MakeBipartite();
        makeBipartite.duplicateBipartiteMap(graph);

        RandomGraphGenerator randomGraphGenerator = new RandomGraphGenerator();
        BipartiteGraph bipartiteGraph = new BipartiteGraph();
        bipartiteGraph.setAdjacentVertices(makeBipartite.makeGraphMap);
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
}
