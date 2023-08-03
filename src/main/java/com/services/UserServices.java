package com.services;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServices {
	public boolean uploadFile(MultipartFile file,String email)
	{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		if(file.getContentType().contains("image"))
		{
			String path="C:\\Users\\modip\\Documents\\workspace-spring-tool-suite-4-4.18.0.RELEASE\\Spring-Starter\\src\\main\\resources\\static\\images\\";
			
			try {
			File f=new File(path,email+"."+file.getContentType().split("/")[1]);  //The Name of the file is attached to the byte data type
			
			byte b[]=file.getBytes(); //actual file in the byte format
			FileOutputStream fos=new FileOutputStream(f); 
			fos.write(b); //actual file with name and data is write at the preferred location as given in file object
			fos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
}
