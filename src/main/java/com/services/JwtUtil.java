package com.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(String mail)
	{
		Date d=new Date();
		Date expirationDate = new Date(d.getTime() + 1000 * 60 * 10); // token expires in 10 hours
        return Jwts.builder()
                .setSubject(mail)
                .setIssuedAt(d)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
	}
	
	public String decodeToken(String token)
	{
		try {
		Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
		String username = claims.getSubject();
		String name = claims.get("name", String.class);
		long timestamp = claims.getIssuedAt().getTime();
		System.out.println("Username: " + username);
		System.out.println("Name: " + name);
		System.out.println("Issued At: " + new Date(timestamp));
		return username;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public StringBuilder generateTokenManually(String mail)
	{
		Date d=new Date();
		mail=mail.replace("@", "%20");
		mail=mail.replace(".", "?");
		StringBuilder input1 = new StringBuilder();
		input1.append(mail);
		input1.reverse();
		input1.append("/"+d.getSeconds()*1000);
		return input1;
		
	}
	
	public String generateToken()
	{
		String data="qwertyuiopasdfghjklzxcvbnm1234567890POIUYTREWQLKJHGFDSAMNBVCXZ";
		StringBuilder token=new StringBuilder("");
		for (int i=0;i<8;i++)
		{
			int index=(int)Math.random()*data.length();
			token.append(data.charAt(index));
		}
		return token.toString(); // we want data here not as object
	}
}
