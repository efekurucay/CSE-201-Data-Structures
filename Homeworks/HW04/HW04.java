/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Homeworks                   ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *10.12.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Homework04                                               ██████ 
██████                                                                        ██████
██████ *Proposed grade: ?                                                     ██████ 
██████                                                                        ██████
██████ *Website: https://efekurucay.com                                       ██████
██████                                                                        ██████
██████ *                                                                      ██████
██████ *                                                                      ██████
██████ *    ███████ ███████ ███████   |    ███████ ███████ ███████            ██████ 
██████ *    ██      ██      ██        |    ██      ██      ██                 ██████ 
██████ *    █████   █████   █████     |    █████   █████   █████              ██████ 
██████ *    ██      ██      ██        |    ██      ██      ██                 ██████ 
██████ *    ███████ ██      ███████   |    ███████ ██      ███████            ██████ 
██████ *                                                                      ██████
██████ *                                                                      ██████
██████ *                                                                      ██████
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████                          
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HW04 {
    public static void main(String[] args) {
        // BST Node-based implementation testi
        System.out.println("BST (Node-based) Test:");
        BSTNode<Integer> bst = new BSTNode<>();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        List<Integer> list = new ArrayList<>();
        System.out.print("Inorder Traversal: ");
        bst.inorder(list);
        System.out.println(list);

        System.out.print("Preorder Traversal: ");
        list.clear();
        bst.preorder(list);
        System.out.println(list);

        System.out.print("Postorder Traversal: ");
        list.clear();
        bst.postorder(list);
        System.out.println(list);

        System.out.println("\n70 is in BST? " + bst.contains(70));
        System.out.println("25 is in BST? " + bst.contains(25));

        // BST Array-based implementation testi
        System.out.println("\nBST (Array-based) Test:");
        BSTArray<Integer> bstArray = new BSTArray<>(15);
        bstArray.insert(50);
        bstArray.insert(30);
        bstArray.insert(70);
        bstArray.insert(20);
        bstArray.insert(40);

        list.clear();
        bstArray.levelorder(list);
        System.out.println("Level-order Traversal: " + list);

        // Priority Queue testleri
        System.out.println("\nSorted Priority Queue Test:");
        SortedPriorityQueue<Integer, String> spq = new SortedPriorityQueue<>();
        spq.insert(1, "High Priority");
        spq.insert(3, "Low Priority");
        spq.insert(2, "Medium Priority");

        while (!spq.isEmpty()) {
            String value = spq.remove();
            System.out.println("Value: " + value);
        }

        System.out.println("\nUnsorted Priority Queue Test:");
        UnsortedPriorityQueue<Integer, String> upq = new UnsortedPriorityQueue<>();
        upq.insert(1, "High Priority");
        upq.insert(3, "Low Priority");
        upq.insert(2, "Medium Priority");

        while (!upq.isEmpty()) {
            String value = upq.remove();
            System.out.println("Value: " + value);
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

 class BSTNode<T extends Comparable<? super T>> implements Tree<T> {
    private TreeNode<T> root;
    private int size;

    public TreeNode<T> getRoot() {
        return root;
    }

    // Constructor
    public BSTNode() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Insert element into BST
    @Override
    public void insert(T element) {
        root = insertRec(root, element);
        size++;
    }

    private TreeNode<T> insertRec(TreeNode<T> node, T element) {
        if (node == null) {
            return new TreeNode<>(element);
        }

        if (element.compareTo(node.element) < 0) {
            node.left = insertRec(node.left, element);
        } else {
            node.right = insertRec(node.right, element);
        }

        return node;
    }

    // Remove element from BST
    @Override
    public boolean remove(T element) {
        int originalSize = size;
        root = removeRec(root, element);
        return size != originalSize;
    }

    private TreeNode<T> removeRec(TreeNode<T> node, T element) {
        if (node == null) {
            return null;
        }

        int compare = element.compareTo(node.element);
        if (compare < 0) {
            node.left = removeRec(node.left, element);
        } else if (compare > 0) {
            node.right = removeRec(node.right, element);
        } else {
            size--;
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.element = findMin(node.right);
            node.right = removeRec(node.right, node.element);
        }
        return node;
    }

    private T findMin(TreeNode<T> node) {
        T min = node.element;
        while (node.left != null) {
            min = node.left.element;
            node = node.left;
        }
        return min;
    }

    @Override
    public boolean contains(T element) {
        return containsRec(root, element);
    }

    private boolean containsRec(TreeNode<T> node, T element) {
        if (node == null) {
            return false;
        }
        
        int compare = element.compareTo(node.element);
        if (compare == 0) {
            return true;
        }
        return compare < 0 ? containsRec(node.left, element) : containsRec(node.right, element);
    }

    // Tree traversal methods
    @Override
    public void levelorder(List<T> list) {
        if (root == null) return;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            list.add(current.element);
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }

    @Override
    public void inorder(List<T> list) {
        inorderRec(root, list);
    }

    private void inorderRec(TreeNode<T> node, List<T> list) {
        if (node != null) {
            inorderRec(node.left, list);
            list.add(node.element);
            inorderRec(node.right, list);
        }
    }

    @Override
    public void preorder(List<T> list) {
        preorderRec(root, list);
    }

    private void preorderRec(TreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.element);
            preorderRec(node.left, list);
            preorderRec(node.right, list);
        }
    }

    @Override
    public void postorder(List<T> list) {
        postorderRec(root, list);
    }

    private void postorderRec(TreeNode<T> node, List<T> list) {
        if (node != null) {
            postorderRec(node.left, list);
            postorderRec(node.right, list);
            list.add(node.element);
        }
    }
}


/*
 * Array-based BST implementation
 */

 class BSTArray<T extends Comparable<? super T>> implements Tree<T> {
    private T[] array;
    private int size;

    public T[] getArray() {
        return array;
    }

    public BSTArray(int capacity) {
        array = (T[]) new Comparable[capacity];
        size = 0;
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
        if (size >= array.length) {
            throw new IllegalStateException("Array is full");
        }
        insertAt(0, element);
        size++;
    }

    private void insertAt(int index, T element) {
        if (array[index] == null) {
            array[index] = element;
            return;
        }

        if (element.compareTo(array[index]) < 0) {
            insertAt(2 * index + 1, element);
        } else {
            insertAt(2 * index + 2, element);
        }
    }

    @Override
    public boolean remove(T element) {
        int index = findIndex(element, 0);
        if (index == -1) return false;
        
        removeAt(index);
        size--;
        return true;
    }

    private int findIndex(T element, int current) {
        if (current >= array.length || array[current] == null) {
            return -1;
        }

        if (element.compareTo(array[current]) == 0) {
            return current;
        }

        if (element.compareTo(array[current]) < 0) {
            return findIndex(element, 2 * current + 1);
        }
        return findIndex(element, 2 * current + 2);
    }

    private void removeAt(int index) {
        // Simple removal strategy: replace with the rightmost leaf
        int lastIndex = size - 1;
        array[index] = array[lastIndex];
        array[lastIndex] = null;
    }

    @Override
    public boolean contains(T element) {
        return findIndex(element, 0) != -1;
    }

    @Override
    public void levelorder(List<T> list) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                list.add(array[i]);
            }
        }
    }

    @Override
    public void inorder(List<T> list) {
        inorderRec(0, list);
    }

    private void inorderRec(int index, List<T> list) {
        if (index >= array.length || array[index] == null) return;
        inorderRec(2 * index + 1, list);
        list.add(array[index]);
        inorderRec(2 * index + 2, list);
    }

    @Override
    public void preorder(List<T> list) {
        preorderRec(0, list);
    }

    private void preorderRec(int index, List<T> list) {
        if (index >= array.length || array[index] == null) return;
        list.add(array[index]);
        preorderRec(2 * index + 1, list);
        preorderRec(2 * index + 2, list);
    }

    @Override
    public void postorder(List<T> list) {
        postorderRec(0, list);
    }

    private void postorderRec(int index, List<T> list) {
        if (index >= array.length || array[index] == null) return;
        postorderRec(2 * index + 1, list);
        postorderRec(2 * index + 2, list);
        list.add(array[index]);
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
    protected int size;

    public ListNode<Entry<P, E>> getHead() {
        return head;
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

/*
 * Sorted PQ implementation
 */
class SortedPriorityQueue<P extends Comparable<? super P>, E> extends AbstractPriorityQueue<P, E> {
    @Override
    public void insert(P priority, E element) {
        Entry<P, E> entry = new Entry<>(priority, element);
        ListNode<Entry<P, E>> newNode = new ListNode<>(entry, null);
        
        if (isEmpty() || priority.compareTo(head.getData().getKey()) < 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            ListNode<Entry<P, E>> current = head;
            while (current.getNext() != null && 
                   priority.compareTo(current.getNext().getData().getKey()) >= 0) {
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
            return null;
        }
        E element = head.getData().getValue();
        head = head.getNext();
        size--;
        return element;
    }

    @Override
    public E peek() {
        return isEmpty() ? null : head.getData().getValue();
    }
}
/*
 * Unsorted PQ implementation
 */
class UnsortedPriorityQueue<P extends Comparable<? super P>, E> extends AbstractPriorityQueue<P, E> {

    @Override
    public void insert(P priority, E element) {
        Entry<P, E> entry = new Entry<>(priority, element);
        ListNode<Entry<P, E>> newNode = new ListNode<>(entry, head);
        head = newNode;
        size++;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        ListNode<Entry<P, E>> minNode = head;
        ListNode<Entry<P, E>> minPrev = null;
        ListNode<Entry<P, E>> current = head;
        ListNode<Entry<P, E>> prev = null;

        while (current.getNext() != null) {
            if (current.getNext().getData().getKey().compareTo(minNode.getData().getKey()) < 0) {
                minNode = current.getNext();
                minPrev = current;
            }
            current = current.getNext();
        }

        if (minPrev == null) {
            head = head.getNext();
        } else {
            minPrev.setNext(minNode.getNext());
        }

        size--;
        return minNode.getData().getValue();
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        ListNode<Entry<P, E>> minNode = head;
        ListNode<Entry<P, E>> current = head;

        while (current != null) {
            if (current.getData().getKey().compareTo(minNode.getData().getKey()) < 0) {
                minNode = current;
            }
            current = current.getNext();
        }

        return minNode.getData().getValue();
    }
}
