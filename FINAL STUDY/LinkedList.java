/*
 * LinkedList (Bağlı Liste) sınıfı, birbirine bağlı düğümlerden oluşan bir veri yapısıdır.
 * Her düğüm bir sonraki düğüme referans içerir ve bu şekilde liste oluşturulur.
 * Bu yapı, dizilerden farklı olarak dinamik boyutludur ve eleman ekleme/çıkarma işlemleri daha verimlidir.
 */
public class LinkedList {
    // Listenin başlangıç düğümünü tutan referans
    private Node head;
    // Listenin son düğümünü tutan referans (şu an kullanılmıyor ama gelecekte kullanılabilir)
    private Node tail;
    // Listedeki eleman sayısını tutan değişken
    private int size;

    /*
     * Kurucu method (Constructor)
     * Yeni bir boş liste oluşturur
     * head ve tail null olarak başlatılır çünkü henüz listede eleman yok
     * size 0 olarak başlatılır çünkü liste boş
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
     * Node (Düğüm) iç sınıfı
     * Her düğüm bir veri (data) ve bir sonraki düğüme referans (next) içerir
     * Object türünde veri tutulur ki her türlü veri saklanabilsin
     * (String, Integer, Custom object vb.)
     */
    private static class Node{
        // Düğümün tuttuğu veri (herhangi bir türde olabilir)
        Object data;
        // Bir sonraki düğüme referans
        Node next;
        
        /*
         * Node constructor'ı
         * @param data: Düğümde saklanacak veri
         */
        Node(Object data){
            this.data = data;
            this.next = null;
        }
    }

    /*
     * addFirst metodu: Listenin başına yeni bir eleman ekler
     * @param element: Eklenecek eleman (herhangi bir türde olabilir)
     * Zaman karmaşıklığı: O(1) - sabit zamanlı işlem
     */
    public void addFirst(Object element){
        // Yeni düğüm oluştur
        Node newNode = new Node(element);
        // Yeni düğümün next'ini mevcut head'e bağla
        newNode.next = head;
        // head'i yeni düğüme ata
        head = newNode;
        // Liste boyutunu bir artır
        size++;
    }

    /*
     * addLast metodu: Listenin sonuna yeni bir eleman ekler
     * @param element: Eklenecek eleman
     * Zaman karmaşıklığı: O(n) - liste boyutu kadar işlem
     */
    public void addLast(Object element){
        Node newNode = new Node(element);
        // Liste boşsa, addFirst metodunu kullan
        if(head == null){
            addFirst(element);
            return;
        }
        else{
            // Son elemana kadar ilerle
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            // Son elemanın next'ine yeni düğümü bağla
            current.next = newNode;
            size++;
        }
    }

    /*
     * removeFirst metodu: Listenin başındaki elemanı çıkarır ve değerini döndürür
     * @return: Çıkarılan elemanın değeri, liste boşsa null
     * Zaman karmaşıklığı: O(1) - sabit zamanlı işlem
     */
    public Object removeFirst(){
        if(head == null){
            return null;
        }
        else{
            Node temp = head;
            head = head.next;
            size--;
            return temp.data;
        }
    }

    /*
     * contains metodu: Verilen elemanın listede olup olmadığını kontrol eder
     * @param element: Aranacak eleman
     * @return: Eleman listede varsa true, yoksa false
     * Zaman karmaşıklığı: O(n) - liste boyutu kadar işlem
     */
    public boolean contains(Object element){
        Node current = head;
        while(current != null){
            if(current.data.equals(element)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /*
     * indexOf metodu: Verilen elemanın listedeki indeksini bulur
     * @param element: Aranacak eleman
     * @return: Elemanın indeksi, eleman listede yoksa -1
     * Zaman karmaşıklığı: O(n) - liste boyutu kadar işlem
     */
    public int indexOf(Object element){
        Node current = head;
        int index = 0;
        while(current != null){
            if(current.data.equals(element)){
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /*
     * main metodu: Test işlemleri için örnek kullanım
     * Listenin temel işlevlerini test eder
     */
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // Test işlemleri ve sonuçları
        list.addFirst("A"); // Liste: A
        list.addLast("B");  // Liste: A -> B
        list.addFirst("C"); // Liste: C -> A -> B
        System.out.println(list.contains("A")); // true
        System.out.println(list.indexOf("B")); // 2
        Object removed = list.removeFirst(); // C silinir, Liste: A -> B
        System.out.println("Removed: " + removed); // "C"
    }
}