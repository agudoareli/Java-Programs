import java.io.FileNotFoundException;//library used for scanner try catch 
import java.io.BufferedWriter;//library used for buffered writer 
import java.io.FileWriter;//library used for file writer 

public class ArtistList{
  private Artist head;//make the head private 
  Artist next;
  
  //use getters and setters for head 
  Artist getHead(){
      return head;
  }
  void setHead(Artist head){
      this.head = head;
  }
  int getLength(){
        Artist temp = getHead();
        int counter = 0;
        while(temp != null){
            counter++;
            temp = temp.next;
        }
        return counter;
    }
    
  
  void append(ArtistList list, Artist temp){//this method appends a node to a list 
    Artist current = getHead();
    
    if(current == null){//if the head is empty, the head will be the new node 
        list.head = temp;
        current = temp;//we are keeping track of the head with the "current" variable 
    }
    else{//if the head was not null, do the following: 
        while(current.next != null){
            current = current.next;//move to the end of the list 
        }
        current.next = temp;//append the new node to the end of the list 
    }
  }
  
  public void printList(){
    Artist temp = this.head;//create a temporary node for the head so we dont move the head 
    //test case if list is empty 
    if(temp == null){
        System.out.println("List is empty");
        return;
    }
    while(temp != null){
        System.out.print(temp.getID() + " | ");//print all the attributes of the node 
        System.out.print(temp.getName() + " | ");
        System.out.print(temp.getAge() + " | ");
        System.out.print(temp.getTalent() + " | \n");
        temp = temp.next;
    }
    
    }
    
    public Artist searchArtist(long id, String name) {
        Artist temp = this.head;//create a temporary node for the head
        //test cases: if list is empty, if ID/name doesnt exist in the list
        if(temp == null){
           System.out.println("List is empty"); 
           return null;
        }
        while(temp != null){
            if(name.length() == 0){//if a name was not passed, compare with the ID
                if(temp.getID() == id){
                    System.out.print(temp.getID() + " | ");
                    System.out.print(temp.getName() + " | ");
                    System.out.print(temp.getAge() + " | ");
                    System.out.print(temp.getTalent() + " | \n");
                    return temp; 
                    
                }
            } 
            //if a name was passed, compare with the name
            else if (temp.getName().toLowerCase().contains(name.toLowerCase())){//turning the node's name and the argument's name to lowercase 
                System.out.print(temp.getID() + " | ");
                System.out.print(temp.getName() + " | ");
                System.out.print(temp.getAge() + " | ");
                System.out.print(temp.getTalent() + " | \n");
                return temp;
            }
            temp = temp.next;
        }
        //if it exited the loop and didnt return anything, nothing was found 
        System.out.println("Name or ID not found");    
        return null; 
    }
    
    public void insertArtistAtIndex(long id, String name, double age, String talent, int index) {
        Artist previous = this.head;
        Artist current = this.head;;
        int indexCounter = 0;
        //test cases: index out of bounds
        if(previous == null && index != 0){
            System.out.println("Index out of bounds");
            return;
        }
        if(index < 0 || index > getLength()){
            System.out.println("Index out of bounds");
            return;
        }
       
        //if index 0, this is the new head 
        if(index == 0){
            Artist toAdd = new Artist();
            toAdd.setId(id);
            toAdd.setName(name);
            toAdd.setAge(age);
            toAdd.setTalent(talent);
            
            toAdd.next = getHead(); //link the node to index 0
            setHead(toAdd);//update this node as the new head 
            current = getHead().next;
            System.out.println("After adding new node: ");
            printList();
        }
        else if(index == 1 && getLength() == 1){
            Artist toAdd = new Artist();
            toAdd.setId(id);
            toAdd.setName(name);
            toAdd.setAge(age);
            toAdd.setTalent(talent);
            
            getHead().next = toAdd;
            current = toAdd;
            
            System.out.println("After adding new node: ");
            printList();
            return;
        }else{
            //stop right before the given index and redirect pointers
            current = getHead().next;
            indexCounter = 1;
            while(indexCounter < index && current != null){
                previous = previous.next;
                current = current.next;
                indexCounter++;
            }
            //if loop exited because you reached the end of the list do the following:
            if(current == null) System.out.println("Index out of bounds");
            //if loop exited because you found the desired position do the following: 
            else{
                Artist toAdd = new Artist();
                toAdd.setId(id);
                toAdd.setName(name);
                toAdd.setAge(age);
                toAdd.setTalent(talent);
                
                previous.next = toAdd;
                toAdd.next = current;
                
                System.out.println("After adding new node: ");
                printList();
            }
        }
    }
    
    public void swapArtists(int index1, int index2){
        //test cases: list is empty/ indexes out of bounds 
        if(this.head == null){
            System.out.println("List is empty");
            return;
        }
        if(getLength() == 1){
            System.out.println("There is only one person in the list");
            return;
        }
        if(index1 < 0 || index2 < 0){
            System.out.println("One or more index out of bounds");
            return;
        }
        if(index1 > getLength() || index2 > getLength()){
            System.out.println("One or more index out of bounds");
            return;
        }
            
        // 1. Check if x and y are equal 
        if (index1 == index2) {
            System.out.println("Indices are the same");
            return;
        }
    
        // 2. Find x and y in the list, use prev pointer, curr pointer, indexCounter for x and y  
        Artist prevX = null, prevY = null;
        Artist currX = getHead(), currY = getHead();
        int indexCountX = 0, indexCountY = 0;
    
        while (currX != null && indexCountX < index1) {
            prevX = currX;
            currX = currX.next;
            indexCountX++;
        }
        while (currY != null && indexCountY < index2) {//using "<" stops before the desired position 
            prevY = currY;
            currY = currY.next;
            indexCountY++;
        }
    
        // 3. If either index is out of bounds, return
        //you went through the whole list and didnt find that index 
        if (currX == null || currY == null) {
            System.out.println("One or more index out of bounds");
            return;
        }
    
        // 4. Update the .next pointers for the prev nodes
        // If prevX is null, it means currX is the head
        if (prevX != null) {
            prevX.next = currY; // previous node of X now points to Y
        } else {
             // if X is the head, set the head to Y. we reassign head at the END so we dont get error 
        }
    
        // If prevY is null, it means currY is the head
        if (prevY != null) {
            prevY.next = currX; // previous node of Y now points to X
        } else {
             // if Y is the head, set the head to X. we do this at the END to avoid errors 
        }
    
        // 5. Update the .next pointers for the curr nodes
        // Swap the next pointers of currX and currY
        Artist temp = currX.next; // store the next of currX to avoid losing the reference
        currX.next = currY.next;  // X now points to whatever Y pointed to
        currY.next = temp;        // Y now points to whatever X pointed to
        
        //6.if currX or currY were at index 0, update head at the end to ensure correct pointers 
        if(currX == getHead() || currY == getHead()){
            if(currX == getHead()) setHead(currY);//if x was the head, y is now the head 
            else {setHead(currX);}//if y was the head, x is now the head 
        }
    
        // 7. Print the list after the swap
        System.out.println("After swapping: ");
        printList();
    }
    
    public void removeArtistById(long id) {
        Artist prev = null;
        Artist curr = getHead();
        
        //1 .check if list is empty 
        if(curr == null){
            System.out.println("Empty list");
            return;
        }
        //2. find the target node 
        while(curr != null && curr.getID() != id){
           prev = curr;
           curr = curr.next;
        }
        if(curr == null){//if you iterated the whole list and didnt find the target node, return 
            System.out.println("ID does not exist in the file");
            return;
        }else{//if you exited the loop because you found the target node
            if(curr == getHead()){
                setHead(curr.next);//if target node is the head, reassign the head 
            }else{
                prev.next = curr.next;//skip the pointer to the target node to remove it from the list
            }
        }
     
        System.out.println("After removing artist with ID " + id + ": ");
        printList();
        return;
    }  
    
    public void showArtistsByTalent(String talent) {
        Artist temp = getHead();
        int flag = 0;
        if(temp == null){
            System.out.println("List is empty");
            return;
        }
        System.out.println("Showing artists with talent: " + talent);
        while(temp != null){
           if(temp.getTalent().toLowerCase().contains(talent.toLowerCase())){
               System.out.print(temp.getID() + " | ");
               System.out.print(temp.getName() + " | ");
               System.out.print(temp.getAge() + " | ");
               System.out.println(temp.getTalent() + " | ");
               flag = 1;
           }//whether talent is found in the temp node or not, move to the next temp 
           temp = temp.next;
           
        }
        
        if(flag == 0){//if no node had a matching talent, its not in the list 
            System.out.println("Talent not found in the file");
        }
        
    }
    public void writeToFile(String filename) {
        //testcase: if list is empty 
        if(getHead() == null){
           System.out.println("List is empty");
           return;
        }
        try{
            //buffered writer can write to many things, so tell it to write to a file 
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            Artist temp = getHead();
            while(temp != null){
                writer.write(temp.getID() + "\n");
                writer.write(temp.getName() + "\n");
                writer.write(temp.getAge() + "\n");
                writer.write(temp.getTalent() + "\n");
                
                temp = temp.next;
            }
            System.out.println("Successfully created file \"" + filename + "\"");
            System.out.println("You will need to end the program to be able to see \"" + filename + "\"\n");
            writer.close();//if we dont close the file at the end, nothing gets written to it 
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("unsucessful");
        }
        
    }
    
    public void quitProgram(){//this method will allow the user to exit the entire program
        System.out.println("\nExiting program...");
        System.exit(0);
        
    }

 
    
}
