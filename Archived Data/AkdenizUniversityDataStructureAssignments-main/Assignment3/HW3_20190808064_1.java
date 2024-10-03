import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Ali Çolak
 * 23.12.2021
 */
public class HW3_20190808064_1 {


    public static void main(String[] args) {

        String fileName = args[0];

        File file = new File(fileName);
        ArrayList<Integer> numbers = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            reader.close();
            String [] array = line.split(" ");
            for (String str : array) {
                numbers.add(Integer.valueOf(str));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BinarySearchTree binarySearchTree = new BinarySearchTree();

        for (int number : numbers) {
            binarySearchTree.insert(number);
        }

        binarySearchTree.isBalancedTree();

    }


}
class BinarySearchTree {

    class Node {

        private Node left;
        private Node right;
        private Node parent;
        private int value;

        public Node (int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public int getValue() {
            return value;
        }

    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }


    public void insert (int x) {
        Node newNode = new Node(x);
        if (this.root==null) {
            this.root = newNode;
        } else {
            Node tempRoot = root;
            while (true) {
                if (tempRoot.getValue()>x) {
                    if (tempRoot.left==null) {
                        newNode.parent = tempRoot;
                        tempRoot.left = newNode;
                        break;
                    } else tempRoot = tempRoot.left;
                } else {
                    if (tempRoot.right==null) {
                        newNode.parent = tempRoot;
                        tempRoot.right = newNode;
                        break;
                    } else tempRoot = tempRoot.right;
                }
            }
        }
    }

    public void isBalancedTree (){

        Node tempRoot = this.root;

        Stack <Node> stack = new Stack<>();

        while (tempRoot != null || !stack.isEmpty() ){
            if(tempRoot != null){
                stack.add(tempRoot);
                int leftHeight = height(tempRoot.left);
                int rightHeight = height(tempRoot.right);
                if (Math.abs(leftHeight-rightHeight)>1) {
                    System.out.println("false");
                    return;
                }
                tempRoot = tempRoot.left;
            }
            else {
                tempRoot = stack.pop();
                tempRoot = tempRoot.right;
            }
        }
        System.out.println("true");
    }

    public int height(Node node) {
        if (node == null) return 0;
        else return 1 + Math.max( height(node.left) , height(node.right) );
    }

}