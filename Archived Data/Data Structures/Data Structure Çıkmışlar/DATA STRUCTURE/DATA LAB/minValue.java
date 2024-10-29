public class minValue{
    public static void main(String[] args) {
        int arr [] = {0,-1,2,3,4,5,6,7,8,9};
        
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(min > arr[i])
                min = arr[i];
        }
        System.out.println(min);

        //Reverse
        
        for (int i = 0; i < arr.length/2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length -1 -i];
            arr[arr.length -1 -i] = temp;

        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
    
}