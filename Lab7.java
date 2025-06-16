import java.io.File;
import java.util.Scanner;

public class Lab7 {
  
  public static void main(String[] args){
    String fileName = "input.txt";        
    Carton[] allCartons = getCartonArrayFromDataFile(fileName);
    
    if (allCartons!=null){
      System.out.println("Number of cartons in the array: "+
            allCartons.length);
    }else{
      System.out.println("No array constructed. Array is null. ");
    }
    
    System.out.println("The cartons are as follows:");    
    displayAllCartons(allCartons);
    
    System.out.println("Sorting the array using bubbleSort");
    
    long start = System.nanoTime();
    bubbleSort(allCartons);
    long end = System.nanoTime();
    long bubbleSortTime=(end-start);    
    
    System.out.println("The array after bubble sort:");    
    displayAllCartons(allCartons);    
    
    System.out.println("Re-constructing the array from input file.");
    allCartons = getCartonArrayFromDataFile(fileName);
    if (allCartons!=null){
      System.out.println("Number of cartons in the array: "+
            allCartons.length);
    }else{
      System.out.println("No array constructed. Array is null. ");
    }
    System.out.println("The cartons after re-reading");
    displayAllCartons(allCartons);  
    System.out.println("Sorting the array using selectionSort");
    start = System.nanoTime();
    selectionSort(allCartons);
    end = System.nanoTime();
    long selectionSortTime=(end-start);
    
    System.out.println("The array after selection sort:");    
    displayAllCartons(allCartons);    
    
    System.out.println("Re-constructing the array from input file.");
    allCartons = getCartonArrayFromDataFile(fileName);
    if (allCartons!=null){
      System.out.println("Number of cartons in the array: "+
            allCartons.length);
    }else{
      System.out.println("No array constructed. Array is null. ");
    }
    System.out.println("The cartons after re-reading");
    displayAllCartons(allCartons);        
    System.out.println("Sorting the array using mergeSort");
    start = System.nanoTime();    
    mergeSort(allCartons);
    end = System.nanoTime();
    long mergeSortTime=(end-start);
    
    System.out.println("The array after merge sort:");    
    displayAllCartons(allCartons);        
    
    System.out.println("********* Runtime summary: ***********");
    System.out.println("Time taken by bubble sort: "+bubbleSortTime+" ns");    
    System.out.println("Time taken by selection sort: "+selectionSortTime+" ns");
    System.out.println("Time taken by merge sort: "+mergeSortTime+" ns");    
  }
  /**
   * Change the body of this method to arrange the Carton
   * objects in the array parameter in ascending order of
   * their volumes.
   * The method must use bubble sort.
   * @param theCartons 
   */
  static void bubbleSort(Carton[] theCartons) {
    //edge cases
    if(theCartons == null) return;
   
    for(int i = 0; i < theCartons.length; i++){//this loop accounts for the number of iterations 
      for(int j = 0; j < theCartons.length-i-1; j++){//this loop accounts for the comparisons and swaps per iteration 
        //the -i is used so we dont check elements that are already sorted. the -1 is used so we dont get nullpointerexception for j+1
        if(theCartons[j].compareTo(theCartons[j+1]) == 1){//if the previous is greater than the next, this if statement swaps them 
          Carton temp = theCartons[j];
          theCartons[j] = theCartons[j+1];
          theCartons[j+1] = temp;
        }
      }
    }
  }

  /**
   * Change the body of this method to arrange the Carton
   * objects in the array parameter in ascending order of
   * their volumes.
   * The method must use selection sort.
   * @param theCartons 
   */  
  static void selectionSort(Carton[] theCartons) {
    //edge cases
    if(theCartons == null) return;

    for(int i = 0; i < theCartons.length; i++){
      int originalMinIndex = i;
      int min = i;
      for(int j = i+1; j < theCartons.length; j++){//always start j at the next of i, so j = i+1.
        if(theCartons[j].compareTo(theCartons[min]) == -1) min = j;
      }
      if(originalMinIndex != min){//if the min value changed, then swap the first index with the new min that was found.
        Carton temp = theCartons[originalMinIndex];
        theCartons[originalMinIndex] = theCartons[min];
        theCartons[min] = temp;
      }
    }
  }

  /**
   * Change the body of this method to arrange the Carton
   * objects in the array parameter in ascending order of
   * their volumes.
   * The method must use merge sort.
   * @param theCartons 
   */    
  static void mergeSort(Carton[] theCartons) {
    //base case
    if(theCartons.length < 2) return;//split the array in halfs until you reach length of 1 or less
    
    int mid = theCartons.length/2;
    Carton[] left = new Carton[mid];//create the left array 
    Carton[] right = new Carton[theCartons.length-mid];//create the right array 
    //populate each array 
    for(int i = 0; i < mid; i++){
      left[i] = theCartons[i];
    }
    for(int i = mid; i < theCartons.length; i++){
      right[i-mid] = theCartons[i];
    }
    //divide them recursively 
    mergeSort(left);
    mergeSort(right);

    merge(theCartons, left, right);
  }

  static void merge(Carton[] theCartons, Carton[] left, Carton[] right){
    int i=0, j=0, k=0;
    while(i < left.length && j < right.length){//iterate through every element of left and right arrays, stop when one is exhausted.
      if(left[i].compareTo(right[j]) == -1){
        theCartons[k] = left[i];
        i++;
      }
      else if(right[j].compareTo(left[i]) == -1){
        theCartons[k] = right[j];//add the appropriate value to the original array 
        j++;
      }
      k++;
    }
    //account for the left-over elements of left or right array
    while(i < left.length){
      theCartons[k] = left[i];
      i++;
      k++;
    }
    while(j < right.length){
      theCartons[k] = right[j];
      j++;
      k++;
    }
  }
  
  /**
   * Display width, length, height, volume of
   * each Carton object in a sequence they appear
   * in the array of the parameter.
   * @param theCartons 
   */
  static void displayAllCartons(Carton[] theCartons){
    //edge case
    if(theCartons == null) return;
    
    for(int i = 0; i < theCartons.length; i++){
      System.out.println(theCartons[i].toString());//for each index in the array, call the toString method which prints all the attributes 
    }
  } 
 
  static Carton[] getCartonArrayFromDataFile(String fileName){
    //edge case
    if(fileName.length() == 0){
      System.out.println("A file name is needed");
      return null;
    }

    try{
      File file = new File(fileName);
      Scanner scanner1 = new Scanner(file);
      int rowCounter = 0;

      while(scanner1.hasNextLine()){
        rowCounter++;
        scanner1.nextLine();//move to the next line in the file 
      }
      Carton[] cartons = new Carton[rowCounter];//this creates an array of size of rows, now we have to initialize each index
      //we must go back to the beginning of the file to pupulate each Carton, so we need to close the scanner and open a new one 
      scanner1.close();
      Scanner scanner2 = new Scanner(file);
      
      try{
        for(int i = 0; i < rowCounter; i++){
          double width = Double.parseDouble(scanner2.next());//assign the first token in each line to width and parse to a double 
          //System.out.println("w " + width);//i am printing out the width to make sure i am assigning the correct values 
          double height = Double.parseDouble(scanner2.next());//assign the second token in each line to height and parse to a double 
          //System.out.println("h " + height);
          double length = Double.parseDouble(scanner2.next());//assign the third token in each line to length and parse to a double 
          //System.out.println("l " + length);

          cartons[i] = new Carton(width, height, length);//assign a node to each index and pass in the required arguments to the constructor 
          if(scanner2.hasNextLine()) scanner2.nextLine();//move to the next line in the scanner 
        }
        scanner2.close();//close the scanner to prevent buffer overflow 
        return cartons;//return the array of cartons that we populated 
      }catch(NumberFormatException e){//we are using a try-catch incase the parsing is unsuccessful 
        e.printStackTrace();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;//if an array was not returned, then null will be returned to indicate an error 
  }  
}


public class Carton {
  private double width, height, length;
  
  Carton(double w, double h, double l){
    width=w;
    height=h;
    length=l;
  }
  
  private double getVolume(){
    return width*height*length;
  }
    
  public int compareTo(Carton o){
    double myVol = this.getVolume();
    double thatVol = o.getVolume();
    if (myVol>thatVol)
      return 1;
    else if (myVol<thatVol)
      return -1;
    else
      return 0;
  }
  
  public String toString(){
    return "Width: "+width+
           "\theight: "+height+
            "\tlength: "+length+
            "\tVolume: "+getVolume();
  }

          
}

