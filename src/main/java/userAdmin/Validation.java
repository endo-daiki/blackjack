package userAdmin;

public class Validation {
	protected static boolean validationId(String id) {
		return !(id.length() == 0);
	}
	protected static boolean validationName(String name) {
		return !(name.length() == 0);
	}
	protected static boolean validationPassword(String password) {
		return !(password.length() == 0);
	}
	protected static boolean passwordCheck(String password, String checkPassword) {
		return !(checkPassword.length() == 0 || !checkPassword.equals(password));
	}
}