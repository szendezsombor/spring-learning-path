package com.luv2code.aopdemo.aspect;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFinfAccountsAdvice(JoinPoint theJoinPoint) {
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("\n=========>>> Executing @After advice on method" + methodSig);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFinfAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("\n=========>>> Executing @AfterThrowing advice on method" + methodSig);
		
		System.out.println("\n=========>>> Exeception is" + theExc);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("\n=========>>> Executing @AfterReturning advice on method" + methodSig);
		
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n=========>>> Result is: " + result);

	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		for(Account account: result) account.setName(account.getName().toUpperCase());
	}

	// advices
	// @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	// @Before("execution(public void updateAccount())")
	// @Before("execution(public void add*())")
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterAndSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n=========>>> Executing @Before advice on addAccount()");
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Mehod: " + methodSig);
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object arg: args) {
			System.out.println(arg);
			
			if (arg instanceof Account) {
				Account theAccount = (Account) arg;
				
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
			}
		}
		
	}
}
