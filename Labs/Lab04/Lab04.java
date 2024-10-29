import java.util.ArrayList;
import java.util.List;
/**
 * Akdeniz University CSE201 Data Structures Labs
 * Yahya Efe Kurucay
 * 10.10.2024
 * Description: Lab04
 * Proposed grade: 
 * Website: https://efekurucay.com
 */

/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */
 
public class Lab04 {
    public static void main(String[] args) {

        //factorial test
        System.out.println("Factorial of 5: " + factorial(5));
        // power test
        System.out.println("2 raised to power 3: " + power(2, 3));
        //palindrome test
        System.out.println("Is 'efe' a palindrome? " + isPalindrome("efe"));
        
        //binary search test
        int[] sortedArray = {1, 2, 3, 4, 5};
        System.out.println("Index of 3 in array: " + binarySearch(sortedArray, 3, 0, sortedArray.length - 1));

        //fibonacci;
          
        System.out.println("5th Fibonacci number: " + fibonacci(5));

        //better fib
        int n=99 ; // Örnek değer
        long[] sequence = new long[n + 1]; // Fibonacci dizisini tutacak dizi
        long result = betterFibonacci(n, sequence);
        System.out.println("Fibonacci(" + n + ") = " + result); // Sonucu yazdır


        //perm
        System.out.println("Permutations of 'abc':");
        generatePermutations("abc", "");
        //
        System.out.println("Subset sum exists? " + subsetSum(new int[]{1, 2, 3, 4}, 4, 5));
        
        //8
        List<List<Integer>> subsequences = getSubsequences(new int[]{1, 2, 3}, 0, new ArrayList<>());
        System.out.println("All subsequences: " + subsequences);
        
        
        //9
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        System.out.println("Original List: " + list);
        list.head = list.reverse(list.head);
        System.out.println("Reversed List: " + list);

        
        
    }


     /*
     * 1. Write a recursive method 'static int factorial(int n)' 
     * that returns the factorial of given n
     */

    static int factorial(int n) {
        if(n<0)return -1;
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    /*
     * 2. Write a recursive method 'static double power(double base, int exponent)'
     *  to compute the result of raising base to the power of exponent
     */
    static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) return 1 / power(base, -exponent);
        return base * power(base, exponent - 1);
    }

     /*
     * 3. Write a recursive method 'static boolean isPalindrome(String s)' 
     *  that checks if a given string is palindrome
     */
    static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }
     /*4. Write a recursive method 
     * 'static int binarySearch(int[] arr, int target, int low, int high)'
     *  to find the index of target in a sorted array
     */
    static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) return -1; // Target not found
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] > target) return binarySearch(arr, target, low, mid - 1);
        return binarySearch(arr, target, mid + 1, high);
    }
     /*5.1. Write a recursive method 'static int fibonacci(int n)'
     *  that returns the nth fibonacci number
     *  fibonacci(5) should return 3: sequence(0, 1, 1, 2, 3, 5)
     */

     static int fibonacci(int n){
        if (n <=1) return 0;
        if (n ==2)return 1;
        return fibonacci(n-1) + fibonacci (n-2);

     }

     /** 5.2. Write a recursive method 'static int betterFibonacci(int n, int[] sequence)'
     *  that returns the nth fibonacci number
     *  betterFibonacci(5) should return 3: sequence(0, 1, 1, 2, 3, 5)
     */
 
     static long betterFibonacci(int n, long[] sequence) {
        if (n <=1) {
            sequence[0] = 0;
            return 0;
        }
        if (n == 2) {
            sequence[1] = 1;
            return 1;
        }

        if (sequence[n] != 0) {
            return sequence[n];
        }
        sequence[n] = betterFibonacci(n - 1, sequence) + betterFibonacci(n - 2, sequence);
        return sequence[n];
    }
    
     /* 5.3. Investigate the performance difference between 5.1 and 5.2
     */

    /*6. Write a recursive method 'static void generatePermutations(String str, string prefix)'
     *  that prints all permutations
     * geneatePermutations("abc", "") should print abc, acb, bac, bca, cab, cba
     */
    
    static void generatePermutations(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                generatePermutations(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i));
            }
        }
    }

    /*7. Write a recursive method 'static boolean subsetSum(int[] array, int n, int sum)'
     *  that determines if there exists a subset of the array  that adds up to sum
     * subsetSum(new int[]{1, 2, 3, 4}, 4, 5) should return true because 1 + 4 = 5
     */

    static boolean subsetSum(int[] array, int n, int sum) {
        if (sum == 0) return true; 
        if (n == 0) return false; 
        if (array[n - 1] > sum) return subsetSum(array, n - 1, sum);
        return subsetSum(array, n - 1, sum) || subsetSum(array, n - 1, sum - array[n - 1]);
    }

    /*8. Write a recursive method List<List<Integer>> getSubsequences(int[] array, int n, List<Integer> current)
     *  that returns all subsequences of an array.
     * getSubsequences(new int[]{1, 2, 3}, 0, new ArrayList<>()) should return list similar to
     * [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]
     */
    static List<List<Integer>> getSubsequences(int[] array, int n, List<Integer> current) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == array.length) {
            result.add(new ArrayList<>(current));
            return result;
        }
  

        result.addAll(getSubsequences(array, n + 1, current));
     
        current.add(array[n]);
        result.addAll(getSubsequences(array, n + 1, current));
        current.remove(current.size() - 1);
        return result;
    }

   /** 9. Implement a basic singly linked list
      * 9.1. Implement a Node class
      * 9.1.1 Node class with only public data and next fields
      * 9.1.2. should have a toString() method to test your code
      * 9.2. Implement a SinglyLinkedList class
      * 9.2.1. SinglyLinkedList class should only have a public head field
      * 9.2.2. void addLast(T data)
      * 9.2.3. Write a recursive method Node<T> reverse(Node<T> node)
      * which reverses a linked list with its given head
      * 9.2.4. should have a toString() method to test your code
      */
    static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    static class SinglyLinkedList<T> {
        public Node<T> head;

        public void addLast(T data) {
            Node<T> newNode = new Node<>(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        public Node<T> reverse(Node<T> node) {
            if (node == null || node.next == null) return node;
            Node<T> rest = reverse(node.next);
            node.next.next = node;
            node.next = null;
            return rest;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<T> current = head;
            while (current != null) {
                sb.append(current.data).append(" -> ");
                current = current.next;
            }
            sb.append("null");
            return sb.toString();
        }
    }

   

     
}
