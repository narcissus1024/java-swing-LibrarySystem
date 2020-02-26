package model;

public class Register {
	private String name; //姓名
	private String code; //账号
	private String password; //密码
	private String adminCode; //管理员密钥
	private String mold; //注册类型
	
	public Register(String name, String code, String password, String adminCode, String mold) {
		super();
		this.name = name;
		this.code = code;
		this.password = password;
		this.adminCode = adminCode;
		this.mold = mold;
	}

	public Register() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

	public String getMold() {
		return mold;
	}

	public void setMold(String mold) {
		this.mold = mold;
	}
	
	
	
	
	
}
