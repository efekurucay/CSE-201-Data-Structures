/**---------------------------------------------------

*Akdeniz University CSE201 Data Structures Labs

*Yahya Efe KuruÃ§ay

*26.09.2024

*Description: Lab02 Exercises

*Proposed grade: 100 / 100

*Website: https://efekurucay.com

*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */
 
public class Lab02 {
    public static void main(String[] args) {
        
    }
}

interface List <E> {
    int size(); // Returns the size of the list
    boolean isEmpty(); // Checks whether the list is empty or not
    E first(); // returns the element/data of first entry in the list
    E last(); // returns te element/data of last entry in the list
    void addFirst(E e); // adds the given element at the start of the list
    void addLast(E e); // adds the given element at the end of the list
    E removeFirst(); // removes the first element in the list
}

interface Circlular {
    void rotate(); // tail becomes next of tail if not empty
}

interface Doubly <E> extends List <E> {
    E removeLast(); //removes the last element from the list
}

interface INode <E> { // storage unit
    E getElement(); // data/element
    Node<E> getPrev(); // returns the previous unit of this unit
    void setPrev(Node<E> prev); // sets the previous of this unit
    Node<E> getNext(); // returns the next unit of this unit
    void setNext(Node<E> next); // sets the next of this unit
}
/*
* According to given above given interfaces, implement:
* For each of the concrete classes, use Node class as the storage unit
* !! SinglyLinkedList class must have head and tail data fields
* !! CircularLÄ°nkedList class must have tail data field
* !! Every class except Node must only have a single constructor without any parameters
* You can implement any additional method you would like
*/
//1. A concrete Node class that implements INode generic interface

class Node<E> implements INode<E> {
    private E element;
    private Node<E> prev;
    private Node<E> next;

    public Node(E element, Node<E> p, Node<E> n) {
        this.element = element;
        prev = p;
        next = n;
    }

    public E getElement() { 
        return element; 
    }
    public Node<E> getPrev() { 
        return prev; 
    }
    public void setPrev(Node<E> p) {
        prev = p; 
    }
    public Node<E> getNext() { 
        return next; 
    }
    public void setNext(Node<E> n) {
        next = n; 
    }

}

//2. A concrete SinglyLinkedList class that implements List generic interface
class SinglyLinkedList<E> implements List<E> {
    private Node<E> head = null; 
    private Node<E> tail = null; 
    private int size = 0;

    public SinglyLinkedList() {}

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, null, head);
        if (size == 0)
            tail = head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
            tail = null;
        return answer;
    }
}

//3. A concrete CircularLinkedlist class that implements List generic interface and Circular interface
class CircularLinkedList<E> implements List<E>, Circlular {
    private Node<E> tail = null; 
    private int size = 0;

    public CircularLinkedList() {}

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, null, null);
            tail.setNext(tail); 
        } else {
            Node<E> newest = new Node<>(e, null, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e); 
        tail = tail.getNext(); 
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;
        else
            tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

}


//4. A concrete DoublyLinkedList class that implements Doubly generic interface
class DoublyLinkedList<E> implements Doubly<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public DoublyLinkedList() {}

    public int size() { 
        return size; 
    }

    public boolean isEmpty() { 
        return size == 0; 
    }  

    public E first() {
        if (isEmpty()){
        return null;
        }
        return 
        head.getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {
        Node<E> newest = new Node<>(e, null, head);
        if (isEmpty())
            tail = newest;
        else
            head.setPrev(newest);
        head = newest;
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, tail, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (isEmpty())
            tail = null;
        else
            head.setPrev(null);
        return answer;
    }

    public E removeLast() {
        if (isEmpty()) return null;
        E answer = tail.getElement();
        tail = tail.getPrev();
        size--;
        if (isEmpty())
            head = null;
        else
            tail.setNext(null);
        return answer;
    }
}
/***
 *              _____                    _____                    _____          
 *             /\    \                  /\    \                  /\    \         
 *            /::\    \                /::\    \                /::\    \        
 *           /::::\    \              /::::\    \              /::::\    \       
 *          /::::::\    \            /::::::\    \            /::::::\    \      
 *         /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
 *        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
 *       /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
 *      /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 *     /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \ 
 *    /:::/__\:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::\____\
 *    \:::\   \:::\   \::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    /
 *     \:::\   \:::\   \/____/  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/ 
 *      \:::\   \:::\    \               \:::\    \       \:::\   \:::\    \     
 *       \:::\   \:::\____\               \:::\____\       \:::\   \:::\____\    
 *        \:::\   \::/    /                \::/    /        \:::\   \::/    /    
 *         \:::\   \/____/                  \/____/          \:::\   \/____/     
 *          \:::\    \                                        \:::\    \         
 *           \:::\____\                                        \:::\____\        
 *            \::/    /                                         \::/    /        
 *             \/____/                                           \/____/         
 *                                                                               
 */


                           
