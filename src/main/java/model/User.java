package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int id;
	private String name;
	private String nickname;
	private String password;
	private int playing;
	private int win;
	private int lose;
	private int draw;
	
	public User() {}
	public void insertUser(String name, String nicname, String password) {
		Connection con = null;
		try {
			 con = DriverManager.getConnection(
				      "jdbc:mysql://localhost/＜データベース名＞?useSSL=false",
				      "root",
				      ""
				    );
			 
			 PreparedStatement pstmt = con.prepareStatement
					 ("insert into User (name, nickname, password) values (?,?,?)");
			 
			 pstmt.setString(1, name);
			 pstmt.setString(2, nickname);
			 pstmt.setString(3, password);
			 ResultSet rs = pstmt.executeQuery();
			 
			 rs.close();
			 pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
