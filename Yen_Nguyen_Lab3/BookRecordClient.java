package library.service.classes;
import library.service.classes.BookGenre;
import library.service.classes.BookRecord;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
public class BookRecordClient
{
	BookRecord[] books = new BookRecord[5];
	int numOfRecords;
	//fill array method
	public void fill(int expFactor){
		boolean filled = false;
			if(books[books.length-1] == null)
				filled = false;
			else
				filled = true;
		if(filled){
			BookRecord[] nbooks = new BookRecord[books.length + expFactor];
			for(int i = 0; i<= books.length-1; i++){
				nbooks[i] = books[i];
			}
			System.out.println("Resize the array from "+books.length+" to "+nbooks.length);
			books = nbooks;
		}
	}
	//check duplicate method
	public boolean isDuplicate(BookRecord[] bookArray, BookRecord aBook){
		boolean output = false;
		for(int i = 0; i<= bookArray.length-1; i++){
			if(aBook.equals(bookArray[i]))
				output = true;
		}
		return output;
	}
	//sort according to tag
	public BookRecord[] sortString(BookRecord[] bookArray, int noRecords){
		BookRecord[] sortedArray = new BookRecord[noRecords];
		for(int i = 0; i<= noRecords-1; i++)
			sortedArray[i] = bookArray[i];			//transfer values; now sortedArray has no null values;
		for(int i = 0; i <= sortedArray.length-2; i++){
			for(int j = i + 1; j <= sortedArray.length-1; j++){
				if(sortedArray[i].getTag().compareTo(sortedArray[j].getTag()) > 0){
					BookRecord replaceBook = sortedArray[i];
					sortedArray[i] = sortedArray[j];
					sortedArray[j] = replaceBook;
				}
			}
		}
		return sortedArray;
	}
	//sort according to number of page
	public BookRecord[] sortPages(BookRecord[] bookArray, int noRecords){
		BookRecord[] sortedArray = new BookRecord[noRecords];
		for(int i = 0; i<= noRecords-1; i++)
			sortedArray[i] = bookArray[i];			//transfer values; now sortedArray has no null values;
		for(int i = 0; i <= sortedArray.length-2; i++){
			for(int j = i + 1; j <= sortedArray.length-1; j++){
				if(sortedArray[i].getNumberOfPage() > sortedArray[j].getNumberOfPage()){
					BookRecord replaceBook = sortedArray[i];
					sortedArray[i] = sortedArray[j];
					sortedArray[j] = replaceBook;
				}
			}
		}
		return sortedArray;
	}
	//search tag method
	public void searchTag(String tag){
		int start = 0, end = books.length - 1;
		while (end >= start){
			int middle = ( start + end ) / 2;  
			if (books[middle].getTag().equals(tag)){
				System.out.println("Book found:\n\n"+books[middle].toString()); //key found
				return;							//return to main method
			}
		else if ( books[middle].getTag().compareTo(tag)>0)
				end = middle - 1; // search left  
			else if( books[middle].getTag().compareTo(tag)<0)
				start = middle + 1; // search right 
		}
		System.out.println("==========\nNo match found!\n==========");
	}
	public static void main(String []args){
		BookRecordClient client = new BookRecordClient();
		int expansionFactor = Integer.parseInt(args[1]);
		client.numOfRecords = 0;
		try {
			File file = new File(args[0]);
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()){
				client.fill(expansionFactor);														//fill array if needed
				StringTokenizer line = new StringTokenizer(scanner.nextLine(),":");
				String title = line.nextToken();
				BookGenre genre = BookGenre.valueOf(line.nextToken());
				StringTokenizer authors = new StringTokenizer(line.nextToken(),",");
				String tag = line.nextToken();
				int numOfPage = Integer.parseInt(line.nextToken());
				String[] authorArray = new String[authors.countTokens()];
				for(int i = 0; i<= authorArray.length-1;i++)
					authorArray[i] = authors.nextToken();
				BookRecord newBook = new BookRecord(title, authorArray, genre, tag, numOfPage);		//construct a new bookRecord
				if(client.isDuplicate(client.books, newBook)){										//check duplicate
					System.out.println("\nDuplicate found:\n"+newBook.toString());
				}
				else{
					client.books[client.numOfRecords] = newBook;
					client.numOfRecords++;
				}
			}
		} catch(IOException ioe){
			System.out.println("The file can not be read");
		}
		//client.books = client.sortString(client.books, client.numOfRecords); ------> no need, will sort when needed.
		//User interactive part
		while(true){
			System.out.println("Select an option:");
			System.out.println("Type \"S\" to list books of a genre");
			System.out.println("Type \"P\" to print out all the book records");
			System.out.println("Type \"T\" to search for a record with a specific tag");
			System.out.println("Type \"Q\" to Quit");
			Scanner input = new Scanner(System.in);
			switch (input.nextLine()) {
				case "S":
				case "s":
				System.out.println("Type a genre. The genres are:");
				for (BookGenre d : BookGenre.values()) 
					System.out.println(d);
				System.out.println("\nThe genre you want is:\n(Please type the genre correctly!)");
				BookGenre userGenre = BookGenre.valueOf(input.nextLine());
				client.books = client.sortPages(client.books, client.numOfRecords);					//sort number of page before printing
				for(int i = 0; i<= client.books.length-1; i++){
					if(client.books[i] != null && client.books[i].getGenre().equals(userGenre))
						System.out.println(client.books[i].toString());
				}
				break;
				case "P":
				case "p"://list out all the records
				client.books = client.sortString(client.books, client.numOfRecords);				//sort tag
				for(int i = 0; i<= client.books.length-1; i++){
					if(client.books[i] != null)
						System.out.println(client.books[i].toString());
				}
				break;
				case "T":
				case "t":
				System.out.println("Please give the tag!");
				client.books = client.sortString(client.books, client.numOfRecords);				//sort tag
				client.searchTag(input.nextLine());
				break;
				case "Q":
				case "q":
				System.out.println("Quitting program");
				System.exit(0);
				default: System.out.println("Wrong option! Try again");
				break;
			}
		}
	}
}