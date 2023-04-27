package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

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
		
		try {
			PreparedStatement pstmt;
			ResultSet rs;
			
			pstmt = con.prepareStatement
					("select * from user where id = ?");
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return false;
			}
			
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
	
	public static User updateUser(String id, String name, String password) {
		Connection con = getConnection();
		User user = null;
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("update user set name = ?, password = ? where id = ?");
			
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, id);
			
			pstmt.executeUpdate();
			
			user = loginUser(id, password);
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void deleteUser(String id) {
		Connection con = getConnection();
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
}
