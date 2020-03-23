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


}
