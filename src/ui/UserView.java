package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Book;
import model.User;
import service.UserService;
import utils.Tools;

/**
 * 普通用户界面
 * 宽：800
 * 高：500
 * @author Administrator
 *
 */
public class UserView{
	User user = null;
	UserService userService = new UserService();
	private JFrame userView = new JFrame("图书管理系统");
	private Container c = userView.getContentPane();
	private JPanel topPanel = new JPanel(); //顶部面板
	private JLabel userMsg = new JLabel(); //用来显示用户姓名
	private JLabel time = new JLabel(); //用来显示登录时间
	private JLabel title = new JLabel("图书信息"); //表格标题
	private JScrollPane mainPanel = new JScrollPane(); //图书信息展览面板
	private JTable table = new JTable();
	private JComboBox<String> findBy = new JComboBox<>(); //检索方式下拉列表
	private String items[] = {"通过书名检索", "通过作者检索", "通过编号检索","检索所有图书"};
	private JTextField input = new JTextField(); //检索输入框
	private JButton submit = new JButton("检索");
	
	private List<Book> books = null; //检索到的图书列表
	String[] tableTitle = new String[]{"图书名称","图书作者","图书编号","借阅信息","存放位置"};//表格头字段
	private String[][] tableData = new String[1][5];//表格内容
	
	
	public UserView(User loginUser) {
		this.user = loginUser;
		init();
		userView.setVisible(true);
	}
	
	/*
	 *  初始化界面
	 */
	private void init() {
		c.setLayout(null);
		userView.setSize(800,500);//先size在location，否则会乱
		userView.setLocationRelativeTo(null);
		userView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userView.setResizable(false);
		
		topPanel();//加载顶部面板
		mainPanel();//加载图书目录列表的面板
		
		createListener();//创建监听器，监听检索事件...
	}
	
	/**
	 *  -顶部面板
	 */
	private void topPanel() {
		topPanel.setLayout(null);
		topPanel.setBounds(0,0,800,100);
		topPanel.setBackground(Color.GRAY);
		
		
		//显示登录用户的名字
		userMsg.setBounds(10,5,200,20);
		userMsg.setFont(new Font("微软雅黑",Font.PLAIN,15));
		userMsg.setText("欢迎您："+user.getName());
		
		//检索方式按钮
		
		ComboBoxModel<String> model = new DefaultComboBoxModel<>(items);
		findBy.setModel(model);
		findBy.setFont(new Font("黑体", Font.PLAIN, 9));
		findBy.setBounds(120,40,80,25);
		
		//检索输入框
		input.setBounds(220, 40, 300, 25);
		
		//检索按钮
		submit.setBounds(550, 40, 80, 25);
		
		//当前时间
		time.setBounds(600, 70, 200, 25);
		time.setFont(new Font("宋体", Font.ITALIC, 11));
		time.setText("登录时间："+Tools.getTime());
		
		topPanel.add(userMsg);
		topPanel.add(findBy);
		topPanel.add(input);
		topPanel.add(submit);
		topPanel.add(time);
		c.add(topPanel);
		topPanel.setVisible(true);
	}
	
	/**
	 * -图书信息展览面板
	 */
	private void mainPanel() {
		/**
		 * - 存在问题：表格内容如何自动换行？
		 */
//		mainPanel.setLayout(null);
		
		//表格的设置
		table.getTableHeader().setReorderingAllowed(false);	//列不能移动
		table.getTableHeader().setResizingAllowed(false); 	//不可拉动表格
		table.setEnabled(false);							//不可更改数据
		table.setRowHeight(20);
		mainPanel.setBounds(45, 140, 700, 300);
		mainPanel.setViewportView(table);	//视口装入表格
		
		//标题
		title.setBounds(350, 110, 100, 25);
		
		c.add(mainPanel);
		c.add(title);
		mainPanel.setVisible(true);
	}
	
	
	
	/**
	 * - 给按钮添加监听事件
	 */
	private void createListener() {
		//检索按钮
		//这段代码，只有我和上帝知道是什么意思
		//再过几个月，只有上帝能看懂
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Tools.cleanTwoArray(tableData); //首先清空表格内容
				String findMsg = ""; //用来获得检索书目使输入的内容
				findMsg = input.getText();
				
				//查询书目列表
				if(findBy.getSelectedItem().equals(items[0])) {
					//通过书名检索,用0代表
					books = userService.findBooks(findMsg, 0);//qaq我也不知道这种模式用的对不对
					System.out.println("通过书名检索");
				}
				if(findBy.getSelectedItem().equals(items[1])) {
					//通过作者检索，用1代表
					books = userService.findBooks(findMsg, 1);
					System.out.println("通过作者检索");
				}
				if(findBy.getSelectedItem().equals(items[2])) {
					//通过编号检索，用2代表
					books = userService.findBooks(findMsg, 2);
					System.out.println("通过编号检索");
				}
				if (findBy.getSelectedItem().equals(items[3])) {
					//检索所有图书，3
					books = userService.findBooks(findMsg, 3);
					System.out.println("检索所有图书");
				}
				
				if(books != null) {
					//将检索的书目列表展示到表格中
					tableData = new String[books.size()][5];
					for(int i=0; i<books.size(); i++) {
						Book book = books.get(i);
						if(book != null) {
							//获取书目数据，存为二维数组
							tableData[i][0] = book.getBookname();
							tableData[i][1] = book.getAuthor();
							tableData[i][2] = book.getNum().toString(); //long类型的编号，转为String类型
							tableData[i][3] = book.getBorrow();
							tableData[i][4] = book.getLocation();
						}
					}
				}
				//将内容添加以模型方式添加到表格中
				TableModel data = new DefaultTableModel(tableData,tableTitle);
				table.setModel(data);
				input.setText("");
			}
		});
	}
	
}
