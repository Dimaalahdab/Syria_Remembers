package pack1;

public class User {
	int userId;
	String name , surname ,username  , password,userRole ;
	public User(int userId, String name, String surname, String username, String password, String userRole) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	public User() {
		
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
