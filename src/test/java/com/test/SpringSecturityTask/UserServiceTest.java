package com.test.SpringSecturityTask;

import com.test.SpringSecturityTask.com.test.config.com.test.repository.UserRepository;
import com.test.SpringSecturityTask.com.test.config.com.test.model.Role;
import com.test.SpringSecturityTask.com.test.config.com.test.model.User;
import com.test.SpringSecturityTask.com.test.config.com.test.model.UserBuilder;
import com.test.SpringSecturityTask.com.test.config.com.test.service.UserServiceImpl;
import com.test.SpringSecturityTask.com.test.config.com.test.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Test class for {@link UserService}
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private BCryptPasswordEncoder passwordEncoderMock;

    private UserServiceImpl userServiceTest;

    private User userTest;

    @Before
    public void setUp() {
        initMocks(this);

        userServiceTest = new UserServiceImpl(userRepositoryMock, passwordEncoderMock);

        userTest = new UserBuilder()
                .setId(1)
                .setUsername("testUser")
                .setPassword("TestPassword1111")
                .setActive(true)
                .setRoles(Collections.singleton(Role.USER))
                .createUserForm();

        Mockito
                .when(userRepositoryMock.save(any()))
                .thenReturn(userTest);

        Mockito
                .when(userRepositoryMock.findByUsername(anyString()))
                .thenReturn(userTest);

    }

    @Test
    public void testFindUserByUsername() {

        String expectedUsername = "testUser";

        User user = userServiceTest.findByUsername(expectedUsername);

        String actualUsername = user.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }
}
