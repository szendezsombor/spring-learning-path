package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			int studentId = 1;
			
			
			System.out.println("Student id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Deleting student...");
			
			// session.delete(myStudent);
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("DONE!");
			
			
		} catch (Exception e) {
			factory.close();
		}

	}

}
