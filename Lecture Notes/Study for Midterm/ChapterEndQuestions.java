
public class ChapterEndQuestions {



    public static void main(String[] args) {
        String input = "Yahya Efe Kuruçay"; 
        int vowelCount = Ch01_2.countVowels(input); // Must be 7 for "Yahya Efe Kuruçay"
        System.out.println("Sesli harf sayısı: " + vowelCount);
    }


}

class Ch01_2 {
//H.W.1.

/* 
*Write a short method in any language that counts the number of 
*vowels in a given character string.
*/

public static int countVowels(String str) {
    int count = 0;
    String vowels = "aeiouAEIOU"; // Hem küçük hem büyük sesli harfler

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        if (vowels.indexOf(ch) != -1) { // Eğer sesli harfse
            count++;
        }
    }
    return count;
}

/* 
*Write a method that takes an array of float values and determines if 
*all thenumbers are different from each other (that is, they are distinct).
*/


/*
*Write a method that takes an array containing the set of all integers in therange 1 to 52 
*and shuffles it into random order. Your method should output eachpossible order with equal probability
 */


    
}