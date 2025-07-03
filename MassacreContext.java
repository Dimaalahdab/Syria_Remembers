package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MassacreContext {
	public Connection getConnected() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/syria_remembers","root","5519918781");
		
	}
	
	
	public ArrayList<Massacre> getMassacres() throws SQLException{
		ArrayList<Massacre> massacres = new ArrayList<>();
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from massacres");
		while(rs.next()) {
			Massacre m = new Massacre();
			m.setId(rs.getInt(1));
			m.setTitle(rs.getString(2));
			m.setDate(rs.getInt(3));
			m.setLocation(rs.getString(4));
			m.setType(rs.getString(5));
			m.setVictims(rs.getInt(6));
			massacres.add(m);
		}
		return massacres;
	}
	
	
	
	
	
	
	
public boolean checkMassacre(  String title, int date, String location, String type ,int victims) throws SQLException {
		
		String query = "select 1 from massacres where tilte=? and date=? and location=?  and  type=?   and victims=?   ";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setString(1, title);
		ps.setInt(2, date);
		ps.setString(3, location );
		ps.setString(4, type);
		ps.setInt(5, victims );
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
}


public boolean checkMassacre(String title) throws SQLException {
	
	String query = "select * from massacres where title=?";
	PreparedStatement ps = getConnected().prepareStatement(query);
	
	ps.setString(1, title);
	

	ResultSet rs = ps.executeQuery();
	
	return rs.next();
}



public void saveMassacre(Massacre m) throws SQLException {
	String query = "insert into massacres values(NULL,?,?,?,?,?)";
	PreparedStatement ps = getConnected().prepareStatement(query);
	
	ps.setString(1, m.getTitle());
    ps.setInt(2, m.getDate());
    ps.setString(3, m.getLocation());
    ps.setString(4, m.getType());
    ps.setInt(5, m.getVictims());
	ps.executeUpdate();
}


}
