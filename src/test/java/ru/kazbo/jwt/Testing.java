package ru.kazbo.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;

class Testing {
	
	// decoded value: thats_my_super_ultra_very_very_secret_key
	final String SECRET_KEY = "dGhhdHNfbXlfc3VwZXJfdWx0cmFfdmVyeV92ZXJ5X3NlY3JldF9rZXk=";
	
	@Test
	void validateValues() {
		String token = generateTokenFromValues();
		System.out.println("The generated token: " + token);
		Map<String, Object> params = JwtUtil.verifyAndGetBody(token, SECRET_KEY),
			expected = values();
		Assertions.assertEquals(expected, params);
		System.out.println("The decoded values: " + params);
	}
	
	private String generateTokenFromValues() {
		Map<String, Object> params = values();
		String token = JwtUtil.generateToken(params, SECRET_KEY);
		return token;
	}
	
	private Map<String, Object> values() {
		Map<String, Object> params = new HashMap<>();
		params.put("username", "Alex");
		params.put("password", "kazbo123");
		params.put("id", 1564654654636L);
		return params;
	}
}
