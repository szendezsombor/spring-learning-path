package com.luv2code.aopdemo.aspect;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		MethodSignature methodSig = (MethodSignature) theProceedingJoinPoint.getSignature();
		myLogger.info("\n=========>>> Executing @Around advice on method" + methodSig);
		
		long start = System.currentTimeMillis();
		
		Object result = theProceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - start;
		
		myLogger.info("\n=========>>> Duration: " + duration / 1000.0 + " sec");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFinfAccountsAdvice(JoinPoint theJoinPoint) {
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("\n=========>>> Executing @After advice on method" + methodSig);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFinfAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("\n=========>>> Executing @AfterThrowing advice on method" + methodSig);
		
		myLogger.info("\n=========>>> Exeception is" + theExc);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("\n=========>>> Executing @AfterReturning advice on method" + methodSig);
		
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n=========>>> Result is: " + result);

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
		myLogger.info("\n=========>>> Executing @Before advice on addAccount()");
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Mehod: " + methodSig);
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object arg: args) {
			myLogger.info(arg.toString());
			
			if (arg instanceof Account) {
				Account theAccount = (Account) arg;
				
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
		}
		
	}
}
