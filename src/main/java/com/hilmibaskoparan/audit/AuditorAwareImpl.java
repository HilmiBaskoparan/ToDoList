package com.hilmibaskoparan.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// @Component: makes the object it marked become an object of Spring
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // get login user information with database
        // You should get the username in the system with Session
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthenticated()){
            System.out.println(authentication.getName());
            System.out.println(authentication.getPrincipal());
            return Optional.of(authentication.getName());
        }
        // If there is someone in the system, add that person's name, otherwise return null
        // return Optional.ofNullable(authentication!=null?authentication.getName():null);
        return Optional.of("HilmiB.");
    }
}
