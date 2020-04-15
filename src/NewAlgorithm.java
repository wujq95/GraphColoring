
import java.util.*;

public class NewAlgorithm {

    static Map<String, Set<String>> vertexMap = new HashMap<>();
    static Map<String, Integer> colorMap = new HashMap<>();
    static Set<String> I1;
    static Set<String> I2;

    public void newAlgorithm(String vertex, Set<String> neighbor){
        vertexMap.put(vertex,neighbor);

        //get max n
        int max = 0;
        for(Map.Entry<String,Integer> entry:colorMap.entrySet()){
            Integer integer  = entry.getValue();
            if(integer>0){
                max = Math.max(max,integer);
            }else{
                max = Math.max(max,-integer);
            }
        }

        int m=0;
        for(int i=1;i<=max;i++){
            HashSet<String> set= getComponent(i,vertex);
            Iterator it = set.iterator();
            boolean flag1 = false;
            boolean flag2 = false;
            while (it.hasNext()){
                String str = (String) it.next();
                if(I1.contains(str)){
                    if(colorMap.get(str)==i){
                        flag1 =true;
                    }
                }else{
                    if(colorMap.get(str)==i){
                        flag2 =true;
                    }
                }
            }
            if(flag1&&flag2){
                m=i+1;
            }
        }
        if(m==0) m=1;
        HashSet<String> set= getComponent(m,vertex);
        Iterator it = set.iterator();
        if(I1.contains(vertex)){
            boolean flag = false;
            while (it.hasNext()){
                String str = (String) it.next();
                if((I2.contains(str))&&(colorMap.get(str)==m)) flag = true;
            }
            if(flag){
                colorMap.put(vertex,-m);
            } else {
                colorMap.put(vertex,m);
            }
        }else{
            boolean flag = false;
            while (it.hasNext()){
                String str = (String) it.next();
                if(I1.contains(str)&&(colorMap.get(str)==m)) flag = true;
            }
            if(flag){
                colorMap.put(vertex,-m);
            } else {
                colorMap.put(vertex,m);
            }
        }

        Iterator iterator = neighbor.iterator();
        while(iterator.hasNext()){
            String neigh = (String) iterator.next();
            Set<String> set2 = vertexMap.get(neigh);
            set2.add(vertex);
            vertexMap.put(neigh,set2);
        }

    }


    /**
     * get the connected component containing v whose colors are form {a1-ai} and {b1-bi}
     * @param n
     * @param vertex
     * @return
     */
    public static HashSet<String> getComponent(int n,String vertex){

        //I1 and I2 are two sides of the connected component
        I1 = new HashSet<>();
        I2 = new HashSet<>();
        I1.add(vertex);

        HashSet<String> componentSet = new HashSet<>();
        componentSet.add(vertex);
        Queue<String> queue = new LinkedList<>();
        queue.offer(vertex);
        while(!(queue.size()==0)){
            String str = queue.poll();
            //check the neighbor of the point
            Set<String> pointNeighbor = vertexMap.get(str);
            Iterator it = pointNeighbor.iterator();
            while(it.hasNext()){
                String neighborName = (String) it.next();
                if(!componentSet.contains(neighborName)){
                    if(colorMap.get(neighborName)<=n){
                        componentSet.add(neighborName);
                        queue.offer(neighborName);
                        if(I1.contains(str)){
                            I2.add(neighborName);
                        }else{
                            I1.add(neighborName);
                        }
                    }
                }
            }
        }

        componentSet.remove(vertex);
        return componentSet;
    }

    /**
     * count the total number of color used
     * @return
     */
    public int getColorNum(){
        Set<Integer> set = new HashSet<>();
        for(Map.Entry<String,Integer> entry:colorMap.entrySet()){
            Integer integer  = entry.getValue();
            set.add(integer);
        }
        return set.size();
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
