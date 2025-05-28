
import java.util.*;

public class LibraryApp {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        try
        {
            BookDAO dao = new BookDAO();
            boolean running = true;

            while(running)
            {
                System.out.println("\n**** WELCOME TO LIBRARY SYSTEM ****");
                System.out.println("1.Add Book");
                System.out.println("2.View All Books");
                System.out.println("3.Search Book");
                System.out.println("4.Update Book");
                System.out.println("5.Delete Book");
                System.out.println("6.Exit");
                System.out.println("\nWhat you want to do: ");
                int choice;
                try {
                    choice = Integer.parseInt(s.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Please enter a valid number between 1 to 6.");
                    continue;
                }

                switch (choice)
                {
                    case 1:
                        System.out.println("How many books do you want to add? ");
                        int numberOfBooks = s.nextInt();
                        s.nextLine();
                        for(int i = 1; i <= numberOfBooks; i++)
                        {
                            System.out.println("\nAdd book "+ i + " : ");
                            System.out.println("Enter book title: ");
                            String title = s.nextLine().trim();
                            while(title.isEmpty())
                            {
                                System.out.println("Title cannot be empty.Please enter again:");
                                title = s.nextLine().trim();
                            }
                            System.out.println("Enter author name : ");
                            String author = s.nextLine();
                            while(author.isEmpty())
                            {
                                System.out.println("Author cannot be empty.Please enter again:");
                                author = s.nextLine().trim();
                            }
                            System.out.println("Enter number of available copies : ");
                            int available = s.nextInt();
                            s.nextLine();
                            while(available < 1)
                            {
                                System.out.println("Copies cannot be less than 1. Please enter again:");
                                available = s.nextInt();
                            }
                            dao.addBook(new Book(title, author,available));
                        }
                        break;

                    case 2:
                        List<Book> books = dao.getAllBooks();
                        if(books.isEmpty())
                        {
                            System.out.println("No books found.");
                        }
                        else
                        {
                            for( Book b :books)
                            {
                                System.out.println(b);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Enter book Title to search: ");
                        String title=s.nextLine().trim();
                        Book book = dao.getBookByTitle(title);
                        if(book != null)
                        {
                            System.out.println(book);
                        }
                        else
                        {
                            System.out.println("Book not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter Title of the book to update: ");
                        String bookUpdate = s.nextLine().trim();
                        Book updateBook = dao.getBookByTitle(bookUpdate);
                        if(updateBook != null)
                        {
                            System.out.println("Enter new Title: ");
                            String newTitle = s.nextLine().trim();
                            System.out.println("Enter new Author: ");
                            String newAuthor = s.nextLine().trim();
                            System.out.println("How many copies of the book available ");
                            int available = s.nextInt();
                            s.nextLine();

                            updateBook.setTitle(newTitle);
                            updateBook.setAuthor(newAuthor);
                            updateBook.setAvailable(available);
                            dao.updateBook(updateBook);

                        }
                        else
                        {
                            System.out.println("Book not found.");
                        }
                        break;

                    case 5:
                        System.out.println("Enter the title of the book to delete: ");
                        String delete = s.nextLine().trim();
                        dao.deleteBook(delete);
                        break;

                    case 6:
                        running=false;
                        System.out.println("Thank You for using the Library System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid Choice. Please select between 1 to 6.");
                        break;
                }
                if (running)
                {
                    System.out.println("\nDo you want to continue? (yes/no): ");
                    String answer = s.nextLine().trim().toLowerCase();
                    if(!answer.equals("yes"))
                    {
                        running=false;
                        System.out.println("Thank You for using the Library System. Goodbye!");
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        s.close();
    }
}
