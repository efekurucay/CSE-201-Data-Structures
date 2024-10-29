import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int arr [] = new int[5];
        /*for (int i = 0; i < arr.length; i++) {
            System.out.print("Please enter the number (digit by digit):");    
            arr[i] = scan.nextInt();
        }
        //reverseNumber(arr,arr.length);
        //test_a(1200808019);
        */
        System.out.println(reverse(123456789, 9));
        System.out.println(reverse2(12345));
    }
    public static int reverseNumber(int arr[],int i){
        if(i==0){
            return arr[i];
        }
        System.out.print(arr[i-1]);
        return reverseNumber(arr,i-1);
    }
    public static void test_a(int n){
        System.out.print(n + "-");
        n = n-2;
        if(n>0){
            test_a(n%10);
        }
    }
    public static String reverse(int number, int i){
        String str = String.valueOf(number);
        if(i==1)
            return str;
        str = ""+str.charAt(i-1);
        number = number/10;
        return str + reverse(number, i-1);
        
    }
    public static String reverse2(int number){
        String str = ""+ number;
        if(str.length()==1)
            return str;
        return str.charAt(str.length()-1) + reverse2(number/10);
    }
}
