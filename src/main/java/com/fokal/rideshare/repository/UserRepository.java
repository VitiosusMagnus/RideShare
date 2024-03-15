package com.fokal.rideshare.repository;

import com.fokal.rideshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdAndIsActiveTrue(Long id);
    List<User> findAllByIsActiveTrue();

}
