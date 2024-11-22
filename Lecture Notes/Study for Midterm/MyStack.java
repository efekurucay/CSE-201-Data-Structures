public class MyStack {
    
    public static void main(String[] args) {
        MyStack stack = new MyStack(3); // 3 elemanlık bir stack oluştur

        // Eleman ekle (push işlemi)
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Stack'in dolu olduğunu kontrol et
        stack.push(40); // Bu eleman eklenemeyecek çünkü stack dolu

        // En üstteki elemanı görme (peek işlemi)
        System.out.println("En üstteki eleman: " + stack.peek());

        // Stack'ten eleman çıkarma (pop işlemi)
        System.out.println("Çıkarılan eleman: " + stack.pop());
        System.out.println("Çıkarılan eleman: " + stack.pop());

        // Stack'in boş olup olmadığını kontrol et
        System.out.println("Stack boş mu? " + stack.isEmpty());
    }
    
    private int maxSize; //max size
    private int top; //en üstteki eleman indexi
    private int[] stackArray; // stacki tutacak dizi


    public MyStack(int size){ 

        maxSize= size;
        stackArray= new int[maxSize];
        top= -1; 
    }

    public boolean isFull(){
        return (top==maxSize-1);
    }

    public boolean isEmpty(){
        return top==-1;
    }

    public void push(int value){

        if(isFull()){
            System.out.println("Stack Dolu, eleman eklenemez");
            
        }
        else{
            stackArray[++top] = value; // top,u bir artır ve elemanı ekle
            // arrayin 0. indexine atadığımız değer gelmiş oldu ilk etapta
        }

    }

    public int pop(){

        if(isEmpty()){

            System.out.println("Stack boş.");
            return -1;
        }

        else{

            return stackArray[top--]; 

        
        }
    }

    public int peek(){

        if (isEmpty()) {
            System.out.println("Stack boş! Gösterilecek eleman yok.");
            return -1; // Stack boş
        } else {
            return stackArray[top]; // En üstteki elemanı döndür
        }
    }









}
