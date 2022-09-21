package library.service.classes;
public class BookRecord
{
	private int bookID;
	private static int totalID;
	private String title;
	private String[] authors;
	private BookGenre genre;
	private String tag;
	private int numberOfPage;
	
	public String getTitle(){return title;}
	public String[] getAuthors(){return authors;}
	public BookGenre getGenre(){return genre;}
	public String getTag(){return tag;}
	public int getNumberOfPage(){return numberOfPage;}
	
	public void setTitle(String t){title = t;}
	public void setAuthors(String[] a){authors = a;}
	public void setGenre(BookGenre g){genre = g;}
	public void setTag(String t){tag = t;}
	public void setNumberOfPage(int n){numberOfPage = n;}
	
	public BookRecord(String t, String[] a, BookGenre g, String ta, int n){
		title = t;
		authors = a;
		genre = g;
		tag = ta;
		numberOfPage = n;
		totalID++;
		bookID = totalID;
	}
	
	public boolean equals(BookRecord aBook){
		boolean a = true;
		if(aBook == null)
			a = false;
		else if(this.getTitle().equals(aBook.getTitle()) && this.getGenre().equals(aBook.getGenre()) && this.getTag().equals(aBook.getTag()) && this.getNumberOfPage() == aBook.getNumberOfPage()){
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
		String output = "=================\nRecord NO: "+ bookID+ ".\nTitle: "+title+".\nAuthor: "+author2+".\nGenre: "+genre+"\nTag: "+tag+"\nNumber of Page: "+numberOfPage+"\n=================\n";
		return output;
	}
}