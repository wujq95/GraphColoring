import java.util.*;

public class FirstFit {

    static HashMap<String, Integer> map = new HashMap<>();
    static Integer colorNum = 0;

    /**
     * FirstFit algorithm
     * @param vertex
     * @param neighbor
     */
    public static void FirstFit(String vertex, Set<String> neighbor) {

        Set<Integer> colorSet = new TreeSet<>();

        //get the colors of neighbors
        Iterator<String> it = neighbor.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            int color = map.get(str);
            colorSet.add(color);
        }

        //find the smallest color
        int num = 1;
        while (!colorSet.add(num)) {
            num++;
        }
        map.put(vertex, num);
        colorNum = Math.max(colorNum, num);
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
            int v = map.get(vertex);
            Iterator<String> it = neighbor.iterator();
            while (it.hasNext()) {
                String str = it.next();
                int color = map.get(str);
                if (color == v) {
                    flag = true;
                }

            }
        }
        return flag;
    }

}
