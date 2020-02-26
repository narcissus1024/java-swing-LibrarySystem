package service;

import java.util.ArrayList;
import java.util.List;

import dao.AdminDao;
import dao.UserDao;
import model.Admin;
import model.Book;
import model.User;

/**
 * - 对普通用户数据进行操作的一层
 * @author Administrator
 *
 */
public class UserService {
	UserDao userDao = new UserDao();
	AdminDao adminDao = new AdminDao();
	
	//验证账号密码格式
	private boolean checkCode(String userName, String userPassword) {
		//账号正确性的判断、管理员与普通用户的判断
		if(userName.length()<5 || userName.contains(" ")) {
			//账号输入错误
//			System.out.println("账号输入错误");
			return false;
		}else if(userPassword.length()<5 || userPassword.contains(" ")){
			//密码输入错误
//			System.out.println("密码输入错误");
			return false;
		}else {
			return true;
		}
	}
	
	//普通用户登录
	public User login(String userName, String userPassword) {
		boolean flag = checkCode(userName, userPassword);
		if (flag) {
			//密码格式正确,检查是否注册
			User user = userDao.findAllByUserAndPasswd(userName, userPassword);
			if (user!=null) {
				return user;
			}else {
				return null;
			}
		}else {
			//密码格式错误，返回空
			return null;
		}
	}
	
	//管理员登录
	public Admin adminLogin(String userName, String userPassword) {
		boolean flag = checkCode(userName, userPassword);
		if (flag) {
			//密码格式正确,检查是否注册
			Admin admin = adminDao.findAllByAdminAndPasswd(userName, userPassword);
			if (admin!=null) {
				return admin;
			}else {
				return null;
			}
		}else {
			//密码格式错误，返回空
			return null;
		}
	}
	
	/**
	 * 检索图书
	 */
	public List<Book> findBooks(String findMsg, int flag) {
		List<Book> books = new ArrayList<>();
		if(findMsg.equals("") && flag != 3) {
			return books;
		}else {
			switch(flag) {
			case 0:
				books = userDao.findBookByBookname(findMsg);
				break;
			case 1:
				books = userDao.findBookByAuthor(findMsg);
				break;
			case 2:
				books = userDao.findBookByNum(findMsg);
				break;
			case 3:
				books = userDao.findAllBook();
				break;
			}
		}
		
		return books;
	}
}
