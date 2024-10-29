public class Hinaoi {
    public static void main(String[] args) {
        hinoi(0, 2, 3);
    }
    static void hinoi(int from,int to, int doc){
        if(doc==1){
            System.out.println("Move 1 disk from "+ from+" to "+to);
        }else{
            hinoi(from, 3-from-to, doc-1);
            System.out.println("Move 1 disk from "+ from+" to "+to);
            hinoi(3-from-to, to, doc-1);
        }
    }
}
