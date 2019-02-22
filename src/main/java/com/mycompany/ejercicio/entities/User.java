package com.mycompany.ejercicio.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String login;

    @Column
    private String name;

    @Column
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User (com.mycompany.ejercicio.cxf.User user){
        this.login = user.getLogin();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public User(){
        super();
    }
}
