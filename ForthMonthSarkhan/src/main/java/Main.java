import java.util.*;

public class Main {
    public static void main(String[] args) {
        //bir arraydə iki elementin cəmi metoda ötürülən rəqəmə bərabərdirsə, bu zaman, biə yeni arrayi göstərsin.

        int[] arr = {1, 2, 5, 4, 8};
        int a = 7;
        System.out.println(Arrays.toString(checkPair(arr, a)));
//        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
//        for(int i =0;i< arr.length;i++){
//            map.put(i, arr[i]);
//            if(map.containsValue(a-arr[i]))//7-1=6
//                int arr2 [] =  new  int[]{arr[i], (a-arr[i])};
//                System.out.println("Found numbers: " + arr[i] + "and " + (a-arr[i]));
//        }


    }
    public static int [] checkPair(int [] arr, int a){
        Map<Integer,Integer> map = new Hashtable<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for(int i =1;i< arr.length;i++){
            map.put(i, arr[i]);
            if(map.containsValue(a-arr[i]))//7-1=6//7-2=5
                list.set(0,arr[i]);
                list.set(1,a-arr[i]);
           // System.out.println("Found numbers: " + arr[i] + "and " + (a-arr[i]));
        }
        int ar2 [] = {list.get(0),list.get(1)};
        return ar2;
    }
}
