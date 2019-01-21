package com.test.SpringSecturityTask.com.test.config.com.test.service;

import com.test.SpringSecturityTask.com.test.config.com.test.repository.UserRepository;
import com.test.SpringSecturityTask.com.test.config.com.test.model.Role;
import com.test.SpringSecturityTask.com.test.config.com.test.model.User;
import com.test.SpringSecturityTask.com.test.config.com.test.model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserForm userForm) {

        User user = new User();

        user.setUsername(userForm.getUsername());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        repository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
