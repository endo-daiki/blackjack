package model;

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
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	public User(String id, String name, String password, String checkPassword) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.checkPassword = checkPassword;
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
