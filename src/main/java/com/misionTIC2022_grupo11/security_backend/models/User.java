package com.misionTIC2022_grupo11.security_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String nick_name;
    private String email;
    private String password;


    @ManyToOne
    @JoinColumn(name = "idRol") // Mentions to the program where the relationship exists
    @JsonIgnoreProperties("users")   // Explanation of the command in "User"
    private Rol rol;

    public Integer getId() {
        return idUser;
    }

    public void setId(Integer id) {
        this.idUser = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
/**
 * All getter and setter
 * @return
 */
