

/**
 * You must not write in any method other than
 * the ones indicated.
 * 
 * You must not change any of the headers.
 * 
 * No additional methods are allowed.
 *
 * @author WHAT IS YOUR NAME?
 */
public class Lab11_Runner {
  
    /**
     * You must not change the main method.
     * The main method is the driver for testing
     * the other methods.
     * 
     * @param args 
     */
    public static void main(String args[]){
      Queue q = new Queue();
      q.enqueue(10);
      q.enqueue(20);
      q.enqueue(30);
      q.enqueue(40);
      q.enqueue(50);
      q.enqueue(60);
      
      printQueue(q);
      System.out.println("I am going to dequeue one element.");
      q.dequeue();        
      
      printQueue(q);  
               
      System.out.println("I am going to enqueue 80.");    
      q.enqueue(80);    
      printQueue(q);    
      
      System.out.println("I am going to reverse my Queue.");        
      reverseQueue(q);
      printQueue(q);       
      
      System.out.println("I am going to enqueue 85.");    
      q.enqueue(85);    
      printQueue(q);        
      
      System.out.println("I am going to reverse my Queue.");        
      reverseQueue(q);
      printQueue(q);    
      
      findMaxInQueue(q);
      printQueue(q);        
      
      System.out.println("I will dequeue six times.");
      q.dequeue();
      q.dequeue();
      q.dequeue();
      q.dequeue();
      q.dequeue();
      q.dequeue();
      printQueue(q);     
      
      System.out.println("I will dequeue once now.");
      q.dequeue();    
      printQueue(q);     
      
      System.out.println("I will dequeue once again.");    
      q.dequeue();    
      printQueue(q);     
      
      System.out.println("\n------------------------");
      System.out.println("Checking for palindromes. ");
      String[] palTest= {"ABCA", "A", "ABRARBA", "ABAKBA",
                         "KAYAK", "acac", "acca",
                         "civic", "repaper", "solar",
                         "Appa", "appa"};
      
      for (int i=0; i<palTest.length; i++){
        
        System.out.println("Testing for string: "+palTest[i]);
        
        String result="not a palindrome";
        
        // Construct a queue from the string
        Queue str = new Queue();
        for (int j=0; j<palTest[i].length(); j++){
          str.enqueue(palTest[i].charAt(j));
        }
        
        System.out.println("Printing the queue before test: ");
        printQueue(str);      
        
        if (isPalindrome(str))
          result="a palindrome";
        
        System.out.println(palTest[i]+" is "+result);
        
        System.out.println("Printing the queue after test: ");
        printQueue(str);
        
        System.out.println("*************************");      
      }
    }
   
    /**
     * Print all the elements of a queue. Q must have
     * the same elements in the same sequence after 
     * printing all the elements.
     * @param Q 
     */
    public static void printQueue(Queue Q){
        // WRITE YOUR CODE HERE.
        System.out.println("My queue is as follows:");  //this line is printed whether the queue is empty or not
        if (Q.isEmpty()) {  //edge case, let the user know if the queue is empty, and return 
            System.out.println("Nothing to print");
            return;
        } else {
            int size = Q.size();    //store the original queue size so we know how many times to remove/print/add from the queue
            for (int i = 0; i < size; i++) {
                Object temp = Q.dequeue();  //remove the first element and store it in a temporary variable
                System.out.print(temp + " "); //print the element
                Q.enqueue(temp);    //add the element to the back of the array 
            }
            System.out.println();
        }
    }  
    /**
     * Print the largest of all the numbers in q. 
     * The queue q must have the same numbers in 
     * the same sequence after entering the method 
     * and before leaving it.
     * You can assume that the Queue in the parameters
     * contains integers only.
     * @param Q 
     */
    public static void findMaxInQueue(Queue Q){
      // WRITE YOUR CODE HERE.
      if (Q.isEmpty()) return;  //edge case: check if the queue is empty, and return so we do not continue with the method 

      int max = Integer.MIN_VALUE;  //create a max variable to return 
      int size = Q.size();    //store the original queue size so we know how many times to iterate the queue
      for (int i = 0; i < size; i++) {
        int current = (int)Q.dequeue();
        if (current > max) max = current;   //if we find a greater number than the current max, then store that number in the max variable 
        Q.enqueue(current); //add the object back into the queue so we do not destroy it 
      }
      System.out.println("The largest number in the queue is: " + max);
    }
  
    
    /**
     * Reverse  the content of the queue. 
     * The method must be capable of reversing 
     * the queue regardless of the data type
     * of the elements.
     * @param Q     
     */  
    public static void reverseQueue(Queue Q){
      // WRITE YOUR CODE HERE.
      if (Q.isEmpty()) return;  //edge case, if the queue is empty then return 
      Stack stack = new Stack();    //create a stack to transfer the queue contents into, then add back to the queue in reverse order 
      int size = Q.size();  //store the original size of the queue so we know how many times to iterate it
      while (size > 0) {
        stack.push(Q.dequeue());
        size--;
      }
      while (!stack.isEmpty()) Q.enqueue(stack.pop());
    }
    
    
    /**
     * The method checks if the string formed by the characters
     * kept in the incoming queue forms a palindrome.
     * You are allowed to use only one stack as a temporary
     * container.
     * 
     * No other containers (such as, arrays, linked lists)
     * are allowed.
     * 
     * Assume that the elements of the incoming Queue q are
     * of character (char) data type.
     * 
     * You may use simple primitive data variables.
     * 
     * The sample output reflects that the sequence of the
     * characters in the incoming Queue q must not change
     * after the execution of this method.
     * 
     * @param q, a Queue containing characters as elements.
     * @return true if the text param is a palindrome,
     * otherwise false.
     */
    public static boolean isPalindrome(Queue q){
      // WRITE YOUR CODE HERE.
      if (q.isEmpty()) return false;  //edge case, if the queue is empty then return false because we were not able to find a palindrome 
      Stack stack = new Stack(); //create a temporary container to store the chars 
      int size = q.size();  //store the orininal size of the queue 

      while (size > 0) {
        char temp = (char)q.dequeue();  //remove the char from the queue and store it in a tmeporary variable 
        stack.push(temp);   //push this tmeporary variable to the stack 
        q.enqueue(temp);//add this temporary variable back to the queue so we do not destroy it 
        size--;
      }
      //after exiting the loop, the queue has the original order and the stack has the reverse order 
      size = q.size();
      boolean toReturn = true;
      while(size > 0) {
        char queueNode = (char)q.dequeue();
        char stackNode = (char)stack.pop();
        if (queueNode != stackNode) toReturn = false;
        q.enqueue(queueNode);   //re-add the queue object so we do not destroy the queue 
        size--;
      }
      return toReturn;
    }  
    
  }

public class Node {
    Object info;
    Node next;
    
    Node(Object info, Node next){
        this.info=info;
        this.next=next;
    }    
}

//package Lab11_Queues;

public class Queue{
	private int QUEUE_SIZE = 100;
	private Object[] items;
	private int front, back, count;

	public Queue() { 
		items = new Object[QUEUE_SIZE];
		front = 0;
		back = QUEUE_SIZE -1;
		count =0;
	}

	public boolean isEmpty(){
		return count==0;
	}

	public boolean isFull(){
		return count == QUEUE_SIZE;
	}

	public void enqueue(Object newItem){
		if (!isFull()){
			back = (back+1) % QUEUE_SIZE;
			items[back] = newItem;
			count++;		
      return;
		} else 
        System.out.println(
                "Trying to enqueue into a full queue");
	}		

	public Object dequeue(){
		if (!isEmpty()){
			Object queueFront = items[front];
			front = (front+1) % QUEUE_SIZE;
			count--;
      return queueFront;
		}else
      System.out.println(
              "Trying to dequeue from an empty queue");
    return null;
	}

	public void dequeueAll(){
		items = new Object[QUEUE_SIZE];
		front = 0;
		back = QUEUE_SIZE -1;
		count =0;
	}

  public Object peek(){
		if (!isEmpty()) {
			return items[front];
		}
		else
      System.out.println(
              "Trying to peek with empty queue");
    return null;       
	}
       
  public int size(){
    return count;       
  }       
              
}// End of class Queue


public class Stack{
    private Node top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty(){
        return (top ==null);
    }

    public void push(Object newItem){
        top = new Node(newItem,  top);
    }

    public Object pop(){
        if (isEmpty()){
            System.out.println(
             "Trying to pop when stack is empty");
            return null;
        }else{
            Node temp = top;
            top = top.next;
            return temp.info;
        }
    }


    void popAll(){
        top = null;
    }

    public Object peek(){
        if (isEmpty()){
           System.out.println(
            "Trying to peek when stack is empty");
           return null;
        }else{	
           return top.info;
        }
    }
}// End of Stack using a linked list

