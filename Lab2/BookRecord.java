package library.service.classes;
public class BookRecord
{
	private int bookID;
	private static int totalID;
	private String title;
	private String[] authors;
	private BookGenre genre;
	
	public String getTitle(){return title;}
	public String[] getAuthors(){return authors;}
	public BookGenre getGenre(){return genre;}
	
	public void setTitle(String t){title = t;}
	public void setAuthors(String[] a){authors = a;}
	public void setGenre(BookGenre g){genre = g;}
	
	public BookRecord(String t, String[] a, BookGenre g){
		title = t;
		authors = a;
		genre = g;
		totalID++;
		bookID = totalID;
	}
	
	public boolean equals(BookRecord aBook){
		boolean a = true;
		if(this.getTitle().equals(aBook.getTitle()) && this.getGenre().equals(aBook.getGenre())){
			if(this.getAuthors().length == aBook.getAuthors().length){
				for(int i = 0; i<= this.getAuthors().length-1; i++){
					if(!this.getAuthors()[i].equals(aBook.getAuthors()[i]))
						a = false;
				}
			}
			else
				a = false;
		}
		else
			a = false;
		return a;
	}
	
	public String toString(){
		String author = "";
		for(int i = 0; i<=this.getAuthors().length-1;i++)
			author = author + this.getAuthors()[i] + ",";
		String author2 = author.substring(0, author.length()-2);
		String output = "=================\nRecord NO: "+ bookID+ ".\nTitle: "+title+". Author: "+author2+". Genre: "+genre+"=================\n";
		return output;
	}
}