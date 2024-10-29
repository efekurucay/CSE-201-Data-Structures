public class Lab02 {
    public static void main(String[] args) {
      LinkedList<Integer> list = new LinkedList<>();
      list.addLast(5);
      list.addLast(15);
      list.addLast(321);
      list.addLast(342);
      list.addLast(1543);
      list.addLast(14324);
      list.addLast(5435);
      list.addAfter(4, 5);
      list.print();
    }
}
class Node<E> {
    private E value;
    private Node<E> next;

    public Node(E value){
        this.value = value;
    }
    public void setValue(E value){
        this.value = value;
    }
    public E getValue(){
        return value;
    }
    public Node<E> getNext(){
        return next;
    }
    public void setNext(Node<E> next){
        this.next = next;
    }

}
class LinkedList<E>{
    private Node<E> head;
    private Node<E> tail;

    public boolean isEmpty(){
        if(head != null){
            return false;
        }
        return true;
    }
    public void addLast(E value){
        Node<E> newnode = new Node<>(value);
        if(head == null){
            head = newnode;
        }else{
            tail.setNext(newnode);  
        }
        tail = newnode;
    }
    private void print(Node<E> node){
        if(node == null){
            return;
        }
        System.out.println(node.getValue());
        print(node.getNext());
    }
    public void print(){
        print(head);
    }
    public Node<E> getNode(int index){
        int counter = 0;
        Node<E> node = head;
        
        if( index <0 || isEmpty()){
            return null;
        }
        while( node != null){
            if(index == counter){
                return node;
 }               
            counter ++;
            node = node.getNext();
            
    }    
        return null;
    
}
   // Methodu tamamla, verilen node u araya eklesin. 
   public void addAfter(E value, int index){
        Node <E> nextNode = getNode(index);
        if(nextNode == null){
            throw new IllegalArgumentException();
        }
        Node<E> newnode = new Node<E>(value);
        newnode.setNext(nextNode.getNext());
        nextNode.setNext(newnode);
        
    }

    }


