package com.mycompany.ejercicio.entities.user;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.UserException;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.cxf.datatypes.User;
import com.mycompany.ejercicio.cxf.exception.GenericException;
import com.mycompany.ejercicio.entities.QuerysConfiguration;
import com.mycompany.ejercicio.entities.role.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    QuerysConfiguration querysConfiguration;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public User findByLogin(String login) throws UserException, DataAccessException {
        String sql = querysConfiguration.getQueryByKey("findUserByLogin");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, login);

            User user = buildUser((Object[]) query.getSingleResult());
            user.getRoles().addAll(roleDAO.findAllByUserLogin(user.getLogin()));

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("findAllUsers");
        List<User> results = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(sql);

            query.getResultList().stream().forEach(resultRow -> {
                User user = buildUser((Object[]) resultRow);

                try {
                    user.getRoles().addAll(roleDAO.findAllByUserLogin(user.getLogin()));
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }

                results.add(user);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }

        return results;
    }

    @Override
    @Modifying
    @Transactional
    public User save(User user) throws UserException, DataAccessException {
        String sql = querysConfiguration.getQueryByKey("insertUser");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, user.getLogin());
            query.setParameter(2, user.getName());
            query.setParameter(3, user.getEmail());

            query.executeUpdate();

            clearAllRolesToUserByLogin(user.getLogin());
            user.getRoles().stream().forEach(role -> {
                try {
                    addRoleToUserByLogin(user.getLogin(), role.getName());
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }

        return user;
    }

    @Override
    @Modifying
    @Transactional
    public int deleteByLogin(String login) throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("deleteUserByLogin");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, login);

            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }
    }

    @Override
    @Modifying
    @Transactional
    public User update(User user) throws UserException, DataAccessException {
        String sql = querysConfiguration.getQueryByKey("updateUserByLogin");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, user.getName());
            query.setParameter(2, user.getEmail());
            query.setParameter(3, user.getLogin());

            query.executeUpdate();

            clearAllRolesToUserByLogin(user.getLogin());
            user.getRoles().stream().forEach(role -> {
                try {
                    addRoleToUserByLogin(user.getLogin(), role.getName());
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }

        return user;
    }

    @Override
    @Modifying
    @Transactional
    public int clearAllRolesToUserByLogin(String login) throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("clearAllRolesToUserByLogin");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, login);

            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }
    }

    @Override
    @Modifying
    @Transactional
    public int addRoleToUserByLogin(String login, String roleName) throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("addRolToUserByLogin");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, login);
            query.setParameter(2, roleName);

            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }
    }

    private User buildUser(Object[] fields){
        User user = new User();
        user.setLogin((String) fields[0]);
        user.setName((String) fields[1]);
        user.setEmail((String) fields[2]);

        return user;
    }
}
