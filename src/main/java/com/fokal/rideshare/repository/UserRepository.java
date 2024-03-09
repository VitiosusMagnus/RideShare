package com.fokal.rideshare.repository;

import com.fokal.rideshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
