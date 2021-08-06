package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterAndSetter() {}
	
	// advices
	// @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	// @Before("execution(public void updateAccount())")
	// @Before("execution(public void add*())")
	@Order(1)
	@Before("forDaoPackageNoGetterAndSetter()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n=========>>> Executing @Before advice on addAccount()");
	}
	
	@Order(2)
	@Before("forDaoPackageNoGetterAndSetter()")
	public void performApiAnalitycs() {
		System.out.println("=========>>> Performing API analytics");
	}
}
