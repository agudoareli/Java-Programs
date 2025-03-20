class Book{
  String title;		// book characteristics
  String author;
  int yearPublished;
  int availableCopies;
  String genre;
  Book next;
	
  Book(){	//use this constructor to link the objects
  } 
  
  Book(String title, String author, int yearPublished, int availableCopies, String genre){
    this.title = title;
    this.author = author;
    this.yearPublished = yearPublished;		//initialize parameters
    this.availableCopies = availableCopies;
    this.genre = genre;
  }
  //i get compile error if i dont include main 
  public static void main(String[] args){

  }

  void getDetails(){	//get all book's details
    System.out.print(this.title + ", ");
    System.out.print(this.author + ", ");
    System.out.print(this.yearPublished + ", ");
    System.out.print("Copies: " + this.availableCopies + ", ");
    System.out.println("Genre: " + this.genre);
  }
  boolean isClassic(){	//is the book classic or not
    if(yearPublished < 1970){
      return true;
    }
    return false;
  }

  
  void setTitle(String title){	//setters
    this.title = title;
  }
  void setAuthor(String author){
    this.author = author;
  }
  void setYearPublished(int yearPublished){
    this.yearPublished = yearPublished;
  }
  void setAvailableCopies(int availableCopies){
    this.availableCopies = availableCopies;
  }
  void setGenre(String genre){
    this.genre = genre;
  }
  
  String getTitle(){	//getters
    return title;
  }
  String getAuthor(){
    return author;
  }
  int getYearPublished(){
    return yearPublished;
  }
  int getAvailbleCopies(){
    return availableCopies;
  }
  String getGenre(){
    return genre;
  }
  //changes by the number that they pass
  void updateCopies(int change){	
    if(this.availableCopies + change < 0){
      this.availableCopies = 0;
    }
    if(change > 0){
      this.availableCopies += change;
    }
    if(change < 0){
      this.availableCopies -= change;
    }
    
  }

}