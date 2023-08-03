package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bean.UserBean;
import com.dao.UserDao;
import com.services.UserServices;

@MultipartConfig(
		maxFileSize = 1048576,
		fileSizeThreshold = 1048576
		)

@Controller
public class SessionController {
	
	@Autowired
	UserDao udao;
	
	@Autowired
	UserServices us;
	
	@GetMapping("/signup")
	public String signup() {
		System.out.println("Sign Up");
		return "SignUp";
	}
	
	@GetMapping("/showusers")
	public String showUsers(Model model,@CookieValue(value = "fav-col",
	        defaultValue = "unknown") String favColour)
	{
		System.out.println(favColour);
		model.addAttribute("users", udao.getAllUsers());
		return "ShowUsers";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam String userId)
	{
		udao.deleteUser(userId);
		return "redirect:/showusers";
	}
	@GetMapping("/login")
	public String login()
	{
		return "LoginForm";
	}
	
	@GetMapping("/fileupload")
	public String fileUpload()
	{
		return "FileUpload";
	}
	
	@GetMapping("/userLogin/forgetpassword")
	public String forgetPassword()
	{
		return "ForgetPassword";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "LoginForm";
	}
	
	@PostMapping("/signup")
	public String signup(UserBean ub,Model model,@RequestParam("uploads") MultipartFile file)
	{
		System.out.println("PostSignUp");
//		System.out.println();
		if(us.uploadFile(file,ub.getEmail()))
		{
			ub.setPassword(BCrypt.hashpw(ub.getPassword(), BCrypt.gensalt()));
			ub.setFileName("images//"+ub.getEmail()+"."+file.getContentType().split("/")[1]);
			if(udao.addUser(ub))
			{
				model.addAttribute("firstName",ub.getFirstName());
				model.addAttribute("welcome","Welcome My Dear ");
			}
			return "SignUp";
		}
		model.addAttribute("error","Enter only images with PNG format");
		return "SignUp";
	}
	
	
	
	@PostMapping("/userLogin")
	public String userLogin(UserBean ubean,Model model,HttpSession session)
	{
		System.out.println(ubean.getEmail());
		System.out.println(ubean.getPassword());
		UserBean ub=udao.getUserByEmail(ubean.getEmail());
		if(ub!=null)
		{
			if(ub.getEmail().equals(ubean.getEmail()))
			{
				System.out.println("Welcome my dear"+ub.getEmail());
				System.out.println(BCrypt.checkpw(ubean.getPassword(), ub.getPassword()));
				boolean check=BCrypt.checkpw(ubean.getPassword(), ub.getPassword());
				System.out.println(check);
				if(check)
				{
					session.setAttribute("name", ubean.getEmail());
					return "redirect:/showusers";
				}
				else {
					model.addAttribute("error","Invalid Password");
					model.addAttribute("email",ubean.getEmail());
					return "LoginForm";
				}
			}
		}
		else {
			model.addAttribute("error","Email Address Not Found");
			model.addAttribute("email",ubean.getEmail());
		}
		
		return "LoginForm";
	}
	
	
	
	@PostMapping("/savefile")
	public String uploadFile(@RequestParam("uploads") MultipartFile file,Model model)
	{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType().split("/")[1]);
		String path="C:\\Users\\modip\\Documents\\workspace-spring-tool-suite-4-4.18.0.RELEASE\\Spring-Starter\\src\\main\\resources\\static\\images\\";
		try {
		File f=new File(path,file.getOriginalFilename());
		
		byte b[]=file.getBytes();
		FileOutputStream fos=new FileOutputStream(f);
		fos.write(b);
		fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("file","images//"+file.getOriginalFilename());
		return "showFiles";
	} 
	
	@PostMapping("/userLogin/forgetpassword")
	public String forgetPassword(UserBean ub) {
		
		
		return "redirect:/login";
	}
	
	

}
