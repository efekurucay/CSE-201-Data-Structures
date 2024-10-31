/**---------------------------------------------------

*Akdeniz University CSE201 Data Structures Labs

*Yahya Efe Kurucay

*31.10.2024

*Description: Lab07 Exercises

*Proposed grade: ?

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
 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import java.util.List;
import java.util.ArrayList;

/*
 * Please don't use any generative AI,
 * 
 * I choose to showcase how to use Strategy design pattern in action,
 * Because of this it may look complicated, but really, it is not
 * 
 * contains method's main implementation of the method will be in
 * DFS and BFS classes. These classes just have 2 methods each, which will be the actual
 * Depth-first search and Breadth-first search implementations.
 * hint: you can use get methods in contains methods
 */
public class Lab07 {
    public static void main(String[] args) {
        // BFS stratejisi ile bir ağaç oluşturalım
        System.out.println("=== BFS Stratejisi ile Test ===");
        BinaryTree<Integer> bfsTree = new BinaryTree<>(new BFS<>());
        
        // Boş ağaç testleri
        System.out.println("\n1. Boş Ağaç Testleri:");
        System.out.println("Boş mu: " + bfsTree.isEmpty());
        System.out.println("Boyut: " + bfsTree.size());
        System.out.println("Yükseklik: " + bfsTree.height());
        
        // Ekleme testleri
        System.out.println("\n2. Ekleme Testleri:");
        System.out.println("Elemanlar ekleniyor: 5, 3, 7, 2, 4, 6, 8");
        bfsTree.insert(5);
        bfsTree.insert(3);
        bfsTree.insert(7);
        bfsTree.insert(2);
        bfsTree.insert(4);
        bfsTree.insert(6);
        bfsTree.insert(8);
        
        System.out.println("\nAğaç Görünümü:");
        System.out.println(bfsTree.toString());
        
        // Traversal testleri
        System.out.println("\n3. Traversal Testleri:");
        System.out.println("Pre-order traversal (1):");
        bfsTree.traverse(1);
        
        System.out.println("In-order traversal (2):");
        bfsTree.traverse(2);
        
        System.out.println("Post-order traversal (3):");
        bfsTree.traverse(3);
        
        System.out.println("Level-order traversal (default):");
        bfsTree.traverse(0);
        
        // Arama testleri
        System.out.println("\n4. Arama Testleri:");
        System.out.println("7 var mı? " + bfsTree.contains(7));
        System.out.println("9 var mı? " + bfsTree.contains(9));
        
        // Silme testleri
        System.out.println("\n5. Silme Testleri:");
        System.out.println("3 siliniyor...");
        bfsTree.delete(3);
        System.out.println("Yeni ağaç görünümü:");
        System.out.println(bfsTree.toString());
        System.out.println("Level-order traversal:");
        bfsTree.traverse(0);
        
        // DFS stratejisi ile yeni bir ağaç test edelim
        System.out.println("\n=== DFS Stratejisi ile Test ===");
        BinaryTree<Integer> dfsTree = new BinaryTree<>(new DFS<>());
        
        // Aynı elemanları ekleyelim
        System.out.println("\n1. DFS Ağacı Oluşturma:");
        dfsTree.insert(5);
        dfsTree.insert(3);
        dfsTree.insert(7);
        dfsTree.insert(2);
        dfsTree.insert(4);
        
        System.out.println("\nDFS Ağaç Görünümü:");
        System.out.println(dfsTree.toString());
        
        // DFS araması test edelim
        System.out.println("\n2. DFS Arama Testleri:");
        System.out.println("4 var mı? " + dfsTree.contains(4));
        System.out.println("8 var mı? " + dfsTree.contains(8));
        
        // Ağaç bilgileri
        System.out.println("\n3. DFS Ağaç Bilgileri:");
        System.out.println("Boyut: " + dfsTree.size());
        System.out.println("Yükseklik: " + dfsTree.height());
        System.out.println("Boş mu: " + dfsTree.isEmpty());
        
        // Exception handling test
        System.out.println("\n4. Hata Durumu Testleri:");
        try {
            dfsTree.delete(10); // Olmayan bir elemanı silmeye çalış
            System.out.println("10 silme denemesi: false");
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }
}

interface Search <T> {
    boolean contains(BinaryTree.Node<T> root, T value);
    BinaryTree.Node<T> get(BinaryTree.Node<T> root, T value);
}

/*
 * Binary Tree interface
 */
interface IBinaryTree <T> {
    void insert(T value);
    boolean contains(T value);
    boolean delete(T value);
    int height();
    boolean isEmpty();
    int size();
    void traverse(int order);
}

/*
 * This is not a BST! order in which elements are stored doesn't matter
 * !!Needs to satisfy complete binary tree properties at all times!!
 */
class BinaryTree <T> implements IBinaryTree <T> {

    // class node 
    static class Node<T> {
        /*
         * no parent reference
         */
        T data;
        Node<T> left;
        Node<T> right;
        
        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    
    /* data fields
     * Necessary data fields:
     *  Node root, int size, Search traversalStrategy (only going to use in contains)
     */
    private Node<T> root;
    private int size;
    private Search<T> traversalStrategy;
    
    // Constructor(Search)
    public BinaryTree(Search<T> strategy) {
        root = null;
        size = 0;
        this.traversalStrategy = strategy;
    }
    // Method to test the code
    public Node<T> getRoot() {
        /* Convenience method for me to test your code */
        return root;
    }

    /*
     * public boolean delete(T value)
     *      ^^ find taret node (node with value), find last node (target) in BFS manner
     *      ^^ (Optional) can use strategy.get(T value) for target, 
     *      ^^ only if strategy is from BFS class, if DFS would be incorrect
     * private boolean deleteLastNode(Node<T> root, Node<T> target) // recursive DFS implementation
     * private int heightRec(Node<T> node) // Recursive helper implemenation
     */
    @Override
    public boolean delete(T value) {
        if (root == null) return false;
        
        // ^^ find taret node (node with value), find last node (target) in BFS manner
        Node<T> target = new BFS<T>().get(root, value);
        if (target == null) return false;
        
        // Find last node
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        Node<T> lastNode = null;
        Node<T> parentOfLast = null;
        
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            if (current.left != null) {
                queue.offer(current.left);
                lastNode = current.left;
                parentOfLast = current;
            }
            if (current.right != null) {
                queue.offer(current.right);
                lastNode = current.right;
                parentOfLast = current;
            }
        }
        target.data = lastNode.data;
        // Remove the last node
        if (parentOfLast.right == lastNode) {
            parentOfLast.right = null;
        } else {
            parentOfLast.left = null;
        }
        
        size--;
        return true;
    }
    
    private int heightRec(Node<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }
    

    //All required methods, additionally below ones
    // private List<T> inorder(Node<T> node) // recursive DFS inorder traversal
    private List<T> inorder(Node<T> node) {
        List<T> result = new ArrayList<>();
        if (node != null) {
            result.addAll(inorder(node.left));
            result.add(node.data);
            result.addAll(inorder(node.right));
        }
        return result;
    }
    //private List<T> postorder(Node<T> node) // recursive DFS postorder traversal
    private List<T> postorder(Node<T> node) {
        List<T> result = new ArrayList<>();
        if (node != null) {
            result.addAll(postorder(node.left));
            result.addAll(postorder(node.right));
            result.add(node.data);
        }
        return result;
    }
    //private List<T> preorder(Node<T> node) // recursive DFS preorder traversal
    private List<T> preorder(Node<T> node) {
        List<T> result = new ArrayList<>();
        if (node != null) {
            result.add(node.data);
            result.addAll(preorder(node.left));
            result.addAll(preorder(node.right));
        }
        return result;
    }
    //private List<T> levelorder(Node<T> node) // iterative BFS levelorder traversal
    private List<T> levelorder(Node<T> node) {
        List<T> result = new ArrayList<>();
        if (node == null) return result;
        
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.data);
            
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        
        return result;
    }
    //return a list with values, order matters, optionally print to observer

      /* public void traverse(int order)
       * Simply, use switch-case to determine traversal technique [1, 2, or 3], by default level-order
       * 1: preorder
       * 2: inorder
       * 3: postorder
       * default: level-order
       */
    @Override
    public void traverse(int order) {
        List<T> result;
        switch (order) {
            case 1:
                result = preorder(root);
                break;
            case 2:
                result = inorder(root);
                break;
            case 3:
                result = postorder(root);
                break;
            default:
                result = levelorder(root);
        }
        System.out.println(result);
    }


  @Override
    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return;
        }
        
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            
            if (current.left == null) {
                current.left = new Node<>(value);
                size++;
                return;
            }
            if (current.right == null) {
                current.right = new Node<>(value);
                size++;
                return;
            }
            
            queue.offer(current.left);
            queue.offer(current.right);
        }
    }
   
    @Override
    public boolean contains(T value) {
        return traversalStrategy.contains(root, value);
    }
    
    @Override
    public int height() {
        return heightRec(root);
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public int size() {
        return size;
    }

       /* ===================== toString() ==========================
        * to see the tree, first implement height method
        */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<List<String>> levels = new ArrayList<>();
        int height = height();

        fillLevels(root, levels, 0);


        int maxWidth = (int) Math.pow(2, height) - 1;
        for (int i = 0; i < levels.size(); i++) {
            List<String> level = levels.get(i);
            int gap = (maxWidth / (int) Math.pow(2, i + 1));

            sb.append(" ".repeat(gap));
            for (String s : level) {
                sb.append(s);
                sb.append(" ".repeat(2 * gap + 1));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private void fillLevels(Node<T> node, List<List<String>> levels, int depth) {
        if (depth >= levels.size()) {
            levels.add(new ArrayList<>());
        }
        if (node == null) {
            levels.get(depth).add(" ");
            return;
        }
        levels.get(depth).add(node.data.toString());
        fillLevels(node.left, levels, depth + 1);
        fillLevels(node.right, levels, depth + 1);
    }

}
// concrete BFS generic class which implements Search interface
class BFS<T> implements Search<T> {
    @Override
    public boolean contains(BinaryTree.Node<T> root, T value) {
        return get(root, value) != null;
    }
    
    @Override
    public BinaryTree.Node<T> get(BinaryTree.Node<T> root, T value) {
        if (root == null) return null;
        
        Queue<BinaryTree.Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            BinaryTree.Node<T> current = queue.poll();
            
            if (current.data.equals(value)) {
                return current;
            }
            
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
        
        return null;
    }
}

//concrete DFS generic class which implements Search interface
//ITERATIVE DFS implementation!

class DFS<T> implements Search<T> {
    @Override
    public boolean contains(BinaryTree.Node<T> root, T value) {
        return get(root, value) != null;
    }
    
    @Override
    public BinaryTree.Node<T> get(BinaryTree.Node<T> root, T value) {
        if (root == null) return null;
        
        Stack<BinaryTree.Node<T>> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            BinaryTree.Node<T> current = stack.pop();
            
            if (current.data.equals(value)) {
                return current;
            }
            
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        
        return null;
    }
}

// Node class implementation


