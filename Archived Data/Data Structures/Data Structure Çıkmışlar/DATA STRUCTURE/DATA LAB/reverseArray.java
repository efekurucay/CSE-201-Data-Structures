public class reverseArray{
    public static void main(String[] args) {
        int arr [] = {1,2};
        reverse(arr, 1, 0);
        
    }
    public static void reverse(int [] arr, int high, int low){
        if(low < high){
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = arr[low];
            reverse(arr, high-1, low+1);
        }
    
    }
}