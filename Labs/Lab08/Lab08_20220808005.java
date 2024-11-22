import java.util.ArrayList; 
import java.util.LinkedList; 
import java.util.List; 
import java.util.Queue; 
 /**---------------------------------------------------

*Akdeniz University CSE201 Data Structures Labs

*Yahya Efe Kurucay

*21.11.2024

*Description: Lab08

*Proposed grade: ?

*Website: https://efekurucay.com

*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████  
 *    ██      ██      ██        
 *    █████   █████   █████      
 *    ██      ██      ██        
 *    ███████ ██      ███████   
 *                            
 *                            
 */
public class Lab08_20220808005 { 
    public static void main(String[] args) { 
        // binary
        BinaryTree<Integer> binaryTree = new BinaryTree<>(10);
        binaryTree.insert(5);
        binaryTree.insert(15);
        binaryTree.insert(10);
        
        List<Integer> inorderList = new ArrayList<>();
        binaryTree.inorder(inorderList);
        System.out.println("Inorder traversal: " + inorderList);

        //Priority Queue
        SortedPriorityQueue<Integer, String> sortedPQ = new SortedPriorityQueue<>();
        sortedPQ.insert(2, "B");
        sortedPQ.insert(1, "A");
        sortedPQ.insert(3, "C");
        
        while (!sortedPQ.isEmpty()) {
            System.out.println("Removed from SortedPQ: " + sortedPQ.remove());
        }
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
 
class BinaryTree<T> implements Tree<T> { 
    protected T[] array;
    protected int size;
    protected int capacity;

    @SuppressWarnings("unchecked")
    public BinaryTree(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(T element) {
        if (size >= capacity) {
            throw new IllegalStateException("Tree is full");
        }
        array[size++] = element;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                // Replace the removed element with the last element
                array[i] = array[size - 1];
                array[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void levelorder(List<T> list) {
        for (int i = 0; i < size; i++) {
            list.add(array[i]);
        }
    }

    @Override
    public void inorder(List<T> list) {
        inorderTraversal(0, list);
    }

    private void inorderTraversal(int index, List<T> list) {
        if (index >= size) return;

        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        inorderTraversal(leftChild, list);
        list.add(array[index]);
        inorderTraversal(rightChild, list);
    }

    @Override
    public void preorder(List<T> list) {
        preorderTraversal(0, list);
    }

    private void preorderTraversal(int index, List<T> list) {
        if (index >= size) return;

        list.add(array[index]);
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        preorderTraversal(leftChild, list);
        preorderTraversal(rightChild, list);
    }

    @Override
    public void postorder(List<T> list) {
        postorderTraversal(0, list);
    }

    private void postorderTraversal(int index, List<T> list) {
        if (index >= size) return;

        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        postorderTraversal(leftChild, list);
        postorderTraversal(rightChild, list);
        list.add(array[index]);
    }

    public T[] getArray() {
        return array;
    }
} 
 
class TreeNode<T> { 
    T element; 
    TreeNode<T> left, right; 
 
    public TreeNode(T element) { 
        this.element = element; 
    } 
} 
 
class BSTNode<T extends Comparable<? super T>> implements Tree<T> {
    private Node<T> root;
    private int size;

    public BSTNode() {
        root = null;
        size = 0;
    }

    private static class Node<T> {
        T element;
        Node<T> left, right;

        Node(T element) {
            this.element = element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(T element) {
        root = insertRec(root, element);
        size++;
    }

    private Node<T> insertRec(Node<T> node, T element) {
        if (node == null) {
            return new Node<>(element);
        }

        if (element.compareTo(node.element) < 0) {
            node.left = insertRec(node.left, element);
        } else if (element.compareTo(node.element) > 0) {
            node.right = insertRec(node.right, element);
        }

        return node;
    }

    @Override
    public boolean remove(T element) {
        int oldSize = size;
        root = removeRec(root, element);
        return size != oldSize;
    }

    private Node<T> removeRec(Node<T> node, T element) {
        if (node == null) return null;

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0) {
            node.left = removeRec(node.left, element);
        } else if (compareResult > 0) {
            node.right = removeRec(node.right, element);
        } else {
            // Node to be deleted found
            size--;

            // Case 1: No child
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: One child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Two children
            Node<T> minNode = findMin(node.right);
            node.element = minNode.element;
            node.right = removeRec(node.right, minNode.element);
        }

        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean contains(T element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<T> node, T element) {
        if (node == null) return false;

        int compareResult = element.compareTo(node.element);
        if (compareResult == 0) return true;
        if (compareResult < 0) return containsRec(node.left, element);
        return containsRec(node.right, element);
    }

    @Override
    public void levelorder(List<T> list) {
        if (root == null) return;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            list.add(node.element);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    @Override
    public void inorder(List<T> list) {
        inorderRec(root, list);
    }

    private void inorderRec(Node<T> node, List<T> list) {
        if (node == null) return;
        inorderRec(node.left, list);
        list.add(node.element);
        inorderRec(node.right, list);
    }

    @Override
    public void preorder(List<T> list) {
        preorderRec(root, list);
    }

    private void preorderRec(Node<T> node, List<T> list) {
        if (node == null) return;
        list.add(node.element);
        preorderRec(node.left, list);
        preorderRec(node.right, list);
    }

    @Override
    public void postorder(List<T> list) {
        postorderRec(root, list);
    }

    private void postorderRec(Node<T> node, List<T> list) {
        if (node == null) return;
        postorderRec(node.left, list);
        postorderRec(node.right, list);
        list.add(node.element);
    }

    public Node<T> getRoot() {
        return root;
    }
} 
 
class BSTArray<T extends Comparable<? super T>> extends BinaryTree<T> { 
    public BSTArray(int capacity) { 
        super(capacity); 
        array = (T[]) new Comparable[capacity]; 
    } 

    @Override
    public void insert(T element) {
        if (size >= capacity) {
            throw new IllegalStateException("Tree is full");
        }

        if (size == 0) {
            array[size++] = element;
            return;
        }

        int index = 0;
        while (index < size) {
            T current = array[index];
            int compareResult = element.compareTo(current);

            if (compareResult < 0) {
                // Go to left child
                int leftChild = 2 * index + 1;
                if (leftChild >= capacity || array[leftChild] == null) {
                    array[leftChild] = element;
                    size++;
                    return;
                }
                index = leftChild;
            } else {
                // Go to right child
                int rightChild = 2 * index + 2;
                if (rightChild >= capacity || array[rightChild] == null) {
                    array[rightChild] = element;
                    size++;
                    return;
                }
                index = rightChild;
            }
        }
    }
} 
 
class ListNode<T> { 
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
 
class Entry<K extends Comparable<? super K>, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
} 
 
interface IPriorityQueue<P extends Comparable<? super P>, E> { 
    void insert(P priority, E element); 
    E remove(); 
    E peek(); 
    boolean isEmpty(); 
    int size(); 
} 
 
abstract class AbstractPriorityQueue<P extends Comparable<? super P>, E> implements IPriorityQueue<P, E> { 
    protected ListNode<Entry<P, E>> head; 
     
        public ListNode<Entry<P, E>> getHead() { 
            return head; 
        } 
     
        public AbstractPriorityQueue() { 
            head = null; 
        } 
    } 
     
    class SortedPriorityQueue<P extends Comparable<? super P>, E> extends AbstractPriorityQueue<P, E> {
        private int size;
    
        @Override
        public void insert(P priority, E element) {
            Entry<P, E> newEntry = new Entry<>(priority, element);
            ListNode<Entry<P, E>> newNode = new ListNode<>(newEntry, null);
    
            if (getHead() == null || priority.compareTo(getHead().getData().getKey()) <= 0) {
                newNode.setNext(getHead());
                head = newNode;
        } else {
            ListNode<Entry<P, E>> current = getHead();
            while (current.getNext() != null && 
                   priority.compareTo(current.getNext().getData().getKey()) > 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        E element = getHead().getData().getValue();
        head = getHead().getNext();
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return getHead().getData().getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
} 
 
class UnsortedPriorityQueue<P extends Comparable<? super P>, E> extends AbstractPriorityQueue<P, E> {
    private int size;

    @Override
    public void insert(P priority, E element) {
        Entry<P, E> newEntry = new Entry<>(priority, element);
        ListNode<Entry<P, E>> newNode = new ListNode<>(newEntry, getHead());
        head = newNode;
        size++;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        ListNode<Entry<P, E>> current = getHead();
        ListNode<Entry<P, E>> prev = null;
        ListNode<Entry<P, E>> maxPriorityNode = current;
        ListNode<Entry<P, E>> maxPriorityPrev = null;

        while (current != null) {
            if (current.getData().getKey().compareTo(maxPriorityNode.getData().getKey()) >= 0) {
                maxPriorityNode = current;
                maxPriorityPrev = prev;
            }
            prev = current;
            current = current.getNext();
        }

        if (maxPriorityPrev == null) {
            // Removing the head
            head = maxPriorityNode.getNext();
        } else {
            maxPriorityPrev.setNext(maxPriorityNode.getNext());
        }
        size--;
        return maxPriorityNode.getData().getValue();
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        ListNode<Entry<P, E>> current = getHead();
        ListNode<Entry<P, E>> maxPriorityNode = current;

        while (current != null) {
            if (current.getData().getKey().compareTo(maxPriorityNode.getData().getKey()) >= 0) {
                maxPriorityNode = current;
            }
            current = current.getNext();
        }

        return maxPriorityNode.getData().getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}