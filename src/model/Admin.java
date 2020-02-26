package model;


public class Admin {
	private Integer id;
	private String adminName;
	private String adminPassword;
	private String name;
	
	public Admin(Integer id, String adminName, String adminPassword, String name) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.name = name;
	}
	public Admin() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
