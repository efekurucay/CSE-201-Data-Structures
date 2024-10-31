import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Ali Çolak
 * 23.12.2021
 */
public class HW3_20190808064_2 {

    public static void main(String[] args) {

        String fileName = args[0];

        File file = new File(fileName);
        ArrayList<Integer> numbers = new ArrayList<>();
        int x = 0;

        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            String line2 = reader.nextLine();
            reader.close();
            String [] array = line.split(" ");
            x = Integer.parseInt(line2);
            for (String str : array) {
                numbers.add(Integer.valueOf(str));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BinarySearchTree2 binarySearchTree = new BinarySearchTree2();

        for (int number : numbers) {
            binarySearchTree.insert(number);
        }

        ArrayList<BinarySearchTree2.Node> externalNodes = binarySearchTree.findExternalNode();

        binarySearchTree.isEqualsX(externalNodes,x);

    }

}

class BinarySearchTree2 {

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

    public BinarySearchTree2() {
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

    public ArrayList<Node> findExternalNode () {

        ArrayList <Node> externalNodes = new ArrayList<>();

        Node temp = this.root;
        Stack<Node> stack = new Stack<>();
        while (temp != null || !stack.isEmpty() ){
            if(temp != null){
                stack.add(temp);
                if (temp.right == null && temp.left==null) {
                    externalNodes.add(temp);
                }
                temp = temp.left;
            }
            else {
                temp = stack.pop();
                temp = temp.right;
            }
        }

        return externalNodes;

    }

    public void isEqualsX (ArrayList<Node> externalNodes, int x) {

        int sum ;
        ArrayList <Node> nodes ;
        boolean isEquals = false;

        for (int i = 0; i < externalNodes.size(); i++) {
            sum = 0 ;
            nodes = new ArrayList<>();

            Node node = externalNodes.get(i);
            sum+=node.getValue();
            nodes.add(node);

            while (node.parent!=null) {
                sum+=node.parent.getValue();
                nodes.add(node.parent);
                node = node.parent;
            }

            if (sum==x) {
                isEquals = true;
                System.out.println("true");
                for (int j = nodes.size()-1; j >= 0; j--) {
                    if (j==0)
                        System.out.print(nodes.get(j).getValue());
                    else
                        System.out.print(nodes.get(j).getValue()+"-");
                }
            }
        }

        if (!isEquals)
            System.out.println("false");
    }
}