package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			/*
			Instructor instructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");
			*/
			
			Instructor instructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Guiter");
			
			instructor.setInstructorDetail(instructorDetail);
			
			session.beginTransaction();

			// CascadeType.ALL -> instructorDetailt is elmenti 
			System.out.println("Saveing...");
			session.save(instructor);
			
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		} catch (Exception e) {
			factory.close();
		}

	}

}
