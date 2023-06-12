package userAdmin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationTest {
	static String id;
	static String name;
	static String password;
	static String checkPassword;
	
	@BeforeEach
	public void setup() {
		id = "";
		name = "";
		password = "";
		checkPassword = "";
	}
	
	@Test 
	public void testValidationId() { //idの入力がない場合falseを返す
		assertEquals(false, Validation.validationId(id));
		id = "test";
		assertEquals(true, Validation.validationId(id));
	}
	
	@Test 
	public void testValidationName() { //名前の入力がない場合falseを返す
		assertEquals(false, Validation.validationName(name));
		name = "name";
		assertEquals(true, Validation.validationName(name));
	}
	
	@Test 
	public void testValidationPassword() { //パスワードの入力がない場合falseを返す
		assertEquals(false, Validation.validationPassword(password));
		password = "password";
		assertEquals(true, Validation.validationPassword(password));
	}
	
	@Test 
	public void testPasswordCheck() { //パスワードと確認用パスワードが違っている、または、確認用パスワードが未入力の場合falseを返す
		assertEquals(false, Validation.passwordCheck(password, checkPassword)); //パスワードと確認用パスワードが違う
		
		password = "";
		assertEquals(false, Validation.passwordCheck(password, checkPassword)); //同じだが、パスワードが未入力の時
		
		password = "password";
		checkPassword = "password";
		assertEquals(true, Validation.passwordCheck(password, checkPassword)); //パスワードが入力されている、かつ、パスワードと確認用パスワードが正しい
	}
}
