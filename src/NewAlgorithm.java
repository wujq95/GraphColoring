
import java.util.*;

public class NewAlgorithm {

    static ArrayList<HashSet<String>> dList = new ArrayList<>();
    static ArrayList<HashSet<String>> cList = new ArrayList<>();
    static HashMap<String,Integer> res = new HashMap<>();
    int n;
    int d;

    HashMap<String, Set<String>> map = new HashMap<>();
    static boolean flag = false;

    NewAlgorithm() { }


    NewAlgorithm(int n,int d){
        for(int i=0;i<d;i++){
            HashSet<String> set = new HashSet<>();
            dList.add(set);
        }
        this.n = n;
        this.d = d;
    }


    public void newAlgorithm(String vertex, Set<String> neighbor){

        map.put(vertex,neighbor);

        for(int i=0;i<dList.size();i++){
            HashSet<String> set = dList.get(i);
            if(set.size()==0){
                set.add(vertex);
                dList.set(i,set);
                return;
            }
            Iterator it = set.iterator();
            boolean flag = true;
            while(it.hasNext()){
                String str = (String) it.next();
                if(neighbor.contains(str)){
                   flag = false;
                   break;
                }
            }
            if(flag){
                set.add(vertex);
                dList.set(i,set);
                return;
            }
        }

        int index = 0;
        int setSize = 0;

        for(int i=0;i<cList.size();i++){
            HashSet<String> set = cList.get(i);
            int size = set.size();
            int num = (int)Math.pow(d/2/n,Math.pow(2,size-1));
            num *= 2;
            num *= n;
            Iterator it = set.iterator();
            String str = (String) it.next();
            Set setNeighbor = map.get(str);
            while(it.hasNext()){
                String s = (String) it.next();
                setNeighbor.retainAll(map.get(s));
            }
            if(setNeighbor.size()>=num){
                if(size>setSize){
                    index = i;
                    setSize =size;
                }
            }

        }

        if(setSize>0){
            HashSet<String> set = cList.get(index);
            set.add(vertex);
            checkDupli(neighbor,set);
            cList.set(index,set);
        }else{
            HashSet<String> set = new HashSet<>();
            set.add(vertex);
            cList.add(set);
        }

    }

    public int getNum(){
        return dList.size()+cList.size();
    }

    public void checkDupli(Set<String> neighbor,HashSet<String> set){
        Iterator it = set.iterator();
        while(it.hasNext()){
            String str = (String) it.next();
            if(neighbor.contains(str)){
                flag = true;
                return;
            }
        }
    }



}
