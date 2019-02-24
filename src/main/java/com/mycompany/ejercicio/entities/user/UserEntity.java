package com.mycompany.ejercicio.entities.user;

import com.mycompany.ejercicio.entities.role.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String login;

    @Column
    private String name;

    @Column
    private String email;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    },
            fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public void addRole(RoleEntity roleEntity) {
        roles.add(roleEntity);
        // roleEntity.getUsers().add(this);
    }

    public void removeRole(RoleEntity roleEntity) {
        roles.remove(roleEntity);
        roleEntity.getUsers().remove(this);
    }
}
