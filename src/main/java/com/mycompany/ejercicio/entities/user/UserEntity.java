package com.mycompany.ejercicio.entities.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

}
