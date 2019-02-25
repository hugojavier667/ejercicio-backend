package com.mycompany.ejercicio;

import com.mycompany.ejercicio.entities.user.UserEntity;
import com.mycompany.ejercicio.entities.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByLogin_thenReturnUser() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("test");
        userEntity.setName("Test user");
        userEntity.setEmail("test@email.com");

        entityManager.persist(userEntity);
        entityManager.flush();

        // when
        UserEntity found = userRepository.findByLogin(userEntity.getLogin());

        // then
        assertThat(found.getName())
                .isEqualTo(userEntity.getName());
    }
}
