package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import login.Validation;

class ValidationTest {
	static String id = "";
	static String name = "";
	static String password = "";
	static String checkPassword = "";
	
	@Test 
	public void testValidationId() { //idの入力がない場合falseを返す
		assertEquals(false, Validation.validationId(id));
	}
	
	@Test 
	public void testValidationName() { //名前の入力がない場合falseを返す
		assertEquals(false, Validation.validationName(name));
	}
	
	@Test 
	public void testValidationPassword() { //パスワードの入力がない場合falseを返す
		assertEquals(false, Validation.validationPassword(password));
	}
	
	@Test 
	public void testPasswordCheck() { //パスワードと確認用パスワードが違っている、または、確認用パスワードが未入力の場合falseを返す
		assertEquals(false, Validation.passwordCheck(password, checkPassword));
	}
}
