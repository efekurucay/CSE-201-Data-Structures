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
*       1. A concrete Node class that implements INode generic interface
*       2. A concrete SinglyLinkedList class that implements List generic interface
*       3. A concrete CircularLinkedlist class that implements List generic interface and Circular interface
*       4. A concrete DoublyLinkedList class that implements Doubly generic interface
* For each of the concrete classes, use Node class as the storage unit
* !! SinglyLinkedList class must have head and tail data fields
* !! CircularLİnkedList class must have tail data field
* !! Every class except Node must only have a single constructor without any parameters
* You can implement any additional method you would like
*/