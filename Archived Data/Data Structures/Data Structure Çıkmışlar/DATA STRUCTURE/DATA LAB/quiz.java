
public class quiz {
    public static void main(String[] args) {
      /*   int arr[]  = {1,32,4,3,2,5};
        System.out.println(findSum(arr, arr.length));*/
        int[][] tabell = new int[][] { { 1, 2, 3 ,0}, { 3, 2, 1 ,0}, { 1, 2, 3, 0},{ 1, 2, 3 ,0} };
        int sum = sumarmatriz(tabell, 3, 3);
        System.out.println(sum);
       // System.out.println(findBinary(11));
    }
    public static int findSum(int[] arr, int size){
        if(size <= 0)
            return 0;
        return findSum(arr, size-1) + arr[size-1];
    }

    public static int sumarmatriz(int matriz[][],int n,int m){
    if(n==0 && m==0){
        return matriz[n][m];
    }
    else if(m==0){
        return sumarmatriz(matriz,n-1,matriz.length-1)+matriz[n][m];
    }
    else{
        return sumarmatriz(matriz,n,m-1)+matriz[n][m];
    }
}
public static int findBinary(int decimal){
    int binary;
    if (decimal == 0)
      binary = 0;
   else
      binary = decimal % 2 + 10 * (findBinary(decimal / 2));
    return binary;
}
}
