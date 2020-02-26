package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.User;
import utils.JDBCUtil;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	

	
	/*
	 * 通过账号、密码查询用户
	 */
	public User findAllByUserAndPasswd(String userName, String userPassword) {
		User user = null;
		Integer id = null;
		String username = null;
		String password = null;
		String name = null;
		try {
			String sql = "select * from t_user where username=? and password=?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			rs = ps.executeQuery();//执行sql查询，返回结果集
			
			//在使用rs.getxxx()之前，必须使用rs.next()，否则会发生错误
			if(rs.next()) {
				id = rs.getInt("uid");
				username = rs.getString("username");
				password = rs.getString("password");
				name = rs.getString("name");
				
				user = new User(id, username,password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return user;
	}
	
	/**
	 * 通过书名检索
	 */
	public List<Book> findBookByBookname(String bookname) {
		List<Book> books = new ArrayList<>();
		try {
			String sql = "select * from t_books where bookname like ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+bookname+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(rs.getInt("id"),rs.getString("bookname"),rs.getString("author"),
						rs.getLong("number"),rs.getString("borrow"),rs.getString("location")));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return books;
	}
	
	/**
	 * 通过作者检索
	 * 
	 */
	public List<Book> findBookByAuthor(String author) {
		List<Book> books = new ArrayList<>();
		try {
			String sql = "select * from t_books where author like ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+author+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(rs.getInt("id"),rs.getString("bookname"),rs.getString("author"),
						rs.getLong("number"),rs.getString("borrow"),rs.getString("location")));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return books;
	}
	
	/**
	 * 通过编号检索
	 */
	public List<Book> findBookByNum(String num) {
		List<Book> books = new ArrayList<>();
		try {
			String sql = "select * from t_books where number = ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(rs.getInt("id"),rs.getString("bookname"),rs.getString("author"),
						rs.getLong("number"),rs.getString("borrow"),rs.getString("location")));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return books;
	}
	
	public List<Book> findAllBook() {
		List<Book> books = new ArrayList<>();
		try {
			String sql = "select * from t_books";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				books.add(new Book(rs.getInt("id"),rs.getString("bookname"),rs.getString("author"),
						rs.getLong("number"),rs.getString("borrow"),rs.getString("location")));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return books;
	}
}
