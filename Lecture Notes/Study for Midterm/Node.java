import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Node {
    int value;          // Düğümde saklanacak değer
    Node leftChild;     // Sol çocuk düğüm
    Node rightChild;    // Sağ çocuk düğüm



    public Node(int value){
        this.value=value;
        leftChild=null;
        rightChild=null;



    }
}

class BinaryTree{

    Node root; // kök

    public BinaryTree(){

        root=null;// ağaç boş
    }

    public void add(int value){

        if(root==null){
            root=new Node(value);//kök boşsa yeni düğümü kök olarak ata
            System.out.println("Kök düğüm eklendi: "+value);
        }
        else{
           /*kök zaten varsa yeni düğümü,
            *kökten küçükse sola,
            *kökten büyükse sağa
            *ekleyeceğiz.
            */
            addRecursive(root, value);
        }


    }

    public void addRecursive(Node current, int value){
        if(value<current.value){//eklenecek, mevcuttan küçük
            if(current.leftChild==null){//eklenecek yer boşsa
                current.leftChild=new Node(value);
                System.out.println("Sola eklendi: "+value);
            }
            else{
            addRecursive(current.leftChild, value); // Sol çocuk doluysa, sol tarafa doğru devam et
            }
        
        }
        else if(value>current.value){
            if (current.rightChild == null) {
                current.rightChild = new Node(value); // Sağ çocuk boşsa, burada yeni düğüm olarak ekle
                System.out.println("Sağ tarafa eklendi: " + value);
            } else {
                addRecursive(current.rightChild, value); // Sağ çocuk doluysa, sağ tarafa doğru devam et
            }

        }

    }



    //ağacı gezme

    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.leftChild); // Sol çocuğu gez
            System.out.print(node.value + " "); // Mevcut düğümün değerini yazdır
            inOrderTraversal(node.rightChild); // Sağ çocuğu gez
        }
    }

    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.value + " "); // Mevcut düğümü yazdır
            preOrderTraversal(node.leftChild);  // Sol çocuğu gez
            preOrderTraversal(node.rightChild); // Sağ çocuğu gez
        }
    }

    public void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.leftChild);  // Sol çocuğu gez
            postOrderTraversal(node.rightChild); // Sağ çocuğu gez
            System.out.print(node.value + " ");  // Mevcut düğümü yazdır
        }
    }
    
    public void breadthFirstTraversal() {
        if (root == null) return;
    
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
    
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.value + " "); // Mevcut düğümü yazdır
    
            if (current.leftChild != null) {
                queue.add(current.leftChild); // Sol çocuğu kuyruğa ekle
            }
    
            if (current.rightChild != null) {
                queue.add(current.rightChild); // Sağ çocuğu kuyruğa ekle
            }
        }
    }

    public void depthFirstTraversal() {
        if (root == null) return;
    
        Stack<Node> stack = new Stack<>();
        stack.push(root);
    
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.value + " "); // Mevcut düğümü yazdır
    
            // Önce sağ çocuğu yığına ekleyin, böylece sol çocuk ilk gezilir
            if (current.rightChild != null) {
                stack.push(current.rightChild);
            }
    
            if (current.leftChild != null) {
                stack.push(current.leftChild);
            }
        }
    }
}