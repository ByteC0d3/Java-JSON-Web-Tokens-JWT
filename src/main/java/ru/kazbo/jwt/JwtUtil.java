package ru.kazbo.jwt;

import java.util.Map;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	public static String generateToken(Map<String, Object> body, String secret) {
		Key key = getKey(secret);
		JwtBuilder builder = Jwts.builder()
			.setClaims(body)
			.signWith(key, SignatureAlgorithm.HS256);
		return builder.compact();
	}
	
	public static Map<String, Object> verifyAndGetBody(String token, String secret) {
		Key key = getKey(secret);
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key)
				.build()
					.parseClaimsJws(token)
						.getBody();
		return claims;
	}
	
	private static Key getKey(String secret) {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
		return key;
	}
}