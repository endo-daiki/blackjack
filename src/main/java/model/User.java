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
	public void insertUser(String name, String nickname, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e){
			throw new IllegalStateException("");
		}
		
		Connection con = null;
		try {
			 con = DriverManager.getConnection(
				      "jdbc:mysql://localhost/blackjack",
				      "root",
				      ""
				    );
			 
			 PreparedStatement pstmt = con.prepareStatement
					 ("insert into user (name, nickname, password) values (?,?,?)");
			 
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
