package library.service.classes;
import library.service.classes.BookGenre;
import library.service.classes.BookRecord;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
public class BookRecordClient
{
	Vector<BookRecord> books;
	
	//check duplicate method
	public boolean isDuplicate(Vector<BookRecord> bookArray, BookRecord aBook){
		boolean output = false;
		for(int i = 0; i<= bookArray.size()-1; i++){
			//BookRecord bookElement = (BookRecord) bookArray.elementAt(i);
			if(aBook.equals(bookArray.elementAt(i)))
				output = true;
		}
		return output;
	}
	//sort according to tag
	public Vector<BookRecord> sortString(Vector<BookRecord> input){
		Vector<BookRecord> bookArray = input;
		for(int i = 0; i <= bookArray.size()-2; i++){
			for(int j = i + 1; j <= bookArray.size()-1; j++){
				if(bookArray.elementAt(i).getTag().compareTo(bookArray.elementAt(j).getTag()) > 0){
					BookRecord replaceBook = bookArray.elementAt(i);
					bookArray.setElementAt(bookArray.elementAt(j), i);
					bookArray.setElementAt(replaceBook, j);
				}
			}
		}
		return bookArray;
	}
	//sort according to number of page
	public Vector<BookRecord> sortPages(Vector<BookRecord> input){
		Vector<BookRecord> bookArray = input;
		for(int i = 0; i <= bookArray.size()-2; i++){
			for(int j = i + 1; j <= bookArray.size()-1; j++){
				if(bookArray.elementAt(i).getNumberOfPage() > bookArray.elementAt(j).getNumberOfPage()){
					BookRecord replaceBook = bookArray.elementAt(i);
					bookArray.setElementAt(bookArray.elementAt(j), i);
					bookArray.setElementAt(replaceBook, j);
				}
			}
		}
		return bookArray;
	}
	//search tag method
	public void searchTag(String tag){
		int start = 0, end = books.size() - 1;
		while (end >= start){
			int middle = ( start + end ) / 2;  
			if (books.elementAt(middle).getTag().equals(tag)){
				System.out.println("Book found:\n\n"+books.elementAt(middle).toString()); //key found
				return;							//return to main method
			}
		else if (books.elementAt(middle).getTag().compareTo(tag)>0)
				end = middle - 1; // search left  
			else if(books.elementAt(middle).getTag().compareTo(tag)<0)
				start = middle + 1; // search right 
		}
		System.out.println("==========\nNo match found!\n==========");
	}
	//
	public static void main(String []args){
		BookRecordClient client = new BookRecordClient();
		client.books = new Vector<BookRecord>();
		try {
			File file = new File(args[0]);
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()){
		
				StringTokenizer line = new StringTokenizer(scanner.nextLine(),":");
				String title = line.nextToken();
				BookGenre genre = BookGenre.valueOf(line.nextToken());
				StringTokenizer authors = new StringTokenizer(line.nextToken(),",");
				String tag = line.nextToken();
				int numOfPage = Integer.parseInt(line.nextToken());
				String[] authorArray = new String[authors.countTokens()];
				for(int i = 0; i<= authorArray.length-1;i++)
					authorArray[i] = authors.nextToken();
				BookRecord newBook = new BookRecord(title, authorArray, genre, tag, numOfPage);
				if(client.isDuplicate(client.books, newBook)){
					System.out.println("\nDuplicate found:\n"+newBook.toString());
				}
				else{
					client.books.add(newBook);
				}
			}
		} catch(IOException ioe){
			System.out.println("The file can not be read");
		}
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
				System.out.println("Type a genre. The genres are:");
				for (BookGenre d : BookGenre.values()) 
					System.out.println(d);
				System.out.println("\nThe genre you want is:\n");
				BookGenre userGenre = BookGenre.valueOf(input.nextLine()); 
				System.out.println("Wait");//assume the use will type in a valid genre//print out records of the selected genre
				client.books = client.sortPages(client.books);
				for(int i = 0; i<= client.books.size()-1; i++){
					if(client.books.elementAt(i).getGenre().equals(userGenre))
						System.out.println(client.books.elementAt(i).toString());
				}
				break;
				case "P": //list out all the records
				client.books = client.sortString(client.books);
				for(int i = 0; i<= client.books.size()-1; i++){
						System.out.println(client.books.elementAt(i).toString());
				}
				break;
				case "T":
				case "t":
				System.out.println("Please give the tag!");
				client.books = client.sortString(client.books);				//sort tag
				client.searchTag(input.nextLine());
				break;
				case "Q": System.out.println("Quitting program");
				System.exit(0);
				default: System.out.println("Wrong option! Try again");
				break;
			}
		}
	}
}