import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        FirstFit ff = new FirstFit();
        CBIP cbip = new CBIP();
        FirstFit.colorNum = 0;
        CBIP.colorNum = 0;

        int num = 0;
        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();
        LinkedHashMap<String, Set<String>> input = rg.generateVAMPHOrder1(graph);
        NewAlgorithm newAlgorithm = new NewAlgorithm();

        FileWriter fw1 = new FileWriter("src/FirstFit.txt");
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("src/CBIP.txt");
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("src/NewAlgorithm.txt");
        PrintWriter pw3 = new PrintWriter(fw2);

        for (Map.Entry<String, Set<String>> entry : input.entrySet()) {
            num++;
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            //System.out.println("vertex: " + vertex + " neighbors: " + neighbor);

            ff.FirstFit(vertex,neighbor);
            cbip.CBIP(vertex,neighbor);
            newAlgorithm.newAlgorithm(vertex, neighbor);

            pw1.println(num+" "+FirstFit.colorNum);
            pw1.println(num+" "+CBIP.colorNum);
            pw1.println(num+" "+newAlgorithm.getColorNum());

        }



        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();

        System.out.println(num);
        System.out.println(FirstFit.colorNum);
        System.out.println(CBIP.colorNum);
        System.out.println(newAlgorithm.getColorNum());
        System.out.println(checkDup(graph.adjacentVertices));
    }


    /**
     * check if there are two adjacent points with same color
     *
     * @param input
     * @return
     */
    public static boolean checkDup(LinkedHashMap<String, Set<String>> input) {
        boolean flag = false;
        for (Map.Entry<String, Set<String>> entry : input.entrySet()) {
            String vertex = entry.getKey();
            Set<String> neighbor = entry.getValue();
            int v = NewAlgorithm.colorMap.get(vertex);
            Iterator<String> it = neighbor.iterator();
            while (it.hasNext()) {
                String str = it.next();
                int color = NewAlgorithm.colorMap.get(str);
                if (color == v) {
                    flag = true;
                }

            }
        }
        return flag;
    }


}
