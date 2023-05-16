package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
	private static Connection con;
	static {
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

	public static boolean updateResult(String id, String result) {
		if(con == null) {
			return false;
		}
		
		boolean checker;
		try {
			PreparedStatement pstmt = null;
			
			switch (result) {
				case "win" :
					pstmt = con.prepareStatement
						("update user set win = win + 1, playing = playing + 1, rate = win / playing where id = ?");
					break;
				case "lose" :
					pstmt = con.prepareStatement
						("update user set lose = lose + 1, playing = playing + 1, rate = win / playing where id = ?");
					break;
				case "draw" :
					pstmt = con.prepareStatement
						("update user set draw = draw + 1, playing = playing + 1, rate = win / playing where id = ?");
					break;
			}
			
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
