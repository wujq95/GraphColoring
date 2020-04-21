import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SingleAlgPerformanceHelper {
    static java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");

    public static void testAlgPerformance(LinkedHashMap<String, Set<String>> input, String order, BipartiteGraph graph) throws IOException {
        FileWriter fw1 = new FileWriter("FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("NewAlgorithm.txt", true);
        PrintWriter pw3 = new PrintWriter(fw3);

        int round = 0;
        int countSameAvgFF = 0;
        HashMap<String, Integer> countFFhashMap = new HashMap<>();
        String avgFF = "";
        double aFF = 0;
        int countSameAvgCBIP = 0;
        HashMap<String, Integer> countCBIPhashMap = new HashMap<>();
        String avgCBIP = "";
        double aCBIP = 0;
        int countSameAvgNewAlg = 0;
        HashMap<String, Integer> countNewAlghashMap = new HashMap<>();
        String avgNewAlg = "";
        double aNewAlg = 0;

        while (countSameAvgFF < 10 && countSameAvgCBIP < 10 && countSameAvgNewAlg < 10) {
            round++;
            int num = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            FirstFit.colorNum = 0;
            FirstFit.map = new HashMap<>();
            CBIP.colorNum = 0;
            CBIP.inputStorage = new HashMap<>();
            CBIP.map = new HashMap<>();
            NewAlgorithm.colorMap = new HashMap<>();
            NewAlgorithm.vertexMap = new HashMap<>();
            NewAlgorithm.I1 = new HashSet<>();
            NewAlgorithm.I2 = new HashSet<>();

            for (Map.Entry<String, Set<String>> entry : input.entrySet()) {
                num++;
                StringBuffer vertex = new StringBuffer(entry.getKey());
                Set<String> neighbor = new HashSet<>();
                neighbor.addAll(entry.getValue());

                ff.FirstFit(vertex.toString(), neighbor);
                cbip.CBIP(vertex.toString(), neighbor);
                newAlgorithm.newAlgorithm(vertex.toString(), neighbor);
            }
            if (FirstFit.checkDup(graph.getAdjacentVertices())) {
                pw1.println("error");
            }
            if (CBIP.checkDup(graph.getAdjacentVertices())) {
                pw2.println("error");
            }
            if (NewAlgorithm.checkDup(graph.getAdjacentVertices())) {
                pw3.println("error");
            }

            if (graph.getP() == 0.0) {
                pw1.println("NO." + round + " test for this graph and input order, the vertices number is " + num + ", input order is " + order + ", the color number used is " + FirstFit.colorNum);
                pw2.println("NO." + round + " test for this graph and input order, the vertices number is " + num + ", input order is " + order + ", the color number used is " + CBIP.colorNum);
                pw3.println("NO." + round + " test for this graph and input order, the vertices number is " + num + ", input order is " + order + ", the color number used is " + newAlgorithm.getColorNum());

            } else {
                pw1.println("NO." + round + " test for this graph and input order, the vertices number is " + num + ", P is " + graph.getP() + ", input order is " + order + ", the color number used is " + FirstFit.colorNum);
                pw2.println("NO." + round + " test for this graph and input order, the vertices number is " + num + ", P is " + graph.getP() + ", input order is " + order + ", the color number used is " + CBIP.colorNum);
                pw3.println("NO." + round + " test for this graph and input order, the vertices number is " + num + ", P is " + graph.getP() + ", input order is " + order + ", the color number used is " + newAlgorithm.getColorNum());
            }
            aFF = (FirstFit.colorNum + aFF * (round - 1)) / (round);
            avgFF = df.format(aFF);

            if (countFFhashMap.get(avgFF) != null) {
                countFFhashMap.put(avgFF, countFFhashMap.get(avgFF) + 1);
            } else {
                countFFhashMap.put(avgFF, 1);
            }

            for (Map.Entry<String, Integer> entry : countFFhashMap.entrySet()) {
                int value = entry.getValue();
                countSameAvgFF = Math.max(countSameAvgFF, value);
            }

            aCBIP = (CBIP.colorNum + aCBIP * (round - 1)) / (round);
            avgCBIP = df.format(aCBIP);

            if (countCBIPhashMap.get(avgCBIP) != null) {
                countCBIPhashMap.put(avgCBIP, countCBIPhashMap.get(avgCBIP) + 1);
            } else {
                countCBIPhashMap.put(avgCBIP, 1);
            }

            for (Map.Entry<String, Integer> entry : countCBIPhashMap.entrySet()) {
                int value = entry.getValue();
                countSameAvgCBIP = Math.max(countSameAvgCBIP, value);
            }

            aNewAlg = (newAlgorithm.getColorNum() + aNewAlg * (round - 1)) / (round);
            avgNewAlg = df.format(aNewAlg);

            if (countNewAlghashMap.get(avgNewAlg) != null) {
                countNewAlghashMap.put(avgNewAlg, countNewAlghashMap.get(avgNewAlg) + 1);
            } else {
                countNewAlghashMap.put(avgNewAlg, 1);
            }

            for (Map.Entry<String, Integer> entry : countNewAlghashMap.entrySet()) {
                int value = entry.getValue();
                countSameAvgNewAlg = Math.max(countSameAvgNewAlg, value);
            }
        }
        pw1.println();
        pw2.println();
        pw3.println();
        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();
    }
}
