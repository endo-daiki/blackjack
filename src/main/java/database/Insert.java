package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert {
	private static Connection con;
	
	public Insert() {
		con = Database.getConnection();
	}
	
	public static boolean insertUser(String id, String name, String password) {
		if(con == null) {
			return false;
		}
		
		boolean checker;
		
		try {
			PreparedStatement pstmt;
			ResultSet rs;
			
			pstmt = con.prepareStatement
					("insert into user (id, name, password) values (?,?,?)");
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			
			rs = pstmt.executeQuery();
			
			rs.close();
			pstmt.close();
			
			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
			
			checker = false;
		}
		
		return checker;
	}
}
