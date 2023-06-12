package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insert {
	private static final Connection con = Database.getConnection();

	public static boolean insertUser(String id, String name, String password) {
		boolean checker = false;

		try {
			PreparedStatement pstmt = 
					con.prepareStatement("insert into newUser (id, name, password) values (?,?,?)");

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);

			ResultSet rs = pstmt.executeQuery();

			rs.close();
			pstmt.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checker;
	}

	public static boolean insertLog(String id, int refund) {
		boolean checker = false;

		try {
			PreparedStatement pstmt = 
					con.prepareStatement("insert into newPlayLog (user_id, log) values (?,?)");

			pstmt.setString(1, id);
			pstmt.setInt(2, refund);
			pstmt.executeUpdate();
			pstmt.close();

			checker = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checker;
	}
}
