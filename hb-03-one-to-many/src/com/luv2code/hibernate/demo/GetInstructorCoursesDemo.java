package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();

			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);

			
			System.out.println("Instructor name: " + tempInstructor);
			
			System.out.println("Courses: " + tempInstructor.getCourses());
			
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		} catch (Exception e) {
			session.close();
			factory.close();
		}

	}

}
