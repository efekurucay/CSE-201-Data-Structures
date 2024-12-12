/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Homeworks                   ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *10.12.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Homework05                                               ██████ 
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
import java.util.Queue;

/*
 * There might be some chanes required in the main method to test
 */

public class HW05 {

    public static void main(String[] args) {
        //NodeHeap<Integer, String> heap = new NodeHeap<>();
        ArrayHeap<Integer, String> heap = new ArrayHeap<>(500);

        // Inserting elements into the heap
        heap.insert(10, "Ten");
        heap.insert(20, "Twenty");
        heap.insert(5, "Five");
        heap.insert(30, "Thirty");
        heap.insert(15, "Fifteen");
        heap.insert(25, "Twenty-Five");
        heap.insert(1, "One");

        // Display the heap (level-order)
        System.out.println("Heap after insertions (level-order):");
        heap.levelorder();

        ArrayHeap<Integer, String> heap2 = new ArrayHeap<>(500);

        heap2.insert(8, "Ten");
        heap2.insert(23, "Twenty");
        heap2.insert(3, "Five");
        heap2.insert(27, "Thirty");
        heap2.insert(16, "Fifteen");
        heap2.insert(22, "Twenty-Five");
        heap2.insert(2, "One");

        // Display the heap (level-order)
        System.out.println("\nHeap after insertions (level-order):");
        heap2.levelorder();

        System.out.println("\nHeap after merge (level-order):");
        var heap3 = ArrayHeap.merge(heap, heap2);
        heap3.levelorder();

        //System.out.println(heap3.getRoot().entry.getValue());

        /* int[] array = {1, 5, 3, 2, 6, 4};
        heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        } */
       

    }


    // Implementation of heap sort algorithm
    public static void heapSort(int[] array) {
        int n = array.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }


    // Helper method to maintain heap property
    public static void heapify(int[] array, int n, int parent) {
        /*
         * heapify implementation (required for heapSort)
         * array: whole array
         * n: length of subarray
         * parrent: index of parrent
         */

         int largest = parent;
         int left = 2 * parent + 1;
         int right = 2 * parent + 2;
         
         // Compare with left child
         if (left < n && array[left] > array[largest]) {
             largest = left;
         }
         
         // Compare with right child
         if (right < n && array[right] > array[largest]) {
             largest = right;
         }
         
         // If largest is not root
         if (largest != parent) {
             swap(array, parent, largest);
             heapify(array, n, largest);
         }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class Entry <K extends Comparable <? super K>, V> implements Comparable<K> {
/*
 * Constructor(K, V)
 * getValue()
 * getKey()
 */
private K key;
private V value;

// Constructor to create a new entry
public Entry(K key, V value) {
    this.key = key;
    this.value = value;
}

// Getter for value
public V getValue() {
    return value;
}

// Getter for key
public K getKey() {
    return key;
}

@Override
public int compareTo(K o) {
    return key.compareTo(o);
}
} 

class TreeNode <T> {
    /*
     * Constructor(T entry)
     * entry, left, right
     */
    T entry;
    TreeNode<T> left;
    TreeNode<T> right;
    
    // Constructor for tree node
    public TreeNode(T entry) {
        this.entry = entry;
        this.left = null;
        this.right = null;
    }
}

interface List <T> {
    int size();
    boolean isEmpty();
}

interface PriorityQueue <P, E> extends List <E> {
    E remove();
    E peek();
    void insert(P priority, E element);
}

class ArrayHeap<K extends Comparable<? super K>, V> implements PriorityQueue<K, V> {
    private Entry<K, V>[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayHeap(int capacity) {
        heap = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
    }

    public Entry<K, V>[] getHeap() {
        return heap;
    }

     //* heapifyUp(index)
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].getKey().compareTo(heap[parentIndex].getKey()) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // heapifyDown(index)
    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < size && heap[leftChild].getKey().compareTo(heap[smallest].getKey()) < 0) {
            smallest = leftChild;
        }

        if (rightChild < size && heap[rightChild].getKey().compareTo(heap[smallest].getKey()) < 0) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Method to merge two heaps
    public static <K extends Comparable<? super K>, V> ArrayHeap<K, V> merge(ArrayHeap<K, V> heap1, ArrayHeap<K, V> heap2) {
        ArrayHeap<K, V> mergedHeap = new ArrayHeap<>(heap1.size + heap2.size);
        
        // Copy elements from first heap
        for (int i = 0; i < heap1.size; i++) {
            mergedHeap.insert(heap1.heap[i].getKey(), heap1.heap[i].getValue());
        }
        
        // Copy elements from second heap
        for (int i = 0; i < heap2.size; i++) {
            mergedHeap.insert(heap2.heap[i].getKey(), heap2.heap[i].getValue());
        }
        
        return mergedHeap;
    }

    private void swap(int index, int otherIndex) {
        Entry<K, V> temp = heap[index];
        heap[index] = heap[otherIndex];
        heap[otherIndex] = temp;
    }

    // Print heap elements in level order
    public void levelorder() {
        ArrayList<V> list = new ArrayList<>();
        levelorder(list);
        System.out.println(list);
    }

    // Helper method for level order traversal
    private void levelorder(ArrayList<V> list) {
        for (int i = 0; i < size; i++) {
            list.add(heap[i].getValue());
        }
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
    public V remove() {
        if (isEmpty()) {
            return null;
        }

        V min = heap[0].getValue();
        heap[0] = heap[size - 1];
        size--;
        if (!isEmpty()) {
            heapifyDown(0);
        }
        return min;
    }

    @Override
    public V peek() {
        if (isEmpty()) {
            return null;
        }
        return heap[0].getValue();
    }

    @Override
    public void insert(K priority, V element) {
        if (size >= heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        
        heap[size] = new Entry<>(priority, element);
        heapifyUp(size);
        size++;
    }
}













