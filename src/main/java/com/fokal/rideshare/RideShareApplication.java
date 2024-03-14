package com.fokal.rideshare;

import com.fokal.rideshare.model.Role;
import com.fokal.rideshare.model.User;
import com.fokal.rideshare.repository.RoleRepository;
import com.fokal.rideshare.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@AllArgsConstructor
@SpringBootApplication
public class RideShareApplication implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(RideShareApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user = new User();
        user.setEmail("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        userRepository.save(user);
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        role.setUser(user);
        roleRepository.save(role);
        role.setName("ROLE_USER");
        roleRepository.save(role);
    }
}
