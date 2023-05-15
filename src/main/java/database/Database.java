package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String url = "jdbc:mariadb://localhost:3306/blackjack";
    private static final String user = "root";
    private static final String pass = "";
    
    
	public static Connection getConnection() {
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
	
}