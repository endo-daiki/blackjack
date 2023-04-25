package login;

public class Validation {
	public static boolean validationId(String id) {
		if(id.length() == 0) {
			return false;
		}
		return true;
	}
	public static boolean validationName(String name) {
		if(name.length() == 0) {
			return false;
		}
		return true;
	}
	public static boolean validationPassword(String password) {
		if(password.length() == 0) {
			return false;
		}
		return true;
	}
	public static boolean passwordCheck(String password, String checkPassword) {
		if(checkPassword.length() == 0 && !checkPassword.equals(password)) {
			return false;
		}
		return true;
	}
}
