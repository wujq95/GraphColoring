import java.util.Iterator;
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
        System.out.println(checkDup(input));
    }


    /**
     * check if there are two adjacent points with same color
     * @param input
     * @return
     */
    public static boolean checkDup(LinkedHashMap<String, Set<String>> input){
        boolean flag = false;
        for(Map.Entry<String, Set<String>> entry:input.entrySet()){
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            int v = FirstFit.map.get(vertex);
            Iterator<String> it = neighbor.iterator();
            while (it.hasNext()){
                String str = it.next();
                int color = FirstFit.map.get(str);
                if(color==v) flag= true;
            }
        }
        return flag;
    }
}
