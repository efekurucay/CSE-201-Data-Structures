import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HW04 {
    public static void main(String[] args) {

    }
}

interface Tree<T> {
    int size();
    boolean isEmpty();
    void insert(T element);
    boolean remove(T element);
    boolean contains(T element);
    void levelorder(List<T> list);
    void inorder(List<T> list);
    void preorder(List<T> list);
    void postorder(List<T> list);
}


class TreeNode<T> {
    T element;
    TreeNode<T> left, right;

    public TreeNode(T element) {
        this.element = element;
    }
}

/*
 * Node-based BST implementation
 */
class BSTNode <T extends Comparable<? super T>> implements Tree<T> {
    private TreeNode<T> root;

    public TreeNode<T> getRoot() {
        // Convenience method for me
        return root;
    }

    /*
     * Constructor()
     */
}

/*
 * Array-based BST implementation
 */
class BSTArray <T extends Comparable<? super T>> implements Tree<T> {
    private T[] array;

    public T[] getArray() {
        return array;
    }

    public BSTArray(int capacity) {
        array = (T[]) new Comparable[capacity];
    }

    

}

class ListNode <T> {
    private T data;
    private ListNode<T> next;

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}

class Entry <K extends Comparable<? super K>, V> {
    /*
     * key-value data fields
     * Constructor(K, V)
     * getKey()
     * getValue()
     */
}

interface IPriorityQueue <P extends Comparable<? super P>, E> {
    void insert(P priority, E element);
    E remove();
    E peek();
    boolean isEmpty();
    int size();
}

abstract class AbstractPriorityQueue <P extends Comparable<? super P>, E> implements IPriorityQueue<P, E> {
    protected ListNode<Entry<P, E>> head;

    public ListNode<Entry<P, E>> getHead() {
        // Convenience for me
        return head;
    }
}

/*
 * Sorted PQ implementation
 */
class SortedPriorityQueue <P extends Comparable<? super P>, E> extends AbstractPriorityQueue <P, E> {

}

/*
 * Unsorted PQ implementation
 */
class UnsortedPriorityQueue <P extends Comparable<? super P>, E> extends AbstractPriorityQueue <P, E> {
    
}