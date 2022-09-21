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
				books[i] = nbooks[i];
			}
			System.out.println("Resize the array from "+books.length+" to "+nbooks.length);
			books = nbooks;
		}
	}
	//check duplicate method
	
	public boolean isDuplicate(BookRecord[] bookArray, BookRecord aBook){
		boolean output = false;
		for(int i = 0; i<= bookArray.length-1; i++){
			if(bookArray[i].equals(aBook))
				output = true;
		}
		return output;
	}
	
	public static void main(String []args){
		BookRecordClient client = new BookRecordClient();
		int expansionFactor = Integer.parseInt(args[1]);
		try {
			File file = new File(args[0]);
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()){
				client.fill(expansionFactor);
				StringTokenizer line = new StringTokenizer(scanner.nextLine(),":");
				String title = line.nextToken();
				BookGenre genre = BookGenre.valueOf(line.nextToken());
				StringTokenizer authors = new StringTokenizer(line.nextToken(),",");
				String[] authorArray = new String[authors.countTokens()];
				for(int i = 0; i<= authorArray.length-1;i++)
					authorArray[i] = authors.nextToken();
				BookRecord newBook = new BookRecord(title, authorArray, genre);
				int blankPosition = 0;
				while(blankPosition == 0){
					for(int i = client.books.length-1; i >= 0; i--){
						if(client.books[i] == null)
							blankPosition = i;
					}
				}
				if(client.isDuplicate(client.books, newBook))
					System.out.println(newBook.toString());
				else
					client.books[blankPosition] = newBook;
			}
		} catch(IOException ioe){
			System.out.println("The file can not be read");
		}
		//User interactive part
		while(true){
			System.out.println("Select an option:");
			System.out.println("Type \"S\" to list books of a genre");
			System.out.println("Type \"P\" to print out all the book recors");
			System.out.println("Type \"Q\" to Quit");
			Scanner input = new Scanner(System.in);
			switch (input.nextLine()) {
				case "S":System.out.println("Type a genre. The genres are:");
				for (BookGenre d : BookGenre.values()) {
					System.out.println(d);
				}
				BookGenre userGenre = BookGenre.valueOf(input.nextLine()); //assume the use will type in a valid genre//print out records of the selected genre
				for(int i = 0; i<= client.books.length-1; i++){
					if(client.books[i].getGenre().equals(userGenre))
						System.out.println(client.books[i].toString());
				}
				break;
				case "P": //list out all the records
				for(int i = 0; i<= client.books.length-1; i++)
					System.out.println(client.books[i].toString());
				break;
				case "Q": System.out.println("Quitting program");
				System.exit(0);
				default: System.out.println("Wrong option! Try again");
				break;
			}
		}
	}
}