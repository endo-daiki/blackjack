package model;

import java.sql.Timestamp;

public class playLog {
	private String user_id;
	private String log;
	private Timestamp created_at;
	
	public playLog() {}
	public playLog(String user_id, String log, Timestamp created_at) {
		this.user_id = user_id;
		this.log = log;
		this.created_at = created_at;
	}
	
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	public String getUserId() {
		return user_id;
	}
	
	public void setLog(String log) {
		this.log = log;
	}
	public String getLog() {
		return log;
	}
	
	public void setTime(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getTime() {
		return created_at;
	}
}	
