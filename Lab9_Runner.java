
import java.util.Arrays;

class Lab9_Runner{
  public static void main(String[] args){
    System.out.println("---------------------------");       
    BinaryTree bst = new BinaryTree();
    bst.insertBST("Mango");    
    bst.insertBST("Jackfruit");        
    bst.insertBST("Rambutan");    
    bst.insertBST("Peach");  
    bst.insertBST("Grapes");    
    bst.insertBST("Kiwi");            
    bst.insertBST("Vanilla");    
    bst.insertBST("Quince");    
    
    System.out.println(bst.insertBST("Mango"));

    System.out.println("---------------------------");        
    System.out.println("Printing BST:");    
    bst.printBT();
    System.out.println("---------------------------");
        
    System.out.print("Total number of nodes: ");    
    System.out.println(bst.size());            
    System.out.println("---------------------------");        
    
    System.out.println("Printing BST in ascending order:");
    bst.printAscending();    
    System.out.println("---------------------------"); 
    
    System.out.println("Printing BST in descending order:");
    bst.printDescending();
    System.out.println("---------------------------");    
        
    System.out.print("The longest string is: ");    
    System.out.println(bst.getLongestString());    
 
    System.out.println("---------------------------");      
    System.out.println("Retrieving the tree content in a String array: ");
    String[] strArray = bst.getAsArray();
    System.out.println( Arrays.toString(strArray));

    System.out.println("---------------------------");     
    
  }  
}

class BinaryTree{
  BTNode root; // The binary tree root
  
  int count; // Number of elements currently in the node
  
  BinaryTree(){
  }
  
  BinaryTree(String str){
    root = new BTNode(str);
  }
  
  /**
   * @return Number of elements in the binary 
   * search tree.
   */
  public int size(){
    return sizeHelper(this.root);//use a helper method to pass in a root
  }
  private int sizeHelper(BTNode root){
    //base case
    if(root == null) return 0;
    return 1 + sizeHelper(root.left) + sizeHelper(root.right);
  }
  
  /**
   * Insert the string in the parameter into
   * the Binary Search Tree.
   * @param str
   * @return true if insertion is successful.
   */
  public boolean insertBST(String str){
    BTNode temp = new BTNode(str);//create a new temporary node to pass in as an argument to the helper method 
    return insertBSTHelper(temp, this.root);//call the helper method so we can pass in a root 
  }
  private boolean insertBSTHelper(BTNode temp, BTNode root){
    //use the first letter of the string to compare ascii values and order the tree as left < root < right
    //the method will reject duplicate values by returning false 
    //base case / edge case
    if(root == null){
      this.root = temp;//I used "this" keyword to update the class-level root instead of updating the local root variable 
      return true;
    }
    String tempVariable = (String) temp.data;//parse the two objects into strings so we can compare the ascii values 
    String rootVariable = (String) root.data;
    //if the root is not null, the new node will either be less than, greater than, or equal to the root
    if (tempVariable.charAt(0) < rootVariable.charAt(0)){
      if(root.left == null){
        root.left = temp;
        return true;
      }else{
        return insertBSTHelper(temp, root.left);//this recursive call will find a null position eventually and insert the new node 
      }
    }
    else if (tempVariable.charAt(0) > rootVariable.charAt(0)){
      if(root.right == null){
        root.right = temp;
        return true;
      }else{
        return insertBSTHelper(temp, root.right);//this will iterate to the right to find a null position 
      }
    }
    else{return false;} //if its not null or <> then we have found a duplicate value, so reject it by returning false
  }
  
  /**
   * Return an array of strings containing the 
   * string content elements of the tree.
   * Order of the strings in the array does not matter.
   * @return a String array 
   */
  public String[] getAsArray(){
    String[] arr = new String[size()];//create a new string array to store the strings and return them, the size of the array is the size of the BST
    return getAsArrayHelper(this.root, arr, new int[1]);//the default value of an undefined array index is 0 which is where I want my index counter to start 
    //in recursion, the primitive int type will not be updated per call after the left traversal hits the base case
    //to fix this, I will update the index using an array (which is pass by reference)
  }
  private String[] getAsArrayHelper(BTNode root, String[] arr, int[] index){
    //base case
    if(root == null){
      return arr;
    }
    else{
      arr[index[0]] = (String) root.data;
      //System.out.println(arr[index[0]]);//TEST
      index[0]++;//increment the index counter when a new node is added to the array 
    }
    //problem solving and recursion: traverse to the left and then to the right to store all the string nodes in the BST
    getAsArrayHelper(root.left, arr, index);
    getAsArrayHelper(root.right, arr, index);
    return arr; //the left and right nodes will both be returned as a string[]
  }
  
  
  /**
   * Print the binary tree in the format
   * shown in the output.
   */
  
  public void printBT(){
    printBTHelper(this.root);
  }
  private void printBTHelper(BTNode root){
    if(root == null) System.out.println("-");
    else{
      System.out.println("-"+root.data);
      printBTHelper(root.left);
      printBTHelper(root.right);
    }
  }
  
  /**
   * Print the elements of the binary
   * tree in ascending order.
   */
  public void printAscending(){
    //call a private helper method to pass in a root 
    printAscendingHelper(this.root);
  }
  private void printAscendingHelper(BTNode root){
    //note: printing a BST in in-order will print in asceding order 
    //base case
    if (root == null) return;
    printAscendingHelper(root.left);
    System.out.println(root.data);
    printAscendingHelper(root.right);
  }
   
  
  /**
   * Print the elements of the binary
   * tree in descending order.
   */  
  public void printDescending(){
    //call a private helper method to pass in a root  
    printDescendingHelper(this.root);
  }
  private void printDescendingHelper(BTNode root){
    //note: printing a BST in reverse in-order will print in descending order
    //base case
    if (root == null) return;
    printDescendingHelper(root.right);
    System.out.println(root.data);
    printDescendingHelper(root.left);
  }
    
  /**
  * Return the longest string of the binary
  * tree.
  * @return the longest string
  */  
  
  public String getLongestString(){
    String[] arr = getAsArray();
    return getLongestStringHelper(arr, new int[1], arr[0]);
  }  
  private String getLongestStringHelper(String[] arr, int[] index, String max){
    //base case 
    if(index[0] >= arr.length-1) return "";//when we have reached the length of the string[], we will return 
    if(arr[index[0]+1].length() > max.length()) max = arr[index[0]+1];//check if the next index's length is greater than the max index's length (which starts at index 0)
    index[0]++;//increase the index counter and pass it into the recursive call
    getLongestStringHelper(arr, index, max);
    return max;//this will return the longest string 
  }
}

class BTNode{
  Object data;
  BTNode left;
  BTNode right;
  
  BTNode(){}
  BTNode(Object obj){
    data = obj;
  }  
}

