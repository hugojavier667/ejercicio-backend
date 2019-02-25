package com.mycompany.ejercicio;

import com.mycompany.ejercicio.cxf.UserExcepction;
import com.mycompany.ejercicio.cxf.datatypes.User;
import com.mycompany.ejercicio.entities.role.RoleEntity;
import com.mycompany.ejercicio.entities.role.RoleRepository;
import com.mycompany.ejercicio.entities.user.UserDAO;
import com.mycompany.ejercicio.entities.user.UserDAOImpl;
import com.mycompany.ejercicio.entities.user.UserEntity;
import com.mycompany.ejercicio.entities.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserDAOImplIntegrationTest {

    @TestConfiguration
    static class UserDAOImplIntegrationTestContextConfiguration {

        @Bean
        public UserDAO userDAO() {
            return new UserDAOImpl();
        }

        @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }
    }

    @Autowired
    private UserDAO userDAO;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("test");
        userEntity.setName("Test user");
        userEntity.setEmail("test@email.com");

        User user = new User();
        userEntity.setLogin("test");
        userEntity.setName("Test user");
        userEntity.setEmail("test@email.com");

        Mockito.when(userRepository.findByLogin(userEntity.getLogin()))
                .thenReturn(userEntity);
    }

    @Test
    public void whenValidLogin_thenUserShouldBeFound() throws UserExcepction {
        String login = "test";
        User found = userDAO.findByLogin(login);

        assertThat(found.getLogin())
                .isEqualTo(login);
    }
}
