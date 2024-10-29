public class deneme {
    public static void main(String[] args) {
        int count = 0 ;
     
        for (int i = 1; i <= 30; i++) {
    
            for(int j = i+2; j <= 30; j++){
                
                for(int k = j+3; k<=30;k++){
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
}
