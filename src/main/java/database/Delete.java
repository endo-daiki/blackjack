package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
	private static final Connection con = Database.getConnection();

	public static boolean deleteUser(String id) {
		boolean checker = false;

		try {
			PreparedStatement pstmt = 
					con.prepareStatement("delete from newUser where id = ?");

			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();

			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checker;
	}

	public static boolean deleteLog(String id) {
		boolean checker = false;

		try {
			PreparedStatement pstmt = 
					con.prepareStatement("delete from newPlayLog where user_id = ?");

			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();

			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checker;
	}
}
