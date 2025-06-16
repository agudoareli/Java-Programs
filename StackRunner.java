
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;


class StackRunner{
  
  public static void main(String[] args) throws Exception{    
    try{    //use a try-catch to read in the input file
      BufferedReader reader = new BufferedReader(new FileReader("input.txt"));  //create a reader for the input file 
      String line = ""; //create a string variable to store the current line the reader is reading 
      while((line = reader.readLine()) != null) { //this will keep looping until the end of the file 
        if(line.isBlank()) continue; //if the line is empty, ignore it by moving to the next iteration in the loop
        if(line.charAt(0) == 'b'){  //if the line starts with b call isBalanced 
          System.out.println("The line is: " + line + "\nThis is for balance check.\nThe expression:" + line.trim().substring(1)); 
          boolean answer = isBalnaced(line.trim().substring(1)); //call the isBalanced method (while skipping the 'b') and store the return value. Trim the line to remove whitespace from the front
          System.out.println(answer ? "The expression is balanced." : "The expression is imbalanced."); //print the appropriate response based on the return value
        }else if(line.charAt(0) == 'p'){ //if the line starts with p call evaluatePostfix 
          System.out.println("The line is: " + line);
          //get the line, trim the leading whitespace, and pass index 2-> end into the evaluatePostFix method. this passes the string without the 'p'
          double answer = evaluatePostfix(line.trim().substring(2));  //trim the line to remove whitespace from the front, and call the method without the 'p'
          System.out.println(answer == Double.NEGATIVE_INFINITY ? "The line contains an invalid postfix expression." : String.format("The postfix evaluation is: %.1f", answer));
        }else{  //if the line starts with anything else, print the error message 
          System.out.println("The line is: " + line);
          System.out.println("The line does not start with a b or a p. I will ignore this line.");
        }
        System.out.println("------------------------- "); //print this line separator after calling any method 
      }
      reader.close(); //close the file reader when we are done reading the file 
    }catch(Exception e){
      e.printStackTrace();
    }
  } 
  
  /**
   * Evaluate the postfix expression in the incoming String.
   * Operators and operands are separated by spaces. See
   * the assignment description for further details.
   * @param postfix
   * @return the evaluated postfix value.
   */  
  static double evaluatePostfix(String postfix){
    String[] tokens = postfix.split("\\s+"); //converts the string into a string array so we can iterate though the numbers  
    Stack stack = new Stack();  //create a stack to evaluate the expression 
    double answer = 0; //create a variable to store any answers we may need to push 
    int index = 0;  //create a counter variable to keep track of the length of the stack 

    while(index < tokens.length){  //iterate until we reach the last number in the given string 
      if(isNumber(tokens[index])){ //check if the current index is a double 
        stack.push(Double.parseDouble(tokens[index])); //convert the substring into a double, then push it to the stack 
      }else{
        char operator = tokens[index].charAt(0); //if the character at the index was not a number, then it is an operator or an invalid value. use a char variable to store it
        if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
          double first = 0, last = 0;
          if (!stack.isEmpty()) first = (double)stack.pop(); //check if the stack is empty before poping, if it is empty, then we cannot apply the operator 
          else{
            System.out.println("Trying to pop when stack is empty");
            System.out.println("Not enough operand in stack to apply the operator " + operator);
            return Double.NEGATIVE_INFINITY;  //return error message 
          }  
          if (!stack.isEmpty()) last = (double)stack.pop(); //we need to check again if the stack is empty because we need 2 operands for the operator 
          else{
            System.out.println("Trying to pop when stack is empty");
            System.out.println("Not enough operand in stack to apply the operator " + operator);
            return Double.NEGATIVE_INFINITY;  //return error message 
          }  
          if (operator == '+') answer = last + first;
          else if (operator == '-') answer = last - first;
          else if (operator == '*') answer = last * first;
          else if (operator == '/') {
            if (first == 0) return Double.NEGATIVE_INFINITY; //dividing by 0 is undefined so return an error message 
            else answer = last / first;
          }
        }
        else {
          System.out.println("The operator " + tokens[index].charAt(0) + " is not recognizable.");
          return Double.NEGATIVE_INFINITY;  //return error message 
        }
        stack.push(answer);  //push the answer back to the stack
      }
      index++;  //move to the next index
    }
    /*
     * if we reached the end of the string and we pop 2 numbers and the second is not null, then
     * that means we have extra elements. if the sencond is null, the we have a valid answer.
    */
    Object firstPop = stack.pop();
    if (!stack.isEmpty()) {
      System.out.println("The postfix expression has extra elements.");
      return Double.NEGATIVE_INFINITY; //return an error message 
    }else{
      return (double)firstPop; //return the top of the stack 
    }
  }
  
  /**
   * The method checks if a sting is a double 
   * precision number.
   * @param tk
   * @return true if the param string is a double precision number
   *         false otherwise.
   */
  static boolean isNumber(String tk){
    if (tk==null)
      return false;
    tk=tk.trim();
    if (tk=="")
      return false;
    
    try{
      double number = Double.parseDouble(tk);
    }catch(NumberFormatException excp){
      return false;
    }
    return true;
  }
  
  /**
   * The method should consider only start '(' and
   * end parentheses ')'. Other characters in the
   * incoming parameter string should be ignored.
   * The method must use a Stack to determine
   * if the incoming string contains a balanced
   * expression or not. See
   * the assignment description for further details.   
   * @param expr
   * @return true if the expression in the String is balanced
   * otherwise, false.
   */
  static boolean isBalnaced(String expr){
    expr = expr.trim(); //remove any leading or trailing whitespace from the given string
    char[] chars = expr.toCharArray(); //converts the string into a char array so we can iterate though the values  
    Stack stack = new Stack(); //create a stack to evaluate the given string 

    for(int i = 0; i < chars.length; i++) {
      switch (chars[i]){
        case '(':
          stack.push(chars[i]); //if we encounter an open parenthasis, add it to the stack 
          break;
        case ')':
          if (!stack.isEmpty()) {
            stack.pop();  //if we encounter a closed parenthais, remove an open parenthasis from the stack 
            break;
          }else {
            return false; //if we encounter a close parenthasis and the stack is empty, this means the expression is unbalanced
          }
        default:
          continue; // ignore any characters other than '(' or ')''
      }
    }
    
   return (stack.isEmpty()); //if the stack is empty after iterating the entire string, then it is balanced. else, it is unbalaanced
  }
}


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

public class Node {
    Object info;
    Node next;
    
    Node(Object info, Node next){
        this.info=info;
        this.next=next;
    }    
}
