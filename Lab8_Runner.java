public class Lab8_Runner {
    public static void main(String[] args) {
        BTNode mango = new BTNode("Mango");
        BTNode jackfruit = new BTNode("Jackfruit");
        BTNode rambutan = new BTNode("Rambutan");
        BTNode peach = new BTNode("Peach");
        BTNode grapes = new BTNode("Grapes");
        BTNode kiwi = new BTNode("Kiwi");
        BTNode vanilla = new BTNode("Vanilla");
        BTNode quince = new BTNode("Quince");

        mango.left = jackfruit;
        mango.right = rambutan;

        jackfruit.left = grapes;
        jackfruit.right = kiwi;

        rambutan.left = peach;
        rambutan.right = vanilla;

        peach.right = quince;

        BinaryTree tree = new BinaryTree(mango);

        System.out.println("---------------------------");
        System.out.println("Printing binary tree:"); 
        tree.printBT();
        
        System.out.println("---------------------------");
        System.out.println("\nIn-Order Traversal:");
        tree.printInOrder();
        
        System.out.println("---------------------------");
        System.out.println("\nReverse In-Order Traversal:");
        tree.printReverseInOrder();
        
        System.out.println("---------------------------");
        System.out.println("\nNodes with Two Children: " + tree.countNodesWithTwoChildren());

        System.out.println("---------------------------");
        System.out.println("Height of Tree: " + tree.getHeight());

        System.out.println("---------------------------");
        System.out.println("Total Number of Nodes: " + tree.size());
        System.out.println("---------------------------");
    }
}

public class BinaryTree {
    private BTNode root;

    public BinaryTree(BTNode root) {
        this.root = root;
    }

    public void printBT() { //Public method to print the tree structure      
        // TODO: Print tree structure with '-' for null branches
        printBTHelper(this.root);   //call the private method to pass in a root node 
    }
    private void printBTHelper(BTNode root){//Private helper method to print tree in pre-order traversal
        if(root == null){ //Print "-" for null branches
            System.out.println("-");
            return;
        }
        else{
            System.out.println("-" + root.data);  //Print node data
            printBTHelper(root.left);//Recur on left subtree
            printBTHelper(root.right);//Recur on right subtree
        }
    }

    public void printInOrder() {//Public method to print the tree in in-order traversal (left -> node -> right)
        // TODO: In-order traversal (left -> node -> right)
        printInOrderHelper(this.root);//Start traversal from the root 
    }
    private void printInOrderHelper(BTNode root){//Private helper method to perform in-order traversal
        if(root == null) return;//Base case: empty node

        printInOrderHelper(root.left);  //Recur on left subtree
        System.out.println(root.data);//Print current node data
        printInOrderHelper(root.right);//Recur on right subtree
    }

    public void printReverseInOrder() {//Public method to print the tree in reverse in-order traversal (right -> node -> left)
        // TODO: Reverse in-order traversal (right -> node -> left)
        printReverseInOrderHelper(this.root);//Start reverse traversal from the root
    }

    private void printReverseInOrderHelper(BTNode root){//Private helper method to perform reverse in-order traversal
        if(root == null) return;//Base case: empty node 
        printReverseInOrderHelper(root.right);  //Recur on right subtree
        System.out.println(root.data);//Print current node data
        printReverseInOrderHelper(root.left);//Recur on left subtree
    }

    public int countNodesWithTwoChildren() {//Public method to count nodes with both left and right children
        // TODO: Count nodes with both left and right children
        return countNodesWithTwoChildrenHelper(this.root);//Start count from the root node 
    }

    private int countNodesWithTwoChildrenHelper(BTNode root){//Private helper method to count nodes with both children
        if(root.left != null && root.right != null){//check if the node has a left and right child that are populated 
            return 1 + countNodesWithTwoChildrenHelper(root.left) + countNodesWithTwoChildrenHelper(root.right);
            //if there is a left AND right child, then 1 is added to the return value 
        }
        else{
            return 0;
            //if the left child OR right child are empty, the if-statement is false, so it will return 0
        }
    }

    public int getHeight() {//Public method to return the height of the tree
        // TODO: Return height of the tree
        return getHeightHelper(this.root) - 1;  //if the tree is empty, it returns -1. if the tree is not empty, it still subtracts one since the root is level 0.
    }

    private int getHeightHelper(BTNode root){//Private helper method to compute the height of the tree
        if(root == null) return 0;
        int leftHeight = 1 + getHeightHelper(root.left);    //this will add 1 per each left node that it traverses 
        int rightHeight = 1 + getHeightHelper(root.right);  //this will add 1 per each right node that it traverses 

        return Math.max(leftHeight, rightHeight);   //return the maximum between left and right because the maximum will be the height of the entire tree 
    }

    public int size() {
        // TODO: Return total number of nodes
        return sizeHelper(this.root);   //call the private method in order to pass a root node 
    }

    private int sizeHelper(BTNode root){
        if (root == null) return 0;//if the tree is empty or a node is empty, return 0;
        return 1 + sizeHelper(root.left) + sizeHelper(root.right); // else, it will add 1 per node that it traverses on the left and right branches 
    }
}

public class BTNode {
    String data;
    BTNode left;
    BTNode right;

    public BTNode(String data) {
        this.data = data;
    }
}


