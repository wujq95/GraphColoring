import java.util.*;

public class FirstFit {

    static HashMap<String,Integer> map = new HashMap<>();
    static Integer colorNum = 0;
    //FirstFit algorithm
    public static void FirstFit(String vertex, Set<String> neighbor){

        Set<Integer> colorSet = new TreeSet<>();

        //get the color of neighbors
        Iterator<String> it = neighbor.iterator();
        while (it.hasNext()){
            String str = it.next();
            int color = map.get(str);
            colorSet.add(color);
        }

        //find the smallest color
        int num = 1;
        while(!colorSet.add(num)){
            num++;
        }
        map.put(vertex,num);
        colorNum = Math.max(colorNum,num);
    }

    //main method
    public static void main(String[] args) {
        int num = 0;
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();
        LinkedHashMap<String, Set<String>> input = rg.generateVAMPHOrder1(graph);
        for(Map.Entry<String, Set<String>> entry:input.entrySet()){
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            FirstFit(vertex,neighbor);
            //System.out.println(vertex);
            num++;
        }
        System.out.println(num);
        System.out.println(colorNum);
    }
}
