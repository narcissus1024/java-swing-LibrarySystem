package test;

import model.User;
import ui.UserView;

/**
 * - 普通用户窗口的测试
 * @author Administrator
 *
 */
public class Test01 {
	public static void main(String[] args) {
		new UserView(new User(1,"9956a","12345","快乐压缩"));
	}
}
