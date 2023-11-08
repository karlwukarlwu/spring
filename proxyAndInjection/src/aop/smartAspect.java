package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
@Aspect
@Component
public class smartAspect {
    @Before("execution(public void aop.Camera.work())")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature);
        System.out.println(signature.getName());
        System.out.println("before");
    }
    @AfterReturning("execution(public void aop.Phone.work())")
    public void afterReturning(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("afterReturning");
    }
}
