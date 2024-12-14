
/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Labs                        ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *05.12.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Lab10 Exercises                                          ██████ 
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


 /*
  * 
 ███▄ ▄███▓▓█████  ██░ ██  ██▓     ██▓ ███▄ ▄███▓    ▄▄▄        ██████  ██▓  ▄████  ██▓ ███▄ ▄███▓   
▓██▒▀█▀ ██▒▓█   ▀ ▓██░ ██▒▓██▒    ▓██▒▓██▒▀█▀ ██▒   ▒████▄    ▒██    ▒ ▓██▒ ██▒ ▀█▒▓██▒▓██▒▀█▀ ██▒   
▓██    ▓██░▒███   ▒██▀▀██░▒██░    ▒██▒▓██    ▓██░   ▒██  ▀█▄  ░ ▓██▄   ▒██▒▒██░▄▄▄░▒██▒▓██    ▓██░   
▒██    ▒██ ▒▓█  ▄ ░▓█ ░██ ▒██░    ░██░▒██    ▒██    ░██▄▄▄▄██   ▒   ██▒░██░░▓█  ██▓░██░▒██    ▒██    
▒██▒   ░██▒░▒████▒░▓█▒░██▓░██████▒░██░▒██▒   ░██▒    ▓█   ▓██▒▒██████▒▒░██░░▒▓███▀▒░██░▒██▒   ░██▒   
░ ▒░   ░  ░░░ ▒░ ░ ▒ ░░▒░▒░ ▒░▓  ░░▓  ░ ▒░   ░  ░    ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░░▓   ░▒   ▒ ░▓  ░ ▒░   ░  ░   
░  ░      ░ ░ ░  ░ ▒ ░▒░ ░░ ░ ▒  ░ ▒ ░░  ░      ░     ▒   ▒▒ ░░ ░▒  ░ ░ ▒ ░  ░   ░  ▒ ░░  ░      ░   
░      ░      ░    ░  ░░ ░  ░ ░    ▒ ░░      ░        ░   ▒   ░  ░  ░   ▒ ░░ ░   ░  ▒ ░░      ░      
       ░      ░  ░ ░  ░  ░    ░  ░ ░         ░            ░  ░      ░   ░        ░  ░         ░      
                                                                                                     


   
  */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Lab10 {
    public static void main(String[] args) {
        System.out.println("AVLTree test");
        System.out.println("");
        System.out.println("██████████████████████");
        AVL<Integer> avlTree = new AVL<>();

        System.out.println("\n sort addde");
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30); // Bu durumda rotasyon gerekecek
        printTreeStatus(avlTree);

        System.out.println("\nbalance add");
        avlTree = new AVL<>();
        avlTree.insert(50);
        avlTree.insert(30);
        avlTree.insert(70);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(60);
        avlTree.insert(80);
        printTreeStatus(avlTree);

        System.out.println("\n LR rotation");
        avlTree = new AVL<>();
        avlTree.insert(50);
        avlTree.insert(30);
        avlTree.insert(40);
        printTreeStatus(avlTree);

        System.out.println("\n RL rotation");
        avlTree = new AVL<>();
        avlTree.insert(50);
        avlTree.insert(70);
        avlTree.insert(60);
        printTreeStatus(avlTree);

        System.out.println("\n deleting");
        avlTree = new AVL<>();
        avlTree.insert(50);
        avlTree.insert(30);
        avlTree.insert(70);
        avlTree.insert(20);
        avlTree.insert(40);
        System.out.println("before delete");
        printTreeStatus(avlTree);
        
        avlTree.remove(30);
        System.out.println("after delete 30");
        printTreeStatus(avlTree);

        System.out.println("\nmanuel rotation");
        avlTree = new AVL<>();
        avlTree.insert(30);
        avlTree.insert(20);
        avlTree.insert(40);
        avlTree.insert(10);
        System.out.println("before rotation");
        printTreeStatus(avlTree);
        
        avlTree.rightRotate(30);
        System.out.println("after right rotation");
        printTreeStatus(avlTree);
    }

    private static void printTreeStatus(AVL<Integer> tree) {
        ArrayList<Integer> inorderList = new ArrayList<>();
        ArrayList<Integer> levelorderList = new ArrayList<>();
        tree.inorder(inorderList);
        tree.levelorder(levelorderList);
        System.out.println("Inorder traversal: " + inorderList);
        System.out.println("Level-order traversal: " + levelorderList);
        System.out.println("Tree size: " + tree.size());
        System.out.println("██████████████████████");
    }
}





interface IList<T> {
    int size();
    boolean isEmpty();
}

interface ITree<T> extends IList<T> {
    void insert(T element);
    boolean remove(T element);
    boolean contains(T element);
    void inorder(List<T> list);
    void levelorder(List<T> list);
}

interface IAVL<T> extends ITree<T> {
    Node<T> rebalance(Node<T> node);
    Node<T> leftRotate(Node<T> node);
    Node<T> rightRotate(Node<T> node);
    Node<T> doubleRotateLR(Node<T> node);
    Node<T> doubleRotateRL(Node<T> node);
}

class Node<T> {
    int height;
    T element;
    Node<T> parent;
    Node<T> left;
    Node<T> right;

    Node(T element) {
        this.element = element;
        this.height = 1;
    }
}

class BST<T extends Comparable<? super T>> implements ITree<T> {
    protected Node<T> root;
    private int size;

    public Node<T> getRoot() {
        return root;
    }

    public BST() {}

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
        root = insertRec(root, element);
    }

    protected Node<T> insertRec(Node<T> node, T element) {
        if (node == null) {
            size++;
            return new Node<>(element);
        }

        if (element.compareTo(node.element) < 0) {
            node.left = insertRec(node.left, element);
            node.left.parent = node;
        }

        if (element.compareTo(node.element) > 0) {
            node.right = insertRec(node.right, element);
            node.right.parent = node;
        }

        return node;
    }

    @Override
    public boolean remove(T element) {
        if (contains(element)) {
            root = removeRec(root, element);
            size--;
            return true;
        }
        return false;
    }

    protected Node<T> removeRec(Node<T> node, T element) {
        if (node == null)
            return null;
        if (element.compareTo(node.element) < 0)
            node.left = removeRec(node.left, element);
        else if (element.compareTo(node.element) > 0)
            node.right = removeRec(node.right, element);
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            node.element = findMin(node.right);
            node.right = removeRec(node.right, node.element);
        }
        return node;
    }

    private T findMin(Node<T> node) {
        Node<T> current = node;
        while (current.left != null)
            current = current.left;
        return current.element;
    }

    private T findMax(Node<T> node) {
        Node<T> current = node;
        while(current.right != null)
            current = current.right;
        return current.element;
    }

    @Override
    public boolean contains(T element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<T> node, T element) {
        if (node == null)
            return false;
        if (element.compareTo(node.element) == 0)
            return true;
        return element.compareTo(node.element) < 0
                ? containsRec(node.left, element)
                : containsRec(node.right, element);
    }

    @Override
    public void inorder(List<T> list) {
        inorderRec(root, list);
    }

    private void inorderRec(Node<T> node, List<T> list) {
        if (node == null)
            return;

        inorderRec(node.left, list);
        list.add(node.element);
        inorderRec(node.right, list);
    }

    @Override
    public void levelorder(List<T> list) {
        if (root == null) {
            return;
        }
    
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
    
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll(); 
            list.add(current.element);    
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}

class AVL<T extends Comparable<? super T>> extends BST<T> implements IAVL<T> {
    private int height(Node<T> node) {
        if (node == null) {return 0;} 
        else {return node.height;}
    }

    private int getBalanceFactor(Node<T> node) {
        if (node == null) {return 0;} 
        else {return height(node.left) - height(node.right);}
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {node.height = 1 + Math.max(height(node.left), height(node.right));}
    }

    @Override
    protected Node<T> insertRec(Node<T> node, T element) {
        node = super.insertRec(node, element);
        updateHeight(node);
        return rebalance(node);
    }

    @Override
    protected Node<T> removeRec(Node<T> node, T element) {
        node = super.removeRec(node, element);
        if (node != null) {
            updateHeight(node);
            return rebalance(node);
        }
        return null;
    }

    @Override
    public Node<T> rebalance(Node<T> node) {
        if (node == null) return null;

        int balance = getBalanceFactor(node);

        // Left heavy
        if (balance > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                return rightRotate(node);
            } else {
                return doubleRotateLR(node);
            }
        }

        // Right heavy
        if (balance < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                return leftRotate(node);
            } else {
                return doubleRotateRL(node);
            }
        }

        return node;
    }


    private Node<T> findNodeAndParent(T element, Node<T>[] result) {
        Node<T> parent = null;
        Node<T> current = root;
        
        while (current != null && !current.element.equals(element)) {
            parent = current;
            current = element.compareTo(current.element) < 0 ? current.left : current.right;
        }
        
        if (result != null) {
            result[0] = parent;
        }
        return current;
    }



    @Override
    public Node<T> leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        Node<T> centerChild = rightChild.left;
        rightChild.left = node;
        node.right = centerChild;
        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }

    public void leftRotate(T element) {
        Node<T>[] parentHolder = new Node[1];
        Node<T> current = findNodeAndParent(element, parentHolder);
        Node<T> parent = parentHolder[0];
        
        if (current == null || current.right == null) return;
        
        Node<T> rotated = leftRotate(current);
        if (parent == null) root = rotated;
        else if (parent.left == current) parent.left = rotated;
        else parent.right = rotated;
    }


    @Override
    public Node<T> rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;
        Node<T> centerChild = leftChild.right;
        leftChild.right = node;
        node.left = centerChild;
        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    public void rightRotate(T element) {
        Node<T>[] parentHolder = new Node[1];
        Node<T> current = findNodeAndParent(element, parentHolder);
        Node<T> parent = parentHolder[0];
        
        if (current == null || current.left == null) return;
        
        Node<T> rotated = rightRotate(current);
        if (parent == null) root = rotated;
        else if (parent.left == current) parent.left = rotated;
        else parent.right = rotated;
    }


    @Override
    public Node<T> doubleRotateLR(Node<T> node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    @Override
    public Node<T> doubleRotateRL(Node<T> node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }
}
//HELPER 
// Ağaçta ekleme veya silme yapılan her düğüm için:
// sol <- Düğümün sol kolunun derinliğini ölç
// sağ <- Düğümün sağ kolunun derinliğini ölç
// şayet sol - sağ >1
//     sola dengele
// şayet sağ - sol < -1
//     sağa dengele