import java.util.*;

public class Main {

    public static void main(String[] args) {

        FirstFit ff = new FirstFit();
        CBIP cbip = new CBIP();
        FirstFit.colorNum=0;
        CBIP.colorNum = 0;

        int num = 0;
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();
        LinkedHashMap<String, Set<String>> input = rg.generateVAMPHOrder1(graph);
        NewAlgorithm newAlgorithm = new NewAlgorithm();


        for(Map.Entry<String, Set<String>> entry:input.entrySet()){
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            //ff.FirstFit(vertex,neighbor);
            //cbip.CBIP(vertex,neighbor);
            newAlgorithm.newAlgorithm(vertex,neighbor);
            num++;

        }

        System.out.println(num);
        //System.out.println(FirstFit.colorNum);
        //System.out.println(CBIP.colorNum);
        System.out.println(newAlgorithm.getColorNum());
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
            int v = NewAlgorithm.colorMap.get(vertex);
            Iterator<String> it = neighbor.iterator();
            while (it.hasNext()){
                String str = it.next();
                int color = NewAlgorithm.colorMap.get(str);
                if(color==v) {
                    flag= true;
                }

            }
        }
        return flag;
    }



}
