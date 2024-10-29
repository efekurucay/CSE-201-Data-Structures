import java.util.*;

public class App {    
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,6,7,8,9};
        print(arr);
        // ARRAY 
        int lenght = arr.length;
        for (int i = 0; i < arr.length/2; i++) {
            int num = arr[--lenght];
            arr[lenght] = arr[i];
            arr[i] = num;
        }
        print(arr);
        
        // LINKEDLIST 
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        int size = list.size();
        printList(list);

        for (int i = 0; i < list.size()/2; i++) {
            int numList = list.get(--size);
            list.set(size, list.get(i));
            list.set(i, numList);
            
        }
        printList(list);

        }

    public static void print(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }
    public static void printList(LinkedList<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) +"   ");
        }
        System.out.println();
    }
}
