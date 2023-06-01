package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
	private static final Connection con = Database.getConnection();

	public boolean updateUser(String id, String newId, String name, String password) {
		boolean checker = false;
		try {
			PreparedStatement pstmt;
			
			pstmt = con.prepareStatement
					("update newUser set id = ?, name = ?, password = ? where id = ?");
			
			pstmt.setString(1, newId);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return checker;
	}

	public boolean updateResult(String id, int refund) {
		boolean checker = false;
		
		try {
			PreparedStatement pstmt = null;
			
			pstmt = con.prepareStatement
					("update newUser set tip = (tip + ?), playing = playing + 1 where id = ?");
			
			pstmt.setInt(1, refund);
			pstmt.setString(2, id);	
			pstmt.executeUpdate();
			pstmt.close();
			
			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checker;
	}
}
