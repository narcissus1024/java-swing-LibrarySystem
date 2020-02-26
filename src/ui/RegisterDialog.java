package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Register;
import service.RegistService;
import utils.Tools;

/**
 * 	注册页面
 * 		管理员注册时，加入了密钥
 * 		注册条件：姓名长度>2    账号密码长度>5  ，账号密码在后端逻辑上暂且无法过滤中文
 * 		注册类别：分为普通用户和管理员
 * @author Administrator
 *
 */
public class RegisterDialog {
	private RegistService registService = new RegistService();
	private Register register = null;
	private JDialog registerView;
	private Container c;
	private JFrame jf;
	
	//字段文本
	private JLabel registerName = new JLabel("姓名：");
	private JLabel registerCode = new JLabel("账号：");
	private JLabel registerPassword = new JLabel("密码：");
	private JLabel registerPassword2 = new JLabel("重复密码：");
	private JLabel registerAdminCode = new JLabel("管理员密钥："); //注册为管理员时，需要输入密钥！
	private JLabel adminCodeNotice = new JLabel("注意：管理员密钥仅在注册管理员时有效...");
	private JLabel registMold = new JLabel("注册类型：");

	//字段输入框
	private JTextField nameField = new JTextField();
	private JTextField codeField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JPasswordField passwordField2 = new JPasswordField();
	private JTextField adminCodeField = new JTextField();
	private JComboBox<String> registMoldField = new JComboBox<String>();
	private String[] registMoldList = {"普通用户","管理员"};
	private ComboBoxModel<String> model = new DefaultComboBoxModel<String>(registMoldList);
	
	//按钮
	private JButton registBtn = new JButton("注册");
	private JButton resetBtn = new JButton("重置");
	
	public RegisterDialog(JFrame jf, String title) {
		this.jf = jf;
		init(title);
		registerView.setVisible(true);
	}
	
	//初始化窗口
	private void init(String title) {
		//窗口
		registerView = new JDialog(jf, title, true);
		c = registerView.getContentPane();
		c.setLayout(null);
		registerView.setSize(500, 300);
		registerView.setLocationRelativeTo(null);
		
		//组件
		registerName.setBounds(120, 20, 100, 30);
		registerCode.setBounds(120, 50, 100, 30);
		registerPassword.setBounds(120,80, 100, 30);
		registerPassword2.setBounds(120, 110, 100, 30);
		registerAdminCode.setBounds(120, 140, 100, 30);//密钥
		adminCodeNotice.setBounds(120, 160, 400, 30);//"注意"
		adminCodeNotice.setForeground(Color.RED);;//"注意"字体
		registMold.setBounds(120, 190, 100, 30); //注册类型
		
		nameField.setBounds(200, 22, 200, 25);
		codeField.setBounds(200, 52, 200, 25);
		passwordField.setBounds(200, 82, 200, 25);
		passwordField2.setBounds(200, 112, 200, 25);
		adminCodeField.setBounds(200, 142, 200, 25); //密钥字段
		registMoldField.setBounds(200, 192, 80, 25);
		
		registBtn.setBounds(180, 230, 60, 25);
		resetBtn.setBounds(260, 230, 60, 25);
		
		registMoldField.setModel(model);
		
		c.add(registerName);
		c.add(registerCode);
		c.add(registerPassword);
		c.add(registerPassword2);
		c.add(registerAdminCode);
		c.add(registMold);
		c.add(adminCodeNotice);
		c.add(nameField);
		c.add(codeField);
		c.add(passwordField);
		c.add(passwordField2);
		c.add(adminCodeField);
		c.add(registMoldField);
		c.add(registBtn);
		c.add(resetBtn);
		
		//监听器
		createListener();
	}

	/**
	 * - 监听器
	 * - 还未解决问题：
	 * 				  2.注册信息合法问题，中文如何排除
	 */
	private void createListener() {
		//注册按钮
		registBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("开始注册...");
				String msg = "";
				//判断两次密码输入是否一致
				if (passwordField.getText().equals(passwordField2.getText())) {
					//一致
					register = new Register(nameField.getText(),codeField.getText(),passwordField.getText(),
							adminCodeField.getText(),registMoldField.getSelectedItem().toString());
					msg = registService.regist(register);
				}else {
					//不一致
					msg = "两次密码输入不一样啊大哥！";//0.0
					passwordField.setText(""); //清空密码
					passwordField2.setText("");
				}
				Tools.createMsgDialog(jf, msg);
			}
		});
		
		//重置按钮
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				codeField.setText("");
				passwordField.setText("");
				passwordField2.setText("");
				adminCodeField.setText("");
				registMoldField.setSelectedIndex(0); //设置为普通用户注册
			}
		});
	}
}
