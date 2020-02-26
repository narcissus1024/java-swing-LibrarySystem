package model;

public class User {
	private Integer id;
	private String userName;
	private String userPassword;
	private String name;
	
	
	
	public User() {
		super();
	}

	
	public User(Integer id, String userName, String userPassword, String name) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	
}
