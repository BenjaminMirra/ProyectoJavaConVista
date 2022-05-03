package com.integral.integral.service;

import com.integral.integral.entities.AppUser;
import com.integral.integral.entities.AppUsuarioRoles;
import com.integral.integral.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String pass= passwordEncoder.encode("password");
        String pass2 = passwordEncoder.encode("password");

        userRepository.save(new AppUser("User","User","user@gmail.com",pass, AppUsuarioRoles.ROLE_USER));
        userRepository.save(new AppUser("Benjamin","Mirra","benja@gmail.com",pass2, AppUsuarioRoles.ROLE_ADMIN));
    }
}
