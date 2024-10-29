
import java.util.*;



public class exam{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String : ");
        String str = scan.nextLine();

        String [] arr = str.split(" ");
        
        System.out.println(eliminate(arr));
       
    }

    public static int eliminate(String[] arr) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if(stack.isEmpty()){
                stack.push(arr[i]);
                continue;
            }
            if(arr[i].equals(stack.peek())){
                stack.pop();
            }else{
                stack.push(arr[i]);
            }
            
        }
        return stack.size();
    }
}