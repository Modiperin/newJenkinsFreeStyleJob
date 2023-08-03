package com.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String to,String subject,String msg) throws MessagingException
	{
		MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg,true);
        
        javaMailSender.send(message);
	}
}
