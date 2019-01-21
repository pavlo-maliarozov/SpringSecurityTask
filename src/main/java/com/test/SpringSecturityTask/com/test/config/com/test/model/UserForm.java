package com.test.SpringSecturityTask.com.test.config.com.test.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Class for fields validation
 */
public class UserForm {

    @NotEmpty(message = "Cannot be empty!")
    @Size(min = 6, max = 10, message = "Username must be from 6 to 10 symbols")
    private String username;

    @NotEmpty(message = "Cannot be empty!")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "Password must contain only letters and numbers")
    private String password;

    public UserForm(@NotEmpty(message = "Cannot be empty!")
                    @Size(min = 6, max = 10, message = "Username must be from 6 to 10 symbols")
                            String username,
                    @NotEmpty(message = "Cannot be empty!")
                    @Size(min = 8, message = "Password must be at least 8 characters")
                    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password must contain only letters and numbers")
                            String password) {
        this.username = username;
        this.password = password;
    }

    public UserForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(username, userForm.username) &&
                Objects.equals(password, userForm.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
