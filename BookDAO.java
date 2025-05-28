
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook( Book book) {
        String checkSql = "SELECT * FROM books WHERE title = ? AND author = ?";
        String updateSql = "UPDATE books SET available = available + ? WHERE title = ? AND author = ?";
        String sql = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql))
        {
            checkStmt.setString(1, book.getTitle());
            checkStmt.setString(2, book.getAuthor());
            ResultSet rs = checkStmt.executeQuery();
            if(rs.next())
            {
                //Book already exists : update available count
                try(PreparedStatement updateStmt = conn.prepareStatement(updateSql))
                {
                    updateStmt.setInt(1, book.getAvailable());
                    updateStmt.setString(2, book.getTitle());
                    updateStmt.setString(3, book.getAuthor());
                    updateStmt.executeUpdate();
                    System.out.println("Book already exists. Increased available count.");
                }
            }
            else
            {
                //New book : Insert into database
                try( PreparedStatement ps = conn.prepareStatement(sql))
                {
                    ps.setString(1,book.getTitle());
                    ps.setString(2,book.getAuthor());
                    ps.setInt(3,book.getAvailable());
                    ps.executeUpdate();
                    System.out.println("Book Added successfully.");
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks()
    {
       String sql = "SELECT * FROM books";
       List<Book> bookList = new ArrayList<>();

       try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
       {
           while (rs.next())
           {
               Book book = new Book(rs.getInt("id"), rs.getString("title"),
                    rs.getString("author"), rs.getInt("available"));
               bookList.add(book);
           }
       }
       catch (SQLException e)
       {
        System.out.println("Error fetching books: " + e.getMessage());
       }
       return bookList;
    }

    public Book getBookByTitle(String title)
    {
        String sql = "SELECT * FROM books WHERE title = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return new Book(rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getInt("available"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error finding book: " + e.getMessage());
        }
        return null;
    }

    public void updateBook(Book book)
    {
        String sql ="UPDATE books SET title = ?, author = ?, available = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,book.getTitle());
            ps.setString(2,book.getAuthor());
            ps.setInt(3,book.getAvailable());
            ps.setInt(4, book.getId());

            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Book Updated Successfully.");
            } else {
                System.out.println("Book not found.");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error Updating book: " + e.getMessage());
        }
    }

    public void deleteBook( String title)
    {
        String sql = "DELETE FROM books WHERE title =?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, title);
            int deleted = ps.executeUpdate();
            if( deleted > 0)
            {
                System.out.println("Book with title "+ title + " deleted successfully.");
                resetBooksIds();
            }
            else
            {
                System.out.println("Book not found");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    public void resetBooksIds()
    {
        List<Book> books = getAllBooks();
        String deleteAllSQL = "DELETE FROM books";
        String insertSql = "INSERT INTO books (id, title, author, available) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement deleteStmt = conn.prepareStatement(deleteAllSQL);
        PreparedStatement insertStmt = conn.prepareStatement(insertSql))
        {
            conn.setAutoCommit(false);//START TRANSACTION
            deleteStmt.executeUpdate();
            int newId = 1;
            for(Book book : books)
            {
                insertStmt.setInt(1,newId++);
                insertStmt.setString(2, book.getTitle());
                insertStmt.setString(3, book.getAuthor());
                insertStmt.setInt(4,book.getAvailable());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
            conn.commit();
            System.out.println("Book IDs reset sussessfully");
        }
        catch (SQLException e)
        {
            System.out.println("Error resetting book IDs: "+ e.getMessage());
        }
    }
}

