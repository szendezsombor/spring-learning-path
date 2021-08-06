package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);
		
		boolean isTheSameCoach = (theCoach == alphaCoach);

		System.out.println("Are they same? " + isTheSameCoach);
		
		System.out.println("Are they same? " + theCoach);
		
		System.out.println("Are they same? " + alphaCoach);
		
		context.close();

	}

}
