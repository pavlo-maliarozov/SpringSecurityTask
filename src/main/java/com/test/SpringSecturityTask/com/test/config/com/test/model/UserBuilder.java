package com.test.SpringSecturityTask.com.test.config.com.test.model;

import java.util.Set;

/**
 * Builder class for {@link User} class
 */
public class UserBuilder {
    private int id;
    private String username;
    private String password;
    private boolean active;
    private Set<Role> roles;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setActive(boolean active) {
        this.active = active;
        return this;
    }

    public UserBuilder setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User createUserForm() {
        return new User(id, username, password, active, roles);
    }
}