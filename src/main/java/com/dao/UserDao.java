package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;
import com.services.JwtUtil;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate stmt;
	
	@Autowired
	JwtUtil jwtToken;
	
	public Boolean addUser(UserBean ubean)
	{
		try {
			
			int rows=stmt.update("insert into users(firstName,email,password,fileName) values(?,?,?,?)",ubean.getFirstName(),ubean.getEmail(),ubean.getPassword(),ubean.getFileName());
			if(rows>0)
			{
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public List<UserBean> getAllUsers()
	{
		List<UserBean> ulist=stmt.query("select * from users",new BeanPropertyRowMapper<UserBean>(UserBean.class));
		return ulist;
	}

	public void deleteUser(String userId) {
		stmt.update("delete from users where userId=?",userId);
	}
	
	public UserBean getUserByEmail(String email)
	{
		try {			
			return stmt.queryForObject("select * from users where email=?", new BeanPropertyRowMapper<UserBean>(UserBean.class), new Object[] {email});
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
	}

	public String addTokenToUser(String email) {
		
//		String token=jwtToken.generateToken(email);
		StringBuilder token=jwtToken.generateTokenManually(email);
		int num=stmt.update("update us"
				+ "ers set token=? where email=?",token,email);
		if(num>0)
		{
			return token.toString();
		}
		else {			
			return "";
		}
	}

}
