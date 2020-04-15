import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        FileWriter fw1 = new FileWriter("src/FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("src/CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("src/NewAlgorithm.txt", true);
        PrintWriter pw3 = new PrintWriter(fw3);

        pw1.println();
        pw2.println();
        pw3.println();

        RandomGraphGenerator rg = new RandomGraphGenerator();
        BipartiteGraph graph = rg.generateRandomGraph();

        for (int i = 0; i < 5; i++) {
            int num = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input = GenerateVAMPHOrder.generateVAMPHOrder1(graph);
            for (Map.Entry<String, Set<String>> entry : input.entrySet()) {
                num++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
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
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order1, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order1, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order1, the color number used is " + newAlgorithm.getColorNum());
        }

        for (int i = 0; i < 5; i++) {
            int num2 = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input2 = GenerateVAMPHOrder.generateVAMPHOrder2(graph);
            for (Map.Entry<String, Set<String>> entry : input2.entrySet()) {
                num2++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
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
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num2 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order2, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num2 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order2, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num2 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order2, the color number used is " + newAlgorithm.getColorNum());
        }

        for (int i = 0; i < 5; i++) {
            int num3 = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input3 = GenerateVAMPHOrder.generateVAMPHOrder3(graph);
            for (Map.Entry<String, Set<String>> entry : input3.entrySet()) {
                num3++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
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
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num3 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order3, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num3 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order3, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num3 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order3, the color number used is " + newAlgorithm.getColorNum());
        }

        for (int i = 0; i < 5; i++) {
            int num4 = 0;
            FirstFit ff = new FirstFit();
            CBIP cbip = new CBIP();
            FirstFit.colorNum = 0;
            CBIP.colorNum = 0;
            NewAlgorithm newAlgorithm = new NewAlgorithm();

            LinkedHashMap<String, Set<String>> input4 = GenerateVAMPHOrder.generateVAMPHOrder4(graph);
            for (Map.Entry<String, Set<String>> entry : input4.entrySet()) {
                num4++;
                String vertex = entry.getKey();
                Set<String> neighbor = entry.getValue();

                ff.FirstFit(vertex, neighbor);
                cbip.CBIP(vertex, neighbor);
                newAlgorithm.newAlgorithm(vertex, neighbor);
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
            pw1.println("NO." + i + " test for this graph and input order, the vertices number is " + num4 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order4, the color number used is " + FirstFit.colorNum);
            pw2.println("NO." + i + " test for this graph and input order, the vertices number is " + num4 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order4, the color number used is " + CBIP.colorNum);
            pw3.println("NO." + i + " test for this graph and input order, the vertices number is " + num4 + ", N is " + graph.getN() + ", P is " + graph.getP() + ", input order is order4, the color number used is " + newAlgorithm.getColorNum());
        }

        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();
    }
}
