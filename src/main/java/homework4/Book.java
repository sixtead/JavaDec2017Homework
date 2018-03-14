package homework4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Book {
    int id;
    int isbn;
    Author author;
    Title title;
    int year;

    private static final String TABLE = "`books`";

    Book(int isbn, Author author, Title title, int year) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    private Book(int id, int isbn, Author author, Title title, int year) {
        this.id = id;
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public int getISBN() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public Title getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public void save() {
        Book book = Book.getByISBN(this.isbn);
        if(title != null) {
            id = book.getId();
        } else {
            author.save();
            title.save();
            try {
				insert(isbn, author.getId(), title.getId(), year);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
        }
    }

    private int insert(int isbn, int authorId, int titleId, int year) throws SQLException {
        String columns = "`isbn`, `author_id`, `title_id`, `year`";
        String values = "?, ?, ?, ?";
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, isbn);
        parameters.put(2, authorId);
        parameters.put(3, titleId);
        parameters.put(4, year);
        return DBQuery.insert(TABLE, columns, values, parameters);
    }

    public static Book getByISBN(int isbn) {
        try (ResultSet rSet = Book.selectByISBN(isbn)) {
            if (rSet.first()) {
                return new Book(rSet.getInt(1),
                        rSet.getInt(2),
                        Author.getById(rSet.getInt(3)),
                        Title.getById(rSet.getInt(4)),
                        rSet.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static ResultSet selectByISBN(int isbn) throws SQLException {
        String columns = "`id`, `isbn`, `author_id`, `title_id`, `year`";
        String conditions = "`isbn` = ?";
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, isbn);
        return DBQuery.select(TABLE, columns, conditions, parameters);
    }

    public static void migrate() {
        DBQuery.create(
            TABLE,
            "`isbn` INT NOT NULL UNIQUE," +
            "`author_id` INT NOT NULL," +
            "`title_id` INT NOT NULL," +
            "`year` INT NOT NULL"
        );

        DBQuery.addFK(TABLE, "books_authors_fk", "author_id", "authors", "id");
        DBQuery.addFK(TABLE, "books_titles_fk", "title_id", "titles", "id");
    }

    public static void drop() {
        DBQuery.drop(TABLE);
    }
}
