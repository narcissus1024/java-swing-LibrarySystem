package model;

public class Book {
	private Integer id;
	private String bookname;
	private String author;
	private Long num;
	private String borrow; //是否可以借阅
	private String location;//存放位置
	
	public Book() {
	}
	
	public Book(Integer id, String bookname, String author, Long num, String borrow, String location) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.num = num;
		this.borrow = borrow;
		this.location = location;
	}
	
	public Book(String bookname, String author, Long num, String borrow, String location) {
		super();
		this.bookname = bookname;
		this.author = author;
		this.num = num;
		this.borrow = borrow;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getBorrow() {
		return borrow;
	}

	public void setBorrow(String borrow) {
		this.borrow = borrow;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
