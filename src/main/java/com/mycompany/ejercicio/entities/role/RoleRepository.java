/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejercicio.entities.role;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    RoleEntity findByName(String name);

    @Transactional
    @Modifying
    int deleteByName(String name);

    List<RoleEntity> findAllByNameIn(List<String> rolesNames);
}
