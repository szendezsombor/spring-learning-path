package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			List<Student> studentList = session.createQuery("from Student").list();
			
			displayStudents(studentList);
			
			System.out.println("\n\n");
			
			studentList = session.createQuery("from Student s where s.lastName='Duck'").list();
			
			System.out.println("Students with duck last name.");
			
			displayStudents(studentList);
			
			System.out.println("\n\n");
			
			studentList = session.createQuery("from Student s where s.lastName='Wall' OR s.firstName='Daffy'").list();
			
			displayStudents(studentList);
			
			System.out.println("\n\n");
			
			studentList = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").list();
			
			displayStudents(studentList);
			
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		} catch (Exception e) {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> studentList) {
		for (Student tempStudent : studentList) {
			System.out.println(tempStudent);
		}
	}

}
