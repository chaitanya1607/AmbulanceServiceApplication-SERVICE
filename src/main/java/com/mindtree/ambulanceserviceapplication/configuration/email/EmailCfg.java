package com.mindtree.ambulanceserviceapplication.configuration.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailCfg {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String to, String body, String topic) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setFrom("ambulanceserviceapp@gmail.com");
		simpleMail.setTo(to);
		simpleMail.setSubject(topic);
		simpleMail.setText(body);
		mailSender.send(simpleMail);
	}

}
