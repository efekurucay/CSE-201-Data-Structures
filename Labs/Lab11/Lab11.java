/**---------------------------------------------------
████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████████████████████████
██████ *Akdeniz University CSE201 Data Structures Labs                        ██████
██████                                                                        ██████
██████ *Yahya Efe Kurucay                                                     ██████
██████                                                                        ██████
██████ *12.12.2024                                                            ██████
██████                                                                        ██████
██████ *Description: Lab11 Exercises                                          ██████ 
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

public class Lab11 {
    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        Integer[] data = {4, 2, 7, 6, 1, 3};
        
        System.out.println("Inserting elements: ");
        for (int num : data) {
            System.out.print(num + " ");
            rbTree.insert(num);
        }
        System.out.println("\n");
        List<Integer> inorderList = new ArrayList<>();
        rbTree.inorder(inorderList);
        System.out.print("Inorder traversal: ");
        System.out.println(inorderList);
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
    private static final boolean RED = true;
    private static final boolean BLACK = false;

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
    public Node<T> reconstruction(Node<T> node) {
        // color change with uncle
        Node<T> uncle = getUncle(node);
        if (uncle != null && uncle.color == RED) {
            node.parent.color = BLACK;
            uncle.color = BLACK;
            node.parent.parent.color = RED;
            return node.parent.parent;
        }
        
        if (node == node.parent.right && node.parent == node.parent.parent.left) {
            node = node.parent;
            leftRotate(node);
        } else if (node == node.parent.left && node.parent == node.parent.parent.right) {
            node = node.parent;
            rightRotate(node);
        }
        
        node.parent.color = BLACK;
        node.parent.parent.color = RED;
        
        if (node == node.parent.left) {
            rightRotate(node.parent.parent);
        } else {
            leftRotate(node.parent.parent);
        }
        
        return node;
    }

    private Node<T> getSibling(Node<T> node) {
        if (node == null || node.parent == null) {
            return null;
        }
        if (node == node.parent.left) {
            return node.parent.right;
        } else {
            return node.parent.left;
        }
    }

    private Node<T> getUncle(Node<T> node) {
        if (node.parent == null || node.parent.parent == null) {
            return null;
        }
        return getSibling(node.parent);
    }

    @Override
    public void insert(T element) {
        Node<T> newNode = new Node<>(element, RED);
        
        if (root == null) {
            root = newNode;
            root.color = BLACK;
            size++;
            return;
        }
        
        Node<T> current = root;
        Node<T> parent = null;
        
        while (current != null) {
            parent = current;
            int compare = element.compareTo(current.element);
            
            if (compare < 0) {
                current = current.left;
            } else if (compare > 0) {
                current = current.right;
            } else {
                return; // Duplicate 
            }
        }
        
        newNode.parent = parent;
        int compare = element.compareTo(parent.element);
        
        if (compare < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        
        size++;
        
        fixInsert(newNode);
    }

    private void fixInsert(Node<T> node) {
        while (node != root && node.parent.color == RED) {
            node = reconstruction(node);
        }
        root.color = BLACK;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = root;
        while (current != null) {
            int compare = element.compareTo(current.element);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    @Override
    public void inorder(List<T> list) {
        inorderTraversal(root, list);
    }

    private void inorderTraversal(Node<T> node, List<T> list) {
        if (node != null) {
            inorderTraversal(node.left, list);
            list.add(node.element);
            inorderTraversal(node.right, list);
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
            
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    @Override
    public boolean remove(T element) {
        Node<T> node = findNode(element);
        if (node == null) {
            return false;
        }
        
        size--;
        return true;
    }

    private Node<T> findNode(T element) {
        Node<T> current = root;
        while (current != null) {
            int compare = element.compareTo(current.element);
            if (compare == 0) {
                return current;
            } else if (compare < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }
}