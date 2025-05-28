
public class Book {
    private int id;
    private String title;
    private String author;
    private int available;

    public Book (int id, String title, String author, int available)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public Book (String title, String author, int available)
    {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    //Getters and Setters
    public int getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }


    //Display method
    @Override
    public String toString()
    {
        return String.format("ID : %d | Title : %s | Author : %s | | Available : %d",
                id, title, author, available);
    }
}
