import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Runner{
  
  public static void main(String[] args){
   
    //do try catch and call all functions while
    //closing/redeclaring scanner to close/reopen
    try{
      File file = new File("books.txt");
      Scanner scanner = new Scanner(file);
      
      int rows = rowCounter(file, scanner);
      Book[] myBooks = new Book[rows];
      scanner.close();
      Scanner scanner2 = new Scanner(file);
      //function calls
      storeArray(file, scanner2, myBooks);
      System.out.println("-----I am printing the books using the object array -----");
     	printBooks(myBooks);
      
      Book oldest = findOldestBook(myBooks);
      System.out.println("-----I am finding the oldest book-----");
      System.out.print("Oldest Book in the collection:\n" + oldest.title + ", " + oldest.author); 
      System.out.print(", " + oldest.yearPublished + ", Copies: ");
      System.out.print(oldest.availableCopies + ", Genre: " + oldest.genre + "\n\n");
      System.out.println("-----I am finding a book by author -----");
      findBooksByAuthor(myBooks, "J.R.R. TolKien");
      System.out.print("\n-----I am updating the copy amount-----");
      updateBookCopies(myBooks, "To Kill a Mockingbird", 1);
      System.out.print("-----I am using input validation to check "); 
      System.out.println("for correct output of updateBookCopies() -----");
      updateBookCopies(myBooks, "To", 9);	
      //create linkedlist object
      LinkedList booksLL = new LinkedList();
     //add a loop to link the book objects 
      int i = 0;
      while(i < myBooks.length){
        booksLL.add(myBooks[i]);
        i++;
      }
      System.out.println("--- I am printing the books using the LL object---");
      booksLL.printList();
      int classicsNum = booksLL.countClassics();
      System.out.println("\n--- I am printing out the number of classic books ----");
      System.out.println("Number of classic books: " + classicsNum);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  //count the rows 
  static int rowCounter(File file, Scanner scanner){
    int rows = 0;
    String line;
    
    while(scanner.hasNextLine()){
      line = scanner.nextLine();	
      rows++;
    }
    return rows;
	}
  //scan a row, split it, and use myBooks[] index to store different Book objects using parameters from the split array
  static void storeArray(File file, Scanner scanner, Book[] myBooks){
    int index = 0;
    String line;
    
    while(scanner.hasNextLine()){
      line = scanner.nextLine();	//scan the line
      String[] withoutSpaces = line.split(", ");	//store line in a string array and split it
      //initialize an object for each index in myBooks[], arguments will come from the split string
      //do not use a second loop to initialize objects, this will cause overwritting of each index
      myBooks[index] = new Book(withoutSpaces[0], withoutSpaces[1], Integer.parseInt(withoutSpaces[2]), Integer.parseInt(withoutSpaces[3]), withoutSpaces[4]);
      index++;
    }
   }
  //print the file
  public static void printBooks(Book[] myBooks){
    for(int i = 0; i < myBooks.length; i++){
      System.out.print(myBooks[i].title + ", ");
      System.out.print(myBooks[i].author + ", ");
      System.out.print(myBooks[i].yearPublished + ", ");
      System.out.print("Copies: " + myBooks[i].availableCopies + ", ");
      System.out.println("Genre: " + myBooks[i].genre + " ");
    }
    System.out.println();
  }
  public static Book findOldestBook(Book[] myBooks){
    int oldestBook = myBooks[0].yearPublished, found = 0;
    for(int i = 1; i < myBooks.length; i++){
      if(myBooks[i].yearPublished < oldestBook){
        oldestBook = myBooks[i].yearPublished;
        found = i;
      }
    }
    //'i' will iterate until the end even if a smaller
    //year is found, so use another flag counter
    return myBooks[found];
  }
  public static void findBooksByAuthor(Book[] myBooks, String authorName){
		//check for string or substring that matches current object's author
    //turn all strings to lower case to output results for any user-input cases
    String argument = authorName.toLowerCase();
    int bookCounter = 0;
    for(int i = 0; i < myBooks.length; i++){
      String objectAuthor = myBooks[i].author;
      String comparison = objectAuthor.toLowerCase();
      if(comparison.contains(argument)){
        if(bookCounter == 0){
          System.out.println("Books by authors containing '" + authorName + "':");
        	bookCounter++;
        }
        System.out.print(myBooks[i].title + ", ");
        System.out.print(myBooks[i].author + ", ");
        System.out.print(myBooks[i].yearPublished + ", ");
        System.out.print("Copies: " + myBooks[i].availableCopies + ", ");
        System.out.println("Genre: " + myBooks[i].genre);
        }
      }
     }
  public static void updateBookCopies(Book[] myBooks, String title, int change){
    //search for a book by title
    int flag = 0, index = 0;	//flag checks if book is found
    for(int i = 0; i < myBooks.length; i++){
      String toFind = myBooks[i].title;
      if(title.equals(toFind)){	//cannot use == to compare strings in diff objects
        myBooks[i].updateCopies(change);//call the method to update copies
        index = i;//track the index where you found title
        flag = 1;
      }
    }
    if(flag == 1){
      System.out.println("\nUpdated available copies for " + myBooks[index].title + ".");
      System.out.println("Updated list of books: ");
      printBooks(myBooks);
      
    }
    if(flag == 0){
      System.out.println("Could not find the book\n");
    }
  }
  
}
