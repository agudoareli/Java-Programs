import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Runner{
    public static void main(String[] args){
        //note: variables created inside a try-catch are only visible inside the try catch 
        try{
            //take user input for file name, assign a scanner and name to it 
           boolean valid = false;
           String fileName = "";
           
           do{
               Scanner userFileName = new Scanner(System.in);  //scanner for user input
               System.out.println("Enter filename: ");
               fileName = userFileName.nextLine();
               
               if(fileName.endsWith(".txt")){
                   valid = true;
               }else{
                   System.out.println("File must end with .txt");
               }
           }while(valid == false);
            
            File file = new File(fileName); //create file to scan 
            Scanner scanner = new Scanner(file);//create a scanner for the file 
            ArtistList list = new ArtistList(); //create linkedlist object 
            
            createObj(list, scanner);//creates all nodes

            Scanner choice = new Scanner(System.in);//scan the user's choice 
            int menuChoice = 0;
            
            do{
                System.out.print("Choose an operation: 1. Print 2. Search 3. Insert 4. Swap ");
                System.out.println("5. Remove 6. Show by Talent 7. Save 8. Quit");
                System.out.println("Enter new choice: ");//prompt the user for a new choice
                menuChoice = choice.nextInt();//parse the input into an int
                switch(menuChoice){
                    case 1:
                        //method1
                        list.printList();
                        System.out.println();
                        break;
                    case 2:
                        //method2
                        System.out.println("Enter ID or name: ");//prompt user for input 
                        Scanner method2input = new Scanner(System.in);  //scan user input
                        String method2in = method2input.nextLine(); //string that is assinged user input 
                        //try catch to see if user input a string or a number 
                        try{
                            long num = Long.parseLong(method2in);
                            list.searchArtist(num, "");
                        }catch(Exception e){
                            list.searchArtist(0, method2in);
                        }
                        System.out.println();
                        break;
                        
                    case 3:
                        //method3
                        System.out.println("Enter ID: ");//prompt user for input 
                        Scanner iDinput = new Scanner(System.in);  //scan user input
                        long id = iDinput.nextLong(); //variable that is assinged user input 
                        
                        System.out.println("Enter Name: ");
                        Scanner nameInput = new Scanner(System.in);
                        String name = nameInput.nextLine(); 
                        
                        System.out.println("Enter Age: ");
                        Scanner ageInput = new Scanner(System.in); 
                        double age = ageInput.nextDouble();  
                        
                        System.out.println("Enter Talent: "); 
                        Scanner talentInput = new Scanner(System.in);
                        String talent = talentInput.nextLine(); 
                        
                        System.out.println("Enter Index To Insert At: ");
                        Scanner indexInput = new Scanner(System.in);  
                        int index = indexInput.nextInt();  
                        
                        list.insertArtistAtIndex(id, name, age, talent, index);
                        System.out.println();
                        break;
                    case 4:
                        //method4
                        System.out.println("Enter Index 1: ");//prompt user for input 
                        Scanner index1Input = new Scanner(System.in);  //scan user input
                        int index1 = index1Input.nextInt(); //variable that is assinged user input 
                        
                        System.out.println("Enter Index 2: ");
                        Scanner index2Input = new Scanner(System.in); 
                        int index2 = index2Input.nextInt(); 
                        
                        list.swapArtists(index1, index2);
                        System.out.println();
                        break;
                    case 5:
                        //method5
                        System.out.println("Enter ID to remove: "); 
                        Scanner idToRemove = new Scanner(System.in); 
                        long id2 = idToRemove.nextLong(); 
                        
                        list.removeArtistById(id2);
                        System.out.println();
                        break;
                    
                    case 6:
                        //method6
                        System.out.println("Enter Talent: ");
                        Scanner talentIn = new Scanner(System.in);  
                        String talentInput2 = talentIn.nextLine();
                        list.showArtistsByTalent(talentInput2);
                        System.out.println();;
                        break;
                    case 7:
                        //method7
                        System.out.println("Enter name of output file: ");
                        Scanner outputFile = new Scanner(System.in); 
                        String outputFilename = outputFile.nextLine(); 
                        list.writeToFile(outputFilename);
                        System.out.println();;
                        break;
                    default:
                        System.out.println("Invalid day, try again: ");
                }
            }while(menuChoice != 8);
            if(menuChoice == 8){
               scanner.close();//close the scanner before quiting the program 
               list.quitProgram();
           }
        }catch(Exception e){
            e.printStackTrace();
        }
 
    }

    public static Artist createObj(ArtistList list, Scanner scanner){
        while(scanner.hasNextLine()){//while the scanner has a next line do the following: 
            try{
                Artist temp = new Artist(); //try to assign a new node per iteration 
                long ID = Long.parseLong(scanner.nextLine());//try to assign a new variable per iteration
                String name = scanner.nextLine();
                double age = Double.parseDouble(scanner.nextLine());
                String talent = scanner.nextLine();
                
                temp.setId(ID);//try to set the attributes per node per iteration 
                temp.setName(name);
                temp.setAge(age);
                temp.setTalent(talent);
                list.append(list, temp);
            }catch(NumberFormatException a){//this exception is used for parsing issues 
                scanner.close();//close the old scanner 
                list.setHead(null);//clear the linkedlist and start over with a correct file 
                System.out.println("Error: an expected input is not valid. Enter another file");//output an error message 
                //prompt for a new file
                System.out.println("Enter new file: ");
                Scanner input = new Scanner(System.in);  //scan user input
                String input2 = input.nextLine(); //store the user input into a string 
                try{//try opening a new file and scanner
                    File file2 = new File(input2);
                    scanner = new Scanner(file2); //this scanner will go back into the while loop to check for a next line 
                }catch(Exception c){
                    c.printStackTrace();
                }
            }
        }    
        return list.getHead();
    }
}