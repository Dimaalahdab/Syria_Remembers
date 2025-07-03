package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserContext {
	public Connection getConnected() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/syria_remembers","root","**********");
		
	}
	
	public boolean checkUser(String username, String password, String userRole) throws SQLException {
		
		String query = "select 1 from users where username=? and password=? and userRole=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3,userRole );
	
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
}
	
public boolean checkUser(String username) throws SQLException {
		
		String query = "select * from users where username=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		
		ps.setString(1, username);
		
	
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
}
	
	
	public void saveUser(User u) throws SQLException {
		String query = "insert into users values(NULL,?,?,?,?,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		
		ps.setString(1, u.getName());
	    ps.setString(2, u.getSurname());
	    ps.setString(3, u.getUsername());
	    ps.setString(4, u.getPassword());
	    ps.setString(5, u.getUserRole());
		ps.executeUpdate();
	}
	
}
