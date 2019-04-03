package onlineBook;

import java.sql.Connection;
import java.util.List;

public interface DbInterface {
	public Connection getConnection();

	public String addBook(Book bk);

	public List<Book> displayBook();

	public String deleteBookDb(Book b);

	public boolean validateAdmin(String uname, String pass);

	public void close();

}
