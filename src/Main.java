import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        FirstFit ff = new FirstFit();
        FirstFit.colorNum=0;

        int num = 0;
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();
        LinkedHashMap<String, Set<String>> input = rg.generateVAMPHOrder1(graph);
        for(Map.Entry<String, Set<String>> entry:input.entrySet()){
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            ff.FirstFit(vertex,neighbor);
            num++;
        }
        System.out.println(num);
        System.out.println(FirstFit.colorNum);
    }
}
