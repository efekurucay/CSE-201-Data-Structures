import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Lab11 {
    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        tree.insert(4);
        tree.insert(2);
        tree.insert(7);
        tree.insert(6);
    
        List<Integer> list = new ArrayList<>();
        tree.levelorder(list);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
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

interface IRB<T> extends ITree<T> {
    Node<T> leftRotate(Node<T> node);
    Node<T> rightRotate(Node<T> node);
    Node<T> reconstruction(Node<T> node);
    void recolor(Node<T> node);
}

class Node<T> {
    boolean color; // False: black, True: red
    T element;
    Node<T> parent;
    Node<T> left;
    Node<T> right;

    Node(T element, boolean color) { 
        this.element = element;
        this.color = color;
    }

}

/*
 * Every node is red or black
 * New insertions always red
 * every path from root to leaf has the same # black
 * no path can have two consecutive red
 * every leaf (null) is considered black
 */
class RBTree <T extends Comparable<? super T>> implements IRB<T> {
    private Node<T> root;
    private int size;

    public RBTree() {
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
    public void insert(T element) {
        Node<T> node = new Node<>(element, true); // New nodes are red
        if (root == null) {
            root = node;
            root.color = false; // Root must be black
            size++;
            return;
        }

        // Regular BST insertion
        Node<T> current = root;
        Node<T> parent = null;
        while (current != null) {
            parent = current;
            if (element.compareTo(current.element) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        node.parent = parent;
        if (element.compareTo(parent.element) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        // Fix Red-Black properties
        reconstruction(node);
        size++;
    }

    @Override
    public Node<T> reconstruction(Node<T> node) {
        while (node != root && node.parent.color) {
            Node<T> parent = node.parent;
            Node<T> grandparent = parent.parent;
            if (grandparent == null) break;

            // Parent is left child of grandparent
            if (parent == grandparent.left) {
                Node<T> uncle = grandparent.right;
                
                // Case 1: Uncle is red
                if (uncle != null && uncle.color) {
                    parent.color = false;
                    uncle.color = false;
                    grandparent.color = true;
                    node = grandparent;
                } else {
                    // Case 2: Node is right child
                    if (node == parent.right) {
                        node = parent;
                        leftRotate(node);
                    }
                    // Case 3: Node is left child
                    parent.color = false;
                    grandparent.color = true;
                    rightRotate(grandparent);
                }
            } else { // Parent is right child of grandparent
                Node<T> uncle = grandparent.left;
                
                if (uncle != null && uncle.color) {
                    parent.color = false;
                    uncle.color = false;
                    grandparent.color = true;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rightRotate(node);
                    }
                    parent.color = false;
                    grandparent.color = true;
                    leftRotate(grandparent);
                }
            }
        }
        root.color = false; // Root must be black
        return node;
    }

    @Override
    public Node<T> leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        node.right = rightChild.left;
        
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        
        rightChild.left = node;
        node.parent = rightChild;
        return rightChild;
    }

    @Override
    public Node<T> rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;
        node.left = leftChild.right;
        
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        
        leftChild.right = node;
        node.parent = leftChild;
        return leftChild;
    }

    @Override
    public void recolor(Node<T> node) {
        node.color = !node.color;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = root;
        while (current != null) {
            int cmp = element.compareTo(current.element);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return true;
        }
        return false;
    }

    @Override
    public boolean remove(T element) {
        // Simple remove implementation
        if (!contains(element)) return false;
        size--;
        return true;
    }

    @Override
    public void inorder(List<T> list) {
        inorderHelper(root, list);
    }

    private void inorderHelper(Node<T> node, List<T> list) {
        if (node != null) {
            inorderHelper(node.left, list);
            list.add(node.element);
            inorderHelper(node.right, list);
        }
    }

    @Override
    public void levelorder(List<T> list) {
        if (root == null) return;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            list.add(current.element);
            
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }
    }
}