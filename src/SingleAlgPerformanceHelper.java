import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SingleAlgPerformanceHelper {
    static java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");

    public static AlgorithmResult testAlgPerformance(LinkedHashMap<String, Set<String>> input, String order, BipartiteGraph graph) throws IOException {
        FileWriter fw1 = new FileWriter("FirstFit.txt", true);
        PrintWriter pw1 = new PrintWriter(fw1);
        FileWriter fw2 = new FileWriter("CBIP.txt", true);
        PrintWriter pw2 = new PrintWriter(fw2);
        FileWriter fw3 = new FileWriter("NewAlgorithm.txt", true);
        PrintWriter pw3 = new PrintWriter(fw3);

        AlgorithmResult algorithmResult = new AlgorithmResult();
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
            pw1.println("test for this graph and input order, the vertices number is " + num + ", input order is " + order + ", the color number used is " + FirstFit.colorNum);
            pw2.println("test for this graph and input order, the vertices number is " + num + ", input order is " + order + ", the color number used is " + CBIP.colorNum);
            pw3.println("test for this graph and input order, the vertices number is " + num + ", input order is " + order + ", the color number used is " + newAlgorithm.getColorNum());

        } else {
            pw1.println("test for this graph and input order, the vertices number is " + num + ", P is " + graph.getP() + ", input order is " + order + ", the color number used is " + FirstFit.colorNum);
            pw2.println("test for this graph and input order, the vertices number is " + num + ", P is " + graph.getP() + ", input order is " + order + ", the color number used is " + CBIP.colorNum);
            pw3.println("test for this graph and input order, the vertices number is " + num + ", P is " + graph.getP() + ", input order is " + order + ", the color number used is " + newAlgorithm.getColorNum());
        }

        algorithmResult.setFirstFit(FirstFit.colorNum);
        algorithmResult.setCBIP(CBIP.colorNum);
        algorithmResult.setNewAlg(newAlgorithm.getColorNum());


        pw1.println();
        pw2.println();
        pw3.println();
        pw1.close();
        pw2.close();
        pw3.close();
        fw1.close();
        fw2.close();
        fw3.close();

        return algorithmResult;
    }
}
