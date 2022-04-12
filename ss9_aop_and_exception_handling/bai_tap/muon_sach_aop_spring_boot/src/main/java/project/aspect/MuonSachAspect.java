package project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MuonSachAspect {

    private static Long count = 0L;

    @AfterReturning(pointcut =
            "execution(public * project.controllers.MuonSachController.create(..)) || " +
                    "execution(public * project.controllers.MuonSachController.update(..)) ||" +
                    "execution(public * project.controllers.MuonSachController.delete(..)) ||" +
                    "execution(public * project.controllers.MuonSachController.remove(..))")
    public void logChange(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        count++;
        System.err.println("Method name: " + methodName);
        System.err.println("Args: " + args);
        System.err.println("Number of actions: " + count);
    }

    @AfterReturning(pointcut = "within(project.controllers.*)")
    public void logAllChange(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        count++;
        System.err.println("Method name: " + methodName);
        System.err.println("Args: " + args);
        System.err.println("Number of actions: " + count);
    }
}
