package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		// retrive be4an from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		// check if they are the same
		
		boolean result = (theCoach == alphaCoach);
		
		System.out.println("Are they same: " + result);
		
		System.out.println("Memory location for the theCoach: " + theCoach);
		
		System.out.println("Memory location for the alphaCoach: " + alphaCoach);
	}

}
