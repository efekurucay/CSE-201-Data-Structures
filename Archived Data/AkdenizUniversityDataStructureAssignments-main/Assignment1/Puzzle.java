import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ali Çolak
 * 1.11.2021
 * 20190808064
 */
public class Puzzle {

    public static Scanner scanner = new Scanner(System.in);
    public static String firstNumber  ;
    public static String secondNumber ;
    public static String resultNumber ;
    public static ArrayList<String> letters = new ArrayList<>();
    public static ArrayList<String> numbers  = new ArrayList<>();
    public static ArrayList<String> numbers2 = new ArrayList<>();
    public static String [] array = new String[3];




    public static void main(String[] args) {

        getInput();

        for (int i = 0; i < 10; i++) {
            numbers.add(String.valueOf(i));
        }

        System.out.println("searching... found!\n");

        puzzleSolve(letters.size(),numbers,numbers2);




    }

    public static void getInput () {

        System.out.print("Enter 1st input value:   ");
        firstNumber = scanner.nextLine();

        if (firstNumber.length()<2 || firstNumber.length()>6) {
            throw new RuntimeException("The string you enter must be between 2 and 6 characters.");
        }

        array[0] = firstNumber;

        System.out.print("Enter 2st input value:   ");
        secondNumber = scanner.nextLine();

        if (secondNumber.length()<2 || secondNumber.length()>6) {
            throw new RuntimeException("The string you enter must be between 2 and 6 characters.");
        }

        array[1] = secondNumber;

        System.out.print("Enter output value:   ");
        resultNumber = scanner.nextLine();

        if (resultNumber.length()<2 || resultNumber.length()>6) {
            throw new RuntimeException("The string you enter must be between 2 and 6 characters.");
        }

        array[2] = resultNumber;



        for (int i = 0; i < firstNumber.length(); i++) {
            if (!letters.contains(String.valueOf(firstNumber.charAt(i)))) {
                letters.add(String.valueOf(firstNumber.charAt(i)));
            }
        }

        for (int i = 0; i < secondNumber.length(); i++) {
            if (!letters.contains(String.valueOf(secondNumber.charAt(i)))) {
                letters.add(String.valueOf(secondNumber.charAt(i)));
            }
        }

        for (int i = 0; i < resultNumber.length(); i++) {
            if (!letters.contains(String.valueOf(resultNumber.charAt(i)))) {
                letters.add(String.valueOf(resultNumber.charAt(i)));
            }
        }

        if (letters.size()>10) {
            throw  new RuntimeException("Total number of different letters in all words must be less than or equal to 10.");
        }

    }

    public static void puzzleSolve(int k , ArrayList<String> numbers , ArrayList<String> numbers2) {

        for (int i = 0; i < numbers.size(); i++) {
            String temp = numbers.get(i);
            numbers2.add(temp);
            numbers.remove(temp);

            if (k==1) {

                int sum = 0;
                int result = 0;
                int first = 0;
                int second= 0;
                for (int j = 0; j < array.length; j++) {
                    String s = array[j];
                    for (int l = 0; l < s.length(); l++) {
                        String letter = String.valueOf(s.charAt(l));
                        int index = letters.indexOf(letter);
                        int number = Integer.parseInt(numbers2.get(index));
                        for (int m = 0; m < s.length()-1-l; m++) {
                            number*=10;
                        }
                        if (j==0) {
                            first +=number;
                            sum+=number;
                        } else if (j==1) {
                            second +=number;
                            sum+=number;
                        } else result+=number;

                    }
                }

                if (sum==result) {
                    System.out.println(array[0]+":    "+first);
                    System.out.println(array[1]+":    "+second);
                    System.out.println(array[2]+":    "+result);
                }
            } else {
                puzzleSolve(k-1,numbers,numbers2);
            }
            numbers2.remove(temp);
            numbers.add(i,temp);
        }



    }

}
