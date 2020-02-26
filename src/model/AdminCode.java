package model;

/**
 * - 密钥类
 * @author Administrator
 *
 */
public class AdminCode {
	private int id;
	private String code;
	private int count;
	
	public AdminCode() {
		super();
	}
	
	public AdminCode(String code, int count) {
		super();
		this.code = code;
		this.count = count;
	}
	
	public AdminCode(int id, String code, int count) {
		this.id = id;
		this.code = code;
		this.count = count;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
