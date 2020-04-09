import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

    	Graph gr = new Graph();
    	gr.read("SampleMMC.mtx");
    	  
    	System.out.println();
    	//gr.printGraphMap(gr.graphMap);
    	
    	MakeBipartite mb1 = new MakeBipartite();
    	mb1.randomBipartiteMap(gr);;
    	//mb1.DisplayBipartiteGraph();
    	
    	MakeBipartite mb2 = new MakeBipartite();
    	mb2.duplicateBipartiteMap(gr);
    	//mb2.DisplayBipartiteGraph();
    	

        FirstFit ff = new FirstFit();
        CBIP cbip = new CBIP();
        FirstFit.colorNum=0;
        CBIP.colorNum = 0;
        
        
        int num = 0;
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();
        LinkedHashMap<String, Set<String>> input = rg.generateVAMPHOrder1(graph);
        int n=1000;
        int d =2;
        NewAlgorithm newAlgorithm = new NewAlgorithm(n,d);

        for(Map.Entry<String, Set<String>> entry:input.entrySet()){
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            //ff.FirstFit(vertex,neighbor);
            //cbip.CBIP(vertex,neighbor);
            num++;
            newAlgorithm.newAlgorithm(vertex,neighbor);
        }

        System.out.println(newAlgorithm.getNum());
        System.out.println(num);
        System.out.println(NewAlgorithm.flag);
        //System.out.println(FirstFit.colorNum);
        //System.out.println(CBIP.colorNum);
        //System.out.println(checkDup(input));
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
            int v = CBIP.map.get(vertex);
            Iterator<String> it = neighbor.iterator();
            while (it.hasNext()){
                String str = it.next();
                int color = CBIP.map.get(str);
                if(color==v) flag= true;
            }
        }
        return flag;
    }
}
