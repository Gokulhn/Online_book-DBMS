package onlineBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbOperations implements DbInterface {
	Connection con = null;
	PreparedStatement pst = null;
	Statement smt = null;
	ResultSet rs = null;

	@Override
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/obookstore";
		String username = "root";
		String password = "mysql123";
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return con;
	}

	@Override
	public String addBook(Book bk) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getConnection();
			String sql = "insert into book (bookname,bookid,Author,bookcost,copies) values(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, bk.getBookname());
			pst.setInt(2, bk.getBookid());
			pst.setString(3, bk.getAuthor());
			pst.setInt(4, bk.getBookcost());
			pst.setInt(5, bk.getCopies());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0) {
				System.out.println("Adding book to database ");
			} else {
				System.out.println("Adding book failed!!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return bk.getBookname();
	}

	@Override
	public List<Book> displayBook() {
		Book b = null;
		List<Book> list = new ArrayList<Book>();
		try {
			con = getConnection();
			smt = con.createStatement();
			String sql = "Select * from Book";
			rs = smt.executeQuery(sql);
			while (rs.next()) {
				b = new Book();
				b.setBookname(rs.getString(1));
				b.setBookid(rs.getInt(2));
				b.setAuthor(rs.getString(3));
				b.setBookcost(rs.getInt(4));
				b.setCopies(rs.getInt(5));
				list.add(b);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public String deleteBookDb(Book b) {
		String del = "delete from Book where bookid = ?";
		try {
			con = getConnection();
			pst = con.prepareStatement(del);
			pst.setInt(1, b.getBookid());
			int a = pst.executeUpdate();
			if (a != 0) {
				System.out.println("Deleting book!!!!!!");
			} else {
				System.out.println("No such book found!!!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return b.getBookname();
	}

	@Override
	public boolean validateAdmin(String uname, String pass) {
		con = getConnection();
		String sql = "select * from login where username=? and password=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, uname);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return false;
	}

	@Override
	public void close() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (smt != null) {
			try {
				smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}