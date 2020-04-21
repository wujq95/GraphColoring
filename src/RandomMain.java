import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

public class RandomMain {

    public static void main(String[] args) throws Exception {
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph(30,0.3);

       Main.runTest(graph);
    }


}
