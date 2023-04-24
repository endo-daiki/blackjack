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
    
    
	private Connection getConnection() {
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
	
	public User getUser(String name, String password) {
		Connection con = getConnection();
		User loginUser = new User();
		
		try {
			PreparedStatement pstmt = con.prepareStatement
					("select * from user where name = ? and password = ?");
			
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginUser.setId(rs.getInt("id"));
				loginUser.setName(rs.getString("name"));
				loginUser.setNickname(rs.getString("nickname"));
				loginUser.setPassword(rs.getString("password"));
			} else {
				return null;
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginUser;
	}
}
