package com.springboot.recipe.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springboot.recipe.model.User;
import com.springboot.recipe.repository.UserRepo;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUsername method.....");
        User user = userRepo.findByUsername(username).orElse(null);
        if (user == null) {
            logger.error("User not Found: " + username);
            throw new UsernameNotFoundException("Invalid Email");
        }
        logger.info("User Authenticated successfully...!!!!!");
        return new CustomUserDetails(user);
    }
}
