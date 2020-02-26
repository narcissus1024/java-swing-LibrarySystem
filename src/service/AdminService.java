package service;

import java.util.ArrayList;
import java.util.List;

import dao.AdminDao;
import model.Book;

public class AdminService extends UserService {
	AdminDao adminDao = new AdminDao();

	/**
	 * - 添加图书
	 * @param book
	 * @return
	 */
	public String addBook(Book book) {
		List<Book> books = new ArrayList<Book>();
		String msg = "";
		//首先，判断输入的数据合法性
		if(book.getAuthor().trim().equals("") || book.getBookname().trim().equals("") ||
				book.getBorrow().trim().equals("") || book.getLocation().trim().equals("") ||
				book.getNum()<0 || book.getNum().toString().trim().equals("")) {
			msg = "请输入正确信息！";
		}else {//其次，判断该图书是否存在
			books = adminDao.findBookByNum(book.getNum().toString());
			if (books.isEmpty()) {//不存在
				msg = adminDao.addBook(book);
			}else {//存在
				msg = "该图书已存在！";
			}
		}
		return msg;
	}
	
	/**
	 * - 根据图书编号查找图书
	 */
	public Book findByNumber(String number) {
		List<Book> books = new ArrayList<Book>();
		if (number.trim().equals("")) {
			return null;
		}else {
			books = adminDao.findBookByNum(number);
			if (books.isEmpty()) {
				return null;
			}
		}
		return books.get(0);
	}

	/**
	 * - 根据图书编号删除图书
	 * @param string
	 */
	public String deleteByNum(String num) {
		return adminDao.deleteByNum(num);
	}

	/**
	 * - 修改图书
	 * @param book
	 */
	public String update(Book book) {
		String msg = "";
		if(book.getAuthor().trim().equals("") || book.getBookname().trim().equals("") ||
				book.getBorrow().trim().equals("") || book.getLocation().trim().equals("") ||
				book.getNum()<0 || book.getNum().toString().trim().equals("")) {
			msg = "请输入正确信息！";
		}else {
			msg = adminDao.updateBook(book);
		}
		return msg;
	}

}
