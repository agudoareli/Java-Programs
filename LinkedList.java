class LinkedList{
  Book head;
  Book next;
  Book current;
  
  //main method required or i get compilation error
  public static void main(String[] args){
  }
  
  void add(Book book){
    if(head == null){
      head = book;
      current = head;
      //keep track of the head and the position youre in 
    }
    current.next = book;//point current to the new book
    current = current.next;//move current to the new book 
    
  }
  
  public void printList(){	//print book details
    Book temp = head;
    while(temp != null){
      temp.getDetails();//from Book class, call getDetails() method
      temp = temp.next;
    }
  }
  
  public int countClassics(){
    Book temp = head;	//use temp so we dont move head
    int counter = 0;
    while(temp != null){
      if(temp.yearPublished < 1970){
        counter++;
        
      }
      temp = temp.next;//move temp to the next node
    }
    return counter;
  }
}