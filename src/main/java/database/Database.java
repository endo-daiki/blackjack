package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.playLog;

public class Database {
	private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String url = "jdbc:mariadb://localhost:3306/blackjack";
    private static final String user = "root";
    private static final String pass = "";
    
    
	private static Connection getConnection() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("取得失敗");
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static User loginUser(String id, String password) {
		Connection con = getConnection();
		User user = new User();
		
		if(con == null) {
			
		}
		
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
			
			user = null;
		}
		return user;
	}
	
	public static boolean insertUser(String id, String name, String password) {
		Connection con = getConnection();
		boolean check = false;
		
		if(con == null) {
			//データベースエラーでログインに戻す
		}
		if(selectUser(id) != null) {
			return false;
			//登録ページに戻す
		}
		
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
			
			check = true;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
		return check;
	}
	
	public static User updateUser(String id, String newId, String name, String password) {
		Connection con = getConnection();
		
		if(con == null) {
			//データベースエラーでログインに戻す
		}
		
		User user = null;
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("update user set id = ?, name = ?, password = ? where id = ?");
			
			pstmt.setString(1, newId);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, id);
			
			pstmt.executeUpdate();
			
			user = selectUser(newId);
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void deleteUser(String id) {
		Connection con = getConnection();
		
		if(con == null) {
			//データベースエラーでログインに戻す
		}
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("delete from user where id = ?");
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static User updateResult(String id, String result) {
		Connection con = getConnection();
		
		if(con == null) {
			//データベースエラーでログインに戻す
		}
		
		User user = null;
		
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
			
			user = selectUser(id);
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static User selectUser(String id) {
		Connection con = getConnection();
		
		if(con == null) {
			//データベースエラーでログインに戻す
		}
		
		User user = new User();
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("select * from user where id = ?");
			
			pstmt.setString(1, id);
			
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
		}
		return user;
	}
	
	public static List<User> getRanking() {
		Connection con = getConnection();
		
		if(con == null) {
			//データベースエラーでログインに戻す
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
	
	public static void insertLog(String id, String result) {
		Connection con = getConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("insert into playLog (user_id, log) values (?,?)");
			
			pstmt.setString(1, id);
			pstmt.setString(2, result);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<playLog> getPlayLog(String id) {
		Connection con = getConnection();
		
		if(con == null) {
			//データベースエラーでログインに戻す
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
