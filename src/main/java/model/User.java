package model;

public class User {

	private int id;
	private String usrName;
	private String pwd;
	private int phone;
	private String email;
	private int role;

	public User() {
	}

	public User(String username, String password, int phone, String email) {
		this.setUsrName(username);
		this.setPwd(password);
		this.setPhone(phone);
		this.setEmail(email);
		this.setRole(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}


}
