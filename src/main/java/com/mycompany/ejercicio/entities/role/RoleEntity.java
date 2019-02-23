package com.mycompany.ejercicio.entities.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;
}
