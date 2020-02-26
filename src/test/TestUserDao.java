package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import model.Book;

public class TestUserDao {
	public static void main(String[] args) {
		
		UserDao userdao = new UserDao();
		List<Book> books = null;
		
//		books = userdao.findBookByBookname("b");//成功
//		books = userdao.findBookByAuthor("胡森");//ok
		books = userdao.findBookByNum("1234567891");//ok
		
		System.out.println(books.get(0).getBookname());
	}
	
}
