package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {
private static Connection con;
	
	public Update() {
		con = Database.getConnection();
	}
	
	public static boolean updateUser(String id, String newId, String name, String password) {
		if(con == null) {
			return false;
		}
		
		boolean checker;
		try {
			PreparedStatement pstmt;
			
			pstmt = con.prepareStatement
					("update user set id = ?, name = ?, password = ? where id = ?");
			
			pstmt.setString(1, newId);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
			
			checker = false;
		}
		
		return checker;
	}
}
