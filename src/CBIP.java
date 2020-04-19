import java.util.*;

public class CBIP {

    static HashMap<String, Set<String>> inputStorage = new HashMap<>();
    static HashMap<String,Integer> map = new HashMap<>();
    static Integer colorNum = 0;

    /**
     * CBIP algorithm
     * @param vertex
     * @param neighbor
     */
    public void CBIP(String vertex, Set<String> neighbor){

        //store the neighbor information to new vertex and previous vertices
        inputStorage.put(vertex,neighbor);
        Iterator iterator = neighbor.iterator();
        while(iterator.hasNext()){
            String neigh = (String) iterator.next();
            Set<String> set = inputStorage.get(neigh);
            set.add(vertex);
            inputStorage.put(neigh,set);
        }

        //mark all vertices in the connected component using the similar method to BFS
        HashMap<String,Integer> mark = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(vertex);
        mark.put(vertex,1);

        while(!(queue.size()==0)){
            String str = queue.poll();
            //check the neighbor of the vertex
            Set<String> pointNeighbor = inputStorage.get(str);
            Iterator it = pointNeighbor.iterator();
            while(it.hasNext()){
                String neighborName = (String) it.next();
                //mark two kinds of vertices with two number
                if(!mark.containsKey(neighborName)){
                    if(mark.get(str)==1){
                        mark.put(neighborName,2);
                    }else{
                        mark.put(neighborName,1);
                    }
                    queue.offer(neighborName);
                }
            }
        }

        Set<Integer> colorSet = new TreeSet<>();
        //get all points in ~Sv(not contain v)
        for(Map.Entry<String,Integer> entry:mark.entrySet()){
            if(entry.getValue()==2){
                colorSet.add(map.get(entry.getKey()));
            }
        }
        //find the smallest color
        int num = 1;
        while(!colorSet.add(num)){
            num++;
        }
        map.put(vertex,num);
        colorNum = Math.max(colorNum,num);
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
