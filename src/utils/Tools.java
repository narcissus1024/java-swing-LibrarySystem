package utils;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tools {
	/**
	 * - 对二维数组赋值为空
	 * @param array
	 */
	public static void cleanTwoArray(Object[][] array) {
		if (array.length == 0) {
			return;
		}
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				array[i][j] = null;
			}
		}
	}
	
	/**
	 * 获取当前系统时间
	 */
	public static String getTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowTime = "";
		nowTime = df.format(new Date());
		return nowTime;
	}
	
	/**
	 * - 错误信息Dialog
	 * @param jf
	 * @param msg
	 */
	public static void createMsgDialog(JFrame jf, String msg) {
		JDialog msgDialog = new JDialog(jf, "错误信息", true);
		Container container = msgDialog.getContentPane();
		msgDialog.setSize(350,150);
		msgDialog.setLocationRelativeTo(null);
		
		JLabel msgLabel = new JLabel(msg);
		msgLabel.setForeground(Color.red);
		msgLabel.setFont(new Font("黑体", Font.PLAIN, 15));
		
		container.add(msgLabel);
		msgDialog.setVisible(true);
	}
	
	/**
	 * 	验证一个字符串是否是纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){ 
			   return false; 
		   } 
	  } 
	  return true; 
	} 
}
