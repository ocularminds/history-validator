package com.leadway.ps.repository;

import com.leadway.ps.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User>findByLogin(String login);
}
