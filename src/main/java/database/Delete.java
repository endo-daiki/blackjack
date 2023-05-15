package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
private static Connection con;
	
	public Delete() {
		con = Database.getConnection();
	}
	
	public static boolean deleteUser(String id) {
		if(con == null) {
			return false;
		}
		
		boolean checker;
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("delete from user where id = ?");
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
			
			checker = false;
		}
		
		return checker;
	}
	
	public static boolean deleteLog(String id) {
		if(con == null) {
			return false;
		}
		
		boolean checker;
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("delete from playLog where user_id = ?");
			
			pstmt.setString(1, id);
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
