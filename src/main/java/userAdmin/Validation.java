package userAdmin;

public class Validation {
	public static boolean validationId(String id) {
		return !(id.length() == 0);
	}
	public static boolean validationName(String name) {
		return !(name.length() == 0);
	}
	public static boolean validationPassword(String password) {
		return !(password.length() == 0 || !password.matches("^[a-zA-Z0-9\\d]{8,}$"));
	}
	public static boolean passwordCheck(String password, String checkPassword) {
		return !(checkPassword.length() == 0 || !checkPassword.equals(password)) || validationPassword(password);
	}
}