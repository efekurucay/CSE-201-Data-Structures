import java.util.NoSuchElementException;

public class HW02_20220808005 {
    public static void main(String[] args) {

        MusicPlayer mp = new MusicPlayer("./Musics");
             
        
    }
}


interface INode<T> {
    // Constructor (T data, Node<T> prev, Node<T> next)
    T getData(); // returns the data
    Node<T> getNext(); // returns the next of this storage unit
    Node<T> getPrev(); // returns the previous storage unit of this unit
    void setNext(Node<T> next); // sets next pointer of this node
    void setPrev(Node<T> prev); // sets the prev pointer of this node
    String toString(); // string representation
}

interface IDoublyCircularLinkedList<T> {
    // must have the data field current
    // Constructor ()
    void addFirst(T data); // adds an element to the head of the list
    void addLast(T data); // adds an element to the tail of the list
    T removeFirst() throws NoSuchElementException; // removes the first element in the list
    T removeLast() throws NoSuchElementException; // removes the last element in the list
    T get(int index) throws IndexOutOfBoundsException; // gets the ith element in the list
    T first() throws NoSuchElementException; // should set current, returns the first data
    T last() throws NoSuchElementException; // should set current, returns the last data
    boolean remove(T data); // should return false if data doesn't exist
    boolean isEmpty(); // checks if the list is empty
    int size(); // returns the size of the list
    T next() throws NoSuchElementException; // should move to the next element
    T previous() throws NoSuchElementException; // should move to the previous element
    T getCurrent() throws NoSuchElementException; // returns the current pointer
    Node<T> getHead(); // returns the head of the list
}

class Node<T> implements INode<T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;

    // Constructor
    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    // Getter for data
    public T getData() {
        return data;
    }

    // Getter for next node
    public Node<T> getNext() {
        return next;
    }

    // Getter for previous node
    public Node<T> getPrev() {
        return prev;
    }

    // Setter for next node
    public void setNext(Node<T> next) {
        this.next = next;
    }

    // Setter for previous node
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    // String representation
    @Override
    public String toString() {
        return data.toString();
    }
}

class DoublyCircularLinkedList<T> implements IDoublyCircularLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private Node<T> current;
    private int size;

    public DoublyCircularLinkedList() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }

    // Add element to the head of the list
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data, null, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            head.setNext(head);
            head.setPrev(head);
        } else {
            newNode.setNext(head);
            newNode.setPrev(tail);
            head.setPrev(newNode);
            tail.setNext(newNode);
            head = newNode;
        }
        size++;
    }

    // Add element to the tail of the list
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data, null, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            head.setNext(head);
            head.setPrev(head);
        } else {
            newNode.setPrev(tail);
            newNode.setNext(head);
            tail.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        }
        size++;
    }

    // Remove the first element of the list
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(tail);
            tail.setNext(head);
        }
        size--;
        return data;
    }

    // Remove the last element of the list
    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        T data = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(head);
            head.setPrev(tail);
        }
        size--;
        return data;
    }

    // Get element by index
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    // Return first element
    public T first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        current = head;
        return head.getData();
    }

    // Return last element
    public T last() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        current = tail;
        return tail.getData();
    }

    // Remove element by data
    public boolean remove(T data) {
        if (isEmpty()) {
            return false;
        }
        Node<T> temp = head;
        do {
            if (temp.getData().equals(data)) {
                if (temp == head) {
                    removeFirst();
                } else if (temp == tail) {
                    removeLast();
                } else {
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    size--;
                }
                return true;
            }
            temp = temp.getNext();
        } while (temp != head);
        return false;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the size of the list
    public int size() {
        return size;
    }

    // Move to the next element
    public T next() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        if (current == null) {
            current = head;
        } else {
            current = current.getNext();
        }
        return current.getData();
    }

    // Move to the previous element
    public T previous() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        if (current == null) {
            current = tail;
        } else {
            current = current.getPrev();
        }
        return current.getData();
    }

    // Get current element
    public T getCurrent() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        if (current == null) {
            return head.getData();
        }
        return current.getData();
    }

    // Return the head of the list
    public Node<T> getHead() {
        return head;
    }
}
