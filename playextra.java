import java.util.*;
import java.io.*;


public class playextra
{
	public static void main(String[] args) throws IOException{

	ArrayList<LibraryBook> books = new ArrayList<LibraryBook>(50); //arrayList of Library Book objects
	int numBooks = 0; //variable that calls inputBooks method and stores the number of Books
	introScreen(books,numBooks); //calling intro screen method to start the program
	}
	


	//filling the ArrayList of LibraryBooks using an external file passed into the method
	//Precondition: The ArrayList that stores LibraryBook objects should not have any values stored and have a pre-set size of 50.
	//Postcondition: The ArrayList that stores LibraryBook objects will be intialized with the external file as LibraryBook objects.
	public static int inputBooks(String inputFile, ArrayList<LibraryBook> books){

	int numBooks = 0; //initialize the numBooks variable to zero
	try {

		Scanner in = new Scanner(new File(inputFile)); //scanner that read the inputFile that is passed
		while(in.hasNext()) //will keep reading the file until the end
		{
		Scanner lsc = new Scanner(in.nextLine()).useDelimiter(";"); //breaking the file into its various pieces using the delimeter


		String title = lsc.next(); //stores title variable
		String name = lsc.next(); //stores author variable
		int copyright = lsc.nextInt(); //stores copyright year variable
		double price = lsc.nextDouble(); //stores price of book variable
		String genre = lsc.next(); //stores genre of book variable

	//switch statement to change the abbreviation to the actual type of genre
		switch(genre) {
		case "f":
		genre = "Fiction";
		break;
		case "d":
		genre = "Drama";
		break;
		case "p": 
		genre = "Poetry";
		break;
		}
		
		books.add(new LibraryBook(title,name,copyright,price,genre)); //add LibraryBook object to the ArrayList by passing as the                    variables as the parameters
		numBooks++; //increments numBooks everytime a LibraryBook object is added to the ArrayList
		}

	     }

	 	catch (IOException e) {

		System.out.println(e.getMessage());
		
		}
		//error catching present (try & catch)
		return numBooks; //returns number of books in the ArrayList
	}

	//sorting the Library Books by title
	//Precondition: The ArrayList of LibraryBook objects should be intialized with the external file data.
	//Postcondition: The ArrayList of LibraryBook objects will be sorted by Title by using a selection sort.
	public static void sortbyTitle (ArrayList<LibraryBook> books, int numBooks){
	//selection sort code using ArrayLists
	int minIndex, index, j; //declaring a minIndex, index, and j variable
	LibraryBook temp;//temp variable that is a LibraryBook object
	int pass = 0;

       for(index = 0; index < numBooks-1; index ++){

	minIndex = index;

	for(j= minIndex+1; j<numBooks; j++){
	if((books.get(j).getTitle()).compareTo(books.get(minIndex).getTitle()) <0) //compares the 2 titles of the LibraryBook objects
	{
		minIndex = j;
	}
	}
	if(minIndex != index){
	temp = books.get(index);
	books.set(index,books.get(minIndex));
	books.set(minIndex,temp);
	//3 lines that switch the positions of the LibraryBook objects (between the 2 indexes)
	}

	}
	}


	//printing out the main menu
	//Precondition:An external file is chosen that ends with .dat and the contents of the file are stored in the ArrayList of Library              Book objects.
	//Postcondition:The method will keep taking user input using a Scanner until the user decides to exit the program.
	public static void printMenu(ArrayList <LibraryBook> books, int numBooks){

	String choice; //stores the option that user picks
	String key;//String key for case 2 when finding a title using a Binary Search
        String keya;//String key for case 3 when finding an author using a Binary Search
	String keyb; //String key for case 4 when finding a copyright using a Binary Search
	String keyc; //String key for case 5 when finding a price using a Binary Search
	Scanner input  = new Scanner(System.in); //creating a Scanner name input 
 do{

	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("            THE GREAT BOOKS SEARCH PROGRAM                 ");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println();
        System.out.println(" 1) Display all book records");
        System.out.println(" 2) Search for a book by Title");
	System.out.println(" 3) Search for a book by Author");
	System.out.println(" 4) Search for a book by Copyright");
        System.out.println(" 5) Search for a book by Price");
        System.out.println(" 6) Exit Search Program");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println();
	System.out.print("     Please enter Your Choice > ");
	choice = input.nextLine(); //stores the input of the user from the Scanner
	//switch statement that goes through to see if it matches any of the options
	switch(choice){
	case "1":{
	clearScreen(); 
	Scanner in = new Scanner(System.in); //creates a Scanner named in to pass into the displayRecords method
	displayRecords(in,books,numBooks); //calls the displayRecords method and runs it until the user returns to the main menu
	clearScreen();
	break;}
	case "2":{
	System.out.print("     Search Title > ");
	Scanner inputkey = new Scanner(System.in); //creates a Scanner named inputkey to store the key that will be passed in searchBook
	key = inputkey.nextLine(); //changes the String key to the input from the user
	int index = searchBook(books,numBooks,key); //stores the index returned from calling the searchBook method
	//if statement that ensures it prints a record that is at a valid index
	if(index!=-1){
	clearScreen();
	System.out.println("     Book Found in Alphabetized List in:");
	System.out.println();
	System.out.print("     Record #" + String.valueOf(index + 1) + ":\n");
	System.out.println(books.get(index).toString()); //using toString() method from LibraryBook class to print the record
	System.out.print("      Please Hit Return to Continue...");
        String input2 = inputkey.nextLine();
        if (input2.equalsIgnoreCase("M")) {
        return;} //will stay at the same screen until enter key is pressed to return to the main menu
        clearScreen();
	}
	//error message if Book Title is not found in the ArrayList of LibraryBook objects
	else {
	System.out.println();
	System.out.println("      Sorry, the book was not found.");
	System.out.println();
	System.out.print("      Please Hit Return to Continue...");
	String input2 = inputkey.nextLine();
        if (input2.equalsIgnoreCase("M")) {
        return;} //will stay at the same screen until the enter key is pressed to reset the screen
	clearScreen();}
	break;}
	case "3":
	{
	 System.out.print("     Search by Author > ");
        Scanner inputkey = new Scanner(System.in); //creates a Scanner named inputkey to store the key that will be passed in searchBook
        keya = inputkey.nextLine(); //changes the String key to the input from the user
        int index = searchBookbyA(books,numBooks,keya); //stores the index returned from calling the searchBook method
        //if statement that ensures it prints a record that is at a valid index
        if(index!=-1){
        clearScreen();
        System.out.println("     Book Found in Alphabetized List in:");
        System.out.println();
        System.out.print("     Record #" + String.valueOf(index + 1) + ":\n");
        System.out.println(books.get(index).toString()); //using toString() method from LibraryBook class to print the record
        System.out.print("      Please Hit Return to Continue...");
        String input2 = inputkey.nextLine();
        if (input2.equalsIgnoreCase("M")) {
        return;} //will stay at the same screen until enter key is pressed to return to the main menu
        clearScreen();
        }
       //error message if Book Author is not found in the ArrayList of LibraryBook objects
       else {
       System.out.println();
       System.out.println("      Sorry, the book was not found.");                                                                                  System.out.println();
                                                                                                                                                    System.out.print("      Please Hit Return to Continue...");                                                                                                                                                                                                                               String input2 = inputkey.nextLine();
       if (input2.equalsIgnoreCase("M")){                                                                                                            return;} //will stay at the same screen until the enter key is pressed to reset the screen                                                                                                               
       clearScreen();}                                                                                                                                                    break;}
        case "4":
        {
         int num = 0;
         System.out.print("     Search by Copyright year > ");
        Scanner inputkey = new Scanner(System.in); //creates a Scanner named inputkey to store the key that will be passed in searchBook
        keyb = inputkey.nextLine(); //changes the String key to the input from the user
        while(num == 0){
        try {
        num = Integer.parseInt(keyb);
        }
        catch(NumberFormatException e){
	System.out.print("     This is not a number. Please enter a number for the copyright year.  > ");
        keyb = inputkey.nextLine();
	}}
        int index = searchBookbyCR(books,numBooks,keyb); //stores the index returned from calling the searchBook method
        //if statement that ensures it prints a record that is at a valid index
        if(index!=-1){
        clearScreen();
        System.out.println("     Book Found in Alphabetized List in:");
        System.out.println();
        System.out.print("     Record #" + String.valueOf(index + 1) + ":\n");
        System.out.println(books.get(index).toString()); //using toString() method from LibraryBook class to print the record
        System.out.print("      Please Hit Return to Continue...");
        String input2 = inputkey.nextLine();
        if (input2.equalsIgnoreCase("M")) {
        return;} //will stay at the same screen until enter key is pressed to return to the main menu
        clearScreen();
        }
       //error message if Book Copyright is not found in the ArrayList of LibraryBook objects
       else {
       System.out.println();
       System.out.println("      Sorry, the book was not found.");                                                                                  System.out.println();
       System.out.print("      Please Hit Return to Continue...");                                                                                                                                                                                                                               String input2 = inputkey.nextLine();
                                                                                                                                                   if (input2.equalsIgnoreCase("M")){                                                                                                            return;} //will stay at the same screen until the enter key is pressed to reset the screen                                           
                                                                                                                                                     clearScreen();}                                                                                                                                                    break;}
      case "5":
        {
        double num1 = 0;
        System.out.print("     Search by Price > ");
        Scanner inputkey = new Scanner(System.in); //creates a Scanner named inputkey to store the key that will be passed in searchBook
        keyc = inputkey.nextLine(); //changes the String key to the input from the user
        while(num1 == 0){
        try {
        num1 = Double.parseDouble(keyc);
        }
        catch(NumberFormatException e){
        System.out.print("     This is not a number. Please enter a number for the price. > ");
        keyc = inputkey.nextLine();
        }}
        int index = searchBookbyP(books,numBooks,keyc); //stores the index returned from calling the searchBook method
        //if statement that ensures it prints a record that is at a valid index
        if(index!=-1){
        clearScreen();
        System.out.println("     Book Found in Alphabetized List in:");
        System.out.println();
        System.out.print("     Record #" + String.valueOf(index + 1) + ":\n");
        System.out.println(books.get(index).toString()); //using toString() method from LibraryBook class to print the record
        System.out.print("      Please Hit Return to Continue...");
        String input2 = inputkey.nextLine();
        if (input2.equalsIgnoreCase("M")) {
        return;} //will stay at the same screen until enter key is pressed to return to the main menu
        clearScreen();
        }
        //error message if Book Copyright is not found in the ArrayList of LibraryBook objects
        else {
        System.out.println();
        System.out.println("      Sorry, the book was not found.");                                                                                  System.out.println();
        System.out.print("      Please Hit Return to Continue...");                                                                                                                                                                                                                               String input2 = inputkey.nextLine();
                                                                                                                                                                                                                                                                                                  if (input2.equalsIgnoreCase("M")){                                                                                                            return;} //will stay at the same screen until the enter key is pressed to reset the screen
                                                                                                                                                                                                                                                                                                                                                                                                                                                clearScreen();}                                                                                                                                                    break;}
      
	case "6":{
	System.out.println("     Goodbye. Have a nice day. :-)"); //print exit message
	break;}	//stops the program from completely running
	default:{
	System.out.println("     You have made an error. Please try again."); //error messages will pop up if it is anything other the               options
	System.out.println();
	System.out.println("     Please enter a number that matches the options from above.");
	System.out.print("       Please Hit Return to Continue..."); 
	Scanner return1 = new Scanner(System.in);
	return1.nextLine(); //hitting return to reset the screen
	clearScreen(); 
	break;
	}
	}}while(!(choice.equals("6"))); //while choice does not equal 3 keep running the program
	}
	
	

	//printing out the bookSearch main screen
	//Precondition: The ArrayList that stores LibraryBook objects should not have any values stored and have a pre-set size of 50.
	//Postcondition: The ArrayList that stores LibraryBook objects will be initialized with the external file as LibraryBook objects, and         the program will be directed to the main menu with the three options.
	public static void introScreen(ArrayList <LibraryBook> books, int numBooks) throws IOException{
	clearScreen();
	System.out.println("                      THE BOOK SEARCH PROGRAM                       ");
	System.out.println("--------------------------------------------------------------------");
	System.out.println();
        System.out.println("    What file is your book data stored in?");
	System.out.println();
	System.out.println("    Here are the files in the current directory:");
	System.out.println();
	ArrayList<String> files = showFiles(); //call showFiles method and stores them in an ArrayList of String objects called files
	System.out.println();
	System.out.print("    Filename : ");
	Scanner filen = new Scanner(System.in); //created a Scanner to receive user input of what file they would like to select
	String fileinput = filen.nextLine(); //takes the input from the user of what file they would like to select and stores it as a               String object
	//while loop that compares file names in the ArrayList called files to user input to ensure that it is present in the directory
	//will keep showing error messages until valid file name is typed
	while(files.contains(fileinput)==false){
	System.out.println();
	System.out.println("    ** Can't open input file. Try again. **");
	System.out.println();
	System.out.println("    Here are the files in the current directory:");
	System.out.println();
	showFiles();
	System.out.println();
	System.out.print("    Filename: ");
	fileinput = filen.nextLine();
	if(files.contains(fileinput) == true){
	break;
	}
	}
	System.out.println();
	Scanner inFile = new Scanner(new File(fileinput)); //Scanner that reads a valid file in the directory
	int count = 0; //count variable that counts the number of books in the file by counting the number of lines
	//while loop to count number of lines in the file
	while(inFile.hasNextLine()){
	count++; //increments the count variable
	inFile.nextLine();
	} 
	System.out.println("    A total of " + count + " have been input & sorted by title."); //prints out the number of books in the file
	System.out.println();
	System.out.print("    Please Hit Return to Continue...");
	Scanner enter = new Scanner(System.in); //Scanner called enter that allows the user to proceed to the main menu by pressing enter
	enter.nextLine();
	clearScreen();
	numBooks = inputBooks(fileinput,books);	//calls inputBooks method to store the input file contents into the ArrayList of LibraryBook         objects
	printMenu(books,numBooks); //prints the main menu
        }

	//displays all the records by hitting return
	//Precondition: The ArrayList of LibraryBook objects should be intialized with the external file data.
	//Postcondition: All the records will be displayed until the user hits "M" or "m" button on their keyboard or goes through                   all the records.
	public static void displayRecords(Scanner stdin, ArrayList<LibraryBook> books, int numBooks){
	sortbyTitle(books,numBooks); //calls sortbyTitle to ensure that the ArrayList of LibraryBook objects is sorted
	for (int i = 0; i < numBooks; ++i) {
      	clearScreen();
        System.out.print("      Record #" + String.valueOf(i + 1) + ":\n");
     	System.out.println(books.get(i).toString());//calls toString() method of LibraryBook class to print the record at the current index
        System.out.print("Please hit return to continue or M for menu.");
        String input = stdin.nextLine(); 
        if (input.equalsIgnoreCase("M")) {
        return; //will stay at the same screen until return or "M" or "m" button on the keyboard is pressed
      	}
       	}clearScreen();
	}
	
	
	
	
	//finding a certain book title using binary search
	//Precondition: The ArrayList of LibraryBook objects should be initialized with the external file data, and a String should have been          passed from a user's input of what book they are trying to find.
	//Postcondition: An integer is returned indicating the index of the LibraryBook object that matches the title(key) being passed. It            will return -1 if not found.
	public static int searchBook(ArrayList<LibraryBook> books, int numBooks, String key){
	 sortbyTitle(books,numBooks); //ensures that the ArrayList of LibraryBook objects is sorted before doing binary search
	int first = 0, last = numBooks-1, middle, location; //declares variables first,last,middle, and location
	boolean found = false; //boolean that indicates if the title was found or not
	//binary search code 
	do
	{
		middle = (first+last) /2; 
			
		if(key.compareTo(books.get(middle).getTitle())==0) //if title is found returns true
		{
		found = true;
		}
		else if(key.compareTo(books.get(middle).getTitle())<0){ 
		last = middle-1;
		}
		else
		first = middle+1;
		//keeps comparing until it is found or runs out of checks
	}while((!found) && (first<=last));
	location = middle;
	return (found ? location: -1); //if found return index, if not return -1
}


	//printing out files that end in .dat 
	//Precondition: Must be in the current directory that you are looking for files in.
	//Postcondition: An ArrayList of Strings will be returned that have the file names that end in .dat.
	public static ArrayList<String> showFiles(){
         File curDir = new File(".");
	 String[] fileNames = curDir.list();
	 ArrayList<String> data = new ArrayList<String>(); //creates ArrayList that stores the files if they end in .dat
	 for(String s:fileNames)
	  if(s.endsWith(".dat"))                                                                                                                         data.add(s); //adds the file if the file ends in .dat
	//for loop that prints out the files that end in .dat
	for(int i =0; i<data.size(); i++){
	System.out.print(data.get(i)+ " "); 
	}
	return data; //returns the ArrayList of String objects that have files that end in .dat
	}

	//clearScreen method
	//Precondition: java.lang. package must be imported
	//Postcondition:Clears the screen entirely.
     public static void clearScreen()
    {
        System.out.println("\u001b[H\u001b[2J");
    }


//extra methods begin from here for extra credit

     //sorting the Library Books by author
     //Precondition: The ArrayList of LibraryBook objects should be intialized with the external file data.
     //Postcondition: The ArrayList of LibraryBook objects will be sorted by Author by using a selection sort.
     public static void sortbyAuthor(ArrayList<LibraryBook> books, int numBooks){
     //selection sort code using ArrayLists
      int minIndex, index, j; //declaring a minIndex, index, and j variable
      LibraryBook temp;//temp variable that is a LibraryBook object
      int pass = 0;
      for(index = 0; index < numBooks-1; index ++){
      minIndex = index;
      for(j= minIndex+1; j<numBooks; j++){
     if((books.get(j).getAuthor()).compareTo(books.get(minIndex).getAuthor()) <0) //compares the 2 authors of the LibraryBook objects
     {
       minIndex = j;
     }
     }                                            
    if(minIndex != index) { 
     temp = books.get(index);
        books.set(index,books.get(minIndex));
        books.set(minIndex,temp);                                                                                                                                             //3 lines that switch the positions of the LibraryBook objects (between the 2 indexes)
     }
                                                                                                                                                  }                                                                                                                                       

     }



  //finding a certain author using binary search
  //Precondition: The ArrayList of LibraryBook objects should be initialized with the external file data, and a String should have been          passed from a user's input of what book they are trying to find.
  //Postcondition: An integer is returned indicating the index of the LibraryBook object that matches the author(key) being passed. It            will return -1 if not found.
  public static int searchBookbyA(ArrayList<LibraryBook> books, int numBooks, String key){
  sortbyAuthor(books,numBooks); //ensures that the ArrayList of LibraryBook objects is sorted before doing binary search
  int first = 0, last = numBooks-1, middle, location; //declares variables first,last,middle, and location
  boolean found = false; //boolean that indicates if the title was found or not
  //binary search code
  do
  {
  middle = (first+last) /2;                                                          
 if(key.compareTo(books.get(middle).getAuthor())==0){ //if author is found returns true                                                           
   found = true;                                                                                                                                                         }                                                                                                                  else if(key.compareTo(books.get(middle).getAuthor())<0){
                                                                                                                                                                                         last = middle-1;                                                                                                                                                                                                       }
  else
  first = middle+1;                                                                                                                            //keeps comparing until it is found or runs out of checks                                                                                                            
   }while((!found) && (first<=last));{                                                                                                                                                                                                                                                                        location = middle;                                                            
                                                                                                                                                                                    return (found ? location: -1); //if found return index, if not return -1
                                                                                                                                             }
}

 //sorting the Library Books by copyright
 //Precondition: The ArrayList of LibraryBook objects should be intialized with the external file data.
 //Postcondition: The ArrayList of LibraryBook objects will be sorted by Copyright by using a selection sort.
 public static void sortbyCR(ArrayList<LibraryBook> books, int numBooks){
 //selection sort code using ArrayLists
 int minIndex, index, j; //declaring a minIndex, index, and j variable
 LibraryBook temp;//temp variable that is a LibraryBook object
 int pass = 0;
 for(index = 0; index < numBooks-1; index ++){
 minIndex = index;
 for(j= minIndex+1; j<numBooks; j++){
 if((books.get(j).getCopyright()) < (books.get(minIndex).getCopyright())) //compares the 2 copyrights of the LibraryBook objects
 {
 minIndex = j;
 }
 }
if(minIndex != index) {
     temp = books.get(index);
        books.set(index,books.get(minIndex));
        books.set(minIndex,temp);                                                                                                                                             //3 lines that switch the positions of the LibraryBook objects (between the 2 indexes)
     }

     }

     }

 //finding a certain copyright using binary search
 //Precondition: The ArrayList of LibraryBook objects should be initialized with the external file data, and a String should have been          passed from a user's input of what book they are trying to find.
 //Postcondition: An integer is returned indicating the index of the LibraryBook object that matches the copyright(key) being passed. It            will return -1 if not found.
 public static int searchBookbyCR(ArrayList<LibraryBook> books, int numBooks, String key){
 sortbyCR(books,numBooks); //ensures that the ArrayList of LibraryBook objects is sorted before doing binary search
 int first = 0, last = numBooks-1, middle, location; //declares variables first,last,middle, and location
 boolean found = false; //boolean that indicates if the title was found or not
 int k = Integer.parseInt(key);//valid string to integer
 //binary search code
 do
 {
 middle = (first+last) /2;
 if(k==(books.get(middle).getCopyright())){ //if copyright is found returns true                                                      
 found = true;                                                                                                                                                         }                                                                                                                  else if(k<(books.get(middle).getCopyright())){
 last = middle-1;                                                                                                                             }
 else
 first = middle+1;                                                                                                                            //keeps comparing until it is found or runs out of checks                                                                                  
 }while((!found) && (first<=last));{                                                                                                                                                                                                                                                                        location = middle;                                                                                                                  return (found ? location: -1); //if found return index, if not return -1
 
       }
       }
 //sorting the Library Books by price
 //Precondition: The ArrayList of LibraryBook objects should be intialized with the external file data.
 //Postcondition: The ArrayList of LibraryBook objects will be sorted by Price by using a selection sort.
 public static void sortbyP(ArrayList<LibraryBook> books, int numBooks){
 //selection sort code using ArrayLists
 int minIndex, index, j; //declaring a minIndex, index, and j variable
 LibraryBook temp;//temp variable that is a LibraryBook object
 int pass = 0;
 for(index = 0; index < numBooks-1; index ++){
 minIndex = index;
 for(j= minIndex+1; j<numBooks; j++){
 if((books.get(j).getPrice()) < (books.get(minIndex).getPrice())) //compares the 2 prices  of the LibraryBook objects
 {
 minIndex = j;
 }
 }
 if(minIndex != index) {
 temp = books.get(index);
 books.set(index,books.get(minIndex));
 books.set(minIndex,temp);//3 lines that switch the positions of the LibraryBook objects (between the 2 indexes)
 }
 }
 }

//finding a certain price using binary search
//Precondition: The ArrayList of LibraryBook objects should be initialized with the external file data, and a String should have been          passed from a user's input of what book they are trying to find.
//Postcondition: An integer is returned indicating the index of the LibraryBook object that matches the price (key) being passed. It            will return -1 if not found.
public static int searchBookbyP(ArrayList<LibraryBook> books, int numBooks, String key){
 sortbyP(books,numBooks); //ensures that the ArrayList of LibraryBook objects is sorted before doing binary search
 int first = 0, last = numBooks-1, middle, location; //declares variables first,last,middle, and location
 boolean found = false; //boolean that indicates if the title was found or not
 double k = Double.parseDouble(key); //valid string to double
//binary search code
do
{
middle = (first+last) /2;
if(k==(books.get(middle).getPrice())){ //if price is found returns true
found = true;                                                                                                                                                         }                                                                                                                  
else if(k<(books.get(middle).getPrice())){
last = middle-1;                                                                                                                             }
else
first = middle+1;                                                                                                                            //keeps comparing until it is found or runs out of checks
}while((!found) && (first<=last));{                                                                                                                                                                                                                                                                        location = middle;                                                                                                                  return (found ? location: -1); //if found return index, if not return -1
                 }
                       }
}	
