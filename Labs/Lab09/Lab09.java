/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Labs                        ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *28.11.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Lab09 Exercises                                          ██████ 
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




import java.util.LinkedList;
import java.util.Queue;

public class Lab09 {
    
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

        //NodeHeap<Integer, String> heap2 = new NodeHeap<>();
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
        //var heap3 = NodeHeap.merge(heap, heap2, 6, "Six");
        var heap3 = ArrayHeap.merge(heap, heap2);
        heap3.levelorder();
        //System.out.println(heap3.getRoot().entry.getValue());
    }

    public static void heapSort(int[] array) {
        int n = array.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) { heapify(array, n, i); }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    public static void heapify(int[] array, int n, int parent) {
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        if (left < n && array[left] > array[largest]) {largest = left;}

        if (right < n && array[right] > array[largest]) {largest = right;}

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
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {return key;}

    public V getValue() {return value;}

    @Override
    public int compareTo(K o) {return key.compareTo(o);}
} 

class TreeNode <T> {
    T entry;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parrent;

    public TreeNode(T entry) {this.entry = entry;}

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

//Array-based Min-heap implementation
 
class ArrayHeap <K extends Comparable<? super K>, V> implements PriorityQueue <K, V> {
    private Entry<K, V>[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 100;

    public Entry<K, V>[] getHeap() {return heap;}

    public ArrayHeap(int capacity) {
        heap = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
    }

    @Override
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public V remove() {
        if (isEmpty()) {return null;}
        Entry<K, V> min = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        if (!isEmpty()) {heapifyDown(0);}
        return min.getValue();
    }

    @Override
    public V peek() {
        if (isEmpty()) {return null;}
        return heap[0].getValue();
    }

    @Override
    public void insert(K priority, V element) {
        if (size >= heap.length) {throw new IllegalStateException("Heap is full");}
        heap[size] = new Entry<>(priority, element);
        heapifyUp(size);
        size++;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[index].getKey().compareTo(heap[parent].getKey()) < 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

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

    private void swap(int index, int otherIndex) {
        Entry<K, V> temp = heap[index];
        heap[index] = heap[otherIndex];
        heap[otherIndex] = temp;
    }

    public static <K extends Comparable<? super K>, V> ArrayHeap<K, V> merge(ArrayHeap<K, V> heap1, ArrayHeap<K, V> heap2) {
        ArrayHeap<K, V> mergedHeap = new ArrayHeap<>(heap1.size() + heap2.size());
        
        // Copy elements from first heap
        for (int i = 0; i < heap1.size(); i++) {
            Entry<K, V> entry = heap1.getHeap()[i];
            mergedHeap.insert(entry.getKey(), entry.getValue());
        }

        // Copy elements from second heap
        for (int i = 0; i < heap2.size(); i++) {
            Entry<K, V> entry = heap2.getHeap()[i];
            mergedHeap.insert(entry.getKey(), entry.getValue());
        }

        return mergedHeap;
    }

    public void levelorder() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.print(heap[i].getKey() + ":" + heap[i].getValue() + " ");
        }
        System.out.println();
    }
}

//Node-based Min-heapimplementation
 
class NodeHeap <K extends Comparable<? super K>, V> implements PriorityQueue <K, V> {
    private TreeNode<Entry<K, V>> root;
    private int size;

    public TreeNode<Entry<K, V>> getRoot() {
        return root;
    }

    public NodeHeap() {
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

    @Override
    public V remove() {
        if (isEmpty()) {
            return null;
        }

        V value = root.entry.getValue();
        TreeNode<Entry<K, V>> lastNode = findLastNode();
        
        if (lastNode == root) {
            root = null;
        } else {
            root.entry = lastNode.entry;
            if (lastNode.parrent.left == lastNode) {
                lastNode.parrent.left = null;
            } else {
                lastNode.parrent.right = null;
            }
            heapifyDown(root);
        }
        
        size--;
        return value;
    }

    @Override
    public V peek() {
        if (isEmpty()) {
            return null;
        }
        return root.entry.getValue();
    }

    @Override
    public void insert(K priority, V element) {
        TreeNode<Entry<K, V>> newNode = new TreeNode<>(new Entry<>(priority, element));
        
        if (isEmpty()) {
            root = newNode;
        } else {
            TreeNode<Entry<K, V>> parent = findParrent();
            newNode.parrent = parent;
            if (parent.left == null) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            heapifyUp(newNode);
        }
        
        size++;
    }

    private void heapifyUp(TreeNode<Entry<K, V>> node) {
        while (node.parrent != null && 
               node.entry.getKey().compareTo(node.parrent.entry.getKey()) < 0) {
            swap(node, node.parrent);
            node = node.parrent;
        }
    }

    private void heapifyDown(TreeNode<Entry<K, V>> node) {
        TreeNode<Entry<K, V>> smallest = node;
        
        if (node.left != null && 
            node.left.entry.getKey().compareTo(smallest.entry.getKey()) < 0) {
            smallest = node.left;
        }
        
        if (node.right != null && 
            node.right.entry.getKey().compareTo(smallest.entry.getKey()) < 0) {
            smallest = node.right;
        }
        
        if (smallest != node) {
            swap(node, smallest);
            heapifyDown(smallest);
        }
    }

    private TreeNode<Entry<K, V>> findLastNode() {
        if (root == null) return null;
        
        Queue<TreeNode<Entry<K, V>>> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode<Entry<K, V>> lastNode = null;
        
        while (!queue.isEmpty()) {
            lastNode = queue.poll();
            if (lastNode.left != null) {
                queue.offer(lastNode.left);
            }
            if (lastNode.right != null) {
                queue.offer(lastNode.right);
            }
        }
        
        return lastNode;
    }

    private TreeNode<Entry<K, V>> findParrent() {
        if (root == null) return null;
        
        Queue<TreeNode<Entry<K, V>>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<Entry<K, V>> current = queue.poll();
            if (current.left == null || current.right == null) {
                return current;
            }
            queue.offer(current.left);
            queue.offer(current.right);
        }
        
        return null;
    }

    private void swap(TreeNode<Entry<K, V>> node, TreeNode<Entry<K, V>> otherNode) {
        Entry<K, V> tempEntry = node.entry;
        node.entry = otherNode.entry;
        otherNode.entry = tempEntry;
    }

    public static <K extends Comparable<? super K>, V> NodeHeap<K, V> merge(
            NodeHeap<K, V> heap1, NodeHeap<K, V> heap2, K priority, V value) {
        NodeHeap<K, V> mergedHeap = new NodeHeap<>();
        
        // Insert the new entry first
        mergedHeap.insert(priority, value);
        
        // Helper function to insert all nodes from a heap
        Queue<TreeNode<Entry<K, V>>> queue = new LinkedList<>();
        
        // Add nodes from first heap
        if (heap1.root != null) {
            queue.offer(heap1.root);
            while (!queue.isEmpty()) {
                TreeNode<Entry<K, V>> node = queue.poll();
                mergedHeap.insert(node.entry.getKey(), node.entry.getValue());
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        // Add nodes from second heap
        if (heap2.root != null) {
            queue.offer(heap2.root);
            while (!queue.isEmpty()) {
                TreeNode<Entry<K, V>> node = queue.poll();
                mergedHeap.insert(node.entry.getKey(), node.entry.getValue());
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        
        return mergedHeap;
    }

    public void levelorder() {
        if (root == null) {
            System.out.println("Heap is empty");
            return;
        }

        Queue<TreeNode<Entry<K, V>>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<Entry<K, V>> current = queue.poll();
            System.out.print(current.entry.getKey() + ":" + current.entry.getValue() + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        System.out.println();
    }
}