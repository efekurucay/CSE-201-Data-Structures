import java.util.*;
import java.util.LinkedList;

public class list{
    public static void main(String[] args) {
      /*   List<Integer> liste = new LinkedList<>();
        liste.add(5);
        liste.add(9);
        liste.add(15);
        liste.add(321);
        liste.add(5);
        liste.add(9);
        liste.add(15);
        liste.add(321);
        System.out.println(liste);
        remoevAll(liste,liste.size());
        System.out.println("*******************");
        System.out.println(liste);*/


      //  int arrr [] = {3,5,1,6,7,135};
        //System.out.println(chcek(arrr, 3, 1, 1));
        System.out.println(sumAll(123,3,0));
    }
    public static void remoevAll(List<Integer> list, int size){
        if(list.size() == 0 || size == 0)
            return;
        else{
            list.set(size-1, null);
            size--;
            remoevAll(list,size--);
        }    
    }
    public static boolean chcek (int arr[], int i,int j ,int k){
        if(arr[j]+arr[k] == arr[i]){
            return true;
        }else if (k+1 < j){
            return chcek(arr, i, j, k+1);
        }else if(j+1<i){
            return chcek(arr, i, j+1, k);
        }else if(i+1 < arr.length){
            return chcek(arr, i+1, j, k);
        }else{
            return false;
        }

    } 
    public static int sumAll(int number , int size, int sum){
        if(size == 0)
            return sum;
        sum = sum + number%10;
        sumAll(number/10 , size-1, sum);
        return sum;
    }
}