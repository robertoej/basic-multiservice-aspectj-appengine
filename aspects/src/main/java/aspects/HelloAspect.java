package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
public class HelloAspect {
    private static final Logger LOGGER = Logger.getLogger(HelloAspect.class.getName());

    @Pointcut("execution(* jersey.*.*(..))")
    public void helloMethods() { }

    @Before("helloMethods()")
    public void beforeHelloMethods(JoinPoint joinPoint) {
        LOGGER.log(Level.INFO, joinPoint.getSignature() + " was called.");
    }
}
