package library.service.classes;//set up the package
import java.io.Serializable;
import library.service.classes.BookGenre;
public class BookRecord implements Serializable{
	transient int recordNo;
	String title;	
	String []authors;
	BookGenre genre;
	static int cnt=10000;
	int pages=0;
	String tag;
	//non-default constructor      
    public BookRecord(String title, String genre, String [] name,String tag,int pages){
		this.setTitle(title);
		this.setGenre(genre);
		this.setAuthors(name);
		this.setRecordNo();
		this.setTag(tag);
		this.setPages(pages);
	}
    //accessors
	public String getTitle(){
		return this.title;
	}	
	public BookGenre getGenre(){
		return this.genre;
	}
	public String [] getAuthors(){
		String []ret = new String[this.authors.length];
		for(int i=0;i<this.authors.length;++i){
			ret[i]=this.authors[i];
		}
		return ret;
	}
	public String getAuthorList(){
		String ret="";
		for(int i=0;i<this.authors.length;++i){
			ret=ret + " " +this.authors[i];
		}
		return ret;
	}
	public String getTag(){
		return this.tag;
	}
	public int getPages(){
		return this.pages;
	}
	public int getRecordNo(){
		return this.recordNo;
	}
	//mutators
	public void setRecordNo(){
		this.recordNo=BookRecord.cnt++;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void setGenre(String genre){
		this.genre=BookGenre.valueOf(genre);
	}
	public void setPages(int pages){
		this.pages=pages;
	}
	public void setTag(String tag){
		this.tag=tag;
	}
	public void setAuthors(String []authorList){
		this.authors = new String[authorList.length];
		for(int i=0;i<authorList.length;++i){
			this.authors[i]=authorList[i];
		}
	}
	
	//toString method
	public String toString(){		
		String str="";
		str = str + "===================================\n";
		str = str + "Record No:" + this.getRecordNo() + "\n";
		str = str + "Tag:" + this.getTag() + "\n";
		str = str + "Title:" + this.getTitle() + "\n";
		str = str + "Genre: " + this.getGenre() + "\n";
		str = str + "Authors: " + this.getAuthorList() + "\n";
		str = str + "No. of Pages: " + this.getPages() + "\n";
		str = str + "===================================\n";
		return str;
	}
	public String print(){
		String author = "";
		for(int i = 0; i<= authors.length-1;i++){
			author = author + authors[i]+",";
		}
		author = author.substring(0,author.length()-2);
		return ""+title+":"+author+":"+tag+":"+pages;
	}
	//toEquals method
	public boolean equals(BookRecord aRecord){//remember you should not compare the record no
		if(!this.getTitle().equals(aRecord.getTitle()))
			return false;
		if(!this.equalsHelper(aRecord))
			return false;
		if(!(this.getPages() == aRecord.getPages()))
			return false;
		return true;
	}
	
	private boolean equalsHelper(BookRecord aRecord){
		for(int i=0;i<this.authors.length;++i){
			if(!this.authors[i].equals(aRecord.authors[i])){ //assume the authors will always be provided in the same order
				return false;
			}
		}
		return true;
	}
}