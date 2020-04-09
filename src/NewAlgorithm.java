
import java.util.*;

public class NewAlgorithm {

    static Map<String, Set<String>> vertexMap = new HashMap<>();
    static Map<String, Integer> colorMap = new HashMap<>();

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
                if(str.startsWith("U")){
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
        if(vertex.startsWith("U")){
            boolean flag = false;
            while (it.hasNext()){
                String str = (String) it.next();
                if((str.startsWith("V"))&&(colorMap.get(str)==m)) flag = true;
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
                if((str.startsWith("U"))&&(colorMap.get(str)==m)) flag = true;
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


    public static HashSet<String> getComponent(int n,String vertex){

        HashSet<String> componentSet = new HashSet<>();
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
                    }
                }
            }
        }

        return componentSet;
    }

    public int getColorNum(){
        Set<Integer> set = new HashSet<>();
        for(Map.Entry<String,Integer> entry:colorMap.entrySet()){
            Integer integer  = entry.getValue();
            set.add(integer);
        }
        return set.size();
    }

}
