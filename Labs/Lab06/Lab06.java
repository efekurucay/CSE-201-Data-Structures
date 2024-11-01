import java.util.ArrayList;
import java.util.List;
/**
 * Akdeniz University CSE201 Data Structures Labs
 * Yahya Efe Kurucay
 * 01.11.2024
 * Description: Lab06
 * Proposed grade:  
 * Website: https://efekurucay.com
 */

/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */
public class Lab06 {
    public static void main(String[] args) {
        GeneralTree<String> tree = new GeneralTree<>();
        TreeNode<String> root = new TreeNode<>("Root", null);
        tree.addRootChild(root);

        TreeNode<String> child1 = new TreeNode<>("Child1", root);
        TreeNode<String> child2 = new TreeNode<>("Child2", root);

        root.addChild(child1);
        root.addChild(child2);

        TreeNode<String> child1_1 = new TreeNode<>("Child1.1", child1);
        child1.addChild(child1_1);

        System.out.println("Preorder traversal:");
        tree.preorder(root);

        System.out.println("\nPostorder traversal:");
        tree.postorder(root);

        System.out.println("\nTree height: " + tree.getHeight(root));
        System.out.println("Depth of Child1.1: " + tree.getDepth(child1_1));
    }
}


// TreeNode<E> generic class represents a single node in the tree
/*
 * Data Fields: Data, parent and List of children
 * Constructor(E data, TreeNode<E> parent)
 * Methods:
 *      Accessors for fields
 *      addChild(TreeNode<E> child)
 */


 // GeneralTree<E> generic class represents a tree
 /*
  * Data Fields: TreeNode<E> root
  * Constructor: Tree() sets root as null
  * Methods:
  *     addRootChild(TreeNode<E> data)
  *     getHeight(TreeNode<E> node)
  *     getDepth(TreeNode<E> node)
  *     preorder(TreeNode<E> node)
  *     postorder(TreeNode<E> node)
  */

class TreeNode<E> {
    private E data;
    private TreeNode<E> parent;
    private List<TreeNode<E>> children;

    public TreeNode(E data, TreeNode<E> parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public TreeNode<E> getParent() {
        return parent;
    }

    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    public List<TreeNode<E>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<E> child) {
        children.add(child);
        child.setParent(this);
    }
}

class GeneralTree<E> {
    private TreeNode<E> root;

    public GeneralTree() {
        this.root = null;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void addRootChild(TreeNode<E> child) {
        if (root == null) {
            root = child;
        } else {
            root.addChild(child);
        }
    }

    public int getHeight(TreeNode<E> node) {
        if (node == null || node.getChildren().isEmpty()) {
            return 0;
        }
        int maxHeight = 0;
        for (TreeNode<E> child : node.getChildren()) {
            maxHeight = Math.max(maxHeight, getHeight(child));
        }
        return maxHeight + 1;
    }

    public int getDepth(TreeNode<E> node) {
        int depthCount = 0;
        TreeNode<E> currentNode = node;
        while (currentNode.getParent() != null) {
            depthCount++;
            currentNode = currentNode.getParent();
        }
        return depthCount;
    }

    public void preorder(TreeNode<E> node) {
        if (node == null) return;
        System.out.print(node.getData() + " ");
        for (TreeNode<E> child : node.getChildren()) {
            preorder(child);
        }
    }

    public void postorder(TreeNode<E> node) {
        if (node == null) return;
        for (TreeNode<E> child : node.getChildren()) {
            postorder(child);
        }
        System.out.print(node.getData() + " ");
    }
}

