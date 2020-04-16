import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();

        LinkedHashMap<String, Set<String>> VAMPHInput1 = GenerateVAMPHOrder.generateVAMPHOrder1(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput1, "order1", graph);

        LinkedHashMap<String, Set<String>> VAMPHInput2 = GenerateVAMPHOrder.generateVAMPHOrder2(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput2, "order2", graph);

        LinkedHashMap<String, Set<String>> VAMPHInput3 = GenerateVAMPHOrder.generateVAMPHOrder3(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput3, "order3", graph);

        LinkedHashMap<String, Set<String>> VAMPHInput4 = GenerateVAMPHOrder.generateVAMPHOrder4(graph);
        SingleAlgPerformanceHelper.testAlgPerformance(VAMPHInput4, "order4", graph);
    }


}
