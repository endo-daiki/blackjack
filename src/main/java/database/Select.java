package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.playLog;

public class Select {
	private static Connection con;
	
	public Select() {
		con = Database.getConnection();
	}
	
	public static boolean selectId(String id) {
		if(con == null) {
			return false;
		}
		
		boolean idChecker;
		try {
			PreparedStatement pstmt = con.prepareStatement
					("select * from user where id = ?");
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				idChecker = true;
			} else {
				idChecker = false;
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
			idChecker = false;
		}
		return idChecker;
	}
	
	public static User selectUser(String id, String password) {
		if(con == null) {
			return null;
		}

		User user = new User();
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("select * from user where id = ? and password = ?");
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPlaying(rs.getInt("playing"));
				user.setWin(rs.getInt("win"));
				user.setLose(rs.getInt("lose"));
				user.setDraw(rs.getInt("draw"));
				user.setRate(rs.getDouble("rate"));
			} else {
				user = null;
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		return user;
	}
	
	public static List<User> selectRanker() {
		if(con == null) {
			return null;
		}
		
		List<User> ranker = new ArrayList<User>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("select name, rate from user order by rate desc limit 5");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setRate(rs.getDouble("rate"));
				ranker.add(user);
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
			ranker = null;
		}
		
		return ranker;
	}

	public static List<playLog> selectPlayLog(String id) {
		if(con == null) {
			return null;
		}
		
		List<playLog> playLogs = new ArrayList<playLog>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("select * from playLog where user_id = ? order by created_at desc");
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String user_id = rs.getString("user_id");
				String log = rs.getString("log");
				Timestamp time = rs.getTimestamp("created_at");
				playLog playLog = new playLog(user_id, log, time);
				playLogs.add(playLog);
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
		return playLogs;
	}
}
