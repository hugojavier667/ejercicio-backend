package com.mycompany.ejercicio.entities.role;

import com.mycompany.ejercicio.cxf.DataAccessException;
import com.mycompany.ejercicio.cxf.RoleExcepction;
import com.mycompany.ejercicio.cxf.datatypes.Role;
import com.mycompany.ejercicio.cxf.exception.GenericException;
import com.mycompany.ejercicio.entities.QuerysConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"db","default"})
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    QuerysConfiguration querysConfiguration;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role findByName(String name) throws RoleExcepction, DataAccessException {
        String sql = querysConfiguration.getQueryByKey("findRoleByName");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, name);

            Role role = buildRole((Object[]) query.getSingleResult());

            return role;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }
    }

    @Override
    public List<Role> findAll() throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("findAllRoles");
        List<Role> results = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(sql);

            query.getResultList().stream().forEach(resultRow -> {
                Role role = buildRole((Object[]) resultRow);
                results.add(role);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }

        return results;
    }

    @Override
    public List<Role> findAllByUserLogin(String login) throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("findAllRolesByUserLogin");
        List<Role> results = new ArrayList<>();
        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, login);

            query.getResultList().stream().forEach(resultRow -> {
                Role role = buildRole((Object[]) resultRow);
                results.add(role);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database for user roles", new GenericException());
        }

        return results;
    }

    @Override
    @Modifying
    @Transactional
    public Role save(Role role) throws RoleExcepction, DataAccessException {
        String sql = querysConfiguration.getQueryByKey("insertRole");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, role.getName());
            query.setParameter(2, role.getDescription());

            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }

        return role;
    }

    @Override
    @Modifying
    @Transactional
    public int deleteByName(String name) throws DataAccessException {
        String sql = querysConfiguration.getQueryByKey("deleteRoleByName");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, name);

            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }
    }

    @Override
    @Modifying
    @Transactional
    public Role update(Role role) throws RoleExcepction, DataAccessException {
        String sql = querysConfiguration.getQueryByKey("updateRoleByName");

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, role.getDescription());
            query.setParameter(2, role.getName());

            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error querying the database", new GenericException());
        }

        return role;
    }

    private Role buildRole(Object[] fields){
        Role role = new Role();
        role.setName((String) fields[0]);
        role.setDescription((String) fields[1]);

        return role;
    }
}
