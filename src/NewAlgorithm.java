
import java.util.*;

public class NewAlgorithm {

    //vertex neighbor information
    static Map<String, Set<String>> vertexMap = new HashMap<>();
    //vertex color information
    static Map<String, Integer> colorMap = new HashMap<>();
    static Set<String> I1;
    static Set<String> I2;

    /**
     * New Algorithm
     * @param vertex
     * @param neighbor
     */
    public void newAlgorithm(String vertex, Set<String> neighbor){
        vertexMap.put(vertex,neighbor);

        //get max color number n from previous vertices
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
        //call getComponent method in each loop to get the connected subgraph
        for(int i=1;i<=max;i++){
            HashSet<String> set= getComponent(i,vertex);
            Iterator it = set.iterator();
            boolean flag1 = false;
            boolean flag2 = false;
            while (it.hasNext()){
                //check if the color is mixed
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
            //get the maximum index i which ai is mixed
            if(flag1&&flag2){
                m=i+1;
            }
        }
        if(m==0) m=1;

        //construct the component graph with index m
        HashSet<String> set= getComponent(m,vertex);
        Iterator it = set.iterator();
        //check the position of new vertex and previous vertices with color am
        if(I1.contains(vertex)){
            boolean flag = false;
            while (it.hasNext()){
                //check whether the other side of subGraph contains vertices with color am
                String str = (String) it.next();
                if((I2.contains(str))&&(colorMap.get(str)==m)) flag = true;
            }
            if(flag){
                //color the vertex with bm
                colorMap.put(vertex,-m);
            } else {
                //color the vertex with am
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

        //store neighbor information into map
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

        //search all vertices in the connected subGraph containing v
        HashSet<String> componentSet = new HashSet<>();
        componentSet.add(vertex);
        Queue<String> queue = new LinkedList<>();
        queue.offer(vertex);
        while(!(queue.size()==0)){
            String str = queue.poll();
            //check the neighbors of the vertex
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
