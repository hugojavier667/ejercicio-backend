package com.mycompany.ejercicio;

import com.mycompany.ejercicio.endpoint.UserServiceRestEndpoint;
import com.mycompany.ejercicio.entities.user.UserDAOImpl;
import com.mycompany.ejercicio.entities.user.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceRestEndpoint.class)
public class UserServiceRestEndpointIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserDAOImpl userDAO;

    /*@Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray()
            throws Exception {

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("test");
        userEntity.setName("Test user");
        userEntity.setEmail("test@email.com");

        List<UserEntity> allUsers = Arrays.asList(userEntity);

        given(userDAO.findAll()).willReturn(allUsers);

        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
                .andExpect(jsonPath("$[0].name", is(userEntity.getName())));
    }*/
}
