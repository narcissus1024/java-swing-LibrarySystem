package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Admin;
import model.User;
import service.UserService;

/**
 * 	登录
 * 	数据库中设置了密钥、账号的索引类型为UNIQUE
 * @author Administrator
 *
 */
public class Login {
	private JFrame jf = new JFrame();
	private Container c = jf.getContentPane();
	private JPanel topPanel = new JPanel(); //top
	private JLabel userLabel = new JLabel("用户名："); //用户名
	private JLabel passwordLabel = new JLabel("密  码：");//密码
	private JLabel titleLabel = new JLabel("欢迎进入图书管理系统");
	
	private ImageIcon image = new ImageIcon("src//images//logo.png"); //以class文件问基准，寻找地址的
	private JLabel logo = new JLabel();//logo
	
	private JTextField userText = new JTextField();//用户名输入文本框
	private JPasswordField passwordText = new JPasswordField();//密码输入文本框
	private JButton loginBtn = new JButton("登录");//登录按钮
	private JButton registerBtn = new JButton("注册");//注册按钮
	
	private ButtonGroup btnGroup = new ButtonGroup();
	private JRadioButton jr1 = new JRadioButton("普通用户",true);
	private JRadioButton jr2 = new JRadioButton("管理员",false);
	
	private Font userAndPasswdFont = new Font("楷体",Font.BOLD,15);
	
	private String userName = "";
	private String userPassword = "";
	
	
	public Login() {
		init();
		
		jf.setVisible(true);
	}
	
	private void init(){
		c.setLayout(null);
		jf.setSize(400,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		
		/*
		 * 登录窗口顶部
		 */
		topPanel.setLayout(null);
		topPanel.setBackground(Color.GRAY);
		topPanel.setSize(400,100);
		
		//设置标题
		titleLabel.setFont(new Font("微软雅黑",Font.BOLD,20));//字体
		titleLabel.setForeground(Color.BLACK);//字体颜色
		titleLabel.setBounds(120,0,400,100);
		topPanel.add(titleLabel);
		
		//设置图片大小、位置，并添加到面板中
		image.setImage(image.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
		logo.setIcon(image);
		logo.setBounds(50,20,55,55);
		topPanel.add(logo);
		
		
		
		/*
		 * 登录窗口文本框
		 */
		userLabel.setBounds(70,20,200,200);
		userLabel.setFont(userAndPasswdFont);
		passwordLabel.setBounds(70,60,200,200);
		passwordLabel.setFont(userAndPasswdFont);
		userText.setColumns(9);
		userText.setBounds(140,110,150,20);
		passwordText.setColumns(11);
		passwordText.setBounds(140, 150, 150, 20);
		
		//登录按钮
		loginBtn.setBounds(190,220,100,30);
		registerBtn.setBounds(70,220,100,30);
		
		
		
		/*
		 * 普通用户和管理员
		 */
		jr1.setBounds(100,180,100,15);
		jr2.setBounds(200,180,100,15);
		btnGroup.add(jr1);
		btnGroup.add(jr2);
		
		
		c.add(topPanel);
		c.add(userLabel);
		c.add(passwordLabel);
		c.add(userText);
		c.add(passwordText);
		c.add(registerBtn);
		c.add(loginBtn);
		c.add(jr1);
		c.add(jr2);
		
		createBtnListener();
	}
	
	private void createBtnListener() {
		/**
		 * 注册按钮的监听事件
		 */
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterDialog(jf, "欢迎注册");
			}
		});
		
		/**
		 * 登录按钮的监听事件
		 */
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户输入的用户名的密码
				userName = userText.getText();
				userPassword = String.valueOf(passwordText.getPassword());//String.valueOf()->字符数组转换为字符串
				
				UserService userService = new UserService();
				
				if(jr1.isSelected()) {
					//普通用户登录
					User user = userService.login(userName,userPassword);
					if (user!=null) {
						//切换普通用户界面
						System.out.println("登录成功");
						jf.dispose();//销毁登录窗口
						new UserView(user);
					}else {
						//提示账号或密码错误
						System.out.println("账号或密码错误");
						createDialog(jf);
					}
				}else {
					//管理员登录
					Admin admin = userService.adminLogin(userName,userPassword);
					if(admin!=null) {
						//切换至管理员页面
						System.out.println("管理员登录成功！");
						jf.dispose();//销毁登录窗口
						new AdminView(admin);
					}else {
						createDialog(jf);
					}
				}
			}
		});
		
	}
	
	
	private void  createDialog(JFrame frame) {
		JDialog msgDialog = new JDialog(frame, "登录失败！", true);
		JLabel msgLabel = new JLabel();
		Container con = msgDialog.getContentPane();
		
		msgDialog.setSize(250,150);
		msgDialog.setLocationRelativeTo(null);
		
		msgLabel.setText("账号或密码错误！");
		msgLabel.setFont(new Font("黑体", Font.PLAIN, 15));
		
		con.add(msgLabel,BorderLayout.WEST);
		
		msgDialog.setVisible(true); //必须放在最后！！
	}
}
