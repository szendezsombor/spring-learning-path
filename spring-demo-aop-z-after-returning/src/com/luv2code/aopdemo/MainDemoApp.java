package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		Account account = new Account();
		account.setName("Pista");
		account.setLevel("10");
		
		accountDAO.setName("foobar");
		accountDAO.setServiceCode("silver");
		
		String nameString = accountDAO.getName();
		String serviceCodeString = accountDAO.getServiceCode();
		
		accountDAO.addAccount(account, true);
		accountDAO.doWork();
		
		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();
		
		context.close();

	}

}
