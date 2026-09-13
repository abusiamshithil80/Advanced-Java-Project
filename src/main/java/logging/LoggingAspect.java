package logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger("LoggingAspect");

    @Before("execution(* getAll())")
    public void log() {
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            username = "anonymous";
        } else {
            username = authentication.getName();
        }

        logger.info(username + " is accessing this method.");
    }
}
