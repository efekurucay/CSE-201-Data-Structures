import java.util.Scanner;

/**
 * @author Ali Çolak
 * 23.11.2021
 */
public class HW2_20190808064 {

    /**
     * main metodu kullanicidan girdileri alir ve ona gore de bir cikti verir.
     * @param args ifadesi run time arguments dir.
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Give the type of input? int=0, string=1, double=2");

        int choice = scanner.nextInt();

        System.out.println("Give the type of input? or=0, and=1, xor=2, complement=3, right shift=4, left shift=5");

        int choice2 = scanner.nextInt();

        if (choice==0){

            if (choice2==0 || choice2==1 || choice2==2 ) {

                System.out.println("First Integer Number : ");
                int number1 = scanner.nextInt();

                System.out.println("Second Integer Number : ");
                int number2 = scanner.nextInt();

                String str = convert(number1);

                String str2 = convert(number2);

                if (choice2==0) {

                    System.out.println(str+" |");
                    System.out.println(str2);
                    System.out.println("------------------------------------");
                    String result = or(str,str2);
                    int result2= binaryToDecimal(result);
                    System.out.print(result+"  = "+result2);


                } else if (choice2==1) {

                    System.out.println(str+" &");
                    System.out.println(str2);
                    System.out.println("------------------------------------");
                    String result = and(str,str2);
                    int result2= binaryToDecimal(result);
                    System.out.print(result+"  = "+result2);

                } else if (choice2==2){

                    System.out.println(str+" ^");
                    System.out.println(str2);
                    System.out.println("------------------------------------");
                    String result = xor(str,str2);
                    int result2= binaryToDecimal(result);
                    System.out.print(result+"  = "+result2);

                }

            } else if (choice2==3) {

                System.out.println("Integer Number : ");
                int number = scanner.nextInt();

                String str = convert(number);

                String result = complement(str);
                System.out.print("~"+str+"  = "+result+"  = "+(number+1)*(-1));


            } else if (choice2==4 || choice2==5) {

                System.out.println("Integer Number : ");
                int number = scanner.nextInt();

                System.out.println("Shift Number : ");
                int shift = scanner.nextInt();

                String str = convert(number);

                if (choice2==4) {

                    System.out.print(number+" >> "+shift+" = "+rs(str,shift)+"  = "+binaryToDecimal(rs(str,shift)));

                } else if (choice2==5) {

                    System.out.print(number+" << "+shift+" = "+ls(str,shift)+"  = "+binaryToDecimal(ls(str,shift)));

                }


            } else throw new RuntimeException("Geçersiz bir secim yaptiniz");


        } else if (choice==1){

            Scanner scanner2 = new Scanner(System.in);

            if (choice2==0 || choice2==1 || choice2==2 ) {

                System.out.println("First String : ");
                String string1 = scanner2.nextLine();

                System.out.println("Second String : ");
                String string2 = scanner2.nextLine();

                String str1 = convert(string1,string2)[0];
                String str2 = convert(string1,string2)[1];

                if (choice2==0) {

                    System.out.println(str1+"|");
                    System.out.println(str2);
                    System.out.println("-------------------------------------------------------------------------");
                    String result = or(str1,str2);
                    String result2= binaryToString(result);
                    System.out.print(result+"  = "+result2);


                } else if (choice2==1) {

                    System.out.println(str1+"&");
                    System.out.println(str2);
                    System.out.println("-------------------------------------------------------------------------");
                    String result = and(str1,str2);
                    String result2= binaryToString(result);
                    System.out.print(result+"  = "+result2);

                } else if (choice2==2){

                    System.out.println(str1+"^");
                    System.out.println(str2);
                    System.out.println("-------------------------------------------------------------------------");
                    String result = xor(str1,str2);
                    String result2= binaryToString(result);
                    System.out.print(result+"  = "+result2);

                }
            } else throw new RuntimeException("Geçersiz bir secim yaptiniz");


        } else if (choice==2) {


            if (choice2==0 || choice2==1 || choice2==2 ) {

                System.out.println("First Double Number : ");
                double number1 = scanner.nextDouble();

                System.out.println("Second Double Number : ");
                double number2 = scanner.nextDouble();

                String str1= convert(number1);

                String str2 = convert(number2);

                if (choice2==0) {

                    System.out.println(str1+" |");
                    System.out.println(str2);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.print(or(str1,str2));

                } else if (choice2==1){

                    System.out.println(str1+" &");
                    System.out.println(str2);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.print(and(str1,str2));

                } else if (choice2==2) {

                    System.out.println(str1+"^");
                    System.out.println(str2);
                    System.out.println("------------------------------------------------------------------------");
                    System.out.print(xor(str1,str2));

                }

            } else throw new RuntimeException("Geçersiz bir secim yaptiniz");


        } else throw new RuntimeException("Geçersiz bir secim yaptiniz");


    }

    /**
     * convert metodu parametre olarak verilen integer sayiyi binary formatina donusturur ve geriye String olarak dondurur.
     * @param number degeri kullanici tarafindan verilen  binary formatina donusturecegimiz integer sayidir.
     * @return geriye dondurdugumuz deger ise parametre olarak number sayinin binary ifadesidir.
     */

    public static String convert (int number) {

        int [] binary = new int[32];
        String str = "";

        int index = 31;

        while(number > 0){
            binary[index--] = number%2;
            number/=2;
        }

        for (int i = 0; i < binary.length; i++) {
            if (i!=0 && i%8==0) str+=" ";
            str+=binary[i];
        }

        return str;
    }

    /**
     * convert metodu parametre olarak verilen String kelimeleri binary formatina donusturur ve geriye String array olarak dondurur.
     * @param str1 degeri kullanici tarafindan verilen binary formatina donusturecegimiz ilk String ifadedir.
     * @param str2 degeri kullanici tarafindan verilen binary formatina donusturecegimiz ikinci String ifadedir.
     * @return geriye dondurdugumuz String array ise str1 ve str2 nin binary formatindaki ifadesidir   array[0] = str1 in binary hali   array[1] = str2 nin binary hali .
     */

    public static String [] convert (String str1, String str2) {

        String s = "";
        String s2 = "";
        String result1 = "";
        String result2 = "";
        String strHigh;
        String strLow;
        if (str1.length()>str2.length()) {
            strHigh = str1;
            strLow = str2;
        } else {
            strHigh = str2;
            strLow = str1;
        }

        char[] charArray = strHigh.toCharArray();

        for (char c : charArray) {
            s += Integer.toBinaryString(c) + " ";
        }
        String [] array = s.split(" ");
        for (String value : array) {
            if (value.length() == 7) {
                result1 += "0";
            }
            result1 += value + " ";
        }

        for (int j = 0; j < strHigh.length() -strLow.length(); j++) {
            s2+="00000000 ";
        }

        char[] charArray2 = strLow.toCharArray();

        for (char c : charArray2) {
            s2 += Integer.toBinaryString(c) + " ";
        }

        String [] array2 = s2.split(" ");
        for (String value : array2) {
            if (value.length() == 7) {
                result2 += "0";
            }
            result2 += value + " ";
        }

        if (str1.equals(strHigh)) return new String[] {result1,result2};
        else return new String[] {result2, result1};
    }

    /**
     * convert metodu parametre olarak verilen double sayiyi binary formatina donusturur ve geriye String olarak dondurur.
     * @param number degeri kullanici tarafindan verilen  binary formatina donusturecegimiz double sayidir.
     * @return geriye dondurdugumuz deger ise parametre olarak number sayinin binary ifadesidir.
     */

    public static String convert (double number) {

        int [] binary = new int[64];
        String str = "";

        String s = Long.toBinaryString(Double.doubleToRawLongBits(number));

        for (int i = 0; i < binary.length-s.length(); i++) {
            binary[i] = 0;
        }

        int counter = 0;
        for (int i = binary.length-s.length(); i < binary.length; i++) {
            binary[i] = Integer.valueOf(String.valueOf(s.charAt(counter)));
            counter++;
        }

        for (int i = 0; i < binary.length; i++) {
            if (i!=0 && i%8==0) str+=" ";
            str+=binary[i];
        }

        return str;
    }

    /**
     * binaryToDecimal metodu parametre olarak verilen String formatindaki binary ifadeyi integer sayiya donusturur ve geriye int olarak dondurur.
     * @param str degeri integer sayilara (and or xor rs ls) islemlerini yaptiktan sonra cikan binary ifadedir.
     * @return geriye dondurdugumuz deger ise verilen str adindaki binary ifadenin integer formatina donusturulmus halidir.
     */

    public static int binaryToDecimal (String str) {

        String s = "";
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)!=' ') s+=String.valueOf(str.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='0') result+=0;
            else result+=Math.pow(2,s.length()-1-i);
        }

        return result;

    }

    /**
     * binaryToString metodu parametre olarak verilen String formatindaki binary ifadeyi String ifadeye donusturur ve geriye String olarak dondurur.
     * @param str degeri String ifadelere (and or xor rs ls) islemlerini yaptiktan sonra cikan binary ifadedir.
     * @return geriye dondurdugumuz deger ise verilen str adindaki binary ifadenin String formatina donusturulmus halidir.
     */

    public static String binaryToString (String str) {

        String s = "";

        for(int index = 0; index < str.length(); index+=9) {
            String temp = str.substring(index, index+8);
            int num = Integer.parseInt(temp,2);
            char letter = (char) num;
            s = s+letter;
        }

        return s;
    }

    /**
     * or metodu parametre olarak verilen String formatindaki binary iki ifadeyi or islemi yapar ve geriye String olarak dondurur.
     * @param str1 ifadesi binary haline donusturulmus or isleminde kullanicagimiz ilk binary ifadedir. Duruma gore bir integer,String ve ya double bir ifadenin binary hali olabilir.
     * @param str2 ifadesi binary haline donusturulmus or isleminde kullanicagimiz ikinci binary ifadedir. Duruma gore bir integer,String ve ya double bir ifadenin binary hali olabilir.
     * @return geriye dondurdugumuz deger ise or islemi icin verilen binary formattaki str1 ve str2 ifadelerinin or islemi uygulandiktan sonraki halidir. Yine ayni sekilde binary formattadir.
     */

    public static String or(String str1, String str2) {

        String result = "";

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i)==' ') {
                result+=" ";
                continue;
            }
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);
            if (char1=='0' && char2=='0') result+="0";
            else result+="1";
        }
        return result;
    }

    /**
     * and metodu parametre olarak verilen String formatindaki binary iki ifadeyi and islemi yapar ve geriye String olarak dondurur.
     * @param str1 ifadesi binary haline donusturulmus and isleminde kullanicagimiz ilk binary ifadedir. Duruma gore bir integer,String ve ya double bir ifadenin binary hali olabilir.
     * @param str2 ifadesi binary haline donusturulmus and isleminde kullanicagimiz ikinci binary ifadedir. Duruma gore bir integer,String ve ya double bir ifadenin binary hali olabilir.
     * @return geriye dondurdugumuz deger ise and islemi icin verilen binary formattaki str1 ve str2 ifadelerinin and islemi uygulandiktan sonraki halidir. Yine ayni sekilde binary formattadir.
     */

    public static String and(String str1, String str2) {

        String result = "";

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i)==' ') {
                result+=" ";
                continue;
            }
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);
            if (char1=='1' && char2=='1') result+="1";
            else result+="0";
        }
        return result;
    }

    /**
     * xor metodu parametre olarak verilen String formatindaki binary iki ifadeyi xor islemi yapar ve geriye String olarak dondurur.
     * @param str1 ifadesi binary haline donusturulmus xor isleminde kullanicagimiz ilk binary ifadedir. Duruma gore bir integer,String ve ya double bir ifadenin binary hali olabilir.
     * @param str2 ifadesi binary haline donusturulmus xor isleminde kullanicagimiz ikinci binary ifadedir. Duruma gore bir integer,String ve ya double bir ifadenin binary hali olabilir.
     * @return geriye dondurdugumuz deger ise xor islemi icin verilen binary formattaki str1 ve str2 ifadelerinin xor islemi uygulandiktan sonraki halidir. Yine ayni sekilde binary formattadir.
     */

    public static String xor(String str1, String str2) {

        String result = "";

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i)==' ') {
                result+=" ";
                continue;
            }
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);
            if ( (char1=='0' && char2=='1') || (char1=='1' && char2=='0')) result+="1";
            else result+="0";
        }
        return result;
    }

    /**
     * complement metodu parametre olarak verilen String formatindaki binary ifadeyi complement islemi yapar ve geriye String olarak dondurur.
     * @param str ifadesi binary haline donusturulmus complement islemi yapacagimiz binary ifadedir. Integer bir ifadenin binary halidir.
     * @return geriye dondurdugumuz deger ise complement islemi icin verilen binary formattaki str ifadeseinin complement islemi uygulandiktan sonraki halidir. Yine ayni sekilde binary formattadir.
     */

    public static String complement(String str) {

        String s = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)==' ') s+=" ";
            else if (str.charAt(i)=='0') s+="1";
            else s+="0";
        }

        return s;

    }

    /**
     * rs metodu parametre olarak verilen String formatindaki binary ifadeyi ayni sekilde parametre olarak verilen integer shift degerine gore rs islemi yaparak ve geriye String olarak dondurur.
     * @param str ifadesi binary haline donusturulmus right shift islemi yapacagimiz binary ifadedir. Integer bir ifadenin binary halidir.
     * @param shift degeri binary halde verilen str ifadesinin kaç tane right shift islemi yapacagimizi belirtir. Integerdir.
     * @return geriye dondurdugumuz deger ise str ifadesinin shift degerine gore right shift yapilmis halidir. Binary formattadir.
     */

    public static String rs(String str, int shift) {

        String s = "";
        String str2="";
        String str3= "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)!=' ') str2+=String.valueOf(str.charAt(i));
        }

        for (int i = 0; i < shift; i++) {
            str3+="0";
        }
        for (int i = shift; i < str2.length(); i++) {
            str3+=String.valueOf(str2.charAt(i-shift));
        }

        for (int i = 0; i < str3.length(); i++) {
            if (i!=0 && i%8==0) s+=" ";
            s+=str3.charAt(i);
        }

        return s;

    }

    /**
     * ls metodu parametre olarak verilen String formatindaki binary ifadeyi ayni sekilde parametre olarak verilen integer shift degerine gore ls islemi yaparak ve geriye String olarak dondurur.
     * @param str ifadesi binary haline donusturulmus left shift islemi yapacagimiz binary ifadedir. Integer bir ifadenin binary halidir.
     * @param shift degeri binary halde verilen str ifadesinin kaç tane left shift islemi yapacagimizi belirtir. Integerdir.
     * @return geriye dondurdugumuz deger ise str ifadesinin shift degerine gore left shift yapilmis halidir. Binary formattadir.
     */

    public static String ls(String str, int shift) {

        String s = "";
        String str2="";
        String str3= "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)!=' ') str2+=String.valueOf(str.charAt(i));
        }


        for (int i = shift; i < str2.length(); i++) {
            str3+=String.valueOf(str2.charAt(i));
        }

        for (int i = 0; i < shift; i++) {
            str3+="0";
        }

        for (int i = 0; i < str3.length(); i++) {
            if (i!=0 && i%8==0) s+=" ";
            s+=str3.charAt(i);
        }

        return s;

    }
}