package com.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logger {

    @AfterReturning(pointcut = "execution(public * com.model.Book.setCount(..))")
    public void logBookCountChange(JoinPoint joinPoint) {
        System.out.println("Book count changed");
    }

    @AfterReturning(pointcut = "within(com.controller.CardController)")
    public void logPersonBorrowBook(JoinPoint joinPoint) {
        System.out.println("New Person borrowed book / returned book / see borrow cards");
    }
}
