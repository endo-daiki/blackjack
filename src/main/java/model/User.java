package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private String id;
	private String name;
	private String nickname;
	private String checkPassword;
	private String password;
	private int playing;
	private int win;
	private int lose;
	private int draw;
	
	public User() {}
	public User(String id, String name, String password, String checkPassword) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.checkPassword = checkPassword;
	}
	
	public void insertUser(String name, String nickname, String password) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("取得失敗");
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/blackjack",
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
		}
	}
	
	public boolean userLogin(String name, String password) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("取得失敗");
		}
		
		Connection con = null;
		boolean check = false;
		try {
			con = DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/blackjack",
						"root",
						""
					);
			
			PreparedStatement pstmt = con.prepareStatement
					("select * from user where name = ? and password = ?");
			
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				check =  true;
				this.id = rs.getString("id");
				this.name = rs.getString("name");
				this.nickname = rs.getString("nickname");
				this.password = rs.getString("password");
				this.playing = rs.getInt("playing");
				this.win = rs.getInt("win");
				this.lose = rs.getInt("lose");
				this.draw = rs.getInt("draw");
			} else {
				check =  false;
			}
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public void editUser (String name, String nickname, String password, int id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("取得失敗");
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/blackjack",
						"root",
						""
					);
			
			PreparedStatement pstmt = con.prepareStatement
					("update user set name = ?, nickname = ?, password = ? where id = ?");
			
			pstmt.setString(1, name);
			pstmt.setString(2, nickname);
			pstmt.setString(3, password);
			pstmt.setInt(4, id);
			
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement
					("select * from user where id = ?");
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				this.id = rs.getString("id");
				this.name = rs.getString("name");
				this.nickname = rs.getString("nickname");
				this.password = rs.getString("password");
				this.playing = rs.getInt("playing");
				this.win = rs.getInt("win");
				this.lose = rs.getInt("lose");
				this.draw = rs.getInt("draw");
			}
			
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void userDelete(int id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("取得失敗");
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/blackjack",
						"root",
						""
					);
			
			PreparedStatement pstmt = con.prepareStatement
					("DELETE FROM user WHERE id = ?");
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname; 
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCheckPassword() {
		return this.checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	
	public int getPlaying() {
		return this.playing;
	}
	public void setPlaying(int playing) {
		this.playing = playing;
	}
	
	public int getWin() {
		return this.win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	
	public int getLose() {
		return this.lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	
 	public int getDraw() {
		return this.draw;
	}
 	public void setDraw(int draw) {
 		this.draw = draw;
 	}
	
	public double getRate() {
		return (this.win / this.draw);
	}
	
}
