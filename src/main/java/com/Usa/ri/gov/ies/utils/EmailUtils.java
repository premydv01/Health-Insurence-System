package com.Usa.ri.gov.ies.utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.Usa.ri.gov.ies.admin.entity.CaseWorkerEntity;
import com.Usa.ri.gov.ies.admin.model.CaseWorker;


@Component
public class EmailUtils {
	@Autowired
	JavaMailSender mailSender;
	
	public boolean sendUserUnlockEmail(CaseWorkerEntity caseworker) {
		boolean isSent=false;
		
		try {
			MimeMessage mimeMsg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMsg);
			
			helper.setTo(caseworker.getEmail());
			helper.setSubject("Unlock your Account");
			helper.setText(getUnlockAccEmailBody(caseworker),true);
			
			mailSender.send(mimeMsg);
			
			isSent=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	
	public String getUnlockAccEmailBody(CaseWorkerEntity cw) throws IOException {
		StringBuffer sb=new StringBuffer("");
		
		FileReader fr=new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		BufferedReader br=new BufferedReader(fr);
		String line=br.readLine();
		
		while(line !=null){
			sb.append(line);
			line=br.readLine();
			}
		br.close();
		
		//formate mail body
		String mailBody=sb.toString();
		mailBody=mailBody.replace("{FNAME}",cw.getFname());
		mailBody=mailBody.replace("{LNAME}",cw.getLname());
		mailBody=mailBody.replace("{TEMP-PWD}",cw.getUserPwd());
		mailBody=mailBody.replace("{EMAIL}",cw.getEmail());
		
		
		 return mailBody;
	}

}
