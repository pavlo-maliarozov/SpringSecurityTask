package com.test.SpringSecturityTask.com.test.config.com.test.service;

import com.test.SpringSecturityTask.com.test.config.com.test.model.User;
import com.test.SpringSecturityTask.com.test.config.com.test.model.UserForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(UserForm user);
    User findByUsername(String userName);
}
