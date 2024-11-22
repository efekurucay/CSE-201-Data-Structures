public class MyQueue {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(3); // 3 elemanlık bir queue oluştur

        // Eleman ekle (enqueue işlemi)
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Queue'nin dolu olduğunu kontrol et
        queue.enqueue(40); // Bu eleman eklenemeyecek çünkü queue dolu

        // En öndeki elemanı görme (peek işlemi)
        System.out.println("En öndeki eleman: " + queue.peek());

        // Queue'den eleman çıkarma (dequeue işlemi)
        System.out.println("Çıkarılan eleman: " + queue.dequeue());
        System.out.println("Çıkarılan eleman: " + queue.dequeue());

        // Queue'nin boş olup olmadığını kontrol et
        System.out.println("Queue boş mu? " + queue.isEmpty());

        // Kalan elemanı çıkarma
        System.out.println("Çıkarılan eleman: " + queue.dequeue());

        // Boş bir queue'den eleman çıkarmaya çalışmak
        System.out.println("Çıkarılan eleman: " + queue.dequeue());
    }


    private int maxSize; //toplam boyut
    private int front; //ön taraf
    private int rear; //arka taraf
    private int []queueArray; //tutacak dizi
    private int nItems; // quyruktaki eleman sayısı


    public MyQueue(int size){

        maxSize=size;
        queueArray=new int[maxSize];
        front=0; //ilk eleman buradan çıkacak
        rear=-1; //ilk eleman buraya eklenecek
        nItems=0; //başlangıçta eleman yok

    }

    public boolean isEmpty(){
        return (nItems ==0);
    }
    public boolean isFull() {
        return (nItems == maxSize);
    }
    public void enqueue(int value){
    //kuyruğun sonuna eleman ekleyecek

        if(isFull()){System.out.println("Queue dolu");}

        else{

            if(rear==maxSize-1){
                rear=-1;
            }//dairesel (circular) queue

            queueArray[++rear]=value;
            nItems++;//eleman sayısını bir arttır
    
        }



    }

    public int dequeue(){
        //kuyruğun ilk elemanını silecek ve döndürecek

        if(isEmpty()){
            System.out.println("Queue boş");
            return -1;
        }

        else{
            int temp = queueArray[front++]; // front'taki elemanı geçici bir değişkene al ve front'u bir artır
            if (front == maxSize) {
                front = 0; // Eğer son indexe ulaşırsak, yeniden başa döneriz (dairesel kuyruk)
            }
            nItems--; // Eleman sayısını bir azalt
            return temp; // Çıkarılan elemanı döndür
        }

    }
    
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue boş! Gösterilecek eleman yok.");
            return -1; // Queue boşsa, hata değeri olarak -1 döndür
        } else {
            return queueArray[front]; // En öndeki elemanı döndür (front pozisyonundaki eleman)
        }
    }
    


}
