package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.EmailBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;
import com.services.EmailServices;
import com.services.JwtUtil;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/private")
public class RestController {
	
	@Autowired
	UserDao udao;
	
	@Autowired
	EmailServices emailService;
	
	@Autowired
	JwtUtil jwtToken;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<ResponseBean<UserBean>> addUser(@RequestBody UserBean ubean)
	{
		System.out.println(ubean.getFirstName());
		System.out.println(ubean.getEmail());
		System.out.println(ubean.getPassword());
		ubean.setPassword(BCrypt.hashpw(ubean.getPassword(), BCrypt.gensalt()));
		ResponseBean<UserBean> res=new ResponseBean<>();
		res.setData(ubean);
		if(udao.addUser(ubean))
		{
			System.out.println("UserAdded");
			res.setMessage("UserAddes Successfully");
			return ResponseEntity.ok(res);
		}
		else {
			res.setMessage("Cannot Add Data");
			return ResponseEntity.badRequest().body(res);
		}
	}
	
	@GetMapping("/sendMail")
	public ResponseEntity<String> sendEmail(@RequestBody EmailBean ebean) throws MessagingException
	{
		 	String to = ebean.getTo();
	        String subject = ebean.getSubject();
	        String url="http://localhost:9003/verify?upn="+jwtToken.generateToken("divpatel2003@gmail.com");
	        System.out.println(url);
	        String text = "<html><body><h1>Your Validation Token is here Please Don't share it with anyone. <p>Click the button below to visit my website:</p><a href='"+url+"'><button>Click Here</button></a></h1></body></html>";
	     
	        Thread t=new Thread()
	        		{
	        			public void run() {	        				
	        				try {
								emailService.sendMail(to, subject, text);
							} catch (MessagingException e) {
								e.printStackTrace();
							}
	        			}
	        		};
	        		t.start();
	        return ResponseEntity.ok().body("EmailSend");
	}
	
	@GetMapping("/generateToken")
	public String generateToken()
	{
		System.out.println("TokenHere");
		jwtToken.decodeToken(jwtToken.generateToken("divpatel2003@gmail.com"));
		return jwtToken.generateToken("zexmodi2005@gmail.com");
	}
	
	@GetMapping("/verify")
	public void verify(@RequestParam("upn") String token)
	{
		System.out.println(token);
		String userName=jwtToken.decodeToken(token);
	}
	
	@GetMapping("/showusers")
	public ResponseEntity<?> showUsers(Model model,HttpServletRequest request)
	{
		System.out.println(request.getHeader("token"));
		Map<String, Object> map=new HashMap<>();
		if(request.getHeader("token")==null || request.getHeader("token").trim().length()==0)
		{
			map.put("message", "Please give me token");
//			return new ResponseEntity(map, HttpStatus.FORBIDDEN);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
		}
		else if((request.getHeader("token")).equals("moc?liamg02%nirepidom/43000"))
		{			
			return ResponseEntity.ok().body(udao.getAllUsers());
		}
		else {
			List<UserBean> lb=null;
			ResponseEntity r = new ResponseEntity("Arrey Tere pass Access Nahi hain re", HttpStatus.FORBIDDEN);
			return r;
		}
	}
	
	@GetMapping("/getDemo")
	public void demo()
	{
		System.out.println("Demoooooo");
	}
	
	@PostMapping("/userLoginByToken")
	public ResponseEntity<String> userLogin(@RequestBody UserBean ubean,Model model)
	{
		System.out.println(ubean.getEmail());
		System.out.println(ubean.getPassword());
		UserBean ub=udao.getUserByEmail(ubean.getEmail());
		if(ub!=null)
		{
			if(ub.getEmail().equals(ubean.getEmail()))
			{
				System.out.println("Welcome my dear "+ub.getEmail());
				System.out.println(BCrypt.checkpw(ubean.getPassword(), ub.getPassword()));
				boolean check=BCrypt.checkpw(ubean.getPassword(), ub.getPassword());
				System.out.println(check);
				if(check)
				{
					String token=udao.addTokenToUser(ubean.getEmail());
					return ResponseEntity.ok().body(token);
				}
				else {
					
					return ResponseEntity.ok().body("Please Enter Correct Password");
				}
			}
		}
		else {
			return ResponseEntity.ok().body("Email Address NotFound");
		}
		return null;
	}
}
