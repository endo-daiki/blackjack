package model;

public class User {
	private String id;
	private String newId;
	private String name;
	private String checkPassword;
	private String password;
	private int playing;
	private int tip;

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
	public User(String id, String newId, String name, String password, String checkPassword) {
		this.id = id;
		this.newId = newId;
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
	public String getNewId() {
		return this.newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
}
