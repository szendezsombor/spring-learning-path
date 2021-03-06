package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Course: " + tempStudent.getCourses());
			
			Course course1 = new Course("Ribuk's Cube - How To Speed Cube");
			Course course2 = new Course("Atari 2600 - Game Development");
			
			//course1.addStudent(tempStudent);
			//course2.addStudent(tempStudent);
			tempStudent.addCourse(course2);
			tempStudent.addCourse(course1);
			
			System.out.println("\nSaving the courses...");
			
			session.save(course1);
			session.save(course2);
			
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		} catch (Exception e) {
			session.close();
			factory.close();
		}

	}

}
