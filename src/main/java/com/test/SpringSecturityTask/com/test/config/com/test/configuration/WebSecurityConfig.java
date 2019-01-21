package com.test.SpringSecturityTask.com.test.config.com.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
            .antMatchers("/", "/home", "/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout().invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/home")
//            .logoutSuccessUrl("/login?logout")
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String userNameQuery = "" +
                " SELECT                 " +
                "  USER_NAME,            " +
                "  PASSWORD,             " +
                "  ACTIVE,               " +
                "  USER_ID               " +
                " FROM USERS.USER_FORM   " +
                " WHERE USER_NAME = ?    ";

        String authQuery = "" +
                " SELECT                         " +
                "  U.USER_NAME,                  " +
                "  UR.ROLES                      " +
                " FROM USERS.USER_FORM U         " +
                " INNER JOIN USERS.USER_ROLE UR  " +
                " ON U.USER_ID = UR.USER_ID      " +
                " WHERE U.USER_NAME = ?          ";

        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(getPasswordEncoder())
            .usersByUsernameQuery(userNameQuery)
            .authoritiesByUsernameQuery(authQuery);

    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
