package com.test.SpringSecturityTask.com.test.config.com.test.repository;

import com.test.SpringSecturityTask.com.test.config.com.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link User}
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
